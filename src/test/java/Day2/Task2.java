package Day2;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class Task2 {
    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/frames");
    }

    @AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }

    @Test
    public void task2() throws InterruptedException{
        //Sayfadaki “Frames” yazısı kontrol edilir (<h1>)
        String actualResult = driver.findElement(By.xpath("//h1[@class='text-center']")).getText();
        String expectedResult = "Frames";
        Assert.assertEquals(actualResult, expectedResult);

        //sayfayı aşağı kaydırdık
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        for (int i = 0; i < 2; i++) {
            jse.executeScript("window.scrollBy(0,250)");
        }

        //“This is a sample page” yazısı kontrol edilir (Büyük frame içinde)
        String expectedResult2 = "This is a sample page";
        driver.switchTo().frame(driver.findElement(By.id("frame1"))); //frame'in içine girdik
        WebElement element = driver.findElement(By.id("sampleHeading"));
        String actualResult2 = element.getText();
        Assert.assertEquals(actualResult2, expectedResult2);

        driver.switchTo().parentFrame(); //ana frame'e geçtik

        //“This is a sample page” yazısı kontrol edilir (küçük frame içinde)
        String expectedResult3 = "This is a sample page";
        driver.switchTo().frame(driver.findElement(By.id("frame2"))); //frame'in içine girdik
        WebElement element1 = driver.findElement(By.id("sampleHeading"));
        String actualResult3 = element1.getText();
        Assert.assertEquals(actualResult3, expectedResult3);

        driver.switchTo().parentFrame(); //ana frame'e geçtik

        //Sayfada “Sample Iframe page There are 2 Iframes in this page” texti kontrol edilir
        String expectedResult4 = "Sample Iframe page There are 2 Iframes in this page";
        String result = driver.findElement(By.xpath("//div[@id='framesWrapper']//div[1]")).getText();
        String [] array = result.split("\\.");
        String actualResult4 = array[0];
        Assert.assertEquals(actualResult4,expectedResult4);
    }
}

/*Frames1
    https://demoqa.com/frames sayfasına gidilir
    Sayfadaki “Frames” yazısı kontrol edilir (<h1>)
    “This is a sample page” yazısı kontrol edilir (Büyük frame içinde)
    “This is a sample page” yazısı kontrol edilir (küçük frame içinde)
    Sayfada “Sample Iframe page There are 2 Iframes in this page” texti kontrol edilir*/