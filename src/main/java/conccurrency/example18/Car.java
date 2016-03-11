package conccurrency.example18;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by evgen on 28.02.16.
 */
public class Car {

    private Lock lock = new ReentrantLock();
    private boolean waxOn = false;
    private Condition condition = lock.newCondition();

    public void waxed() {
        lock.lock();
        try {
            waxOn = true;
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }

    public void buffed() {
        lock.lock();
        try {
            waxOn = false;
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }

    public void waitForWaxing() throws InterruptedException {
        lock.lock();
        try {
            while (!waxOn)
                condition.await();
        }finally {
            lock.unlock();
        }
    }

    public void waitForBuffing() throws InterruptedException {
        lock.lock();
        try {
            while (waxOn)
                condition.await();
        }finally {
            lock.unlock();
        }
    }
}
