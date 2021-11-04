package com.example.pe.demo.model.api;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class DemoRequest {

    @NotNull
    private BigDecimal monto;

    @NotEmpty
    private String monedaOrigen;

    @NotEmpty
    private String monedaDestino;

}
