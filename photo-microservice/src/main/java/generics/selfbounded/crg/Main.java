package generics.selfbounded.crg;

/**
 * Created by evgen on 09.01.16.
 */
public class Main {

    public static void main(String[] args) {
        SubType st1 = new SubType(), st2 = new SubType();
        st1.set(st2);
        SubType st3 = st1.get();
        st1.f();
    }
}
