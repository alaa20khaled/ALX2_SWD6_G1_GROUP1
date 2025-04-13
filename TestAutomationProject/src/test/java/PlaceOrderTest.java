import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class PlaceOrderTest {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void placeOrderTest() {
        driver.get("https://www.demoblaze.com/");

        WebElement product = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Samsung galaxy s6']")));

        product.click();

        WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Add to cart')]")));
        addToCartButton.click();

        WebElement cartButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("cartur")));
        cartButton.click();

        WebElement checkoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Place Order']")));
        checkoutButton.click();

        WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
        nameField.sendKeys("John Doe");

        WebElement countryField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("country")));
        countryField.sendKeys("USA");

        WebElement cityField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("city")));
        cityField.sendKeys("New York");

        WebElement creditCardField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("card")));
        creditCardField.sendKeys("1234 5678 9876 5432");

        WebElement monthField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("month")));
        monthField.sendKeys("12");

        WebElement yearField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("year")));
        yearField.sendKeys("2025");

        WebElement purchaseButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Purchase']")));
        purchaseButton.click();
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='Thank you for your purchase!']")));
        Assert.assertTrue(successMessage.isDisplayed(), "Order was not successfully placed.");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
