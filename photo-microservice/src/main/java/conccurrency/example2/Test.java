package conccurrency.example2;

/**
 * Created by evgen on 12.01.16.
 */
public class Test implements Runnable{

    static int count = 0;

    @Override
    public void run() {
        while (true){
            try {
                count++;
                Thread.sleep(500);
                System.out.println(count + " Thread: " + Thread.currentThread().getId());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new Test());
        Thread t2 = new Thread(new Test());
        Thread t3 = new Thread(new Test());
        t1.start();
        t2.start();
        t3.start();
    }
}
