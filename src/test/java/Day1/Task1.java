package Day1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Task1 {
    @Test
    public void Task1() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://petstore.octoperf.com/actions/Catalog.action");
        driver.findElement(By.cssSelector("input[name='keyword']")).sendKeys("fish");
        driver.findElement(By.cssSelector("input[name='searchProducts']")).click();

        String expectedID = "FI-FW-02";
        String actualID = driver.findElement(By.xpath("//font[normalize-space()='FI-FW-02']")).getText();
        Assert.assertEquals(actualID,expectedID);

        Thread.sleep(3000);
        driver.quit();
    }
}

/*Go to https://petstore.octoperf.com/actions/Catalog.action
        Enter “fish” in inputBox and click “search” box
        Verify product ID is “FI-FW-02”*/