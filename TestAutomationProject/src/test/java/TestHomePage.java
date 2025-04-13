import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class TestHomePage {
WebDriver driver ;
WebDriverWait wait;
    String baseurl = "https://www.demoblaze.com/";

@BeforeClass
    public void setUp() {
    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
    driver.manage().window().maximize();

    wait =  new WebDriverWait(driver, Duration.ofSeconds(10));
}
    private void goToHomePage() {
        driver.get(baseurl);
    }

    @Test(priority = 1)
    public void verifyProductListIsDisplayed() {

        goToHomePage();
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("card-title")));
    List<WebElement> productTitles = driver.findElements(By.className("card-title"));
    Assert.assertTrue(productTitles.size()>0,"No products found on the home page.");
    System.out.println("All Products: "+productTitles.size());
    for (WebElement product:productTitles){
        System.out.println("product title: "+product.getText());
    }}
    @Test (priority = 2)
public void verifyCategoriesOnHomePage(){
    goToHomePage();
   driver.manage().window().maximize();
   WebElement phonesCategory = driver.findElement(By.linkText("Phones"));
   WebElement laptopsCategory =driver.findElement(By.linkText("Laptops"));
   WebElement monitorsCategory =driver.findElement(By.linkText("Monitors"));
   Assert.assertTrue(phonesCategory.isDisplayed(),"phones category is not visible");
   Assert.assertTrue(laptopsCategory.isDisplayed(),"laptops category is not visible");
   Assert.assertTrue(monitorsCategory.isDisplayed(),"monitors category is not visible");


    }

@Test (priority = 3)
public void nextPageButtonChangeProducts(){
    goToHomePage();
    List<WebElement> initialProducts=driver.findElements(
            By.cssSelector(".card-title"));
    List<String>initialProductTitle=new ArrayList<>();
    for (WebElement product :initialProducts){
        initialProductTitle.add(product.getText());
    }
    driver.findElement(By.id("next2")).click();
    wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.cssSelector(".card-title")));
    List<WebElement>newProducts=driver.findElements(
            By.cssSelector(".card-title"));
    List<String>newProductTitle=new ArrayList<>();
    for (WebElement product :newProducts){
        newProductTitle.add(product.getText());
        Assert.assertNotEquals(newProductTitle, initialProductTitle,
                "Products didn't change after clicking Next");
    }
}
@Test(priority = 4)
public void PreviousButtonChangeProducts() {
    goToHomePage();
    List<WebElement>preButtons=driver.findElements(By.id("prev2"));
    if (!preButtons.isEmpty()){
        boolean isdisplayed =preButtons.get(0).isDisplayed();
        Assert.assertFalse(isdisplayed,"Previous button should not be displayed on the first page.");

    }
    else {
        Assert.assertTrue(true);
    }
}
@Test(priority = 5)
public void NavigateThroughTheDifferentTabsInTheHeader(){
    goToHomePage();
    driver.findElement(By.linkText("Contact")).click();
    WebDriverWait wait =  new WebDriverWait(driver, Duration.ofSeconds(10));
    WebElement contactModal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("exampleModal")));
    Assert.assertTrue(contactModal.isDisplayed(), "Contact modal should be displayed");
}

    @AfterClass
        public void TearDown(){
    if (driver!=null){
    driver.quit();

    }

}
}
