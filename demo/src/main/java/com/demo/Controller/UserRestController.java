package com.demo.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {
    @GetMapping("/")
    public String home() {
        return "Acceso Concedido. Autoridades Permitidas: USER, ADMIN, CREATOR, EDITOR.";
    };

    @GetMapping("/new")
    public String create() {
        return "Acceso Concedido. Autoridades Permitidad: ADMIN, CREATOR";
    };

    @GetMapping("/edit")
    public String edit() {
        return "Acceso Concedido. Autoridades Permitidad: ADMIN, EDITOR";
    }

    @GetMapping("/delete")
    public String delete() {
        return "Acceso Concedido. Autoridad Permitidad: ADMIN";
    };

    @GetMapping("/403")
    public String accessDenied() {
        return "Acceso Denegado Por Spring Security. No Autorizado";
    }
}
