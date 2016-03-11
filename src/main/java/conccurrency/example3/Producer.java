package conccurrency.example3;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by evgen on 13.01.16.
 */
public class Producer extends Thread {

    private Queue<Integer> queue;
    Random random = new Random(47);

    public Producer(Queue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        for (;;){
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int i = random.nextInt(100);
            queue.offer(i);
            System.out.println("add " + i);
            if (queue.size() >= 20) {
                System.out.println("Full queue");
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
