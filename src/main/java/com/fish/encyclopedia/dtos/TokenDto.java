package com.fish.encyclopedia.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class TokenDto {
    @JsonProperty("token")
    private String token = null;

}
