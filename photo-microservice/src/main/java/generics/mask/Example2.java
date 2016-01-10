package generics.mask;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by evgen on 31.12.15.
 */
public class Example2 {

    public static void main(String[] args) {
        List<? extends Fruit> list ;
        list = new ArrayList<Apple>();
        List<Orange> oranges = new ArrayList<>();
        oranges.add(new Orange());
        list = oranges;
        System.out.println(list);
        /**
         *
         */
        List<? extends Fruit> fruits = new ArrayList<>();
    }

    static class Fruit {}

    static class Apple  extends Fruit {}

    static class Orange extends Fruit {}
}
