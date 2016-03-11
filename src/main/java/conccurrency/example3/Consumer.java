package conccurrency.example3;

import java.util.Queue;
import java.util.concurrent.TimeUnit;

/**
 * Created by evgen on 13.01.16.
 */
public class Consumer extends Thread {

    private Queue<Integer> queue;

    public Consumer(Queue<Integer> queue){
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(queue.poll());
            if(queue.size() < 5){
                System.out.println("Empty queue");
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
