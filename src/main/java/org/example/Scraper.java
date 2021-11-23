package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Scraper {
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;

    public void Login(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 30);
        js = (JavascriptExecutor) driver;

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://maya.um.edu.my/sitsvision/wrd/siw_lgn");

        driver.findElement(By.id("MUA_CODE.DUMMY.MENSYS")).sendKeys("U2102749");
        driver.findElement(By.id("PASSWORD.DUMMY.MENSYS")).sendKeys("f6b5a33106UM4322423");
        driver.findElement(By.name("BP101.DUMMY_B.MENSYS")).click();
        WebElement timetable = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/div/div/div/div/div/div/a/div/div[1]")));
        timetable.click();
        driver.findElement(By.xpath("//body/div[2]/div[2]/center/div/div/div[4]/a")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    public void Scrape(){
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
        driver.findElement(By.xpath("//div/input[contains(@aria-label,'Faculty')]")).sendKeys("FACULTY OF COMPUTER SCIENCE AND INFORMATION TECHNOLOGY"+ Keys.ENTER);
        WebElement searchButton = driver.findElement(By.name("BP103.DUMMY_B.MENSYS.1"));
        searchButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'Teaching Timetable')]")));
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        do{
            JsoupHTMLParser.Parser(driver.getPageSource());
            js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.linkText("Next")));
            driver.findElement(By.linkText("Next")).click();
        } while(driver.findElements(By.className("sv-disabled")).isEmpty());
        JsoupHTMLParser.Parser(driver.getPageSource());

        driver.findElement(By.linkText("Back")).click();
    }
}
