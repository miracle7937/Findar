package com.findar.findar.models;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RegistrationForm {
    private String username;
    private String password;
    private String confirmPassword;
}
