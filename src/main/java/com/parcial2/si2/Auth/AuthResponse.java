package com.parcial2.si2.Auth;

import java.util.Objects;

public class AuthResponse {
    private String token;

    // Constructor sin argumentos
    public AuthResponse() {
    }

    // Constructor con todos los argumentos
    public AuthResponse(String token) {
        this.token = token;
    }

    // Getter para token
    public String getToken() {
        return token;
    }

    // Setter para token
    public void setToken(String token) {
        this.token = token;
    }

    // Método toString
    @Override
    public String toString() {
        return "AuthResponse{" +
                "token='" + token + '\'' +
                '}';
    }

    // Método equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AuthResponse that = (AuthResponse) o;

        return Objects.equals(token, that.token);
    }

    // Método hashCode
    @Override
    public int hashCode() {
        return token != null ? token.hashCode() : 0;
    }

    // Builder estático
    public static class Builder {
        private String token;

        public Builder() {
        }

        public Builder token(String token) {
            this.token = token;
            return this;
        }

        public AuthResponse build() {
            return new AuthResponse(token);
        }
    }

    // Método estático para obtener el builder
    public static Builder builder() {
        return new Builder();
    }
}