package org.sreejit.timeseriessimple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class TimeSeriesSimpleApplication {

	public static void main(String[] args) {
		SpringApplication.run(TimeSeriesSimpleApplication.class, args);
	}
}
