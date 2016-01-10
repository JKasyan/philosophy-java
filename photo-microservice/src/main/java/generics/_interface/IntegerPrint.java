package generics._interface;

/**
 * Created by evgen on 08.01.16.
 */
public class IntegerPrint implements Printable<Integer> {

    @Override
    public void print(Integer integer) {
        System.out.println(integer);
    }
}
