package generics.mask;

/**
 * Created by evgen on 05.01.16.
 */
public class Example8 {

    static void rawArgs(Holder holder, Object obj){
        holder.setT(obj);
        holder.setT(new WildCard());
        Object t = holder.getT();
    }

    static void unboundedArgs(Holder<?> holder, Object obj){
        //holder.setT(obj);
    }

    static <T> T exact1(Holder<T> holder){
        T t  = holder.getT();
        return t;
    }

    static <T> T exact2(Holder<T> holder,T arg){
        holder.setT(arg);
        T t  = holder.getT();
        return t;
    }

    static <T> T wildSubType(Holder<T> holder, T arg){
        return null;
    }

    private static class WildCard{}
}
