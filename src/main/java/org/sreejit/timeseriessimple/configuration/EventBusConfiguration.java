package org.sreejit.timeseriessimple.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class EventBusConfiguration {

    @Bean
    public ApplicationEventMulticaster simpleApplicationEventMulticaster() {
        final SimpleApplicationEventMulticaster eventMulticaster = new SimpleApplicationEventMulticaster();
        final ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(4);
        threadPoolTaskExecutor.setMaxPoolSize(10);
        threadPoolTaskExecutor.initialize();
        eventMulticaster.setTaskExecutor(threadPoolTaskExecutor);
        return eventMulticaster;
    }
}
