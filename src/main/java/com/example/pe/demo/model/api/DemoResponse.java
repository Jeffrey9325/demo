package com.example.pe.demo.model.api;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DemoResponse {
    private BigDecimal monto;
    private BigDecimal montoTipoCambio;
    private String monedaOrigen;
    private String monedaDestino;
    private BigDecimal tipoCambio;
}
