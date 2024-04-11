package abdul_bari;

import java.io.*;


class RandomFileAccess{
    public static void main(String[] args) throws Exception{
        RandomAccessFile rf = new RandomAccessFile("C:/FilePath/file.txt","rw"); // open the file in read write mode
        // byte b[] = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'}; let's assume this is what the file contains

        System.out.println((char)rf.read());
        System.out.println((char)rf.read());
        System.out.println((char)rf.read());
        rf.write('d'); // write onto the file
        System.out.println((char)rf.read());
        rf.skipBytes(3); // skip number of bytes
        System.out.println((char)rf.read());
        rf.seek(3);
        System.out.println((char)rf.read());
        System.out.println(rf.getFilePointer());
        rf.seek(rf.getFilePointer() + 2);
    }
}