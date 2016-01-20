package conccurrency.example10.my;

/**
 * Created by evgen on 20.01.16.
 */
public class Foo {
    volatile int i;
    void f1(){i++;}
    void f2(){i+=3;}
}

class M {
    public static void main(String[] args) {
        new Foo();
    }
}
