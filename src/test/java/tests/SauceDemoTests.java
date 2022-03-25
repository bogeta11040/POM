package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;

import java.util.concurrent.TimeUnit;

public class SauceDemoTests {
    WebDriver driver;

    @BeforeMethod
    public void init() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.saucedemo.com/");
    }

    @AfterMethod
    public void tearDown() {
//        driver.quit();
    }

    @Test(enabled = false)
    public void login() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
    }

    @Test
    public void buyProduct() {
        String[] products = {"Sauce Labs Backpack", "Sauce Labs Bolt T-Shirt"};

        LoginPage loginPage = new LoginPage(driver);
        InventoryPage inventoryPage = new InventoryPage(driver);
        CheckoutStepOnePage checkoutStepOnePage = new CheckoutStepOnePage(driver);

        loginPage.login("standard_user", "secret_sauce");

        inventoryPage.addProductsToCart(products);
        double[] prices = inventoryPage.getProductPrices(products);
        String[] descriptions = inventoryPage.getProductDescriptions(products);

        inventoryPage.goToCart();

        CartPage cartPage = new CartPage(driver);

        double[] cartPrices = cartPage.getProductPrices(products);
        String[] cartDescriptions = cartPage.getProductDescriptions(products);

        Assert.assertEquals(prices, cartPrices,"");
        Assert.assertEquals(descriptions, cartDescriptions,"");

        cartPage.checkout();

        checkoutStepOnePage.checkout("Test", "Test", "12345");

        CheckoutStepTwoPage checkoutStepTwoPage = new CheckoutStepTwoPage(driver);

        double[] checkoutPrices = checkoutStepTwoPage.getProductPrices(products);
        String[] checkoutDescriptions = checkoutStepTwoPage.getProductDescriptions(products);

        Assert.assertEquals(prices, checkoutPrices);
        Assert.assertEquals(descriptions, checkoutDescriptions);
        Assert.assertEquals(
                checkoutStepTwoPage.getItemTotal(),
                checkoutStepTwoPage.getProductPricesSum(checkoutPrices)
        );

        checkoutStepTwoPage.clickFinish();

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-complete.html");
    }

}
