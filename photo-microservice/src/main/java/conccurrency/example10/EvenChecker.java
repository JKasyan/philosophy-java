package conccurrency.example10;

import conccurrency.IntGenerator;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by evgen on 14.01.16.
 */
public class EvenChecker implements Runnable{

    private final int id;
    private IntGenerator generator;

    public EvenChecker(int id, IntGenerator generator) {
        this.id = id;
        this.generator = generator;
    }

    @Override
    public void run() {
        while (!generator.isCanceled()){
            int value = generator.next();
            if(value % 2 != 0) {
                System.out.println(value + " not even");
                generator.cancel();
            }
        }
    }

    public static void test(IntGenerator generator, int count) {
        System.out.println("rrr");
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < count; i++) {
            service.execute(new EvenChecker(i, generator));
        }
    }

    public static void test(IntGenerator generator){
        test(generator, 10);
    }
}
