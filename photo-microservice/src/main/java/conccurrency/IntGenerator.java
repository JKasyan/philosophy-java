package conccurrency;

/**
 * Created by evgen on 14.01.16.
 */
public abstract class IntGenerator {

    private volatile boolean canceled;

    public abstract int next();

    public void cancel() {
        canceled = false;
    }

    public boolean isCanceled() {
        return canceled;
    }
}
