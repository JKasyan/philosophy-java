package conccurrency.example4;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by evgen on 12.01.16.
 */
public class CallableDemo {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        List<Future<String>> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(executor.submit(new TaskWithResult(i)));
        }
        for (Future<String> future:list) {
            try {
                System.out.println(future.get());
            }catch (InterruptedException | ExecutionException e) {
                System.out.println(e);
                return;
            }finally {
                executor.shutdown();
            }
        }
    }
}
