package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;
    private By contactLink= By.xpath("//a[@data-target=\"#exampleModal\"]");
    private By Email= By.id("recipient-email");
    private By Name= By.id("recipient-name");
    private By Massage= By.id("message-text");
    private By SendMassage= By.xpath("//button[text()='Send message']");


    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void UesrNavContactPage(){
        driver.findElement(contactLink).click();
    }
    public void userData(){

        driver.findElement(Email).sendKeys("fatmaelzhraa@gmail.com");
        driver.findElement(Name).sendKeys("fatmaelzhraa");
        driver.findElement(Massage).sendKeys(" fhgfkhghjgkj");
        driver.findElement(SendMassage).click();

    }
    public void Entering_Data_With_Empty_Name(){

        driver.findElement(Email).sendKeys("fatmaelzhraa@gmail.com");
        driver.findElement(Name).sendKeys("");
        driver.findElement(Massage).sendKeys(" fhgfkhghjgkj");
        driver.findElement(SendMassage).click();

    }
    public void Entering_Data_With_Empty_Massage(){
        driver.findElement(Email).sendKeys("fatmaelzhraa@gmail.com");
        driver.findElement(Name).sendKeys("Fatmaelzhraa");
        driver.findElement(Massage).sendKeys(" ");
        driver.findElement(SendMassage).click();
    }
    public void Entering_Data_With_Empty_Email(){
        driver.findElement(Email).sendKeys("");
        driver.findElement(Name).sendKeys("Fatmaelzhraa");
        driver.findElement(Massage).sendKeys(" fhgfkhghjgkj");
        driver.findElement(SendMassage).click();
    }
    public void Entering_Data_With_Wrong_EmailFormat(){
        driver.findElement(Email).sendKeys("fatmaelzhraa@gmail$com");
        driver.findElement(Name).sendKeys("Fatma");
        driver.findElement(Massage).sendKeys(" fhgfkhghjgkj");
        driver.findElement(SendMassage).click();
    }
    public void Leaving_it_all_empty(){
        driver.findElement(Email).sendKeys("");
        driver.findElement(Name).sendKeys("");
        driver.findElement(Massage).sendKeys(" ");
        driver.findElement(SendMassage).click();
    }
}
