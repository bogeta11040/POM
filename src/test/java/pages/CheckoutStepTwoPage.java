package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutStepTwoPage extends BasePage{

    public CheckoutStepTwoPage(WebDriver driver) {
        super(driver);
    }

    By finishBtn = By.id("finish");
    By cancelBtn = By.id("cancel");
    By itemTotal = By.cssSelector(".summary_subtotal_label");

    public void clickCancel(){
        clickElement(driver.findElement(cancelBtn),"cancel button");
    }

    public void clickFinish(){
        clickElement(driver.findElement(finishBtn),"finish button");
    }

    public double getItemTotal(){
        return Double.parseDouble(getText(driver.findElement(itemTotal),"item total label").replace("Item total: $", ""));
    }

    public double getProductPricesSum(double[] productPrices){
        double sum = 0;
        for (double price:productPrices) {
            sum+=price;
        }
        return sum;
    }

}