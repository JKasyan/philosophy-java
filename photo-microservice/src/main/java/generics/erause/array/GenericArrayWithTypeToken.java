package generics.erause.array;

/**
 * Created by evgen on 30.12.15.
 */
public class GenericArrayWithTypeToken<T> {

    private T[] array;

    @SuppressWarnings("unchecked")
    public GenericArrayWithTypeToken(Class<T> clazz, int size) {
        this.array = (T[])java.lang.reflect.Array.newInstance(clazz, size);
    }

    public void add(int index, T t){
        array[index] = t;
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        return array[index];
    }

    public T[] rep(){
        return array;
    }

    public static void main(String[] args) {
        @SuppressWarnings("unchecked")
        GenericArrayWithTypeToken array = new GenericArrayWithTypeToken(Integer.class, 10);
        //Lol
        //Integer[] a = generics.array.rep();
        P p = new P("erd");
    }
}

class P<H>{

    H h;

    P(H h){
        this.h = h;
        System.out.println("Hash: " + h.hashCode());
    }
}
