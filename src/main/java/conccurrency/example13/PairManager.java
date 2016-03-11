package conccurrency.example13;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by evgen on 20.01.16.
 */
public abstract class PairManager {

    AtomicInteger counter = new AtomicInteger(0);
    Pair pair = new Pair();
    private List<Pair> storage = Collections.synchronizedList(new ArrayList<>());

    public synchronized Pair getPair() {
        return new Pair(pair.getX(), pair.getY());
    }

    protected void store(Pair pair) {
        storage.add(pair);
        try {
            TimeUnit.MILLISECONDS.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public abstract void increment();

}

class PairManager1 extends PairManager {

    @Override
    public synchronized void increment() {
        pair.incrementX();
        pair.incrementY();
        store(pair);
    }
}

class PairManager2 extends PairManager {

    @Override
    public void increment() {
        Pair temp;
        synchronized (this) {
            pair.incrementX();
            pair.incrementY();
            temp = getPair();
        }
        store(temp);
    }
}
