package com.example.pe.demo.model.api;

import com.example.pe.demo.util.validation.SaveDemoValidationGroup;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Builder
public class TipoCambioRequest {
    @NotEmpty(groups = SaveDemoValidationGroup.class)
    private String monedaOrigen;
    @NotEmpty(groups = SaveDemoValidationGroup.class)
    private String monedaDestino;
    @NotNull
    private BigDecimal tipoCambio;

    private LocalDate date;
}
