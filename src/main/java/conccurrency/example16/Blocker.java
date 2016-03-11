package conccurrency.example16;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by evgen on 27.02.16.
 */
public class Blocker {

    synchronized void waitingCall() {
        try {
            while (!Thread.interrupted()){
                wait();
                System.out.print(Thread.currentThread() + " ");
            }
        }catch (InterruptedException e) {
            throw new RuntimeException();
        }
    }

    synchronized void prod() {
        notify();
    }

    synchronized void prodAll() {
        notifyAll();
    }
}

class Task implements Runnable {

    static Blocker blocker = new Blocker();


    @Override
    public void run() {
        blocker.waitingCall();
    }
}

class Task2 implements Runnable {

    static Blocker blocker = new Blocker();

    @Override
    public void run() {
        blocker.waitingCall();
    }
}


class NotifyVSNotifyAll {

    public static void main(String[] args) throws Exception {
        ExecutorService ex = Executors.newCachedThreadPool();
        for(int i = 0; i < 5 ; i++)
            ex.execute(new Task());
        ex.execute(new Task2());
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {

            boolean prod = true;

            @Override
            public void run() {
                if (prod) {
                    System.out.print("\nnotify() ");
                    Task.blocker.prod();
                    prod = false;
                } else {
                    System.out.print("\nnotifyAll() ");
                    Task.blocker.prodAll();
                    prod = true;
                }
            }
        }, 400, 400);
        TimeUnit.SECONDS.sleep(5);
        timer.cancel();
        System.out.println("\nTimer canceled");
        TimeUnit.MILLISECONDS.sleep(500);
        System.out.println("Task2.blocked.prodAll()");
        Task2.blocker.prodAll();
        TimeUnit.MILLISECONDS.sleep(500);
        System.out.println("\nShutting down");
        ex.shutdownNow();
    }
}