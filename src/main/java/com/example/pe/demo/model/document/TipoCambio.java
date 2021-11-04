package com.example.pe.demo.model.document;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Builder
@Document
public class TipoCambio {
    @Id
    private String id;
    private String monedaOrigen;
    private String monedaDestino;
    private BigDecimal tipoCambio;
    private LocalDate date;
}
