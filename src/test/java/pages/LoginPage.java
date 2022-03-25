package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage{

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    By username = By.id("user-name");
    By password = By.id("password");
    By login = By.id("login-button");

    public void login(String usernameText, String passwordText){
        enterUsername(usernameText);
        enterPassword(passwordText);
        clickLogin();
    }

    public void enterUsername(String usernameText){
        typeText(driver.findElement(username),usernameText,"username input");
    }

    public void enterPassword(String passwordText){
        typeText(driver.findElement(password),passwordText,"password input");
    }

    public void clickLogin(){
        clickElement(driver.findElement(login),"login button");
    }

}