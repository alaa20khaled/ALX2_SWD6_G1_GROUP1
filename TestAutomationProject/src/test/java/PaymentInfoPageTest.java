import Payment.CartPage;
import Payment.PaymentInfoPage;
import Payment.TestBase;
import Payment.TestUtil;
import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PaymentInfoPageTest extends TestBase {

    private PaymentInfoPage paymentInfoPage;
    private CartPage cartPage;
    private Alert alert;

    @Test(priority =1)

    public void verifyPurchaseWithoutInfo() {

        cartPage = new CartPage(driver);

        paymentInfoPage = cartPage.goToPaymentPage();

        paymentInfoPage.clickPurchaseBtn();

        String txt = TestUtil.handleAlert(driver, alert);

        Assert.assertEquals(txt, "Please fill out Name and Creditcard.");

    }

    @Test(priority = 2)
    public void verifyPurchaseWithInfo() {
        cartPage = new CartPage(driver);
        paymentInfoPage = cartPage.goToPaymentPage();

        paymentInfoPage.sendDetails();
        paymentInfoPage.clickPurchaseBtn();

        Assert.assertEquals(paymentInfoPage.getSuccessMsg(), "Thank you for your purchase!");
    }
}
