package generics.selfbounded;

/**
 * Created by evgen on 09.01.16.
 */
public class BasicHolder<T> {

    private T element;

    public void set(T t){
        this.element = t;
    }

    public T get(){
        return element;
    }

    public void f(){
        System.out.println(element.getClass().getSimpleName());
    }
}
