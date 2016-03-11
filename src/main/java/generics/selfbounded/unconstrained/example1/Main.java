package generics.selfbounded.unconstrained.example1;

/**
 * Created by evgen on 09.01.16.
 */
class Main {

    public static void main(String[] args) {
        BasicOther b1 = new BasicOther();
        b1.set(new Other());
        b1.f();
    }
}
