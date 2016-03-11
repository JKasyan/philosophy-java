package conccurrency.example26;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by evgen on 08.03.16.
 */
public class Pool<T> {

    private int size;
    private List<T> list = new ArrayList<>();
    private Semaphore semaphore;
    private volatile boolean[] checkedOut;

    public Pool(Class<T> clazz, int size) {
        this.size = size;
        semaphore = new Semaphore(size, true);
        try {
            for(int i = 0;i < size;i++) {
                list.add(clazz.newInstance());
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public T checkout() throws InterruptedException {
        semaphore.acquire();
        return getItem();
    }

    public void checkIn(T t) throws InterruptedException {
        if(releaseItem(t))
            semaphore.acquire();
    }

    private synchronized T getItem() {
        for (int i = 0; i < size; i++) {
            if(!checkedOut[i]) {
                checkedOut[i] = true;
                return list.get(i);
            }
        }
        return null;
    }

    private synchronized boolean releaseItem(T t) {
        int index = list.indexOf(t);
        if(index == -1) return false;
        if(checkedOut[index]) {
            checkedOut[index] = false;
            return true;
        }
        return false;
    }
}

class Fat {

    private volatile double d;
    private static int counter = 0;
    private final int id = counter++;

    public Fat() {
        for (int i = 0; i < 10_000;i++) {
            d += (Math.PI + Math.E) /(double) i;
        }
    }

    public void operation() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Fat{" +
                "id=" + id +
                '}';
    }
}

class CheckoutTask<T> implements Runnable {

    private static int counter = 0;
    private final int id = counter++;
    private Pool<T> pool;

    public CheckoutTask(Pool<T> pool) {
        this.pool = pool;
    }

    @Override
    public void run() {
        try {
            T item = pool.checkout();
            System.out.println(this + " Checked out " + item);
            pool.checkIn(item);
            System.out.println(this + " check in " + item);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "CheckoutTask{" +
                "id=" + id +
                '}';
    }
}

class SemaphoreDemo {

    final static int SIZE = 25;

    public static void main(String[] args) {
        Pool<Fat> pool = new Pool<>(Fat.class, SIZE);
        ExecutorService ex = Executors.newCachedThreadPool();
        for(int i = 0;i < SIZE;i++) {
            ex.execute(new CheckoutTask<>(pool));
        }
        System.out.println("All tasks created");

    }
}
