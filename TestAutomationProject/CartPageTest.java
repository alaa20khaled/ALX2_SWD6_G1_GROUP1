import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.WebDriver;

import java.time.Duration;
import java.util.List;

public class CartPageTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeTest
    public void setup() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait =  new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }
    private void goToHomePage() {
        driver.get("https://www.demoblaze.com/");
    }

    @Test(priority = 2)
    public void testMultipleItemsCartTotal() {
        goToHomePage();
        wait.until(ExpectedConditions.elementToBeClickable
                (By.linkText("Samsung galaxy s6"))).click();
        wait.until(ExpectedConditions.elementToBeClickable
                (By.xpath("//a[text()='Add to cart']"))).click();
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
        driver.get("https://www.demoblaze.com/");


        wait.until(ExpectedConditions.elementToBeClickable
                (By.linkText("Nexus 6"))).click();
        wait.until(ExpectedConditions.elementToBeClickable
                (By.xpath("//a[text()='Add to cart']"))).click();
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();

        driver.findElement(By.id("cartur")).click();
        WebElement total = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("totalp")));
        Assert.assertEquals(total.getText(), "1010");

    }

    @Test(priority = 3)
    public void testDeleteItemFromCart() throws InterruptedException {

        goToHomePage();
        // Add an item to the cart
        addItemToCart("Samsung galaxy s6");


        WebElement cartLink = driver.findElement(By.id("cartur"));
        cartLink.click();
        Thread.sleep(2000);

        //  Identify items in the cart before deletion
        List<WebElement> initialCartItems = driver.findElements(By.xpath("//tbody[@id='tbodyid']/tr"));
        int initialItemCount = initialCartItems.size();

        //  Click on Delete for the first item in the cart
        if (!initialCartItems.isEmpty()) {
            WebElement deleteLink = initialCartItems.get(0).findElement(By.xpath(".//td[4]/a"));
            deleteLink.click();
            Thread.sleep(2000);

            // Step 6: Verify that the item is deleted
            List<WebElement> currentCartItems = driver.findElements(By.xpath("//tbody[@id='tbodyid']/tr"));
            int currentItemCount = currentCartItems.size();

            Assert.assertEquals(currentItemCount, initialItemCount - 1, "Item should be deleted from the cart.");


            System.out.println("Test Passed: Item successfully deleted from the cart.");

        } else {
            Assert.fail("Cart is empty, cannot perform delete operation.");
        }
    }

    private void addItemToCart(String itemName) throws InterruptedException {
        WebElement phoneCategory = driver.findElement(By.linkText("Phones"));
        phoneCategory.click();
        Thread.sleep(1000);

        List<WebElement> items = driver.findElements(By.xpath("//div[@id='tbodyid']/div/div/div/h4/a"));
        for (WebElement item : items) {
            if (item.getText().equalsIgnoreCase(itemName)) {
                item.click();
                Thread.sleep(1000);
                WebElement addToCartButton = driver.findElement(By.xpath("//a[@class='btn btn-success btn-lg']"));
                addToCartButton.click();
                Thread.sleep(2000);
                driver.switchTo().alert().accept();
                Thread.sleep(1000);
                driver.navigate().back();
                Thread.sleep(1000);
                break;
            }
        }
    }

    @Test(priority = 1)
    public void verifyEmptyCartOnFirstVisit() {
        goToHomePage();


        WebElement cartButton = driver.findElement(By.id("cartur"));
        cartButton.click();

        WebDriverWait wait =  new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement cartPageTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='Products']")));

        Assert.assertTrue(cartPageTitle.isDisplayed(), "Cart page title is not visible.");

        List<WebElement> cartProducts = driver.findElements(By.cssSelector(".success td"));
        Assert.assertTrue(cartProducts.isEmpty(), "Cart is not empty on first visit. There are products in the cart.");


        System.out.println("Cart is empty on first visit.");
    }
        @AfterTest
        public void tearDown () {
            if (driver != null) {
                driver.quit();
            }
        }
    }
