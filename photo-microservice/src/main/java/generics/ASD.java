package generics;

/**
 * Created by evgen on 30.12.15.
 */
public class ASD {

    private Object object;

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public static void main(String[] args) {
        ASD asd = new ASD();
        asd.setObject("item");
        String object = (String)asd.getObject();
    }
}
