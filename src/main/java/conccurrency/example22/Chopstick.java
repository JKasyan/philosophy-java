package conccurrency.example22;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by evgen on 05.03.16.
 */
public class Chopstick {

    private boolean taken = false;
    private Thread owner;

    public synchronized void take() throws InterruptedException {
        while (taken)
            wait();
        taken = true;
        owner = Thread.currentThread();
        System.out.println("Owner: " + owner);
    }

    public synchronized void drop() {
        taken = false;
        owner = null;
        notifyAll();
    }
}

class Philosopher implements Runnable {

    private Chopstick leftChopstick;
    private Chopstick rightChopstick;
    private int id;
    private int ponderFactor;
    private Random random = new Random(47);

    public Philosopher(Chopstick leftChopstick, Chopstick rightChopstick, int id, int ponderFactor) {
        this.leftChopstick = leftChopstick;
        this.rightChopstick = rightChopstick;
        this.id = id;
        this.ponderFactor = ponderFactor;
    }

    private void pause() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(random.nextInt(ponderFactor * 250));
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                System.out.println(this + " thinking");
                pause();
                System.out.println("Grabbing left");
                leftChopstick.take();
                System.out.println("Grabbing right");
                rightChopstick.take();
                System.out.println(this + " eating");
                pause();
                leftChopstick.drop();
                rightChopstick.drop();
            }
        }catch (InterruptedException e) {
            System.out.println("Philosopher interrupted");
        }
    }

    @Override
    public String toString() {
        return "Philosopher{" +
                "id=" + id +
                '}';
    }
}

class Main {

    public static void main(String[] args) throws InterruptedException {
        int ponder = 5;
        int size = 5;
        ExecutorService service = Executors.newCachedThreadPool();
        Chopstick[] chopsticks = new Chopstick[size];
        for (int i = 0;i<size;i++) {
            chopsticks[i] = new Chopstick();
        }
        for (int i = 0;i<size;i++) {
            service.execute(new Philosopher(
                    chopsticks[i],
                    chopsticks[i+1], i, ponder));
        }
        TimeUnit.SECONDS.sleep(5);
        service.shutdownNow();
    }
}
