package programs;

import java.io.FileReader;
import java.io.IOException;

public class Practice {
    static FileReader reader = null;
    public static void main(String[] args) {
        try {
            reader = new FileReader("file.txt");
            var value = reader.read(); // read a single value from the file
        } catch (IOException e) {
            System.out.println("Could not read data.");
        } finally {
            if ( reader != null){
                try{
                    reader.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
}