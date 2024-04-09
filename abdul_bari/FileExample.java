package abdul_bari;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class FileExample {
    public static void main(String[] args){
        try {
            FileInputStream fis = new FileInputStream("C:/Users/ErnestSarfo/Desktop/workflow/java-essentials/abdul_bari/Test.txt");

            int x;

            while((x = fis.read()) != -1){
                System.out.println((char)x);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

//public class FileExample {
//    public static void main(String[] args){
//        try {
//            FileOutputStream fos = new FileOutputStream("C:/Users/ErnestSarfo/Desktop/workflow/java-essentials/abdul_bari/Test.txt");
//            String str = "Learn Java programming";
//
//            byte b[] = str.getBytes();
//
//            for (byte x : b)
//                fos.write(str.getBytes());
//
//            fos.close();
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
//public class FileExample {
//    public static void main(String[] args){
//        try {
//            FileOutputStream fos = new FileOutputStream("C:/Users/ErnestSarfo/Desktop/workflow/java-essentials/abdul_bari/Test.txt");
//            String str = "Learn Java programming";
//            fos.write(str.getBytes());
//            fos.close();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}

