package conccurrency.example9;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Created by evgen on 14.01.16.
 */
public class ThreadException2 implements Runnable {

    @Override
    public void run() {
        Thread t = Thread.currentThread();
        System.out.println("run() by: " + t);
        System.out.println("eh: " + t.getUncaughtExceptionHandler());
        throw new RuntimeException();
    }
}

class MyExceptionHandler implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("Caught: " + e);
    }
}

class HandlerThreadFactory implements ThreadFactory {

    @Override
    public Thread newThread(Runnable r) {
        System.out.println(this + " creating new thread.");
        Thread thread = new Thread(r);
        System.out.println("Created: " + thread);
        thread.setUncaughtExceptionHandler(new MyExceptionHandler());
        System.out.println("eh: " + thread.getUncaughtExceptionHandler());
        return thread;
    }
}

class Main2 {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool(new HandlerThreadFactory());
        executorService.execute(new ThreadException2());
    }
}
