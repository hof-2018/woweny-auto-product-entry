package com.hof.wovenyautoproductentry.configuration.properties;

import org.springframework.context.annotation.Configuration;

@Configuration
public class BaseAuthenticationProperties {
    private String username;
    private String password;

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
