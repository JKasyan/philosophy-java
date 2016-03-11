package conccurrency.example25;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.NANOSECONDS;

/**
 * Created by evgen on 08.03.16.
 */
public class DelayedTask implements Delayed, Runnable {

    private static int counter = 0;
    private final int id = counter++;
    private final int delta;
    private final long trigger;
    protected static List<DelayedTask> sequence = new ArrayList<>();

    public DelayedTask(int delta) {
        this.delta = delta;
        trigger = System.nanoTime() + NANOSECONDS.convert(delta, MILLISECONDS);
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(trigger - System.nanoTime(), NANOSECONDS);
    }

    @Override
    public void run() {

    }

    @Override
    public int compareTo(Delayed o) {
        DelayedTask task = (DelayedTask)o;
        if (trigger < task.trigger) return -1;
        if (trigger > task.trigger) return 1;
        return 0;
    }
}
