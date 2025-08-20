package com.smartlink.ms_smartlink_sensors.services;

import com.smartlink.ms_smartlink_sensors.domain.dtos.GraphicPointDto;
import com.smartlink.ms_smartlink_sensors.repositories.SensorDataRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
public class SensorGraphicService {
    private final SensorDataRepository repository;

    public SensorGraphicService(SensorDataRepository repository) {
        this.repository = repository;
    }

    public Flux<GraphicPointDto> getSensorData(Integer sensorId, Integer timeOptionMinutes) {
        // Envolvemos la llamada bloqueante del repositorio en un entorno reactivo
        return Mono.fromCallable(() -> repository.findDataForGraphic(sensorId, timeOptionMinutes))
                .flatMapMany(Flux::fromIterable)
                .subscribeOn(Schedulers.boundedElastic());
    }
}