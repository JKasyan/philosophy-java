package generics.mask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by evgen on 02.01.16.
 */
public class Example4 {

    public static void main(String[] args) {
        List<? extends Animal> animals = Arrays.asList(new Cat());
        Cat dog = (Cat)animals.get(0);
        animals.contains(new Cat());
        animals.indexOf(new Dog());
        /**
         *
         */
        ArrayList<? extends Animal> list = new ArrayList<>();
        //list.add((Animal)new Cat());
        //list.addAll(Arrays.asList(new Animal()));
        /**
         *
         */
        ArrayList<Cat> cats = new ArrayList<>();
        //ArrayList<Animal> list1 = cats;
        ArrayList<? extends Animal> list1 = cats;
    }

    static class Animal{
        private Animal(){}
    }

    static class Cat extends Animal{}

    static class Dog extends Animal{}
}
