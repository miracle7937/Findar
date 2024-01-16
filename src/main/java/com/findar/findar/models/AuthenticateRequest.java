package com.findar.findar.models;

import lombok.Data;

@Data
public class AuthenticateRequest {
    private  String userName;
    private String password;
}
