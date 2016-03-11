package conccurrency.example19;

import conccurrency.example1.LiftOff;

import java.util.concurrent.BlockingQueue;

/**
 * Created by evgen on 29.02.16.
 */
public class LiftOffRunner implements Runnable {

    private final BlockingQueue<LiftOff> blockingQueue;

    public LiftOffRunner(BlockingQueue<LiftOff> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public void add(LiftOff liftOff) {
        try {
            blockingQueue.put(liftOff);
        } catch (InterruptedException e) {
            System.out.println("Interrupted during put(T t)");
        }
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                LiftOff take = blockingQueue.take();
                take.run();
            }
        } catch (InterruptedException e) {

        }
    }
}
