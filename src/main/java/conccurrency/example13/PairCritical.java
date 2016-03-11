package conccurrency.example13;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by evgen on 20.01.16.
 */
public class PairCritical {

    public static void testApproaches(PairManager pm1, PairManager pm2) {
        PairManipulator pairManipulator1 = new PairManipulator(pm1);
        PairManipulator pairManipulator2 = new PairManipulator(pm2);
        PairChecker pc1 = new PairChecker(pm1);
        PairChecker pc2 = new PairChecker(pm2);
        ExecutorService ex = Executors.newCachedThreadPool();
        ex.execute(pairManipulator1);
        ex.execute(pairManipulator2);
        ex.execute(pc1);
        ex.execute(pc2);
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("pm1: " + pairManipulator1 + ", pm2: " + pairManipulator2);
        System.exit(0);
    }

    public static void main(String[] args) {
        PairManager pm1 = new PairManager1();
        PairManager pm2 = new PairManager2();
        testApproaches(pm1, pm2);
    }
}
