package conccurrency.example5;

import java.util.concurrent.TimeUnit;

/**
 * Created by evgen on 13.01.16.
 */
public class SimpleDaemon implements Runnable {

    @Override
    public void run() {
        try {
            while (true){
                TimeUnit.SECONDS.sleep(1);
                System.out.println(Thread.currentThread() + " " + this);
            }
        }catch (InterruptedException e){
            System.out.println("print interrupted");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0;i<10;i++) {
            Thread thread = new Thread(new SimpleDaemon());
            thread.setDaemon(true);
            thread.setName("Daemon " + thread.getId());
            thread.start();
        }
        System.out.println("all daemons started");
        TimeUnit.SECONDS.sleep(20);
    }
}
