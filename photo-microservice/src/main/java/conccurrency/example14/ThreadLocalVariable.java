package conccurrency.example14;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by evgen on 21.01.16.
 */
public class ThreadLocalVariable {

    private static ThreadLocal<Integer> value = new ThreadLocal<Integer>(){

        private Random random = new Random(47);
        @Override
        protected synchronized Integer initialValue() {
            return random.nextInt(10_000);
        }
    };

    public static void increment() {
        value.set(value.get() + 1);
    }

    public static Integer get() {
        return value.get();
    }

    public static void main(String[] args) throws Exception {
        ExecutorService ex = Executors.newCachedThreadPool();
        for(int i = 0;i < 5; i++)
            ex.execute(new Accessor(i));
        TimeUnit.SECONDS.sleep(3);
        ex.shutdown();
    }
}

class Accessor implements Runnable {

    private final int id;

    public Accessor(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()){
            ThreadLocalVariable.increment();
            System.out.println(this);
            Thread.yield();
        }
    }

    @Override
    public String toString() {
        return "#" + id + ": " + ThreadLocalVariable.get();
    }
}
