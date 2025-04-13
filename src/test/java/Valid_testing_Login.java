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

class ValidLogin {
    String username;
    String password;

    ValidLogin(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

public class Valid_testing_Login {
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
    @Test
    public void testvalidLogin() {
        login("Ahmed3", "password123");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nameofuser")));

        String welcomeText = driver.findElement(By.id("nameofuser")).getText();
        Assert.assertTrue(welcomeText.contains("Welcome"), "Login failed or username not displayed.");
    }
    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
