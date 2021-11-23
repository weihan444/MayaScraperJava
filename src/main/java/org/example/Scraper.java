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
    public static String Scrape(){
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("user-agent=Mozilla/5.0 (Windows Phone 10.0; Android 4.2.1; Microsoft; Lumia 640 XL LTE) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Mobile Safari/537.36 Edge/12.10166");
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 30);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {
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
            Thread.sleep(3000);
            //js.executeScript("window.scrollBy(0, 3000)");
            //WebElement backButton = driver.findElement(By.xpath("//a[contains(@class, 'sv-btn-block')]"));
        } catch(InterruptedException e){
            System.out.println(e);
        }
        return driver.getPageSource();
    }
}
