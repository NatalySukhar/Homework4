package ua;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import javax.security.auth.login.Configuration;
import java.beans.EventHandler;
import java.io.File;

import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;


public abstract class DriverScript {
    public static WebDriver getDriver(){
        System.setProperty("webdriver.gecko.driver", Paths.get(System.getProperty("user.dir"),"drivers","geckodriver.exe").toString());
        WebDriver driver=new FirefoxDriver();
        return driver;
    }
    public static EventFiringWebDriver getConfiguredDriver(){
        WebDriver driver=getDriver();
        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        EventFiringWebDriver wrappedDriver= new EventFiringWebDriver(driver);
        wrappedDriver.register(new ua.EventHandler());
        return wrappedDriver;
    }
    public static void quitDriver(WebDriver driver) {driver.quit();

    }

}
