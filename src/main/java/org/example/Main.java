package org.example;

import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.setProperty("webdriver.chrome.driver", ".\\Driver\\chromedriver.exe");
        Scraper s = new Scraper();
        s.Login();
        s.Scrape();
    }
}
