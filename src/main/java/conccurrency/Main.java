package conccurrency;

import java.util.Calendar;

/**
 * Created by evgen on 11.01.16.
 */
public class Main extends Thread {

    @Override
    public void run() {
        throw new RuntimeException("Error");
    }

    public static void main(String[] args) {
        Main thread1 = new Main();
        Calendar.getInstance();
        String name = Thread.currentThread().getName();
        System.out.println(name);
        try {
            thread1.run();
        }catch (RuntimeException e){
            e.printStackTrace();
        }
        System.out.println("Hello!");
    }
}


