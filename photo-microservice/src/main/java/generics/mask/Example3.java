package generics.mask;

/**
 * Created by evgen on 31.12.15.
 */
public class Example3 {

    public static void main(String[] args) {
        A<? extends Fruit> a = new A<>();
        System.out.println(a.getR());
    }

    private static class Fruit {}

    private static class A<R> {

        Object r;

        A(){
            r = new Object();
        }

        @SuppressWarnings("unchecked")
        public R getR() {
            return (R)r;
        }
    }
}
