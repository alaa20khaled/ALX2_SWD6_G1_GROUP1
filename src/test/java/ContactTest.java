import org.testng.annotations.Test;
import pages.HomePage;

public class ContactTest extends BaseTest {
    @Test(priority = 0)
    public void NavContact() throws InterruptedException {
        HomePage Home = new HomePage(driver);
        Home.UesrNavContactPage();
        Thread.sleep(1000);
        Home.userData();
    }
    @Test(priority = 1)
    public void withEmptyName() throws InterruptedException {
        HomePage Home = new HomePage(driver);
        Home.UesrNavContactPage();
        Thread.sleep(1000);
        Home.Entering_Data_With_Empty_Name();
    }
    @Test(priority = 2)
    public void withEmptyMassage() throws InterruptedException {
        HomePage Home = new HomePage(driver);
        Home.UesrNavContactPage();
        Thread.sleep(1000);
        Home.Entering_Data_With_Empty_Massage();
    }
    @Test(priority = 3)
    public void withEmptyEmail() throws InterruptedException {
        HomePage Home = new HomePage(driver);
        Home.UesrNavContactPage();
        Thread.sleep(1000);
        Home.Entering_Data_With_Empty_Email();
    }
    @Test(priority = 4)
    public void With_Wrong_EmailFormat() throws InterruptedException {
        HomePage Home = new HomePage(driver);
        Home.UesrNavContactPage();
        Thread.sleep(1000);
        Home.Entering_Data_With_Wrong_EmailFormat();
    }
    @Test(priority = 4)
    public void empty_Data() throws InterruptedException {
        HomePage Home = new HomePage(driver);
        Home.UesrNavContactPage();
        Thread.sleep(1000);
        Home.Leaving_it_all_empty();
    }
}
