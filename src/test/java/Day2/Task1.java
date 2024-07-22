package Day2;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Task1 {
    WebDriver driver;

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
    public void task1() throws InterruptedException {
        //Make Appointment butonuna tıklanır
        driver.findElement(By.id("btn-make-appointment")).click();

        //Kullanıcı adı ve şifre girilir (adı: John Doe, şifre: ThisIsNotAPassword)
        driver.findElement(By.id("txt-username")).sendKeys("John Doe");
        driver.findElement(By.id("txt-password")).sendKeys("ThisIsNotAPassword");

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
        driver.findElement(By.xpath("//textarea[@class='form-control']")).sendKeys("comment deneme");

        //“Book Appointment” butonuna tıklanır
        driver.findElement(By.cssSelector("button[class='btn btn-default']")).click();

        //“Appointment Confirmation” yazısı kontrol edilir
        String expectedResult = "Appointment Confirmation";
        System.out.println("expectedResult = " + expectedResult);
        String actualResult = driver.findElement(By.xpath("//h2")).getText();
        System.out.println("actualResult = " + actualResult);
        Assert.assertEquals(actualResult, expectedResult);

        Thread.sleep(2000);
    }
}


/*Tarayıcı açılır (https://katalon-demo-cura.herokuapp.com/)
   Make Appointment butonuna tıklanır
   Kullanıcı adı ve şifre girilir (adı: John Doe, şifre: ThisIsNotAPassword)
   Login butonuna tıklanır
   Facility Honkong seçilir
   “Apply for hospital readmission” check boxı seçilir
   “Healthcare Program” Medicaid radyo butonu seçilir
   “Visit Date (Required)” alanına tarih girilir
   “Comment” girilir
   “Book Appointment” butonuna tıklanır
   “Appointment Confirmation” yazısı kontrol edilir*/