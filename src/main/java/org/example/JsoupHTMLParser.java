package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.*;
import java.nio.Buffer;

public class JsoupHTMLParser {
    public static void parser(String html, String fileName){
        try{
            File output = new File("./" + fileName + ".csv");
            FileWriter fw = new FileWriter(fileName + ".csv", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            if (output.length() == 0){
                pw.println("Module,Occurrence,Mode,Date/Time,Tutorial,Target");
            }

            Document doc = Jsoup.parse(html);
            Elements rows = doc.select("tr");
            Elements module = rows.select("td:eq(0)");
            Elements occ = rows.select("td:eq(1)");
            Elements mode = rows.select("td:eq(2)");
            Elements time = rows.select("td:eq(4)");
            Elements tutor = rows.select("td:eq(5)");
            Elements target = rows.select("td:eq(7)");
            StringBuilder sb = new StringBuilder();

            for(int i = 0; i < module.size(); i++){
                sb.setLength(0);
                sb.append(module.get(i).text());
                sb.append(",");
                sb.append(occ.get(i+4).text());
                sb.append(",");
                sb.append(mode.get(i).text());
                sb.append(",");
                sb.append(time.get(i).text());
                sb.append(",");
                sb.append(tutor.get(i).text());
                sb.append(",");
                sb.append(target.get(i).text());
                sb.append("\n");
                pw.print(sb);
            }
            pw.close();
        } catch (IOException e){
            System.out.println(e);
        }
    }
}
