
package com.smartlink.ms_smartlink_sensors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

@SpringBootApplication(exclude = {
		org.springframework.boot.autoconfigure.security.reactive.ReactiveUserDetailsServiceAutoConfiguration.class,
		org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration.class
})
@Import({
		energy.lift.lib.authentication.utils.config.PostgresqlConfiguration.class,
		energy.lift.lib.authentication.utils.config.SecurityUtilConfig.class,
		energy.lift.lib.authentication.utils.filters.JwtAuthenticationFilterUtil.class,
})
@EnableConfigurationProperties({
		energy.lift.lib.authentication.utils.properties.PostgresProperties.class,
		energy.lift.lib.authentication.utils.properties.CommunicationServiceProperties.class
})
public class MsSmartlinkSensorsApplication {
	public static void main(String[] args) {
		SpringApplication.run(MsSmartlinkSensorsApplication.class, args);
	}
}