package conccurrency.example9;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by evgen on 14.01.16.
 */
public class ThreadException implements Runnable {

    @Override
    public void run() {
        throw new RuntimeException();
    }
}

class Main {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Class<ThreadException> threadExceptionClass = ThreadException.class;
        try {
            executorService.execute(new ThreadException());
        }catch (RuntimeException e) {
            System.out.println("Hello");
        }

    }
}
