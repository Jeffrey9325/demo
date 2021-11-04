package com.example.pe.demo.business;

import com.example.pe.demo.model.dto.TipoCambioDto;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

public interface DemoService {
    Observable<TipoCambioDto> getAll();
    Completable saveTipoCambio(TipoCambioDto demoDto);
    Completable updateTipoCambio(TipoCambioDto demoDto);
    Single<TipoCambioDto> postExchange(TipoCambioDto demoDto);
}
