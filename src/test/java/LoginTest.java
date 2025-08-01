import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {
    static WebDriver driver;

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\path\\to\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("file:///C:/path/to/login.html"); // Update path
    }

    @Test
    public void testValidLogin() throws InterruptedException {
        driver.findElement(By.id("username")).sendKeys("milan");
        driver.findElement(By.id("password")).sendKeys("test123");
        driver.findElement(By.cssSelector("input[value='Login']")).click();

        Thread.sleep(1000); // wait for JS
        String msg = driver.findElement(By.id("message")).getText();
        Assert.assertEquals("Login Successful!", msg);
    }

    @Test
    public void testInvalidLogin() throws InterruptedException {
        driver.navigate().refresh();

        driver.findElement(By.id("username")).sendKeys("wrong");
        driver.findElement(By.id("password")).sendKeys("wrongs");
        driver.findElement(By.cssSelector("input[value='Login']")).click();

        Thread.sleep(1000);
        String msg = driver.findElement(By.id("message")).getText();
        Assert.assertEquals("Invalid Credentials!", msg);
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}
