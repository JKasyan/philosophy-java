package conccurrency.example4;

import java.util.concurrent.Callable;

/**
 * Created by evgen on 12.01.16.
 */
public class TaskWithResult implements Callable<String> {

    private int id;

    public TaskWithResult(int id) {
        this.id = id;
    }

    @Override
    public String call() throws Exception {
        return "result " + id;
    }
}
