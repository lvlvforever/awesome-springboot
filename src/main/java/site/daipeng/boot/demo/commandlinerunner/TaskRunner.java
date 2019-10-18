package site.daipeng.boot.demo.commandlinerunner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import site.daipeng.boot.demo.scheduler.AsyncTask;

/**
 * @author daipeng
 * @date 2019/8/21 16:26
 * @description
 */
@Component
@Order(1)
public class TaskRunner implements CommandLineRunner {
    @Autowired
    private AsyncTask asyncTask;
    @Override
    public void run(String... args) throws Exception {
        asyncTask.asyncTask1();
        asyncTask.asyncTask2();

    }
}
