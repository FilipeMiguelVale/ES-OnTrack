package selenium_dev.selenium_dev;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Test1 {
    

    @Test
    public void setUp(){
        System.out.println("done");
        System.setProperty("webdriver.gecko.driver", "driver/geckodriver");
        WebDriver driver = new FirefoxDriver();       
        driver.navigate().to("http://192.168.160.87:23000/#auth/login");

    }



}

