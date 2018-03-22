package ua;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import java.io.File;

import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;


public abstract class DriverScript {
    protected EventFiringWebDriver driver;
    protected WebDriverWait wait;
    public static WebDriver getDriver(String browser){
        switch (browser){
            case"firefox":
                System.setProperty("webdriver.gecko.driver", Paths.get(System.getProperty("user.dir"),"drivers","geckodriver.exe").toString());
                return new  FirefoxDriver();
            case"chrome":

                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+ "/drivers/chromedriver.exe");
                return new ChromeDriver();

            case"ie":
                    System.setProperty("webdriver.ie.driver",  System.getProperty("user.dir")+ "/drivers/IEDriverServer.exe");
                return new InternetExplorerDriver();
            default:System.setProperty("webdriver.chrome.driver", new File(DriverScript.class.getResource( "/chromedriver.exe").getFile()).getPath());
                return new ChromeDriver();
    }}
        @BeforeClass
        @Parameters("selenium.browser")
    public void  getConfiguredDriver(@Optional("chrome") String browser){
        WebDriver driver1=getDriver(browser);
        driver1.manage().window().maximize();

        driver1.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver= new EventFiringWebDriver(driver1);
        driver.register(new ua.EventHandler());
wait=new WebDriverWait(driver,5);
    }
    @AfterClass
    public void quitDriver() {
        if(driver!=null)
            driver.quit();
    }
}



