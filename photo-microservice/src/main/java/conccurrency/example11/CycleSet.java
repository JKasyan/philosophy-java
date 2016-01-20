package conccurrency.example11;

/**
 * Created by evgen on 20.01.16.
 */
public class CycleSet {

    private int[] array;
    private int len;
    private int index;

    public CycleSet(int len){
        array = new int[len];
        this.len = len;
        for(int i = 0;i<len;i++){
            array[i] = -1;
        }
    }

    public synchronized void add(int i) {
        array[index] = i;
        index = ++index % len;
    }

    public synchronized boolean contains(int a){
        for (int i=0;i< len;i++) {
            if(array[i] == a) return true;
        }
        return false;
    }
}
