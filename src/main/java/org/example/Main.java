package org.example;

import java.io.IOException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        Scraper s = new Scraper();
        System.setProperty("webdriver.chrome.driver", ".\\Driver\\chromedriver.exe");

        String[] faculty = {
                "FACULTY OF ARTS AND SOCIAL SCIENCES",
                "FACULTY OF BUSINESS & ECONOMICS",
                "FACULTY OF BUILT ENVIRONMENT",
                "FACULTY OF BUSINESS AND ACCOUNTANCY",
                "FACULTY OF DENTISTRY",
                "FACULTY OF ECONOMICS AND ADMINISTRATION",
                "CENTRE FOR FOUNDATION STUDIES IN SCIENCE",
                "UNIVERSITY",
                "INSTITUTE FOR ADVANCED STUDIES",
                "ACADEMY OF ISLAMIC STUDIES",
                "ACADEMY OF MALAY STUDIES",
                "FACULTY OF ENGINEERING",
                "FACULTY OF LAW",
                "FACULTY OF MEDICINE",
                "FACULTY OF PHARMACY",
                "FACULTY OF EDUCATION",
                "ASIA EUROPE INSTITUTE",
                "FACULTY OF CREATIVE ARTS",
                "FACULTY OF SCIENCE",
                "FACULTY OF LANGUAGES AND LINGUISTICS",
                "LIBRARY",
                "CENTRE FOR SPORT & EXERCISE SCIENCES",
                "FACULTY OF COMPUTER SCIENCE AND INFORMATION TECHNOLOGY",
                "INTERNATIONAL INSTITUTE OF PUBLIC POLICY AND MANAGEMENT"
        };

        System.out.print("Enter Username: ");
        String user = sc.nextLine();
        System.out.print("Enter Password: ");
        String pass = sc.nextLine();

        s.Login(user, pass);
        for (String fac: faculty) {
            s.Scrape(fac);
        }
    }
}
