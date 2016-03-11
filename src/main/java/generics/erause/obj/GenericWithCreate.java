package generics.erause.obj;

/**
 * Created by evgen on 30.12.15.
 */
abstract class GenericWithCreate<T> {

    final T t;

    public GenericWithCreate() {
        this.t = create();
    }

    abstract T create();

    public static void main(String[] args) {
        Creator creator = new Creator();
        creator.f();

    }
}

class X{}

class Creator extends GenericWithCreate<X>{

    @Override
    X create() {
        return new X();
    }

    void f(){
        System.out.println(t.getClass().getSimpleName());
    }
}
