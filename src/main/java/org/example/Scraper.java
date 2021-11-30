package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Scraper {
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;

    public void Login(String user, String pass){
        //Initialize Driver
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 30);
        js = (JavascriptExecutor) driver;

        //Set timeouts and go to maya site
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://maya.um.edu.my/sitsvision/wrd/siw_lgn");

        //Login
        driver.findElement(By.id("MUA_CODE.DUMMY.MENSYS")).sendKeys(user);
        driver.findElement(By.id("PASSWORD.DUMMY.MENSYS")).sendKeys(pass);
        driver.findElement(By.name("BP101.DUMMY_B.MENSYS")).click();

        //Wait for timetable to load then go to maya timetable
        WebElement timetable = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/div/div/div/div/div/div/a/div/div[1]")));
        timetable.click();
        driver.findElement(By.xpath("//body/div[2]/div[2]/center/div/div/div[4]/a")).click();
    }

    public void Scrape(String faculty){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        //Get a list of input field and key in year, semester and faculty and search
        List<WebElement> inputField = driver.findElements(By.xpath("//div[contains(@tabindex, '-1')]"));
        inputField.get(0).click();
        driver.findElement(By.xpath("//div/input[contains(@aria-label,'Year')]")).sendKeys("2021/2022" + Keys.ENTER);
        inputField.get(1).click();
        WebElement slot = driver.findElement(By.xpath("//div/input[contains(@aria-label,'Slot')]"));
        slot.sendKeys("SEMESTER 1");
        for (int i = 0; i < 9; i++) {
            slot.sendKeys(Keys.ARROW_DOWN);
        }
        slot.sendKeys(Keys.ENTER);
        inputField.get(2).click();
        driver.findElement(By.xpath("//div/input[contains(@aria-label,'Faculty')]")).sendKeys(faculty + Keys.ENTER);
        WebElement searchButton = driver.findElement(By.name("BP103.DUMMY_B.MENSYS.1"));
        searchButton.click();

        //Wait until the timetable is loaded
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'Teaching Timetable')]")));

        //Start Scraping
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        do{
            JsoupHTMLParser.Parser(driver.getPageSource(), faculty);
            js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.linkText("Next")));
            driver.findElement(By.linkText("Next")).click();
        } while(driver.findElements(By.className("sv-disabled")).isEmpty());

        JsoupHTMLParser.Parser(driver.getPageSource(), faculty);

        driver.findElement(By.linkText("Back")).click();
    }
}
