package anotations.example1;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by evgen on 10.01.16.
 */
public class UseCaseTracker {

    public static void check(List<Integer> tasks, Class<?> type) {
        for (Method method:type.getDeclaredMethods()){
            UseCase useCase = method.getAnnotation(UseCase.class);
            if (useCase != null){
                System.out.println("Found use case: " + useCase.id() + " " + useCase.description());
                tasks.remove(new Integer(useCase.id()));
            }
        }
        tasks.forEach(x -> System.out.println("Warning missing use case: " + x));
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        Collections.addAll(list, 47, 48, 49, 50);
        check(list, PasswordUtils.class);
    }
}
