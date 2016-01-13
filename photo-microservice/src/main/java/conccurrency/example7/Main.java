package conccurrency.example7;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by evgen on 13.01.16.
 */
public class Main {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++) {
            executorService.execute(new FileSaver());
        }
    }
}
