package conccurrency.example3;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by evgen on 13.01.16.
 */
public class Main {

    public static void main(String[] args) {
        Queue<Integer> queue = new ConcurrentLinkedQueue<>();
        Producer producer = new Producer(queue);
        Consumer c1 = new Consumer(queue);
        Consumer c2 = new Consumer(queue);
        producer.run();
        c1.run();
        c2.run();
    }
}
