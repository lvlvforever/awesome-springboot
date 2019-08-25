package site.daipeng.boot.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {


        System.err.println("app start"+Thread.currentThread().getId());

        SpringApplication.run(DemoApplication.class, args);

        System.err.println("app end"+Thread.currentThread().getId());


    }

}
