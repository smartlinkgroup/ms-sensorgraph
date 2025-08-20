package com.smartlink.ms_smartlink_sensors.controllers;

import com.smartlink.ms_smartlink_sensors.domain.dtos.GraphicPointDto;
import com.smartlink.ms_smartlink_sensors.services.SensorGraphicService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController

@RequestMapping("/api/v1/sensors")
@CrossOrigin(origins = "http://localhost:4200")
public class SensorGraphicController {
    private final SensorGraphicService service;

    public SensorGraphicController(SensorGraphicService service) {
        this.service = service;
    }

    @GetMapping("/{sensorId}/graphics")

    public Flux<GraphicPointDto> getSensorGraphic(
            @PathVariable Integer sensorId,
            @RequestParam(defaultValue = "15") Integer minutes) {
        return service.getSensorData(sensorId, minutes);
    }
}