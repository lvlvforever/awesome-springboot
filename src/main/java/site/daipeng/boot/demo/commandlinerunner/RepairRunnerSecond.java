package site.daipeng.boot.demo.commandlinerunner;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author daipeng
 * @date 2019/8/21 16:26
 * @description
 */
@Component
@Order(4)
public class RepairRunnerSecond implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        //TODO 业务逻辑
        System.err.println(args);
        System.err.println("i am repair runner second"+Thread.currentThread().getId());

    }
}
