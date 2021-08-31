import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


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

    @AfterTest
    public static void TestClosure(){
        driver.quit();
    }

}
