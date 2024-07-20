package run;

import java.util.ArrayList;

public class CharaterCounter {

    public static void main(String[] args) {
        String x = "abc";
        String y = "dasdfasasdasabcwerwerwererw,dasdasdabcdasdasabc";
        int count = 0;
        for (int i = 0; i <= y.length()-3; i++) {
            if(y.substring(i, i+3).equals(x)){
                count++;
            }
        }

        System.out.println(count);
    }
}
