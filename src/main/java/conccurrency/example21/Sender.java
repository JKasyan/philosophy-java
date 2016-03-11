package conccurrency.example21;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by evgen on 05.03.16.
 */
public class Sender implements Runnable {

    private final PipedWriter out = new PipedWriter();

    public PipedWriter getOut() {
        return out;
    }

    @Override
    public void run() {
        try {
            while (true) {
                for(char c = 'A';c < 'z'; c++) {
                    out.write(c);
                    TimeUnit.MILLISECONDS.sleep(500);
                }
            }
        } catch (IOException e) {
            System.out.println("IO exception");
        } catch (InterruptedException e) {
            System.out.println("Sender interrupted");
        }
    }
}

class Receiver implements Runnable {

    private final PipedReader in;

    public Receiver(Sender sender) throws IOException {
        this.in = new PipedReader(sender.getOut());
    }

    @Override
    public void run() {
        try {
            while (true) {
                System.out.print("Read: " + (char) in.read() + ", ");
            }
        }catch (IOException e) {
                System.out.println("IO exception");
            }
    }
}

class Main {

    public static void main(String[] args) throws Exception {
        Sender sender = new Sender();
        Receiver receiver = new Receiver(sender);
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(sender);
        service.execute(receiver);
        TimeUnit.SECONDS.sleep(5);
        service.shutdownNow();
    }
}
