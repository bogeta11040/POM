package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutStepOnePage extends BasePage{

    public CheckoutStepOnePage(WebDriver driver) {
        super(driver);
    }

    By firstname = By.id("first-name");
    By lastname = By.id("last-name");
    By ZIP = By.id("postal-code");
    By continueBtn = By.id("continue");
    By cancel = By.id("cancel");

    public void enterFirstName(String name){
        typeText(driver.findElement(firstname),name,"firstname input");
    }

    public void enterLastName(String name){
        typeText(driver.findElement(lastname),name,"lastname input");
    }

    public void enterZIP(String zip){
        typeText(driver.findElement(ZIP),zip,"zip code input");
    }

    public void clickContinue(){
        clickElement(driver.findElement(continueBtn),"continue button");
    }

    public void clickCancel(){
        clickElement(driver.findElement(cancel),"cancel button");
    }

    public void checkout(String firstName, String lastName, String zip){
        enterFirstName(firstName);
        enterLastName(lastName);
        enterZIP(zip);
        clickContinue();
    }
}