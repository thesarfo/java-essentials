Java provides classes for data streaming, in two forms....the byte stream and the character stream

Input stream means reading from a resource to a program
Output stream means writing from a program to a resource

1. Byte Stream - 1 byte
- InputStream
- OutputStream

2. Character Stream - 2 bytes
- Reader
- Writer


## Input Stream
Methods
1. int read(): It reads just one byte of data from the input stream. It consumes all the data from the input stream into the program. Once the stream is out of bytes, it will return -1.
2. int read(byte[] b): It reads up to b.length bytes of data from the input stream . Lets say b is a byte array of 5 chats. if we call the read method on it, it will only read 5 bytes/chars from the input stream
3. int read(byte[] b, int off, int len): it works like the above method, just that you can specify the exact position you want to start reading from(offset).
4. int available(): It returns the number of bytes in the input stream. Before reading, you can use this method to find out how many bytes area available.
5. long skip(long n): It is used to skip over and discards n bytes of data from the input stream.
6. void mark(int limit): if you don't want the bytes to be removed from the stream, after you have read them, you can put a mark on them. and continue reading the bytes. the limit is simply how long you want the mark to be valid
7. void reset(): If you want to return to the starting point of the stream(where you marked), you can reset it, and it will come back
8. boolean markSupported(): every input stream does not support mark. It is only possible if the stream is a buffered stream(has a buffer). So before putting a mark, you can call this method on it, and it'll return either true or false.
9. void close(): after using a stream, you have to close the stream. after you're done with what you're doing, you call the close() method.

## Output Stream
Methods
1. void write(int b): It writes the specified byte(just 1) onto the output stream
2. void write(byte[] b): It writes b.length bytes from the byte array to the output stream.
3. void write(byte[] ary, int off, int len): it works like the above method, just that you can specify the exact position you want to start write from(offset)
4. void flash(): This only works on a buffered output stream. it forces the data to move from the buffer onto the output stream/resource
5. void close(): Once you've finished using the output stream, you close it.


## Writing Examples

For instance, we want to create a file and then write some string of text into it. see below
```java
public class FileExample {
    public static void main(String[] args){
        try {
            FileOutputStream fos = new FileOutputStream("C:/Users/ErnestSarfo/Desktop/workflow/java-essentials/abdul_bari/Test.txt");
            String str = "Learn Java programming";
            fos.write(str.getBytes()); // we convert the string into an array of bytes
            fos.close();
        } catch (IOException e) {
            throw new RuntimeException(e); // handle any errors
        }
    }
}
```
You can also do it one byte at a time, but with a loop.
```java
public class FileExample {
    public static void main(String[] args){
        try {
            FileOutputStream fos = new FileOutputStream("C:/Users/ErnestSarfo/Desktop/workflow/java-essentials/abdul_bari/Test.txt");
            String str = "Learn Java programming";

            byte b[] = str.getBytes();

            for (byte x : b)
                fos.write(str.getBytes());

            fos.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
```

Also using an offset
```java
public class FileExample {
    public static void main(String[] args){
        try {
            FileOutputStream fos = new FileOutputStream("C:/Users/ErnestSarfo/Desktop/workflow/java-essentials/abdul_bari/Test.txt");
            String str = "Learn Java programming";

            byte b[] = str.getBytes();

            fos.write(b, 6, str.length() - 6);

            fos.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
```

## Reading examples
```java
public class FileExample {
    public static void main(String[] args){
        try {
            FileInputStream fis = new FileInputStream("C:/Users/ErnestSarfo/Desktop/workflow/java-essentials/abdul_bari/Test.txt");

            byte b[] = new byte[fis.available()]; // we create an array of bytes containing the length the input stream(fis.available())

            fis.read(b); // we read from the file(1 by 1)

            String str = new String(b); // create a string of bytes
            System.out.println(str);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
```
Using a do while loop. i.e one by one
```java
public class FileExample {
    public static void main(String[] args){
        try {
            FileInputStream fis = new FileInputStream("C:/Users/ErnestSarfo/Desktop/workflow/java-essentials/abdul_bari/Test.txt");

            int x;

            do{
                x = fis.read(); 
                if (x != -1)
                    System.out.print((char)x); // print each byte if it hasn't reached the end of the file
            } while (x != -1);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
```

Using a while loop
```java
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
```

### Reading from a file and copying the contents into another file
Assuming you have a file called "source1.txt" which contains the text "JAVA TEST FILE" and I want to copy the contents of that file into a file called "source2.txt", but i want the contents being copied into the second file to be in lowercase. How would i do that in Java

```java
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class SC101 {
    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("source1.txt");
        FileOutputStream fos = new FileOutputStream("source2.txt");
        
        int b;
        while ((b=fis.read()) != -1){
            if (b>=65 && b<=120) fos.write(b+32);
            else fos.write(b);
            
            fis.close();
            fos.close();
        }
    }
}
```

**This code copies a file ("source1.txt") to a new file ("source2.txt"), converting all lowercase letters to uppercase during the process.**

* **Behind the scenes:** It reads the file one byte at a time. Each byte represents a character (letter, symbol, etc.).
* **Case conversion:** If the byte corresponds to a lowercase letter (a-z in ASCII), it adds 32 to the value, effectively converting it to uppercase (A-Z).
* **Preserving other characters:** Numbers, symbols, and uppercase letters are copied directly without any change.
* **Writing the new file:** The converted bytes (uppercase or unchanged) are written to the new file "source2.txt".

When you're using fileinputstream, the file must already be existing, otherwise you will get a filenotfound exception. But with fileoutputstream, if the file doesn't exist, it will create the file.


### Challenge: Now copy the contents of both "source1" and "source2" into a file called "Destination.txt"

```java
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.SequenceInputStream;

public class SC102 {
    public static void main(String[] args) throws Exception {
        FileInputStream fis1 = new FileInputStream("Source1.txt");
        FileInputStream fis2 = new FileOutputStream("Source2.txt");

        FileOutputStream fos = new FileOutputStream("Destination.txt");

        SequenceInputStream sis = new SequenceInputStream(fis1, fis2);
        
        int b;
        while ((b = sis.read()) != -1){
            fos.write(b);
        }
        
        sis.close();
        fis1.close();
        fis2.close();
        fos.close();
    }
}
```

## Reading from an array
So far, we've learnt how to read data if they are in files, but what if they are in an array. The ByteArrayInputStream allows us to do that. see below
```java
class HelloWorld {
    public static void main(String[] args) throws Exception {
        byte b[] = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v'};
        
        ByteArrayInputStream bis = new ByteArrayInputStream(b);
        
        int x;
        
        while ((x = bis.read()) != -1){
            System.out.println((char)x);
        }
        bis.close();
    }
}
```

Now the above code actually reads the bytes 1 by 1. What if we want to read them all together. The .readAllBytes() method allows us to do that. see below
```java
class HelloWorld {
    public static void main(String[] args) throws Exception {
        byte b[] = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v'};
        
        ByteArrayInputStream bis = new ByteArrayInputStream(b);
        
        String str = new String(bis.readAllBytes());

        System.out.println(str);
        
        bis.close();
    }
}
```

You can also write onto a byte array
```java
class HelloWorld {
    public static void main(String[] args) throws Exception {

        ByteArrayOutputStream bos = new ByteArrayOutputStream(b);

        bos.write('a');
        bos.write('b');
        bos.write('c');
        bos.write('d');
        
        byte b[] = bos.toByteArray();
        
        for (byte x : b){
            System.out.println((char) x);
        }
        
        bos.close();
    }
}
```












