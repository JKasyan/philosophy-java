package conccurrency.example11;

/**
 * Created by evgen on 20.01.16.
 */
public class SerialNumberGenerator {

    private static int serial;

    public synchronized static int nextSerialNumber(){
        return ++serial;
    }
}
