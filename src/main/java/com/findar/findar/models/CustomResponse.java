package com.findar.findar.models;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomResponse {
    private boolean status;
    private String message;
}
