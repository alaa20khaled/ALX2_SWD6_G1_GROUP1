import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class Logout_testing {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeTest
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    public void login(String username, String password) {
        driver.get("https://www.demoblaze.com/");
        driver.findElement(By.id("login2")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginusername")));
        driver.findElement(By.id("loginusername")).sendKeys(username);
        driver.findElement(By.id("loginpassword")).sendKeys(password);
        driver.findElement(By.xpath("//button[text()='Log in']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logout2")));
    }

    @Test
    public void testLogoutAfterLogin() {
        login("Ahmed3", "password123");

        // Logout
        driver.findElement(By.id("logout2")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login2")));
        Assert.assertTrue(driver.findElement(By.id("login2")).isDisplayed(), "User is not logged out properly.");
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}

