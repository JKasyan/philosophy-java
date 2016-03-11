package generics.erause.obj;

/**
 * Created by evgen on 30.12.15.
 */
public class ClassAsFactory<T> {

    T t;

    public ClassAsFactory(Class<T> type) {
        try {
            this.t = type.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    static class Employee{}


    public static void main(String[] args) {
        ClassAsFactory<Employee> factory = new ClassAsFactory<>(Employee.class);
        System.out.println(factory.t);
    }
}
