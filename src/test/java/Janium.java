import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.KeyEvent;

@Listeners(ITestListener.class)

public class Janium {
    public static WebDriver driver;
    static String BaseURL = "https://opensource-demo.orangehrmlive.com/";
    static JavascriptExecutor js_ex;

    @BeforeTest
    public static void WebSetup() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","D:\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(BaseURL);
        Thread.sleep(3000);
        Assert.assertTrue((driver.getTitle().contains("OrangeHRM")));
    }

    @Test(priority = 0)
    public static void LandingPageVerification(){
        Assert.assertTrue((driver.getPageSource().contains("LOGIN Panel")));
        System.out.println("landing page Successful");
    }

    @Test(priority = 1)
    public static void UserLogin() throws InterruptedException {
        driver.findElement(By.xpath("//input[@id='txtUsername']")).sendKeys("Admin");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("admin123");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@id='btnLogin']")).click();
        Thread.sleep(3000);
        Assert.assertTrue((driver.getPageSource().contains("Dashboard")));
        System.out.println("Login Succeed");
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test(priority = 2)
    public static void AdminAdd() throws Exception {
        MySreenRecorder.startRecording("");
        driver.findElement(By.xpath("//b[contains(text(),'Admin')]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@id='btnAdd']")).click();
        Thread.sleep(2000);
        Select dropdown = new Select(driver.findElement(By.xpath("//select[@id='systemUser_userType']")));
        dropdown.selectByValue("1");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@id='systemUser_employeeName_empName']")).sendKeys("dav");
        Thread.sleep(2000);
        Robot r = new Robot();
        int keyCode =  KeyEvent.VK_ENTER;
        r.keyPress(keyCode);
        r.keyRelease(keyCode);
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@id='systemUser_userName']")).sendKeys("admin_nasir");
        Thread.sleep(1000);
        dropdown = new Select(driver.findElement(By.xpath("//select[@id='systemUser_status']")));
        dropdown.selectByValue("1");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@id='systemUser_password']")).sendKeys("nA$ir123");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@id='systemUser_confirmPassword']")).sendKeys("nA$ir123");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@id='btnSave']")).click();
        Thread.sleep(4000);
        MySreenRecorder.stopRecording();
    }

    @Test(priority = 3)
    public static void Admin() throws InterruptedException, AWTException {
        driver.findElement(By.xpath("//b[contains(text(),'Admin')]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@id='searchSystemUser_userName']")).sendKeys("Admin Nasir");
        Thread.sleep(1000);
        Select dropdown = new Select(driver.findElement(By.xpath("//select[@id='searchSystemUser_userType']")));
        dropdown.selectByValue("1");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@id='searchSystemUser_employeeName_empName']")).sendKeys("dav");
        Thread.sleep(2000);
        Robot r = new Robot();
        int keyCode =  KeyEvent.VK_ENTER;
        r.keyPress(keyCode);
        r.keyRelease(keyCode);
        Thread.sleep(2000);
        dropdown = new Select(driver.findElement(By.xpath("//select[@id='searchSystemUser_status']")));
        dropdown.selectByValue("1");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@id='searchBtn']")).click();
        Thread.sleep(4000);
        Assert.assertTrue(driver.getPageSource().contains("No Records Found"));
        driver.findElement(By.xpath("//input[@id='searchSystemUser_userName']")).clear();
//        driver.findElement(By.xpath("//input[@id='searchSystemUser_userName']")).sendKeys("admin_nasir");
//        Thread.sleep(1000);
//        Assert.assertTrue((driver.getPageSource().contains("admin_nasir")));
    }

    @AfterTest
    public static void TestClosure(){
        driver.quit();
    }

}
