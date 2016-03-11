package conccurrency.example10.my;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by evgen on 15.01.16.
 */
public class Holder<T> {

    private Object[] objects;
    private int size;
    private Lock lock = new ReentrantLock();

    Holder() {
        objects = new Object[0];
    }

    public void add(T t) {
        lock.lock();
        try {
            int oldLength = objects.length;
            Object[] oldObjects = objects;
            Object[] newObjects = Arrays.copyOf(oldObjects, oldLength + 1);
            newObjects[oldLength] = t;
            size++;
            this.objects = newObjects;
        }finally {
            lock.unlock();
        }
    }

    @SuppressWarnings("unchecked")
    public T getLast() {
        lock.lock();
        try {
            return (T)objects[size - 1];
        }finally {
            lock.unlock();
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(objects);
    }
}

class Producer implements Runnable {

    private final Holder<Integer> holder;

    public Producer(Holder<Integer> holder) {
        this.holder = holder;
    }

    @Override
    public void run() {
        try {
            int i = 0;
            while (true) {
                TimeUnit.MILLISECONDS.sleep(10);
                holder.add(i++);
                if(i % 100 == 0) {
                    System.out.println("Added: " + i);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Consumer implements Runnable{

    private final Holder<Integer> holder;

    public Consumer(Holder<Integer> holder) {
        this.holder = holder;
    }

    @Override
    public void run() {
        try {
            int i = 0;
            while (true) {
                i++;
                TimeUnit.MILLISECONDS.sleep(10);
                if(i % 100 == 0) {
                    System.out.println("Get: " + holder.getLast());
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Main {

    public static void main(String[] args) {
        Holder<Integer> holder = new Holder<>();
        ExecutorService s = Executors.newCachedThreadPool();
        s.execute(new Producer(holder));
        s.execute(new Producer(holder));
        s.execute(new Consumer(holder));
        s.execute(new Consumer(holder));
    }
}
