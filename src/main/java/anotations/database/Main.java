package anotations.database;

/**
 * Created by evgen on 11.01.16.
 */
public class Main {

    public static void main(String[] args) {

        SubHolder s1 = new SubHolder();
        SubHolder s2 = new SubHolder();
        s1.setElement(s2);
        System.out.println(0xABE);
        System.out.println(2750 * 2750);
        float a = 0xAB_EP2F;
        System.out.println(a);
        Byte[] Byte[] = {{0}};
        System.out.println(20 * 365);
        int[] g = {9,2,};
        System.out.println(g.length);
    }

    static class Holder<T extends Holder<T>>{

        private T element;

        public T getElement() {
            return element;
        }

        public void setElement(T element) {
            this.element = element;
        }
    }

    static class SubHolder extends Holder<SubHolder>{}
}

