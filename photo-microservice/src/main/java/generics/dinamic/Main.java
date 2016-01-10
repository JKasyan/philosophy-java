package generics.dinamic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by evgen on 10.01.16.
 */
public class Main {

    @SuppressWarnings("unchecked")
    static void oldStyleMethod(List list) {
        list.add(new Cat());
    }

    public static void main(String[] args) {
        List<Dog> dogs = new ArrayList<>();
        oldStyleMethod(dogs);
        Class<Dog> type = Dog.class;
        List<Dog> dogs1 = Collections.checkedList(new ArrayList<>(), type);
        oldStyleMethod(dogs1);
    }
}

class Cat{}

class Dog{}
