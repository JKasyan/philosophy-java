package generics;

import java.util.Objects;

/**
 * Created by evgen on 29.12.15.
 */
public class GeneratorImpl<T> implements Generator<T>{

    private Class<T> clazz;

    private GeneratorImpl(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public T next() {
        try {
            return clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException("");
        }
    }

    public static <T> Generator<T> create(Class<T> clazz){
        return new GeneratorImpl<>(clazz);
    }
    public static void main(String[] args) {
        Generator<R> gen = GeneratorImpl.create(R.class);
        R next = gen.next();
        System.out.println(next);

    }
}

class R{

    public R() {
        System.out.println("Create generics.R");
    }

    @Override
    public String toString() {
        return "generics.R";
    }
}

class S<S extends Comparable<S>> implements Comparable<S>{

    private S s;

    public S(S s) {
        this.s = s;
    }

    @Override
    public int compareTo(S o) {
        Objects.requireNonNull(o);
        return s.compareTo(o);
    }
}