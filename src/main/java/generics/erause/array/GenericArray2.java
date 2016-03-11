package generics.erause.array;

/**
 * Created by evgen on 30.12.15.
 */
public class GenericArray2<T> {

    private Object[] array;

    public GenericArray2(int size) {
        array = new Object[size];
    }

    @SuppressWarnings("unchecked")
    public T[] getArray() {
        return (T[])array;
    }

    public void add(int index, T t){
        array[index] = t;
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        return (T)array[index];
    }

    public static void main(String[] args) {
        GenericArray2<Integer> genericArray2 = new GenericArray2<>(5);
        genericArray2.add(0, 15);
        genericArray2.add(1, 15);
        genericArray2.add(2, 15);
        genericArray2.add(3, 15);
        genericArray2.add(4, 15);
        System.out.println(genericArray2.get(1));
    }

}
