package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        String html = "extracted.html";
        System.setProperty("webdriver.chrome.driver", ".\\Driver\\chromedriver.exe");
        String pageSource = Scraper.Scrape();
        HTMLWriter.WriteHTML(html, pageSource);
        JsoupHTMLParser.Parser(html);



    }
}
