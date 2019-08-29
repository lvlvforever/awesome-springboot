package site.daipeng.boot.demo.scheduler;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TaskSchedule {
    @Scheduled(initialDelay = 10,fixedRate = 1000)
    public void taskFixRate() throws Exception{
       // System.err.println("fix rate "+Thread.currentThread().getId()+ " " + System.currentTimeMillis());

        Thread.sleep(500);

    }
    @Scheduled(fixedDelay = 1000)
    public void taskFixDelay() throws Exception{
//        System.err.println("fix delay "+Thread.currentThread().getId()+ " " + System.currentTimeMillis());

    }
    @Scheduled(cron = "1/5 * * * * *")
    public void taskCron() throws Exception{
//        System.err.println("cron "+Thread.currentThread().getId()+ " " + System.currentTimeMillis());

    }
}
