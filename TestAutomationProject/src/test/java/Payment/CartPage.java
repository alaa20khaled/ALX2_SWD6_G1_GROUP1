package Payment;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
    private WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public PaymentInfoPage goToPaymentPage() {
        driver.findElement(By.xpath("//button[text()='Place Order']")).click();
        return new PaymentInfoPage(driver);
    }
}
