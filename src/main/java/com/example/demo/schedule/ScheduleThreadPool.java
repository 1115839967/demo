package com.example.demo.schedule;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * 定时任务执行池
 */

public class ScheduleThreadPool {
    private static ScheduledExecutorService service;
    private static ScheduleThreadPool instance = new ScheduleThreadPool();

    public ScheduleThreadPool() {
    }

    public ScheduleThreadPool(int corePoolSize) {
        service = Executors.newScheduledThreadPool(corePoolSize);
    }

    public static ScheduleThreadPool getInstance() {
        return instance;
    }

    public ScheduledExecutorService getService() {
        return service;
    }
}
