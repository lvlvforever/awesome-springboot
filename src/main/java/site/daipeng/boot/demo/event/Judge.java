package site.daipeng.boot.demo.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Judge{
    private int count = 0;
    @Autowired
    private ApplicationContext applicationContext;
    @Scheduled(cron = "0/10 * * * * ?")
    public void blowTheWhistle() {

        WhistleCount data = new WhistleCount();
        data.setCount(count++);
        applicationContext.publishEvent(data);


    }
}
