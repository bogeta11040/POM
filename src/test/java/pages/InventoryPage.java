package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage extends BasePage{

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    String addToCartBtn = "//div[text()='$$']/../../..//button";
    By cart = By.cssSelector(".shopping_cart_link");
    String productPrice = "//div[text()='$$']/../../..//div[@class='inventory_item_price']";
    String productDescription = "//div[text()='$$']/../../..//div[@class='inventory_item_desc']";

    public void addProductToCart(String productName){
        clickElement(driver.findElement(By.xpath(addToCartBtn.replace("$$", productName))),"product name");
    }

    public void addProductsToCart(String ... products){
        for (String product:products) {
            addProductToCart(product);
        }
    }

    public void goToCart(){
        clickElement(driver.findElement(cart),"cart icon");
    }

    @Override
    public double getProductPrice(String product) {
        return Double.parseDouble(getText(driver.findElement(By.xpath(this.productPrice.replace("$$",product))),"product price label").replace("$", ""));
    }

    @Override
    public String getProductDescription(String product) {
        return getText(driver.findElement(By.xpath(this.productDescription.replace("$$",product))),"product description label");
    }

    @Override
    public double[] getProductPrices(String... products) {
        double[] prices = new double[products.length];

        for (int i = 0;i<products.length;i++) {
            prices[i] = this.getProductPrice(products[i]);
        }

        return prices;
    }

    @Override
    public String[] getProductDescriptions(String... products) {
        String[] descriptions = new String[products.length];

        for (int i = 0;i<products.length;i++) {
            descriptions[i] = this.getProductDescription(products[i]);
        }

        return descriptions;
    }
}