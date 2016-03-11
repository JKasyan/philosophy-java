package conccurrency.example15;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by evgen on 25.02.16.
 */
public class Car {

    private boolean waxOn = false;

    public synchronized void waxed() {
        waxOn = true;
        notifyAll();
    }

    public synchronized void buffed() {
        waxOn = false;
        notifyAll();
    }

    public synchronized void waitForWaxing() throws InterruptedException {
        while (!waxOn)
            wait();
    }

    public synchronized void waitForBuffing() throws InterruptedException {
        while (waxOn)
            wait();
    }
}

class WaxOn implements Runnable {

    private Car car;

    WaxOn(Car car) {
        this.car = car;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                System.out.println("Wax on!");
                TimeUnit.MILLISECONDS.sleep(200);
                car.waxed();
                car.waitForBuffing();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Ending wax on task");
    }
}

class WaxOff implements Runnable {

    private Car car;

    WaxOff(Car car) {
        this.car = car;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                car.waitForWaxing();
                System.out.println("Wax off");
                TimeUnit.MILLISECONDS.sleep(200);
                car.buffed();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Ending wax off task");
    }
}

class Main {

    public static void main(String[] args) throws Exception {
        Car car = new Car();
        ExecutorService ex = Executors.newCachedThreadPool();
        ex.execute(new WaxOff(car));
        ex.execute(new WaxOn(car));
        TimeUnit.SECONDS.sleep(5);
        ex.shutdownNow();
    }
}
