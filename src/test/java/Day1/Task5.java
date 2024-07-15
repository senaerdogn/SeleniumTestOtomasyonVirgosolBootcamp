package Day1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class Task5 {
    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://selenium08.blogspot.com/2019/11/dropdown.html");
    }

    @AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }

    @Test
    public void Task5(){

        //get size of dropdown menu
        WebElement month = driver.findElement(By.name("Month"));
        Select select = new Select(month);
        List<WebElement> list = select.getOptions();
        System.out.println("list.size() = " + list.size());

        //get all texts
        for (WebElement element : list) {
            System.out.println("element.getText() = " + element.getText());
        }

        //select March by value
        select.selectByValue("Ma");

        //select April by index
        select.selectByIndex(4);

        //select October by text
        select.selectByVisibleText("October");

        //Verify: get selected options size=3
        int selectedMonths = select.getAllSelectedOptions().size();
        Assert.assertEquals(selectedMonths, 3);
    }
}


/*Multi-Select Task
    go to https://selenium08.blogspot.com/2019/11/dropdown.html
    get size of dropdown menu
    get all texts
    select March by value
    select April by index
    select October by text
    Verify: get selected options size=3*/