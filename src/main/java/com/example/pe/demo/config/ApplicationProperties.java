package com.example.pe.demo.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.math.BigDecimal;
import java.util.Map;

@Getter
@Setter
@ConfigurationProperties
public class ApplicationProperties {

}
