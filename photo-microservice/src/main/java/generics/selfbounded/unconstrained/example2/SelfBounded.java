package generics.selfbounded.unconstrained.example2;

/**
 * Created by evgen on 10.01.16.
 */
public class SelfBounded<T extends SelfBounded<T>> {

    private T element;

    public SelfBounded<T> set(T element){
        this.element = element;
        return this;
    }

    public T get(){
        return element;
    }

}

class A extends SelfBounded<A>{}

class B extends SelfBounded<A>{}

class C extends SelfBounded<C>{

    C setAndGet(C element){
        set(element);
        return get();
    }
}

class F extends SelfBounded {}


