package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class JsoupHTMLParser {
    public static void Parser(String html){
        try(PrintWriter pw = new PrintWriter("output.csv")){
            File in = new File("C:/Users/Yau/Master Folder/Project Bin/Maya Scraping/" + html);
            Document doc = Jsoup.parse(in, null);
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
        } catch (IOException e){
            System.out.println(e);
        }
    }
}
