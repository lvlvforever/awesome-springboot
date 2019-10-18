package site.daipeng.boot.demo.scheduler;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import java.time.chrono.ThaiBuddhistChronology;

/**
 * @author daipeng
 * @date 2019/8/29 19:36
 * @description
 */
@Component
public class AsyncTask {

    @Async("taskExecutor")
    public void asyncTask1() throws Exception {
        System.err.println("async task 1 " + Thread.currentThread().getName());
        Thread.sleep(100);
        System.err.println("async task 1 " + Thread.currentThread().getId());


    }
    @Async
    public void asyncTask2() {
        System.err.println("async task 2 " + Thread.currentThread().getName());

    }
}
