package conccurrency.example10;

import conccurrency.IntGenerator;

/**
 * Created by evgen on 14.01.16.
 */
public class EvenGenerator extends IntGenerator {

    private int evenValue = 0;

    @Override
    public int next() {
        synchronized (this) {
            ++evenValue;
            Thread.yield();
            ++evenValue;
            return evenValue;
        }
    }

    public static void main(String[] args) {
        EvenChecker.test(new EvenGenerator());
    }
}
