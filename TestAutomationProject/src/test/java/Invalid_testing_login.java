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

class InValidLogin {
    String username;
    String password;

    InValidLogin(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

public class Invalid_testing_login {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeTest
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait =  new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }
    // Reusable Login function
    public void login(String username, String password) {
        driver.get("https://www.demoblaze.com/");

        driver.findElement(By.id("login2")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginusername")));

        driver.findElement(By.id("loginusername")).sendKeys(username);
        driver.findElement(By.id("loginpassword")).sendKeys(password);
        driver.findElement(By.cssSelector("#logInModal > div > div > div.modal-footer > button.btn.btn-primary")).click();
    }
    // ❌ Test Case 2: Valid username, invalid password
    @Test(priority = 1)
    public void testValidUsernameInvalidPassword() {
        login("Ahmed3", "wrongpass123");
        String alertText = wait.until(ExpectedConditions.alertIsPresent()).getText();
        Assert.assertTrue(alertText.contains("Wrong password."), "Expected alert for wrong password.");
        driver.switchTo().alert().accept();
    }

    // ❌ Test Case 3: Invalid username and password
    @Test(priority = 2)
    public void testInvalidUsernameAndPassword() {
        login("InvalidUser123", "invalidPass");
        String alertText = wait.until(ExpectedConditions.alertIsPresent()).getText();
        Assert.assertTrue(alertText.contains("User does not exist."), "Expected alert for non-existing user.");
        driver.switchTo().alert().accept();
    }

    @Test(priority = 3)
    public void testblankusernameandpassword() {
        login("", ""); // Leave password blank
        // Check if the alert or error message appears for the blank password field
        String alertText = wait.until(ExpectedConditions.alertIsPresent()).getText();
        Assert.assertTrue(alertText.contains("Please fill out Username and Password."), "Expected alert for blank password and username.");
        driver.switchTo().alert().accept();
    }
    // ✅ Close browser after all tests
    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
