package com.demo.Controller;

import com.demo.Model.RoleEntity;
import com.demo.Model.UserEntity;
import com.demo.Repository.RoleRepository;
import com.demo.Repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashSet;
import java.util.Set;

@Controller
public class WebController {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public WebController(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("usuarios", userRepository.findAll());
        return "home";
    }

    @GetMapping("/new")
    public String newUserForm(Model model) {
        model.addAttribute("roles", roleRepository.findAll());
        return "new";
    }

    @PostMapping("/new")
    public String createUser(@RequestParam("username") String username,
                             @RequestParam("password") String password,
                             @RequestParam(name = "roles", required = false) Set<Integer> roleIds) {

        UserEntity user = new UserEntity();
        user.setUserName(username);
        user.setUserPassword(passwordEncoder.encode(password));
        user.setUserEnable(true);

        if (roleIds != null && !roleIds.isEmpty()) {
            Set<RoleEntity> roles = new HashSet<>(roleRepository.findAllById(roleIds));
            user.getRoles().addAll(roles);
        }

        userRepository.save(user);
        return "redirect:/?created=true";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Integer id, Model model) {
        UserEntity usuario = userRepository.findByIdWithRoles(id)
                .orElseThrow(() -> new IllegalArgumentException("Id No Encontrado: " + id));
        model.addAttribute("usuario", usuario);
        return "edit";
    }

    @PostMapping("/edit/{id}")
    public String updateUser(@PathVariable Integer id,
                             @RequestParam(name = "enabled", required = false) Boolean enabled) {
        UserEntity usuario = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Id No Encontrado: " + id));

        usuario.setUserEnable(enabled != null);
        userRepository.save(usuario);
        return "redirect:/?updated=true";
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable Integer id) {
        userRepository.deleteById(id);
        return "redirect:/?deleted=true";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/403")
    public String accessDenied() {
        return "403";
    }
}