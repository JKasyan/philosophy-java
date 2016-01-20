package conccurrency.example12;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by evgen on 15.01.16.
 */
public class AttemptLocking {

    private Lock lock = new ReentrantLock();

    public void untimed() {
        boolean captured = lock.tryLock();
        try {
            System.out.println("tryLock(): " + captured);
        }finally {
            if(captured) {
                lock.unlock();
            }
        }
    }

    public void timed() {
        boolean captured = false;
    }
}
