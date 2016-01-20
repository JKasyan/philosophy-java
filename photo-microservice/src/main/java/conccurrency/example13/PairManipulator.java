package conccurrency.example13;

/**
 * Created by evgen on 20.01.16.
 */
public class PairManipulator implements Runnable {

    private PairManager pairManager;

    public PairManipulator(PairManager pairManager) {
        this.pairManager = pairManager;
    }

    @Override
    public void run() {
        while (true) {
            pairManager.increment();
        }
    }

    @Override
    public String toString() {
        return "Pair: " +
                pairManager.getPair() +
                ", checkCounter: " +
                pairManager.counter.get();
    }
}
