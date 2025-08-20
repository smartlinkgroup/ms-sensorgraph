package com.smartlink.ms_smartlink_sensors.domain.dtos;

import java.time.LocalDateTime;

public record GraphicPointDto(
        LocalDateTime fecha,
        Double valor
) {}