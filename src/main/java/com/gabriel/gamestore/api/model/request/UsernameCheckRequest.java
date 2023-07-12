package com.gabriel.gamestore.api.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UsernameCheckRequest {

    @NotBlank
    private String username;
}
