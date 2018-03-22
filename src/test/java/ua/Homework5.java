package ua;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Homework5 extends DriverScript {
    @DataProvider
    public Object[][] getLoginData(){
        return new String[][]{{"webinar.test@gmail.com","Xcg7299bnSmMuRLp9ITw"}};
    }
    @Test(dataProvider = "getLoginData")
    public void shop(String login,String password){
        driver.navigate().to("http://prestashop-automation.qatestlab.com.ua/");

    }
}
