package generics.erause.array;

import java.lang.reflect.*;
import java.util.Arrays;

/**
 * Created by evgen on 30.12.15.
 */
public class GenericArray<T> {

    private T[] array;

    @SuppressWarnings("unchecked")
    public GenericArray(int size){
        array = (T[]) new Object[size];
    }

    public void add(int index, T t){
        array[index] = t;
    }

    public T[] getArray() {
        return array;
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }
}

class Main{
    public static void main(String[] args) throws Exception{
        GenericArray<String> genericArray = new GenericArray<>(5);
        genericArray.add(0, "1");
        System.out.println(genericArray);
        Object[] i = genericArray.getArray();
        /**
         *
         */
        Field arrayField = genericArray.getClass().getDeclaredField("generics/array");
        arrayField.setAccessible(true);
        System.out.println(arrayField);
        Object obj = arrayField.get(genericArray);
        System.out.println(obj.getClass());
        Object[] array = null;
        if(obj instanceof Object[]){
            array = (Object[])obj;
        }
        System.out.println(Arrays.toString(array));
        /**
         *
         */
        ReflectionArray<Integer> reflectionArray = new ReflectionArray<>(Integer.class, 5);
        reflectionArray.add(0, 15);
        System.out.println(reflectionArray);
        Field arrField = reflectionArray.getClass().getDeclaredField("generics/array");
        arrField.setAccessible(true);
        System.out.println(arrayField);
//        Object array1 = arrField.get("generics.array");
//        if(array1 instanceof Integer[]){
//            System.out.println("Integer");
//        }
    }
}


class ReflectionArray<T> {

    private T[] array;

    @SuppressWarnings("unchecked")
    public ReflectionArray(Class<T> type, int size){
        array = (T[])java.lang.reflect.Array.newInstance(type, size);
    }

    public T[] getArray() {
        return array;
    }

    public void add(int index, T t){
        array[index] = t;
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }
}
