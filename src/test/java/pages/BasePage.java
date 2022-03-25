package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    WebDriver driver;
    int wait = 10;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    String productPrice = "//div[text()='$$']/../..//div[@class='inventory_item_price']";
    String productDescription = "//div[text()='$$']/../..//div[@class='inventory_item_desc']";

    public double getProductPrice(String product){
        return Double.parseDouble(getText(driver.findElement(By.xpath(productPrice.replace("$$",product))),"product price label").replace("$", ""));
    }

    public double[] getProductPrices(String ... products){
        double[] prices = new double[products.length];

        for (int i = 0;i<products.length;i++) {
            prices[i] = getProductPrice(products[i]);
        }

        return prices;
    }

    public String getProductDescription(String product){
        return getText(driver.findElement(By.xpath(productDescription.replace("$$",product))),"product description label");
    }

    public String[] getProductDescriptions(String ... products){
        String[] descriptions = new String[products.length];

        for (int i = 0;i<products.length;i++) {
            descriptions[i] = getProductDescription(products[i]);
        }

        return descriptions;
    }

    public void clickElement(WebElement element, String log){
        WebDriverWait webDriverWait = new WebDriverWait(driver, wait);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(element));

        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().build().perform();

        System.out.println("Clicked: "+log+" element!");
    }

    public void typeText(WebElement element,String text, String log){
        WebDriverWait webDriverWait = new WebDriverWait(driver, wait);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(element));

        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().build().perform();

        element.clear();
        element.sendKeys(text);

        System.out.println("Typed: "+text+" in "+log+" element!");
    }

    public String getText(WebElement element, String log){
        WebDriverWait webDriverWait = new WebDriverWait(driver, wait);
        webDriverWait.until(ExpectedConditions.visibilityOf(element));

        System.out.println("Getting text from: "+log+" element!");

        return element.getText().trim();
    }

}