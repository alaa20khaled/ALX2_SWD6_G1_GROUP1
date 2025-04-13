import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.time.Duration;

class ValidUser {
    String username;
    String password;

    ValidUser(String username, String password) {
        this.username = username + System.currentTimeMillis(); // unique
        this.password = password;
    }}

public class Valid_Sign_up_page {
    WebDriver driver;
    WebDriverWait wait;
    @BeforeTest
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait =  new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }
    // Reusable sign-up function
    public void signUp(String username, String password) {
        driver.get("https://www.demoblaze.com/");
        driver.findElement(By.id("signin2")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sign-username")));
        driver.findElement(By.id("sign-username")).clear(); // Clear any pre-filled data
        driver.findElement(By.id("sign-username")).sendKeys(username);
        driver.findElement(By.id("sign-password")).clear();
        driver.findElement(By.id("sign-password")).sendKeys(password);
        driver.findElement(By.xpath("//button[text()='Sign up']")).click();
        String alertText = wait.until(ExpectedConditions.alertIsPresent()).getText();
        driver.switchTo().alert().accept();
        Assert.assertEquals(alertText, "Sign up successful.", "Expected successful sign-up.");
    }
    // ========== TEST CASES ==========

    // Test Case 1: Validate signing up with valid username and password
    @Test(priority = 1)
    public void testSignUpWithValidCredentials() {
        signUp("Omarahmed", "password123");
    }

    // Test Case 2: Validate signing up using different format for the username and password
    @Test(priority = 2)
    public void testSignUpWithDifferentFormats() {
        signUp("-&/%%%", "@@@###");
        signUp("995263", "ahmed");
    }
    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}







