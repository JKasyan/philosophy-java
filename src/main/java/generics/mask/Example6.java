package generics.mask;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by evgen on 03.01.16.
 */
public class Example6 {

    public static void main(String[] args) {
        List<? super Apple> apples = new ArrayList<>();
        apples.add(new Jonathan());
    }

    private static class Apple{}

    private static class Jonathan extends Apple{}
}
