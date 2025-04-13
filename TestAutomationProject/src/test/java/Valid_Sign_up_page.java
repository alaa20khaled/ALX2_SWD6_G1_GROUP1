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
import java.util.Random;

public class Valid_Sign_up_page {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeTest
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://www.demoblaze.com/");
    }

    public void SignUp() {

        driver.findElement(By.id("signin2")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sign-username")));
        String randomUsername = "user" + new Random().nextInt(100000);
        String password = "Test@123";

        driver.findElement(By.id("sign-username")).clear();
        driver.findElement(By.id("sign-username")).sendKeys(randomUsername);

        driver.findElement(By.id("sign-password")).clear();
        driver.findElement(By.id("sign-password")).sendKeys(password);

        driver.findElement(By.xpath("//button[text()='Sign up']")).click();

        String alertText = wait.until(ExpectedConditions.alertIsPresent()).getText();
        driver.switchTo().alert().accept();

        System.out.println("✅ Tried signing up with:");
        System.out.println("Username: " + randomUsername);
        System.out.println("Password: " + password);
        System.out.println("Alert Message: " + alertText);

        Assert.assertTrue(
                alertText.equals("Sign up successful.") || alertText.equals("This user already exist."),
                "Unexpected alert message: " + alertText
        );
    }

    @Test(priority = 1)
    public void testAutoSignUp() {
       SignUp();
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
