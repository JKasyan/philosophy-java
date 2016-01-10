package generics.mask;

/**
 * Created by evgen on 02.01.16.
 */
public class Example5 {

    public static void main(String[] args) {
        Holder<Number> number = new Holder<>(5);
        //Holder<Number> digit = number;
        Holder<? extends Number> digit = number;
        Holder<Long> longHolder = new Holder<>(5L);
        //number = longHolder;
        digit = new Holder<>(5L);
        digit = longHolder;
        //digit.setValue(5);
    }


    private static class Holder<T>{

        private T value;

        public Holder(T value) {
            this.value = value;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            return super.equals(obj);
        }
    }
}
