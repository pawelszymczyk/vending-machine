package vendingmachine.domain;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Adam Dubiel
 */
public class Clock {

    private final List<TickListener> listeners = new ArrayList<>();

    public Clock(TickListener... listeners) {
        for(TickListener listener : listeners) {
            addListener(listener);
        }
    }

    public final void addListener(TickListener listener) {
        if (!listeners.contains(listener)) {
            listeners.add(listener);
        }
    }

    public void nextTick() {
        for (TickListener listener : listeners) {
            listener.ticktock();
        }
    }

}
