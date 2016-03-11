package generics.erause.obj;

/**
 * Created by evgen on 30.12.15.
 */
public class ClassFactory2 {

    public static void main(String[] args) {
        Foo<Integer> foo = new Foo<>(new IntegerFactory());
        System.out.println(foo.getT());
    }
}

interface Factory<T>{
    T create();
}

class IntegerFactory implements Factory<Integer>{
    @Override
    public Integer create() {
        return 0;
    }
}

class Foo<T>{

    private T t;

    public <F extends Factory<T>> Foo(F factory) {
        this.t = factory.create();
    }

    public T getT() {
        return t;
    }
}
