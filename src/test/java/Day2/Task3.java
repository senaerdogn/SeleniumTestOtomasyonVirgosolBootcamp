package Day2;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Task3 {
    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/nestedframes");
    }

    @AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }

    @Test
    public void task3() throws InterruptedException{
        //“Nested Frames” başlık kontrol edilir (<h1>)
        String expectedText = "Nested Frames";
        String actualText = driver.findElement(By.xpath("//h1[@class='text-center']")).getText();
        Assert.assertEquals(actualText, expectedText);

        //sayfayı aşağı kaydırdık
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        for (int i = 0; i < 1; i++) {
            jse.executeScript("window.scrollBy(0,250)");
        }

        //“Parent frame” yazısı kontrol edilir (büyük frame içinde)
        driver.switchTo().frame(driver.findElement(By.id("frame1")));
        String expectedResult = "Parent frame";
        String actualResult = driver.findElement(By.xpath("//body[text()='Parent frame']")).getText();
        Assert.assertEquals(actualResult, expectedResult);

        //“Child Iframe” yazısı kontrol edilir (Büyük frame içindeki frame içinde)
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@srcdoc='<p>Child Iframe</p>']")));
        String expectedResult2 = "Child Iframe";
        String actualResult2 = driver.findElement(By.xpath("//p[text()='Child Iframe']")).getText();
        Assert.assertEquals(actualResult2, expectedResult2);

        driver.switchTo().parentFrame();
        driver.switchTo().parentFrame();

        //Sayfada “Sample Nested Iframe page” texti kontrol edilir
        String expectedResult3 = "Sample Nested Iframe page";
        String result = driver.findElement(By.xpath("//div[@id='framesWrapper']//div[1]")).getText();
        String [] array = result.split("\\.");
        String actualResult3 = array[0];
        Assert.assertEquals(actualResult3,expectedResult3);

        Thread.sleep(2000);
    }
}

/*Frames2
    https://demoqa.com/nestedframes Sayfasına gidilir
    “Nested Frames” başlık kontrol edilir (<h1>)
    “Parent frame” yazısı kontrol edilir (büyük frame içinde)
    “Child Iframe” yazısı kontrol edilir (Büyük frame içindeki frame içinde)
    Sayfada “Sample Nested Iframe page.” texti kontrol edilir*/