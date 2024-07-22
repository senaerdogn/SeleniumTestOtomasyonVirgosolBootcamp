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
import java.util.List;
import java.util.Set;

public class Task4 {
    WebDriver driver;
    JavascriptExecutor jse;

    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/browser-windows");
    }

    @AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }

    @Test
    public void task4() throws InterruptedException{
        //“Browser Windows” başlık kontrol edilir (<h1>)
        String expectedText = "Browser Windows";
        String actualText = driver.findElement(By.xpath("//h1[@class='text-center']")).getText();
        Assert.assertEquals(actualText, expectedText);

        //“New Tab” butonuna tıklanır
        driver.findElement(By.id("tabButton")).click();

        //Açılan sayfanın Url’si kontrol edilir (https://demoqa.com/sample )
        String expectedUrl = "https://demoqa.com/sample";
        Set<String> windowHandlesSet = driver.getWindowHandles();
        List<String> windowHandlesList = new ArrayList<>(windowHandlesSet);
        String mainWindow = windowHandlesList.get(0);
        String newWindow = windowHandlesList.get(1);
        String actualUrl = driver.switchTo().window(newWindow).getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);

        //Açılan sayfadaki “This is a sample page” yazı kontrol edilir
        String expectedResult = "This is a sample page";
        String actualResult = driver.findElement(By.id("sampleHeading")).getText();
        Assert.assertEquals(actualResult, expectedResult);

        //Ana sayfaya geçilip “New Window” butonuna tıklanır
        driver.switchTo().window(mainWindow);
        WebElement element = driver.findElement(By.id("windowButton"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);

        //Açılan sayfadaki “This is a sample page” yazı kontrol edilir
        windowHandlesSet = driver.getWindowHandles();
        windowHandlesList = new ArrayList<>(windowHandlesSet);
        String newWindowHandle = windowHandlesList.get(2);
        driver.switchTo().window(newWindowHandle);
        String expectedRes = "This is a sample page";
        String actualRes = driver.findElement(By.id("sampleHeading")).getText();

        Thread.sleep(3000);

    }
}

/*Window Handles
    https://demoqa.com/browser-windows sayfasına gidilir
    “Browser Windows” başlık kontrol edilir (<h1>)
    “New Tab” butonuna tıklanır
    Açılan sayfanın Url’si kontrol edilir (https://demoqa.com/sample )
    Açılan sayfadaki “This is a sample page” yazı kontrol edilir
    Ana sayfaya geçilip “New Window” butonuna tıklanır
    Açılan sayfadaki “This is a sample page” yazı kontrol edilir
    Açılan sayfa kapatılır

 */

/*Burada bazen ilgili "New Tab" butonuna "driver.findElement(locator).click()" ile tıklanamıyor ve "ElementClickInterceptedException" hatası fırlatılıyor.
Böyle durumlarda "JavascriptExecutor" interface'ine ait olan "executeScript()" metodunu kullanarak elemente tıklama işlemi yapabiliriz. */