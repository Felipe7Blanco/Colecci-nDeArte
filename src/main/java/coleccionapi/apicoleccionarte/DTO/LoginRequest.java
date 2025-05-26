package coleccionapi.apicoleccionarte.DTO;

import jakarta.validation.constraints.NotNull;

public class LoginRequest {
        @NotNull
        private String username;
        @NotNull
        private String password;

        // Getters y Setters
        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

