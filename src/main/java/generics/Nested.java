package generics;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by evgen on 29.12.15.
 */
public class Nested {

    public static void main(String[] args) {
        Holder<A.B> holder = new Holder<>(new C());
        A.B t = holder.getT();
        System.out.println(holder.metaData());
        System.out.println(getMap());
        f(getMap());
    }

    public static  <K,V> Map<K,V> getMap(){
        return new HashMap<>();
    }

    static void f(Map<Integer, Integer> map){}

}

class Holder<T>{

    private T t;

    public Holder(T t){
        this.t = t;
    }

    public T getT() {
        return t;
    }

    public Class<?> metaData(){
        return t.getClass();
    }
}

class A {
    class B {
    }
}

class C extends A.B {

    static A a = new A();

    public C() {
        a.super();
    }

    public void a() {
        {
            final class D {
            }
        }
    }
}