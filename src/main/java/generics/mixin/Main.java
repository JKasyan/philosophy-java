package generics.mixin;

/**
 * Created by evgen on 10.01.16.
 */
public class Main {
}

interface TimeStamped {
    long getStamp();
}

class TimeStampedImpl implements TimeStamped{

    @Override
    public long getStamp() {
        return 0;
    }
}


