package generics.array;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created by evgen on 30.12.15.
 */
public class ArrayGeneric<T> {

    private Class<T> type;

    public ArrayGeneric(Class<T> type) {
        this.type = type;
    }

    @SuppressWarnings("unchecked")
    T[] create(int size){
        return (T[])Array.newInstance(type, size);
    }

    public static void main(String[] args) {
        ArrayGeneric<Integer> arrayGeneric = new ArrayGeneric<>(Integer.class);
        Integer[] integers = arrayGeneric.create(10);
        System.out.println(Arrays.toString(integers));
        new K();
    }

}

class K {
    private Object object;

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

}
