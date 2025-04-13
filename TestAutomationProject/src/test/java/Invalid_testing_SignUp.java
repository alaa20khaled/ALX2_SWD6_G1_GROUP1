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

class InValidUser {
    String username;
    String password;

    InValidUser(String username, String password) {
        this.username = username + System.currentTimeMillis(); // unique
        this.password = password;
    }}

public class Invalid_testing_SignUp {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeTest
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait =  new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    public void signUp(String username, String password) {
        driver.get("https://www.demoblaze.com/");
        driver.findElement(By.id("signin2")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sign-username")));

        driver.findElement(By.id("sign-username")).clear(); // Clear any pre-filled data
        driver.findElement(By.id("sign-username")).sendKeys(username);

        driver.findElement(By.id("sign-password")).clear();
        driver.findElement(By.id("sign-password")).sendKeys(password);

        driver.findElement(By.xpath("//button[text()='Sign up']")).click();

    }

    @Test(priority = 1)
    public void testSignUpWithBlankUsername() {
        signUp("", "password123"); // Leave username blank
        // Check if the alert or error message appears for the blank username field
        String alertText = wait.until(ExpectedConditions.alertIsPresent()).getText();
        Assert.assertTrue(alertText.contains("Please fill out Username and Password."), "Expected alert for blank username.");
        driver.switchTo().alert().accept();
    }
    @Test(priority = 2)
    public void testSignUpWithBlankPassword() {
        signUp("user123", ""); // Leave password blank
        // Check if the alert or error message appears for the blank password field
        String alertText = wait.until(ExpectedConditions.alertIsPresent()).getText();
        Assert.assertTrue(alertText.contains("Please fill out Username and Password."), "Expected alert for blank password.");
        driver.switchTo().alert().accept();
    }
    @Test(priority = 3)
    public void testSignUpWithUsedUsername() {
        // First signup with a unique username and password
        String username = "usedUser";
        String password = "password123";
        signUp(username, password);

        // Get the alert message after first sign-up
        String alertText1 = wait.until(ExpectedConditions.alertIsPresent()).getText();
        driver.switchTo().alert().accept();

        // Try to sign up again using the same username
        signUp(username, password); // Reuse the same username

        // Check if an alert appears, indicating the username is already taken
        String alertText2 = wait.until(ExpectedConditions.alertIsPresent()).getText();
        Assert.assertFalse(alertText2.contains("This user already exists."), "Expected alert for already used username.");
        driver.switchTo().alert().accept();
    }
    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
