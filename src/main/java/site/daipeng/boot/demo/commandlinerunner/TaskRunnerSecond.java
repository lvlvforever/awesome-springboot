package site.daipeng.boot.demo.commandlinerunner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.websocket.OnError;

/**
 * @author daipeng
 * @date 2019/8/21 16:26
 * @description
 */
@Component
@Order(3)
public class TaskRunnerSecond implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
//        System.err.println("i am task runner second"+Thread.currentThread().getId());

    }
}
