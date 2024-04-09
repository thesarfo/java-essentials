package abdul_bari;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;

public class BufferedDemo {
    public static void main(String[] args) throws Exception{
//        FileInputStream fis = new FileInputStream("C:/Users/ErnestSarfo/Desktop/workflow/java-essentials/abdul_bari/Test.txt");
//        BufferedInputStream bis = new BufferedInputStream(fis);
//
//        System.out.print((char) bis.read());
//        System.out.print((char) bis.read());
//        System.out.print((char) bis.read());
//
//        bis.mark(10);
//
//        System.out.print((char) bis.read());
//        System.out.print((char) bis.read());
//        bis.reset();
//
//        System.out.print((char) bis.read());
//        System.out.print((char) bis.read());

//        int x;
//
//        while((x = bis.read()) != -1) {
//            System.out.println((char) x);
//        }

        FileReader fis = new FileReader("C:/Users/ErnestSarfo/Desktop/workflow/java-essentials/abdul_bari/Test.txt");
        BufferedReader bis = new BufferedReader(fis);

        System.out.print((char) bis.read());
        System.out.print((char) bis.read());
        System.out.print((char) bis.read());

        bis.mark(10);

        System.out.print((char) bis.read());
        System.out.print((char) bis.read());
        bis.reset();

        System.out.print((char) bis.read());
        System.out.print((char) bis.read());


    }
}
