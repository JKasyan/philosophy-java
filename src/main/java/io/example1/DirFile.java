package io.example1;

import java.io.File;
import java.util.Arrays;

/**
 * Created by evgen on 10.03.16.
 */
public class DirFile {

    public static void main(String[] args) {
        File file = new File(".");
        String[] list = file.list();
        Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
        for(String s:list) {
            System.out.println(s);
        }
    }
}
