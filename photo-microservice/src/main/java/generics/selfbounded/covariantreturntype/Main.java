package generics.selfbounded.covariantreturntype;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by evgen on 10.01.16.
 */
public class Main {

    public static void main(String[] args) {
        List list = new ArrayList<>();
        Class<Integer> type = Integer.class;
        List<List> lists = Collections.checkedList(list, Integer.class);
    }
}

class Base{}

class Derived extends Base{}

interface OrdinaryGetter {
    Base get();
}

interface DerivedGetter extends OrdinaryGetter {
    @Override
    Derived get();
}


interface GenericGetter<T extends GenericGetter<T>> {
    T get();
}

interface Getter extends GenericGetter<Getter>{}

class GenericAndReturnTypes {
    void test(Getter getter){
        Getter result = getter.get();
        GenericGetter gg = getter.get();
    }

    void testWithoutGeneric(DerivedGetter derivedGetter){
        Derived d1 = derivedGetter.get();
        Base d2 = derivedGetter.get();
    }
}

class OrdinarySetter {
    void set(Base base){
        System.out.println("OrdinarySetter.set(base)");
    }
}

class DerivedSetter extends OrdinarySetter {
    void set(Derived derived){
        System.out.println("DerivedSetter.set(derived)");
    }
}
