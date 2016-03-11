package generics.erause.array;

/**
 * Created by evgen on 30.12.15.
 */
public class Array {

    static Generic<Integer>[] generic;

    public static void main(String[] args) {
        generic = (Generic<Integer>[])new Object[10];
        System.out.println(generic);
    }
}

interface Generic<T>{}
