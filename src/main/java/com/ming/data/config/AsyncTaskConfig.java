package com.ming.data.config;

import org.springframework.context.annotation.Bean;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 *
 * 配置异步任务的线程池数量
 * @author alun
 * @data 2019/5/11
 */
public class AsyncTaskConfig {

    @Bean
    public AsyncTaskExecutor validateExecutor() {
        return getAsyncTaskExecutor("validate", 1);
    }

    private AsyncTaskExecutor getAsyncTaskExecutor(String prefix, int poolSize) {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setThreadNamePrefix(prefix + "-Executor-");
        executor.setCorePoolSize(poolSize);
        executor.setMaxPoolSize(poolSize);
        return executor;
    }
}
