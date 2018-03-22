package ua;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Homework5 extends DriverScript {
    private By version= By.id("_desktop_logo");
    private By versionMobile= By.id("_mobile_logo");
    @DataProvider
    public Object[][] getLoginData(){
        return new String[][]{{"webinar.test@gmail.com","Xcg7299bnSmMuRLp9ITw"}};
    }
    @Test(dataProvider = "getLoginData")
    public void shop(String login,String password){
        driver.navigate().to("http://prestashop-automation.qatestlab.com.ua/");
              if (driver.findElement(version).isEnabled())
            System.out.println("The driver is desktop");
        else if (driver.findElement(versionMobile).isEnabled())
            System.out.println("The driver is mobile");
    }
}
