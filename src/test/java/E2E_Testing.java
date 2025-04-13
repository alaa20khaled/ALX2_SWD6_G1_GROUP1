import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public  class E2E_Testing {
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

    @Test(priority = 1)
    public void loginTest() {
        driver.findElement(By.id("login2")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginusername"))).sendKeys("Osama eissa");
        driver.findElement(By.id("loginpassword")).sendKeys("password");
        driver.findElement(By.xpath("//button[text()='Log in']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nameofuser")));

        // Assertion to verify login was successful
        String welcomeText = driver.findElement(By.id("nameofuser")).getText();
        Assert.assertTrue(welcomeText.contains("Welcome"), "Login failed or username not displayed.");
    }
    @Test(priority = 2)
    public void productpagae() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Samsung galaxy s6"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Add to cart"))).click();

        wait.until(ExpectedConditions.alertIsPresent());

        String alertText = driver.switchTo().alert().getText();
        Assert.assertTrue(alertText.contains("Product added"), "Product was not added to the cart.");

        driver.switchTo().alert().accept();
    }
    @Test(priority = 3)
    public void placeOrderTest() {
        driver.findElement(By.id("cartur")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Place Order']"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name"))).sendKeys("Ahmed");
        driver.findElement(By.id("country")).sendKeys("Egypt");
        driver.findElement(By.id("city")).sendKeys("Cairo");
        driver.findElement(By.id("card")).sendKeys("123456789");
        driver.findElement(By.id("month")).sendKeys("04");
        driver.findElement(By.id("year")).sendKeys("2025");

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Purchase']"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("sweet-alert")));


        String purchaseConfirmation = driver.findElement(By.className("sweet-alert")).getText();
        Assert.assertTrue(purchaseConfirmation.contains("Thank you for your purchase!"), "Purchase was not successful.");

        driver.findElement(By.xpath("//button[text()='OK']")).click();
    }


    @AfterClass
    public void tearDown() {
        driver.quit();
    }

}


