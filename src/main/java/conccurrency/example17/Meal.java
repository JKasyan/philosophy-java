package conccurrency.example17;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by evgen on 28.02.16.
 */
public class Meal {

    private final int id ;

    public Meal(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Meal " + id;
    }
}

class Restaurant {

    Meal meal;
    final Chef chef = new Chef(this);
    final WaitPerson waitPerson = new WaitPerson(this);
    ExecutorService ex = Executors.newCachedThreadPool();

    public Restaurant() {
        ex.execute(chef);
        ex.execute(waitPerson);
    }

    public static void main(String[] args) {
        new Restaurant();
    }
}

class Chef implements Runnable {

    final Restaurant restaurant;
    private int count = 0;

    public Chef(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    while (restaurant.meal != null)
                        wait();
                }
                if(++count == 10){
                    restaurant.ex.shutdownNow();
                }
                System.out.println("Order up!");
                synchronized (restaurant.waitPerson) {
                    restaurant.meal = new Meal(count);
                    restaurant.waitPerson.notifyAll();
                }
                TimeUnit.MILLISECONDS.sleep(500);
            }
        } catch (InterruptedException e) {
            System.out.println("Chef interrupted");
        }
    }
}

class WaitPerson implements Runnable {

    final Restaurant restaurant;

    public WaitPerson(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    while (restaurant.meal == null) {
                        wait();
                    }
                }
                System.out.println("WaitPerson got " + restaurant.meal);
                synchronized (restaurant.chef) {
                    restaurant.meal = null;
                    restaurant.chef.notifyAll();
                }
            }
        }catch (InterruptedException e) {
            System.out.println("WaitPerson interrupted");
        }
    }
}

