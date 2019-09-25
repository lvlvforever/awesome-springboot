package site.daipeng.boot.demo.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Observable;

@Component
public class Runner {
    @EventListener
    public void listenWhistle(Object o) {
        if (o instanceof WhistleCount) {
            System.err.println("runner get the judge whistle " + ((WhistleCount) o).getCount());
        }

    }
}
