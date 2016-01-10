package generics.mask;

/**
 * Created by evgen on 30.12.15.
 */
public class Example1 {

    public static void main(String[] args) {
        Fruit[] fruits = new Apple[5];
        fruits[0] = new Apple();
        //fruits[1] = new Fruit();
        fruits = new Orange[5];
    }

    static class Fruit {}

    static class Apple  extends Fruit {}

    static class Orange extends Fruit {}
}

