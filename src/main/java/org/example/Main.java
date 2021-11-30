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

        for(int i = 0; i < faculty.length; i++){
            System.out.printf("%d. %s\n", i + 1, faculty[i]);
        }

        System.out.println("Choose faculties that you wish to scrape (1 - 24) separated with space: ");
        String[] input = sc.nextLine().split(" ");
        int[] choice = new int[input.length];

        for(int i = 0; i < input.length; i++){
            choice[i] = Integer.parseInt(input[i]);
        }

        s.login(user, pass);
        for (int i : choice) {
            System.out.println("Scraping " + faculty[i-1] + " ...");
            s.scrape(faculty[i-1]);
        }
        System.out.println("Scraping Complete!");
        s.closeBrowser();
    }
}
