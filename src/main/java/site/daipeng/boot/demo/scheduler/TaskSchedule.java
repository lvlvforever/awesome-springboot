package site.daipeng.boot.demo.scheduler;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TaskSchedule {

    @Scheduled(cron = "1/10 * * * * *")
    public void task() {
        System.err.println(System.currentTimeMillis() + "task");
    }
}
