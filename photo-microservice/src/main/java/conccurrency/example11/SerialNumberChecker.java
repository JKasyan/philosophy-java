package conccurrency.example11;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by evgen on 20.01.16.
 */
public class SerialNumberChecker implements Runnable {

    private static final int SIZE = 5;
    private CycleSet cycleSet = new CycleSet(1000);
    private static ExecutorService executor = Executors.newCachedThreadPool();

    @Override
    public void run() {
        while (true) {
            int serial = SerialNumberGenerator.nextSerialNumber();
            if(cycleSet.contains(serial)){
                System.out.println("Duplicate: " + serial);
                System.exit(0);
            }
            if(serial == Integer.MAX_VALUE){
                System.out.println("Integer.MAX_VALUE");
                System.exit(0);
            }
            cycleSet.add(serial);
        }
    }

    public static void main(String[] args) {
        for(int i = 0; i < SIZE;i++) {
            executor.execute(new SerialNumberChecker());
        }
    }
}
