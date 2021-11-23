package org.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.setProperty("webdriver.chrome.driver", ".\\Driver\\chromedriver.exe");
        String[] faculty = {"FACULTY OF COMPUTER SCIENCE AND INFORMATION TECHNOLOGY", "FACULTY OF LANGUAGES AND LINGUISTICS", "UNIVERSITY"};
        Scraper s = new Scraper();
        s.Login();
        for (String fac: faculty) {
            s.Scrape(fac);
        }
    }
}
