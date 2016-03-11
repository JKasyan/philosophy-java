package conccurrency.example15.task;

/**
 * Created by evgen on 27.02.16.
 */
public class TaskOne implements Runnable {

    @Override
    public synchronized void run() {
        System.out.println("TaskOne run()");
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
