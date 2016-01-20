package conccurrency.example13;

/**
 * Created by evgen on 20.01.16.
 */
public class Pair {

    int x, y;

    Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    Pair(){
        this(0,0);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void incrementX(){
        x++;
    }

    public void incrementY(){
        y++;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public class PairsNotEqualsException extends RuntimeException{

        public PairsNotEqualsException() {
            super("Pair values not equal");
        }
    }

    public void checkValues(){
        if(x != y) throw new PairsNotEqualsException();
    }
}
