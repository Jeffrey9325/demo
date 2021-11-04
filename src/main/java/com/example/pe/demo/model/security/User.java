package com.example.pe.demo.model.security;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class User {
    private String user;
    private String pwd;
    private String token;
}
