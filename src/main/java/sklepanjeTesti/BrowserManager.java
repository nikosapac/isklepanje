package sklepanjeTesti;

import java.time.Duration;
import java.util.Hashtable;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserManager {
	 
	   public static WebDriver doBrowserSetup(String browserName){

	        WebDriver driver = null;
	        if (browserName.equalsIgnoreCase("chrome")){
	            //steup chrome browser
	         //   WebDriverManager.chromedriver().setup();
	    		
	    		ChromeOptions options = new ChromeOptions();
	    	    options.addArguments("--start-maximized");
	    	    options.addArguments("--remote-allow-origins=*");
	   // 	    options.addArguments("--headless=new");
	            options.addArguments("window-size=1920,1080");
	            	            
	            //initialize driver for chrome
	            driver = new ChromeDriver(options);

	            //add implicit timeout
	            driver.manage()
	           .timeouts()
	           .implicitlyWait(Duration.ofSeconds(30));

	        }
	   return driver;
	}
}