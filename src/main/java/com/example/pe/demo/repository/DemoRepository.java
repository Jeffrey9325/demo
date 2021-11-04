package com.example.pe.demo.repository;

import com.example.pe.demo.model.document.TipoCambio;
import io.reactivex.Single;
import org.springframework.data.repository.reactive.RxJava2CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;

@Repository
public interface DemoRepository extends RxJava2CrudRepository<TipoCambio, String> {

    Single<TipoCambio> findByMonedaOrigenAndMonedaDestino(String monedaOrigen,
                                                          String monedaDestino);

    Single<Boolean> existsByDate(LocalDate date);
}
