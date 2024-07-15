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

public class Task3 {

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
    public void Task3() {
        //click red and green checkBoxes
        WebElement redButton = driver.findElement(By.xpath("//input[@value='red']"));
        redButton.click();
        WebElement greenButton = driver.findElement(By.xpath("//input[@value='green']"));
        greenButton.click();

        //Verify Red is selected, Orange is not selected
        Assert.assertTrue(redButton.isSelected());
        WebElement orangeButton = driver.findElement(By.xpath("//input[@value='orange']"));
        Assert.assertFalse(orangeButton.isSelected());

        //Verify Blue is enabled, Green is selected
        WebElement blueButton = driver.findElement(By.xpath("//input[@value='blue']"));
        Assert.assertTrue(blueButton.isEnabled());
        Assert.assertTrue(greenButton.isSelected());

        //Assert.assertTrue(blueButton.isEnabled() && greenButton.isSelected());
    }
}


/*Go to
            https://selenium08.blogspot.com/2019/07/check-box-and-radio-buttons.html
            click red and green checkBoxes
            Verify Red is selected, Orange is not selected
            Verify Blue is enabled, Green is selected
 */