package com.example.pe.demo.model.api;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
public class TipoCambioResponse {
    private String id;
    private String monedaOrigen;
    private String monedaDestino;
    private BigDecimal tipoCambio;
    private LocalDate date;
}
