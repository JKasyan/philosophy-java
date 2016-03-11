package conccurrency.example19;

import java.util.concurrent.*;

/**
 * Created by evgen on 01.03.16.
 */
public class BlockingQueueTest {

    public static void main(String[] args) throws Exception {
        ExecutorService ex = Executors.newCachedThreadPool();
        BlockingQueue<Integer> queue = new LinkedBlockingDeque<>(20);
        ex.execute(new Producer(queue));
//        ex.execute(new Producer(queue));
//        ex.execute(new Consumer(queue));
        TimeUnit.SECONDS.sleep(10);
        ex.shutdownNow();
    }
}

class Producer implements Runnable {

    private final BlockingQueue<Integer> queue;

    public Producer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        int i = 0;
        while (!Thread.interrupted()) {
            try {
                System.out.println("Adding: " + i);
                queue.put(i);
                TimeUnit.MILLISECONDS.sleep(100);
                i++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Consumer implements Runnable {

    private final BlockingQueue<Integer> queue;

    public Consumer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                Integer i = queue.take();
                System.out.println("Get: " + i);
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
