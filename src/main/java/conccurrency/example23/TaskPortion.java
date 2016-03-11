package conccurrency.example23;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by evgen on 06.03.16.
 */
public class TaskPortion implements Runnable {

    private static int count = 0;
    private final int id = ++count;
    private Random random = new Random(47);
    private final  CountDownLatch latch;

    public TaskPortion(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            doWork();
            latch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void doWork() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(random.nextInt(2000));
        System.out.println("Completed " + this);
    }

    @Override
    public String toString() {
        return String.format("%1$-3d", id);
    }
}

class WaitingTask implements Runnable {

    private final CountDownLatch latch;
    private static int count = 0;
    private final int id = ++count;

    public WaitingTask(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return String.format("Waiting task %1$-3d", id);
    }

}

class Main {

    private final static int SIZE = 100;

    public static void main(String[] args) {
        ExecutorService ex = Executors.newCachedThreadPool();
        CountDownLatch latch = new CountDownLatch(SIZE);
        for(int i = 0;i<10;i++) {
            ex.execute(new WaitingTask(latch));
        }
        for(int i = 0;i<SIZE;i++) {
            ex.execute(new TaskPortion(latch));
        }
        System.out.println("Launched all tasks");
        ex.shutdownNow();
    }
}
