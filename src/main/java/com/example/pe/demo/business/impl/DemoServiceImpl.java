package com.example.pe.demo.business.impl;

import com.example.pe.demo.business.DemoService;
import com.example.pe.demo.model.document.TipoCambio;
import com.example.pe.demo.model.dto.TipoCambioDto;
import com.example.pe.demo.repository.DemoRepository;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DemoServiceImpl implements DemoService {

    private DemoRepository repository;

    @Override
    public Observable<TipoCambioDto> getAll() {
        return repository.findAll()
                .map(this::buildTipoCambioDto)
                .toObservable()
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Completable saveTipoCambio(TipoCambioDto demoDto) {
        return repository.save(buildTipoCambio(demoDto))
                .ignoreElement()
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Completable updateTipoCambio(TipoCambioDto tipoCambioDto) {
        TipoCambio tipoCambio = findTipoCambioById(tipoCambioDto.getId());
        return repository.save(buildTipoCambio(tipoCambio, tipoCambioDto.getTipoCambio()))
                .ignoreElement()
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Single<TipoCambioDto> postExchange(TipoCambioDto tipoCambioDto) {
        return repository.findByMonedaOrigenAndMonedaDestino(tipoCambioDto.getMonedaOrigen(),
                        tipoCambioDto.getMonedaDestino())
                .map(tipoCambio ->  buildTipoCambioDto(tipoCambio, tipoCambioDto))
                .subscribeOn(Schedulers.io());
    }

    private TipoCambioDto buildTipoCambioDto(TipoCambio tipoCambio) {
        return TipoCambioDto.builder()
                .id(tipoCambio.getId())
                .monedaOrigen(tipoCambio.getMonedaOrigen())
                .monedaDestino(tipoCambio.getMonedaDestino())
                .tipoCambio(tipoCambio.getTipoCambio())
                .date(tipoCambio.getDate())
                .build();
    }

    private TipoCambio buildTipoCambio(TipoCambioDto tipoCambioDto) {
        return TipoCambio.builder()
                .monedaOrigen(tipoCambioDto.getMonedaOrigen())
                .monedaDestino(tipoCambioDto.getMonedaDestino())
                .tipoCambio(tipoCambioDto.getTipoCambio())
                .date(dateValidation(tipoCambioDto.getDate()))
                .build();
    }

    @SneakyThrows
    private LocalDate dateValidation(LocalDate date) {
        if (findByDate(date)) {
            System.out.println("no se puede crear tipo de cambio");
            throw new Exception();
        }
        return date;
    }

    private boolean findByDate(LocalDate date) {
        return repository.existsByDate(date).blockingGet();
    }

    private TipoCambio findTipoCambioById(String id) {
        return repository.findById(id).blockingGet();
    }

    private TipoCambio buildTipoCambio(TipoCambio tipoCambioDocument, BigDecimal tipoCambio) {
        return TipoCambio.builder()
                .id(tipoCambioDocument.getId())
                .monedaOrigen(tipoCambioDocument.getMonedaOrigen())
                .monedaDestino(tipoCambioDocument.getMonedaDestino())
                .tipoCambio(tipoCambio)
                .build();
    }

    private TipoCambioDto buildTipoCambioDto(TipoCambio tipoCambio, TipoCambioDto tipoCambioDto) {
        return TipoCambioDto.builder()
                .monto(tipoCambioDto.getMonto())
                .montoTipoCambio(postExchange(tipoCambioDto,
                        tipoCambio.getTipoCambio()))
                .monedaOrigen(tipoCambio.getMonedaOrigen())
                .monedaDestino(tipoCambio.getMonedaDestino())
                .tipoCambio(tipoCambio.getTipoCambio())
                .build();
    }

    private BigDecimal postExchange(TipoCambioDto tipoCambioDto, BigDecimal tipoCambio) {
        BigDecimal monto = new BigDecimal(0);
        switch (tipoCambioDto.getMonedaOrigen()) {
            case "soles":
                switch (tipoCambioDto.getMonedaDestino()) {
                    case "dolares":
                        monto = tipoCambioDto.getMonto()
                                .divide(tipoCambio, 3, RoundingMode.CEILING);
                        break;
                    case "euros":
                        monto = tipoCambioDto.getMonto()
                                .divide(tipoCambio, 3, RoundingMode.CEILING);
                        break;
                }
                break;
            case "dolares":
                switch (tipoCambioDto.getMonedaDestino()) {
                    case "soles":
                        monto = tipoCambioDto.getMonto().multiply(tipoCambio);
                        break;
                }
            case "euros":
                switch (tipoCambioDto.getMonedaDestino()) {
                    case "soles":
                        monto = tipoCambioDto.getMonto().multiply(tipoCambio);
                        break;
                }
        }
        return monto;
    }
}
