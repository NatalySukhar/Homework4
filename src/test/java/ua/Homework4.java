package ua;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.nio.file.Paths;
import java.util.UUID;

public class Homework4 extends DriverScript {
   private String name= UUID.randomUUID().toString();
    private int count=(0 + (int) (Math.random() * 100));
    private double price=(0.1 + (int) (Math.random() * 100));
    private By category= By.id("subtab-AdminCatalog");
    private By newCategory= By.id("page-header-desc-configuration-add");
    private By nameCategory= By.id("form_step1_name_1");//("name_1");
    private By eInput= By.id("email");
    private By pInput= By.id("passwd");
    private By loginBtn= By.name("submitLogin");
    private By countI= By.cssSelector("#tab_step3 > a");
    private By countIrandom= By.cssSelector("#form_step3_qty_0");
    private By priceI= By.cssSelector("#tab_step2 > a");
    private By priceIrandom= By.cssSelector("#form_step2_price");
    private By switchInput= By.className("switch-input");
    private By submitProdact= By.cssSelector("#form > div.product-footer > div.col-lg-6.text-lg-right > div > button.btn.btn-primary.js-btn-save");
    @DataProvider
    public Object[][] getLoginData(){
        return new String[][]{{"webinar.test@gmail.com","Xcg7299bnSmMuRLp9ITw"}};
    }
    @Test(dataProvider = "getLoginData")
    public void open(String login,String password){
        EventFiringWebDriver driver=getConfiguredDriver();
        driver.navigate().to("http://prestashop-automation.qatestlab.com.ua/admin147ajyvk0/");//driver.get(Properties.getBaseAdminDir());}
        driver.findElement(eInput).sendKeys(login);
        driver.findElement(pInput).sendKeys(password);
        driver.findElement(loginBtn).click();
        WebDriverWait wait=new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.elementToBeClickable(category));
        WebElement element=driver.findElement(category);
        Actions actions=new Actions(driver);
        actions.moveToElement(element).build().perform();
        wait.until(ExpectedConditions.elementToBeClickable( element.findElements(By.cssSelector("li")).get(0)));
        element.findElements(By.cssSelector("li")).get(0).click();
        System.out.println("Page title is: " +driver.getTitle());
              Assert.assertEquals(driver.getTitle(),"товары • prestashop-automation", "Unexpected title");

        wait.until(ExpectedConditions.elementToBeClickable(newCategory));

        driver.findElement(newCategory).click();
        wait.until(ExpectedConditions.elementToBeClickable(nameCategory));
        driver.findElement(nameCategory).sendKeys(name);
        driver.findElement(countI).click();
        driver.findElement(countIrandom).sendKeys(Keys.BACK_SPACE);
        driver.findElement(countIrandom).sendKeys(count+"");
        driver.findElement(priceI).click();
        driver.findElement(priceIrandom).sendKeys(Keys.CONTROL,"a");
        driver.findElement(priceIrandom).sendKeys(Keys.DELETE);
                driver.findElement(priceIrandom).sendKeys(price+"");
        driver.findElement(switchInput).click();
        driver.findElement(submitProdact).click();
    }}
/*package ua;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Homework4 extends DriverScript {

    @Parameters("browser")
    @BeforeMethod
    public void getConfiguredDriver(@Optional("chrome") String browser){
        driver=getDriver(browser);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);}
    @DataProvider
    public Object[][] getLoginData(){
        return new String[][]{{"webinar.test@gmail.com","Xcg7299bnSmMuRLp9ITw"}};
    }
    private String name= UUID.randomUUID().toString();
    private int count=(0 + (int) (Math.random() * 100));
    private float price=(0 + (float) (Math.random() * 100));
    private By category= By.id("subtab-AdminCatalog");
    private By newCategory= By.id("page-header-desc-configuration-add");
    private By nameCategory= By.id("form_step1_name_1");//("name_1");
    private By eInput= By.id("email");
    private By pInput= By.id("passwd");
    private By loginBtn= By.name("submitLogin");
    private By countI= By.cssSelector("#tab_step3 > a");
    private By countIrandom= By.cssSelector("#form_step3_qty_0");
    private By priceI= By.cssSelector("#tab_step2 > a");
    private By priceIrandom= By.cssSelector("#form_step2_price");
    private By switchInput= By.className("switch-input");
    private By submitProdact= By.cssSelector("#form > div.product-footer > div.col-lg-6.text-lg-right > div > button.btn.btn-primary.js-btn-save");

    @Test(dataProvider = "getLoginData")
    public void open(String login,String password){
        driver.navigate().to("http://prestashop-automation.qatestlab.com.ua/admin147ajyvk0/");//driver.get(Properties.getBaseAdminDir());}
   driver.findElement(eInput).sendKeys(login);
    driver.findElement(pInput).sendKeys(password);
   driver.findElement(loginBtn).click();
         wait.until(ExpectedConditions.elementToBeClickable(category));
        WebElement element=driver.findElement(category);
        Actions actions=new Actions(driver);
        actions.moveToElement(element).build().perform();
        wait.until(ExpectedConditions.elementToBeClickable( element.findElements(By.cssSelector("li")).get(0)));
        element.findElements(By.cssSelector("li")).get(0).click();
        System.out.println("Page title is: " +driver.getTitle());
        wait.until(ExpectedConditions.elementToBeClickable( element.findElements(By.cssSelector("li")).get(0)));
        element.findElements(By.cssSelector("li")).get(0).click();
        System.out.println("Page title is: " +driver.getTitle());
        Assert.assertEquals(driver.getTitle(),"товары • prestashop-automation", "Unexpected title");

        wait.until(ExpectedConditions.elementToBeClickable(newCategory));
        driver.findElement(newCategory).click();
        wait.until(ExpectedConditions.elementToBeClickable(nameCategory));
        driver.findElement(nameCategory).sendKeys(name);
        driver.findElement(countI).click();
        driver.findElement(countIrandom).sendKeys((count+""));
        driver.findElement(priceI).click();
        driver.findElement(priceIrandom).sendKeys((price+""));
        driver.findElement(switchInput).click();

        driver.findElement(submitProdact).click();
  }}*/


