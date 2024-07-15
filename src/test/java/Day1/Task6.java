package Day1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class Task6 {
    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/select-menu");
    }

    @AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }

    @Test
    public void Task6(){
        //Select the Old Style Select Menu using the element id.
        WebElement dropDown = driver.findElement(By.id("oldSelectMenu"));
        Select select = new Select(dropDown);

        //Print all the options texts of the dropdown.
        List<WebElement> list = select.getOptions();
        for (WebElement element : list) {
            System.out.println("element.getText() = " + element.getText());
        }

        //Select 'Purple' using the index and get text
        select.selectByIndex(4);
        System.out.println("Purple = " + select.getFirstSelectedOption().getText());

        //After that, select 'Magenta' using visible text and get text
        select.selectByVisibleText("Magenta");
        System.out.println("Magenta = " + select.getFirstSelectedOption().getText());

        //Select an option using value of 'White' and get text
        select.selectByValue("6");
        System.out.println("White = " + select.getFirstSelectedOption().getText());
    }
}

/*Select Task
    Open "https://demoqa.com/select-menu".
    Select the Old Style Select Menu using the element id.
    Print all the options texts of the dropdown.
    Select 'Purple' using the index and get text
    After that, select 'Magenta' using visible text and get text
    Select an option using value of 'White' and get text*/