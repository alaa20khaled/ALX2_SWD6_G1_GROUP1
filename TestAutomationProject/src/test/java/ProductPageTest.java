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

public class ProductPageTest {
    WebDriver driver ;
    WebDriverWait wait;
    String baseurl = "https://www.demoblaze.com/";

    @BeforeClass

    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get(baseurl);

        wait =  new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    private void goToHomePage() {
        driver.get(baseurl);
    }

    @Test(priority = 2)
    public void validateAddingProductToCart() {

        goToHomePage();
        WebElement item = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(), 'Samsung galaxy s6')]")));
        item.click();

        // Add the item to the cart
        WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='btn btn-success btn-lg']")));
        addToCartButton.click();

        try {
            wait.until(ExpectedConditions.alertIsPresent()).accept();
        } catch (Exception e) {

        }


        WebElement cartTab = wait.until(ExpectedConditions.elementToBeClickable(By.id("cartur")));
        cartTab.click();


        WebElement cartItem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(text(), 'Samsung galaxy s6')]")));
        Assert.assertTrue(cartItem.isDisplayed(), "The item was not added to the cart.");
    }
    @Test(priority = 1)
    public void validateProductPageNavigation() {
        goToHomePage();
        WebElement item = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(), 'Samsung galaxy s6')]")));
        item.click();


        WebElement productTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@class='name']")));
        Assert.assertTrue(productTitle.isDisplayed(), "Product page not loaded properly.");
    }


    @AfterClass
    public void TearDown(){
        if (driver!=null){
            driver.quit();

        }

    }
}
