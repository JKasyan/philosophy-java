package generics;

/**
 * Created by evgen on 30.12.15.
 */
public class Erasure {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        Derived2 d = new Derived2();
    }
}

class Generic<T>{

    private T element;

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }
}

class Derived1<T> extends Generic {}

class Derived2 extends Generic{}

//class Derived3 extends generics.Generic<?>{}

