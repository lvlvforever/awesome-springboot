package site.daipeng.boot.demo.commandlinerunner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author daipeng
 * @date 2019/8/21 16:26
 * @description
 */
@Component
@Order(1)
public class TaskRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.err.println("i am task runner"+Thread.currentThread().getId());

    }
}
