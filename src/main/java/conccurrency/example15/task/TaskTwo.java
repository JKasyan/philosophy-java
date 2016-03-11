package conccurrency.example15.task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by evgen on 27.02.16.
 */
public class TaskTwo implements Runnable {

    private Runnable runnable;

    public TaskTwo(Runnable runnable) {
        this.runnable = runnable;
    }

    @Override
    public synchronized void run() {
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        runnable.notifyAll();
    }
}

class Main {

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        TaskOne taskOne = new TaskOne();
        service.execute(taskOne);
        service.execute(new TaskTwo(taskOne));
    }
}
