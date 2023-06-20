package org.sreejit.timeseriessimple.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class TaskExecutorConfiguration {

    @Bean(name = "taskExecutor")
    public TaskExecutor getTaskExecutor() {
        final ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(4);
        threadPoolTaskExecutor.setMaxPoolSize(10);
        threadPoolTaskExecutor.initialize();
        threadPoolTaskExecutor.setBeanName("taskExecutor");

        return threadPoolTaskExecutor;
    }
}
