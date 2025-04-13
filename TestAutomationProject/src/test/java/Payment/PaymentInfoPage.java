package Payment;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PaymentInfoPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public PaymentInfoPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void sendDetails() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name"))).sendKeys("Ali");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("country"))).sendKeys("Egypt");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("city"))).sendKeys("Alexandria");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("card"))).sendKeys("12345678555555");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("month"))).sendKeys("4");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("year"))).sendKeys("2025");
    }

    public void clickPurchaseBtn() {
        driver.findElement(By.xpath("//button[text()='Purchase']")).click();
    }

    public String getSuccessMsg() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='Thank you for your purchase!']"))).getText();
    }
}