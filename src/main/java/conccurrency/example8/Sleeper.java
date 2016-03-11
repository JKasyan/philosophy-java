package conccurrency.example8;

/**
 * Created by evgen on 14.01.16.
 */
public class Sleeper extends Thread {

    private int duration;

    Sleeper(String name, int sleepTime) {
        super(name);
        this.duration = sleepTime;
        start();
    }

    @Override
    public void run() {
        try {
            sleep(duration);
        } catch (InterruptedException e) {
            System.out.println(getName() + " was interrupted. " + "isInterrupted() " + isInterrupted());
        }
        System.out.println(getName() + " was awakened");
    }
}

class Joiner extends Thread {

    private Sleeper sleeper;

    Joiner(String name, Sleeper sleeper) {
        super(name);
        this.sleeper = sleeper;
        start();
    }

    @Override
    public void run() {
        try {
            sleeper.join();
        } catch (InterruptedException e) {
            System.out.println("interrupted");
        }
        System.out.println("join was completed");
    }
}

class Main {

    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            System.out.println("Ku!");
        });
        t.start();
        Sleeper sleepy = new Sleeper("sleepy", 1500);
        Sleeper grumpy = new Sleeper("grumpy", 1500);
        Joiner dopey = new Joiner("Dopey", sleepy);
        Joiner doc = new Joiner("Doc", grumpy);
        grumpy.interrupt();
    }
}

