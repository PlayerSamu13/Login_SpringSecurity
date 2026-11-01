package com.demo.Model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    @Column(name = "username", unique = true, nullable = false, length = 45)
    private String userName;

    @Column(name = "password", nullable = false, length = 64)
    private String userPassword;

    @Column(name = "enabled", nullable = false)
    private boolean userEnable = true;

    public boolean isUserEnable() {
        return userEnable;
    }

    public void setUserEnable(boolean nuevoUserEnable) {
        this.userEnable = nuevoUserEnable;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<RoleEntity> roles = new HashSet<RoleEntity>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer nuevoId) {
        this.id = nuevoId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String nuevoUserName) {
        this.userName = nuevoUserName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String nuevaUserPassword) {
        this.userPassword = nuevaUserPassword;
    }

    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleEntity> nuevosRoles) {
        this.roles.clear();
        if(nuevosRoles != null) {
            this.roles.addAll(new HashSet<>(nuevosRoles));
        }
    }
}
