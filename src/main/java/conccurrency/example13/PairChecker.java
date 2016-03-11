package conccurrency.example13;

/**
 * Created by evgen on 20.01.16.
 */
public class PairChecker implements Runnable {

    private PairManager pm;

    public PairChecker(PairManager pm) {
        this.pm = pm;
    }

    @Override
    public void run() {
        while (true) {
            pm.counter.incrementAndGet();
            pm.getPair().checkValues();

        }
    }
}
