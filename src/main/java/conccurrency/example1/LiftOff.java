package conccurrency.example1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by evgen on 12.01.16.
 */
public class LiftOff implements Runnable {

    private int countDown = 10;
    private static int taskCount = 0;
    private final int id = taskCount++;

    LiftOff(){}
    LiftOff(int count){
        this.countDown = count;
    }

    public String status(){
        return "#" + id + "(" + (countDown > 0 ? countDown : "LiftOff!") + "), ";
    }

    @Override
    public void run() {
        try {
            while (countDown-- > 0) {
                System.out.println(status());
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        for (int i = 0; i<5;i++){
            executor.execute(new LiftOff());
        }
        executor.shutdown();
        System.out.println("Waiting for lift");
    }
}

