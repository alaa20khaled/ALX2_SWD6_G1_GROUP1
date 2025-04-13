package Payment;



import org.antlr.v4.runtime.misc.NotNull;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

public class TestUtil {
    public static String handleAlert(@NotNull WebDriver driver, Alert alert) {
        alert = driver.switchTo().alert();
        String alertText = alert.getText();
        alert.accept();
        return alertText;
    }
}
