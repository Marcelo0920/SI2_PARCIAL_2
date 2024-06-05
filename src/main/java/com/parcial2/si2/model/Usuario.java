package com.parcial2.si2.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String name;
    private int age;
    private String email;
    private double effectiveHours;
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String userCode;
    private String password;

    public Usuario() {
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private String name;
        private int age;
        private String email;
        private double effectiveHours;
        private String phoneNumber;
        private Role role;
        private String userCode;
        private String password;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder effectiveHours(double effectiveHours) {
            this.effectiveHours = effectiveHours;
            return this;
        }

        public Builder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder role(Role role) {
            this.role = role;
            return this;
        }

        public Builder userCode(String userCode) {
            this.userCode = userCode;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Usuario build() {
            if (this.role == null) {
                throw new IllegalStateException("Role cannot be null");
            }
            Usuario usuario = new Usuario();
            usuario.id = this.id;
            usuario.name = this.name;
            usuario.age = this.age;
            usuario.email = this.email;
            usuario.effectiveHours = this.effectiveHours;
            usuario.phoneNumber = this.phoneNumber;
            usuario.role = this.role;
            usuario.userCode = this.userCode;
            usuario.password = this.password;
            return usuario;
        }
    }

    // Getters and Setters...

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public double getEffectiveHours() {
        return effectiveHours;
    }

    public void setEffectiveHours(double effectiveHours) {
        this.effectiveHours = effectiveHours;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (role == null) {
            throw new IllegalStateException("Role is not set for this user");
        }
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
