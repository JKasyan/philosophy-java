package conccurrency.example15.my;

import java.util.Queue;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by evgen on 25.02.16.
 */
public class Container {

    private Queue<Integer> queue = new ConcurrentLinkedQueue<>();
    private static final int HIGH = 20;
    private static final int LESS = 10;

    public synchronized void add(int i) throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(100);
        queue.offer(i);
        notifyAll();
    }

    public synchronized int get() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(200);
        notifyAll();
        return queue.poll();
    }

    public synchronized void checkFull() throws InterruptedException {
        while(queue.size() >= HIGH)
            wait();
    }

    public synchronized void checkEmpty() throws InterruptedException {
        while(queue.size() <= LESS)
            wait();
    }

    public int size() {
        return queue.size();
    }
}

class Consumer implements Runnable {

    private Container container;

    Consumer(Container container) {
        this.container = container;
    }

    @Override
    public void run() {
        try {
            while (true) {
                container.checkEmpty();
                int i = container.get();
                System.out.println("Get " + i + ", size: " + container.size());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Producer implements Runnable {

    private AtomicInteger i = new AtomicInteger();
    private Container container;

    Producer(Container container) {
        this.container = container;
    }

    @Override
    public void run() {
        try {
            while (true) {
                container.checkFull();
                container.add(i.incrementAndGet());
                System.out.println("Added " + i + ", size: " + container.size());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class TestMain {

    public static void main(String[] args) throws Exception {
        Container container = new Container();
        ExecutorService ex = Executors.newCachedThreadPool();
        ex.execute(new Producer(container));
        ex.submit(new Consumer(container));
        ex.submit(new Consumer(container));
        Future<?> future = ex.submit(new Consumer(container));
        TimeUnit.SECONDS.sleep(5);
        future.cancel(true);
        TimeUnit.SECONDS.sleep(10);
        ex.shutdownNow();
    }
}
