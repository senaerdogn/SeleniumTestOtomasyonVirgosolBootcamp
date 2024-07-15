package Day1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Task4 {
    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://selenium08.blogspot.com/2019/07/check-box-and-radio-buttons.html");
    }

    @AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }

    @Test
    public void Task4(){
        WebElement browserIE = driver.findElement(By.cssSelector("input[value='IE']"));
        //click IE
        browserIE.click();

        //Verify IE is selected, Opera is not selected
        WebElement browserOpera = driver.findElement(By.cssSelector("input[value='Opera']"));
        Assert.assertTrue(browserIE.isSelected() && !browserOpera.isSelected());

        WebElement browserMozilla = driver.findElement(By.cssSelector("input[value='Mozilla']"));
        //Click Mozilla
        browserMozilla.click();

        //Verify Mozilla is selected, IE is not selected
        Assert.assertTrue(browserMozilla.isSelected() && !browserIE.isSelected());
    }
}

/*Go to
            https://selenium08.blogspot.com/2019/07/check-box-and-radio-buttons.html
            click IE
            Verify IE is selected, Opera is not selected
            Click Mozilla
            Verify Mozilla is selected, IE is not selected*/