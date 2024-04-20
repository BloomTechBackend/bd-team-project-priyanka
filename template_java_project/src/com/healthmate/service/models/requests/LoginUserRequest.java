package com.healthmate.service.models.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginUserRequest {
    private String email;
    private String password;
    private LoginUserRequest(Builder builder) {
        this.email = builder.email;
        this.password = builder.password;
    }
    public static Builder builder() { return new Builder(); }
    public static class Builder {
        private String email;
        private String password;
        public Builder() {}
        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }
        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }
        public LoginUserRequest build() {
            return new LoginUserRequest(this);
        }
    }
}
