package ua;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import java.io.File;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public abstract class DriverScript {
    protected EventFiringWebDriver driver;
    protected WebDriverWait wait;
    public  WebDriver getDriver(String browser){
        switch (browser){
            case"firefox":
                System.setProperty("webdriver.gecko.driver", Paths.get(System.getProperty("user.dir"),"drivers","geckodriver.exe").toString());
                return new  FirefoxDriver();
            case"chrome":

                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+ "/drivers/chromedriver.exe");
                return new ChromeDriver();

            case"ie":
                    System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+ "/drivers/IEDriver .exe");
                return new InternetExplorerDriver();
            case "headless-chrome":
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+ "/drivers/chromedriver.exe");
                ChromeOptions options=new ChromeOptions();
                options.addArguments("headless");
             options.addArguments("windows-size=800x600");
             return new ChromeDriver(options);
            case "remoteChrome":
                ChromeOptions optionsRemote=new ChromeOptions();
                try {return new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), optionsRemote);}
                catch (MalformedURLException e){e.printStackTrace();}

            case "mobile":
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+ "/drivers/chromedriver.exe");
                Map <String, String> mobileEmulation= new HashMap<>();
                mobileEmulation.put("deviceName", "IPhone 6");
            ChromeOptions mobileOptions=new ChromeOptions();
            mobileOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
            return new ChromeDriver(mobileOptions);

            default:System.setProperty("webdriver.chrome.driver", new File(DriverScript.class.getResource( "/chromedriver.exe").getFile()).getPath());
                return new ChromeDriver();
    }}
        @BeforeClass
        @Parameters("selenium.browser")
    public void  getConfiguredDriver(@Optional("remoteChrome") String browser){
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



