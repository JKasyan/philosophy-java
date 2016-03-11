package conccurrency.example7;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

/**
 * Created by evgen on 13.01.16.
 */
public class FileSaver implements Runnable{

    private static volatile int ID;

    @Override
    public void run() {
        File file = new File("/home/evgen/test_threads/file_" + ++ID + ".txt");
        System.out.println("create file with id: " + ID);
        PrintWriter pw;
        try {
            pw = new PrintWriter(file);
            pw.write("Line: " + ID + "\n");
            try {
                for (int i = 0; i < 100; i++) {
                    pw.write(i + " Thread: " + Thread.currentThread().getName() + "\n");
                    TimeUnit.MILLISECONDS.sleep(100);
                    if (i % 10 == 0) {
                        System.out.println(i + "Thread: " + Thread.currentThread().getName());
                    }
                    Thread.yield();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
