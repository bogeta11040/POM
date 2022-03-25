package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage{

    public CartPage(WebDriver driver) {
        super(driver);
    }

    By checkout = By.id("checkout");
    By continueShopping = By.id("continue-shopping");

    String removeFromCartBtn = "//div[text()='$$']/../..//button";

    public void removeProductFromCart(String productName){
        clickElement(driver.findElement(By.xpath(removeFromCartBtn.replace("$$", productName))),"remove button");
    }

    public void removeProductsFromCart(String ... products){
        for (String product:products) {
            removeProductFromCart(product);
        }
    }

    public void checkout(){
        clickElement(driver.findElement(checkout),"checkout button");
    }

}