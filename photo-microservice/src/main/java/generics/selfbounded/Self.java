package generics.selfbounded;

/**
 * Created by evgen on 09.01.16.
 */
public class Self<T extends Self<T>> {

    public static void main(String[] args) {
        Self self = new Self();

    }
}
