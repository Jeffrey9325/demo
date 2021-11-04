package com.example.pe.demo.controller;

import com.example.pe.demo.business.DemoService;
import com.example.pe.demo.model.api.DemoRequest;
import com.example.pe.demo.model.api.DemoResponse;
import com.example.pe.demo.model.api.TipoCambioRequest;
import com.example.pe.demo.model.api.TipoCambioResponse;
import com.example.pe.demo.model.dto.TipoCambioDto;
import com.example.pe.demo.util.validation.SaveDemoValidationGroup;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController

@AllArgsConstructor
@RequestMapping("/demo/v1")
public class DemoController {

    private DemoService demoService;

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public Observable<TipoCambioResponse> getAll() {
        return demoService.getAll()
                .map(this::buildTipoCambioResponse);
    }

    @Validated(value = SaveDemoValidationGroup.class)
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    public Completable saveDemo(@Valid @RequestBody TipoCambioRequest request) {
        return demoService.saveTipoCambio(buildTipoCambioDto(request));
    }

    @PatchMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Completable updateDemo(@PathVariable("id") String id,
                                  @Valid @RequestBody TipoCambioRequest request) {
        return demoService.updateTipoCambio(buildTipoCambioDto(id, request));
    }

    @PostMapping(value = "/exchange", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Single<DemoResponse> postExchange(@Valid @RequestBody DemoRequest request) {
        return demoService.postExchange(buildDemoDto(request))
                .map(this::buildDemoResponse);
    }

    private TipoCambioResponse buildTipoCambioResponse(TipoCambioDto tipoCambioDto) {
        return TipoCambioResponse.builder()
                .id(tipoCambioDto.getId())
                .monedaOrigen(tipoCambioDto.getMonedaOrigen())
                .monedaDestino(tipoCambioDto.getMonedaDestino())
                .tipoCambio(tipoCambioDto.getTipoCambio())
                .date(tipoCambioDto.getDate())
                .build();
    }

    private TipoCambioDto buildTipoCambioDto(TipoCambioRequest request) {
        return TipoCambioDto.builder()
                .monedaOrigen(request.getMonedaOrigen())
                .monedaDestino(request.getMonedaDestino())
                .tipoCambio(request.getTipoCambio())
                .date(request.getDate())
                .build();
    }

    private TipoCambioDto buildTipoCambioDto(String id, TipoCambioRequest request) {
        return TipoCambioDto.builder()
                .id(id)
                .tipoCambio(request.getTipoCambio())
                .build();
    }

    private TipoCambioDto buildDemoDto(DemoRequest demo) {
        return TipoCambioDto.builder()
                .monto(demo.getMonto())
                .monedaOrigen(demo.getMonedaOrigen())
                .monedaDestino(demo.getMonedaDestino())
                .build();
    }

    private DemoResponse buildDemoResponse(TipoCambioDto tipoCambioDto) {
        return DemoResponse.builder()
                .monto(tipoCambioDto.getMonto())
                .montoTipoCambio(tipoCambioDto.getMontoTipoCambio())
                .monedaOrigen(tipoCambioDto.getMonedaOrigen())
                .monedaDestino(tipoCambioDto.getMonedaDestino())
                .tipoCambio(tipoCambioDto.getTipoCambio())
                .build();
    }
}
