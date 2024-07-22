package Day2;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Task5 {
    WebDriver driver;
    JavascriptExecutor jse;

    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://katalon-demo-cura.herokuapp.com/");
    }

    @AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }

    @Test
    public void task4() throws InterruptedException{
        //Make Appointment butonuna tıklanır
        driver.findElement(By.id("btn-make-appointment")).click();

        //Kullanıcı adı ve şifre girilir (Kullanıcı adı ve şifreyi “Demo account” alanından get metodu kullanarak alıp girilir)
        String username = driver.findElement(By.cssSelector("input[value='John Doe']")).getAttribute("value");
        String password = driver.findElement(By.cssSelector("input[value='ThisIsNotAPassword']")).getAttribute("value");
        driver.findElement(By.id("txt-username")).sendKeys(username);
        driver.findElement(By.id("txt-password")).sendKeys(password);

        //Login butonuna tıklanır
        driver.findElement(By.id("btn-login")).click();

        //Facility Honkong seçilir
        WebElement dropDown = driver.findElement(By.id("combo_facility"));
        Select select = new Select(dropDown);
        select.selectByValue("Hongkong CURA Healthcare Center");

        //“Apply for hospital readmission” check boxı seçilir
        driver.findElement(By.id("chk_hospotal_readmission")).click();

        //“Healthcare Program” Medicaid radyo butonu seçilir
        driver.findElement(By.id("radio_program_medicaid")).click();

        //“Visit Date (Required)” alanına tarih girilir
        driver.findElement(By.id("txt_visit_date")).sendKeys("22/07/2024");

        //“Comment” girilir
        driver.findElement(By.id("txt_comment")).sendKeys("comment yaz");

        //“Book Appointment” butonuna tıklanır
        driver.findElement(By.id("btn-book-appointment")).click();

        // “Appointment Confirmation” yazısı kontrol edilir
        String expectedResult = "Appointment Confirmation";
        String actualResult = driver.findElement(By.xpath("//h2")).getText();
        Assert.assertEquals(actualResult,expectedResult);

        //Sağ üst köşedeki üç çizgi olan menü butonuna tıklanır
        driver.findElement(By.xpath("//i[@class='fa fa-bars']")).click();

        //“Log out” butonuna tıklanır
        driver.findElement(By.cssSelector("a[href='authenticate.php?logout']")).click();

        //Url kontrol edilir (https://katalon-demo-cura.herokuapp.com/)
        String expectedUrl = "https://katalon-demo-cura.herokuapp.com/";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);

        //“We Care About Your Health” yazısı kontrol edilir
        String expectedText = "We Care About Your Health";
        String actualText = driver.findElement(By.xpath("//h3")).getText();
        Assert.assertEquals(actualText,expectedText);
    }
}


/*Tarayıcı açılır (https://katalon-demo-cura.herokuapp.com/)
    Make Appointment butonuna tıklanır
    Kullanıcı adı ve şifre girilir (Kullanıcı adı ve şifreyi “Demo account” alanından get metodu kullanarak alıp girilir)
    Login butonuna tıklanır
    Şifrenizi değiştirin popupı tamama tıklanır
    Facility Honkong seçilir
    “Apply for hospital readmission” check boxı seçilir
    “Healthcare Program” Medicaid radyo butonu seçilir
    “Visit Date (Required)” alanına tarih girilir
    “Comment” girilir
    “Book Appointment” butonuna tıklanır
    “Appointment Confirmation” yazısı kontrol edilir
    Sağ üst köşedeki üç çizgi olan menü butonuna tıklanır
    “Log out” butonuna tıklanır
    Url kontrol edilir (https://katalon-demo-cura.herokuapp.com/ )
    “We Care About Your Health” yazısı kontrol edilir*/