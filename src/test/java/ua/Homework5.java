package ua;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class Homework5 extends DriverScript {
    private By version = By.id("_desktop_logo");
    private By versionMobile = By.id("_mobile_logo");
    private By allProducts = By.className("all-product-link");
    private By randomGoods = By.className("product-title");
    private By name = By.className("h1");
    private By prise = By.cssSelector("#main > div.row > div:nth-child(2) > div.product-prices > div.product-price.h5 > div > span");
    private By addTo = By.className("add-to-cart");
    private By goToReg = By.cssSelector("#blockcart-modal > div > div > div.modal-body > div > div:nth-child(2) > div > a");
    private By name1 = By.cssSelector("#main > div > div.cart-grid-body.col-xs-12.col-lg-8 > div.card.cart-container > div.cart-overview.js-cart > ul > li > div > div.product-line-grid-body.col-md-4.col-xs-8 > div:nth-child(1) > a");
    private By prise1 = By.cssSelector("#main > div > div.cart-grid-body.col-xs-12.col-lg-8 > div.card.cart-container > div.cart-overview.js-cart > ul > li > div > div.product-line-grid-body.col-md-4.col-xs-8 > div:nth-child(2) > span");
    private By quantity = By.className("form-control");
    private By submit = By.cssSelector("#main > div > div.cart-grid-right.col-xs-12.col-lg-4 > div > div.checkout.cart-detailed-actions.card-block > div > a");
    @DataProvider
    public Object[][] getLoginData() {
        return new String[][]{{"webinar.test@gmail.com", "Xcg7299bnSmMuRLp9ITw"}};
    }

    @Test(dataProvider = "getLoginData")
    public void shop(String login, String password) {
        driver.navigate().to("http://prestashop-automation.qatestlab.com.ua/");
        if (driver.findElement(version).isEnabled())
            System.out.println("The driver is desktop");
        else if (driver.findElement(versionMobile).isEnabled())
            System.out.println("The driver is mobile");
    }

    @Test(dependsOnMethods = "shop")
    public void sell() {
        driver.navigate().to("http://prestashop-automation.qatestlab.com.ua/");
        wait.until(ExpectedConditions.elementToBeClickable(allProducts));
        driver.findElement(allProducts).click();
              driver.findElements(randomGoods).get((int) (Math.random() * 6)).click();
       String nameI= driver.findElement(name).getText().toLowerCase();
        String priceI =driver.findElement(prise).getText().toLowerCase();
        System.out.println(nameI+","+priceI);
        driver.findElement(addTo).click();
        wait.until(ExpectedConditions.elementToBeClickable(goToReg));
        driver.findElement(goToReg).click();
        String nameB= driver.findElement(name1).getText().toLowerCase();
        String priceB =driver.findElement(prise1).getText().toLowerCase();
        String quan =driver.findElement(quantity).getAttribute("value");
        if (nameI.equals(nameB)&priceI.equals(priceB)&quan.equals("1"))
            System.out.println("Order is OK");
        driver.findElement(submit).click();

    }}