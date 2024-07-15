package Day1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Task2 {
    @Test
    public void Task2() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        //Go to https://petstore.octoperf.com/actions/Catalog.action
        driver.get("https://petstore.octoperf.com/actions/Catalog.action");

        //Enter “fish” in inputBox and click “search” box
        driver.findElement(By.cssSelector("input[name='keyword']")).sendKeys("fish");
        driver.findElement(By.cssSelector("input[name='searchProducts']")).click();

        //Click productID “FI-FW-02 Goldfish”
        driver.findElement(By.xpath("//font[normalize-space()='FI-FW-02']")).click();

        //Select “EST-20” item and “Add to Cart”
        driver.findElement(By.xpath("//a[normalize-space()='EST-20']")).click();
        driver.findElement(By.xpath("//a[@class='Button']")).click();

        //Click “Fish” module
        driver.findElement(By.xpath("//img[@src='../images/sm_fish.gif']")).click();

        //Click productID “FI-SW-01 Angelfish”
        driver.findElement(By.xpath("//a[@href='/actions/Catalog.action?viewProduct=&productId=FI-SW-01']")).click();

        //Select large angel fish and add to cart
        driver.findElement(By.xpath("//a[@href='/actions/Catalog.action?viewItem=&itemId=EST-1']")).click();
        driver.findElement(By.xpath("//a[@href='/actions/Cart.action?addItemToCart=&workingItemId=EST-1']")).click();

        //Verify total cost “$22.00”
        String expectedCost = "Sub Total: $22.00";
        WebElement Cost = driver.findElement(By.xpath("//*[contains(text(), 'Sub Total: $22.00')]"));
        String actualCost= Cost.getText();
        Assert.assertEquals(expectedCost, actualCost);


        Thread.sleep(3000);

        driver.quit();
    }
}


/*Go to https://petstore.octoperf.com/actions/Catalog.action
        Enter “fish” in inputBox and click “search” box
        Click productID “FI-FW-02 Goldfish”
        Select “EST-20” item and “Add to Cart”
        Click “Fish” module
        Click productID “FI-SW-01 Angelfish”
        Select large angel fish and add to cart
        Verify total cost “$22.00”
*/