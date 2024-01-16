package com.findar.findar.models;

import lombok.*;

@Data
@AllArgsConstructor
public class JwtAuthenticationResponse {

    private String accessToken;
    private final  String tokenType = "Bearer";

}
