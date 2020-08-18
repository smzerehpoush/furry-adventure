package me.mahdiyar.digipay.payment.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author Seyyed Mahdiyar Zerehpoush
 */
@Configuration
public class NotificationTaskExecutor {
    @Bean
    public TaskExecutor notificationThreadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(20);
        executor.setMaxPoolSize(40);
        executor.setThreadNamePrefix("notification_task_executor_thread");
        executor.initialize();
        return executor;
    }
}
