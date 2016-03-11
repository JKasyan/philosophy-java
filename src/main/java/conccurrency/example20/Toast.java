package conccurrency.example20;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by evgen on 05.03.16.
 */
public class Toast {

    private final int id;
    private enum Status {DRY, BUTTERED, JAMMED}
    private Status status = Status.DRY;

    public Toast(int id) {
        this.id = id;
    }

    public void butter() {
        status = Status.BUTTERED;
    }

    public void jam() {
        status = Status.JAMMED;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Toast{" +
                "id=" + id +
                ", status=" + status +
                '}';
    }
}

class ToasterQueue extends LinkedBlockingQueue<Toast> {}

class Toaster implements Runnable {

    private ToasterQueue toasterQueue;
    private int count;
    private Random random = new Random(47);

    public Toaster(ToasterQueue toasterQueue) {
        this.toasterQueue = toasterQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(100 + random.nextInt(500));
                Toast toast = new Toast(count++);
                toasterQueue.put(toast);
            }
        } catch (InterruptedException e) {
            System.out.println("Toaster interrupted");
        }
        System.out.println("Toast off");
    }
}

class Butterer implements Runnable {

    private ToasterQueue toasterQueue, butteredQueue;

    public Butterer(ToasterQueue butteredQueue, ToasterQueue toasterQueue) {
        this.butteredQueue = butteredQueue;
        this.toasterQueue = toasterQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                Toast toast = toasterQueue.take();
                toast.butter();
                butteredQueue.put(toast);
            }
        } catch (InterruptedException e) {
            System.out.println("Butterer interrupted");
        }
        System.out.println("Buttered off");
    }
}

class Jammer implements Runnable {

    private ToasterQueue butteredQueue, finishQueue;

    public Jammer(ToasterQueue butteredQueue, ToasterQueue finishQueue) {
        this.butteredQueue = butteredQueue;
        this.finishQueue = finishQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {

                Toast toast = butteredQueue.take();
                toast.jam();
                finishQueue.put(toast);

            }
        } catch (InterruptedException e) {
            System.out.println("Jammer interrupted");
        }
        System.out.println("Jam of");
    }
}

class Eater implements Runnable {

    private ToasterQueue finishQueue;
    private int count;

    public Eater(ToasterQueue finishQueue) {
        this.finishQueue = finishQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()){
                Toast toast = finishQueue.take();
                if (toast.getId() != count++) {
                    System.out.println("Error " + toast);
                } else {
                    System.out.println("Chop: " + toast);
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Eater interrupted");
        }
        System.out.println("Eater off");
    }
}

class Main {

    public static void main(String[] args) throws Exception {
        ToasterQueue toasterQueue = new ToasterQueue();
        ToasterQueue butteredQueue = new ToasterQueue();
        ToasterQueue finishQueue = new ToasterQueue();
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(new Toaster(toasterQueue));
        service.execute(new Butterer(toasterQueue, butteredQueue));
        service.execute(new Jammer(butteredQueue, finishQueue));
        TimeUnit.SECONDS.sleep(5);
        service.shutdownNow();
    }
}
