package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class HTMLWriter {
    public static void WriteHTML(String filename, String content){
        try{
            FileWriter writer = new FileWriter(filename);
            BufferedWriter bw = new BufferedWriter(writer);
            bw.write(content);
            bw.close();
        } catch(IOException e) {
            System.out.println(e);
        }


    }
}
