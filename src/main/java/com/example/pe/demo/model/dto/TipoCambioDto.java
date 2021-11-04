package com.example.pe.demo.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Builder
public class TipoCambioDto {
    private String id;
    private BigDecimal monto;
    private String monedaOrigen;
    private String monedaDestino;
    private BigDecimal montoTipoCambio;
    private BigDecimal tipoCambio;
    private LocalDate date;
}
