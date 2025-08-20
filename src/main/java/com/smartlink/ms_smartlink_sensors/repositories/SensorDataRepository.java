// Archivo: src/main/java/com/smartlink/ms_smartlink_sensors/repositories/SensorDataRepository.java
package com.smartlink.ms_smartlink_sensors.repositories;

import com.smartlink.ms_smartlink_sensors.domain.dtos.GraphicPointDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SensorDataRepository {
    private static final Logger log = LoggerFactory.getLogger(SensorDataRepository.class);
    private final DataSource dataSource;

    public SensorDataRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<GraphicPointDto> findDataForGraphic(Integer sensorId, Integer timeOptionMinutes) {
        List<GraphicPointDto> response = new ArrayList<>();
        String sql = "SELECT * FROM obtener_datos_grafica_final(?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, sensorId);
            stmt.setInt(2, timeOptionMinutes);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                response.add(new GraphicPointDto(
                        rs.getTimestamp("fecha").toLocalDateTime(),
                        rs.getDouble("valor")));
            }
        } catch (SQLException e) {
            log.error("Error al ejecutar la consulta para el sensor {}: {}", sensorId, e.getMessage());
            throw new RuntimeException("Error en la base de datos", e);
        }
        return response;
    }
}