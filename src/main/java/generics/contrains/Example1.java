package generics.contrains;

/**
 * Created by evgen on 30.12.15.
 */
public class Example1 {

    public static void main(String[] args) {

    }
}

interface Color{
    java.awt.Color getColor();
}

class Colored<R extends Color>{

    R item;

    public Colored(R item) {
        this.item = item;
    }

    public java.awt.Color color(){
        return item.getColor();
    }
}

class Dimension{
    int x, y, z;
}

class ColoredDimension<F extends Dimension & Color>{

    F f;

    public ColoredDimension(F f) {
        this.f = f;
    }

    int getX(){
        return f.x;
    }
}