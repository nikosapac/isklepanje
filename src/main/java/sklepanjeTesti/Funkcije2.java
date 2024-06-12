package sklepanjeTesti;

import java.awt.AWTException;

import java.nio.file.*;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import sklepanjeTesti.config.EnvironmentConfig;

public class Funkcije2 {
	
	WebDriver driver;
	
	EnvironmentConfig cfg = ConfigFactory.create(EnvironmentConfig.class);
	String tokenURL = cfg.jwtToken();
	String email = cfg.iTriglavEmail();
	String pass = cfg.iTriglavPass();
	
	public Funkcije2(WebDriver driver) {
		this.driver = driver;
	}
	
	
	public void primerjajSpremenljivke(Object var1, Object var2) {
		if (var1 instanceof String && var2 instanceof String) {
            if (!((String) var1).equals((String) var2)) {
                RuntimeException exception = new RuntimeException("Strings are not equal: var1=" + var1 + ", var2=" + var2);
                exception.printStackTrace();
                throw exception;
            }
        } else if (var1 instanceof Integer && var2 instanceof Integer) {
            if (!((Integer) var1).equals((Integer) var2)) {
                RuntimeException exception = new RuntimeException("Integers are not equal: var1=" + var1 + ", var2=" + var2);
                exception.printStackTrace();
                throw exception;
            }
        } else {
            throw new RuntimeException("Unsupported variable types: var1=" + var1.getClass().getSimpleName() + ", var2=" + var2.getClass().getSimpleName());
        }
    }
	// qa
	public String JwtToken() throws InterruptedException  {
		driver.get(tokenURL);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		Thread.sleep(1500);
		
		WebElement mail = driver.findElement(By.name("E-naslov"));
		mail.clear();
		mail.sendKeys(email);
		
		WebElement geslo = driver.findElement(By.name("Geslo"));
		geslo.clear();
		geslo.sendKeys(pass);
		
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3500));
		WebElement prijava = driver.findElement(By.id("next"));
		prijava.click();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3500));
		String token = driver.findElement(By.id("encodedToken")).getText();
		System.out.println(token);
		
		return token;
	}
	
	public String JwtTokenDev() throws InterruptedException  {
		driver.get(tokenURL);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		Thread.sleep(1500);

		WebElement mail = driver.findElement(By.name("E-naslov"));
		mail.clear();
		mail.sendKeys(email);
		
		WebElement geslo = driver.findElement(By.name("Geslo"));
		geslo.clear();
		geslo.sendKeys(pass);
		
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3500));
		WebElement prijava = driver.findElement(By.id("next"));
		prijava.click();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3500));
		String token = driver.findElement(By.id("encodedToken")).getText();
		System.out.println(token);
		
		return token;
	}
	
	public  void FileUpload() throws AWTException, InterruptedException {
		
		Robot rb = new Robot();
				
		String path = System.getProperty("user.dir"); //POIŠČE LOKALNO POT DATOTEKE
		String fullpath = path + "\\slike\\Screenshot_20230201_105537_si.zav_triglav.mobile_zastopnik.test.ae.jpg";
		
		StringSelection str = new StringSelection(fullpath);
		
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);
		
		Thread.sleep(2000);
		
		rb.keyPress(KeyEvent.VK_CONTROL);
	    rb.keyPress(KeyEvent.VK_V);
	    
	    Thread.sleep(1000);
	    
	    rb.keyRelease(KeyEvent.VK_CONTROL);
	    rb.keyRelease(KeyEvent.VK_V);
	    
	    Thread.sleep(1000);
		
	    rb.keyPress(KeyEvent.VK_ENTER);
	    rb.keyRelease(KeyEvent.VK_ENTER);
	    
	    Thread.sleep(1000);
	}
	
	public  void FileUploadJPG() throws AWTException, InterruptedException {
		
		Robot rb = new Robot();
				
		String path = System.getProperty("user.dir"); //POIŠČE LOKALNO POT DATOTEKE
		String fullpath = path + "\\slike\\itriglav.jpg";
		
		StringSelection str = new StringSelection(fullpath);
		
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);
		
		Thread.sleep(2000);
		
		rb.keyPress(KeyEvent.VK_CONTROL);
	    rb.keyPress(KeyEvent.VK_V);
	    
	    Thread.sleep(1000);
	    
	    rb.keyRelease(KeyEvent.VK_CONTROL);
	    rb.keyRelease(KeyEvent.VK_V);
	    
	    Thread.sleep(1000);
		
	    rb.keyPress(KeyEvent.VK_ENTER);
	    rb.keyRelease(KeyEvent.VK_ENTER);
	    
	    Thread.sleep(1000);
	}
	
	public void ScrollInKlik(String pot) throws InterruptedException {
		/*
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath(pot))).build().perform();
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,100)");
		Thread.sleep(500);
		driver.findElement(By.xpath(pot)).click();
		Thread.sleep(1000);
		*/
		
		int viewportHeight = ((Long) ((JavascriptExecutor) driver).executeScript("return window.innerHeight")).intValue();
	    int halfViewport = viewportHeight / 2;
	    
	    WebElement ele = driver.findElement(By.xpath(pot));
    	int ele_Position = ele.getLocation().getY();
    	((JavascriptExecutor) driver).executeScript("window.scroll(0, " + (ele_Position - halfViewport) + ");");
    	Thread.sleep(1000);
    	driver.findElement(By.xpath(pot)).click(); 
    	Thread.sleep(1000);
		
	}
		
	public void ScrollInKlik2(By locator) throws InterruptedException {
		/*Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(locator)).build().perform();
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,100)");
		Thread.sleep(500);
		driver.findElement(locator).click();
		Thread.sleep(1000);
		*/
		int viewportHeight = ((Long) ((JavascriptExecutor) driver).executeScript("return window.innerHeight")).intValue();
	    int halfViewport = viewportHeight / 2;
	    
	    WebElement ele = driver.findElement(locator);
    	int ele_Position = ele.getLocation().getY();
    	((JavascriptExecutor) driver).executeScript("window.scroll(0, " + (ele_Position - halfViewport) + ");");
    	Thread.sleep(1000);
    	driver.findElement(locator).click(); 
    	Thread.sleep(1000);
		
	}
	
	public void ScrollInKlik2WebEl(WebElement webEll) throws InterruptedException {
	    int viewportHeight = ((Long) ((JavascriptExecutor) driver).executeScript("return window.innerHeight")).intValue();
	    int halfViewport = viewportHeight / 2;
	    
	    int ele_Position = webEll.getLocation().getY();
	    ((JavascriptExecutor) driver).executeScript("window.scroll(0, " + (ele_Position - halfViewport) + ");");
	    Thread.sleep(1000);
	    webEll.click(); 
	    Thread.sleep(1000);
	}
	
	//First make list of web elements:
	//List<WebElement> spremeniPak = driver.findElements(By.className("change-package"));
	//usage: f.ScrollInKlik2WebEleList(spremeniPak.get(i), spremeniPak.get(i));
	public void ScrollInKlik2WebEleList(WebElement scrollElement, WebElement clickElement) throws InterruptedException {
	    int viewportHeight = ((Long) ((JavascriptExecutor) driver).executeScript("return window.innerHeight")).intValue();
	    int halfViewport = viewportHeight / 2;

	    int ele_Position = scrollElement.getLocation().getY();
	    ((JavascriptExecutor) driver).executeScript("window.scroll(0, " + (ele_Position - halfViewport) + ");");
	    Thread.sleep(1000);
	    clickElement.click(); 
	    Thread.sleep(1000);
	}

	
	public void ScrollToElement(By locator) throws InterruptedException {

		int viewportHeight = ((Long) ((JavascriptExecutor) driver).executeScript("return window.innerHeight")).intValue();
	    int halfViewport = viewportHeight / 2;
	    
	    WebElement ele = driver.findElement(locator);
    	int ele_Position = ele.getLocation().getY();
    	((JavascriptExecutor) driver).executeScript("window.scroll(0, " + (ele_Position - halfViewport) + ");");
    	Thread.sleep(1000);
		
	}
	
	public void waitForLoaderToFinish(WebDriver driver, By loaderLocator) throws InterruptedException {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
	    wait.until(ExpectedConditions.presenceOfElementLocated(loaderLocator));
	    wait.until(ExpectedConditions.invisibilityOfElementLocated(loaderLocator));
	    Thread.sleep(1000);
	}
	
	public String Datum(int dnevi) {
		
		
		  SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");

		  
 	      Calendar calendar = Calendar.getInstance();
 	      calendar.add(Calendar.DAY_OF_MONTH, dnevi); 

 	      String datum = formatter.format(calendar.getTime());
 
 	      return datum;
	}
	
	public String Datum2(int dnevi) {
		
		
		  SimpleDateFormat formatter = new SimpleDateFormat("d.M.yyyy");

		  
	      Calendar calendar = Calendar.getInstance();
	      calendar.add(Calendar.DAY_OF_MONTH, dnevi); 

	      String datum = formatter.format(calendar.getTime());

	      return datum;
	}
	
	public String DatumMesci(int mesec) {
		
		  SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");

	      Calendar calendar = Calendar.getInstance();
	      calendar.add(Calendar.MONTH, mesec); 

	      String datum = formatter.format(calendar.getTime());

	      return datum;
	}
	
	public void reCaptcha() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(
                By.xpath("/html/body/div[3]/div[2]/div/div[3]/div/div[1]/div/div/div/iframe")));
       
         wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("/html/body/div[2]/div[3]/div[1]/div/div/span/div[1]"))).click();
         
         driver.switchTo().defaultContent();
         Thread.sleep(1000);
	}
	/*
	public void Cookiji(String xpath) throws InterruptedException {
		// klikne na X ikono
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(280));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Moja zavarovanja']")));
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		
		if(IsVisible(By.xpath(xpath)) == true) {
		
		driver.findElement(By.xpath(xpath)).click();
		} else if(IsVisible(By.xpath(xpath)) == false) {
			System.out.println("Piškoti niso prikazani");
		}
		
	}	
	*/
	
	public void Cookiji(String xpath) throws InterruptedException {
		// klikne na X ikono
		try {
			Thread.sleep(1000);
			
			waitForLoaderToFinish(driver,By.className("loader"));
			
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(90));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
					
			if(IsVisible(By.xpath(xpath)) == true) {
				driver.findElement(By.xpath(xpath)).click();;
			} else if(IsVisible(By.xpath(xpath)) == false) {
				System.out.println("Piškoti niso prikazani");
			}
		}
		catch(TimeoutException e) {
			e.printStackTrace();
		}
	}
	
	public String DatumUre(int ure) {
		
		  SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");

	      Calendar calendar = Calendar.getInstance();
	      calendar.add(Calendar.HOUR, ure); 

	      String datum = formatter.format(calendar.getTime());

	      return datum;
	}
	
	public void checkSklenitev() throws Exception {
		
		//WebElement error = driver.findElement(By.xpath("//*[contains(text(),'Prišlo je do napake')]"));
		
		WebElement ele = driver.findElement(By.xpath("//*[text()='SKLENITEV USPEŠNA']"));
		
		new WebDriverWait(driver,Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOf(ele));
		
		if(ele.isDisplayed() == true) {
			System.out.println("Sklenitev je bila uspešna");
		} else {
			throw new Exception("Sklenitev NI bila uspešna");
		}
	}
	
	public void virtualniAsistent() throws InterruptedException {
	    driver.findElement(By.xpath("//a[contains(@class,'close-control')]")).click();
		Thread.sleep(2500);
		driver.findElement(By.xpath("//img[contains(@class,'close-control')]")).click();
	}
	
	public void implicitWait(int sec) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(sec));
	}
	
	// Validacija
	
	public boolean Errorji(By locator)
	{
	    List<WebElement> elementi = driver.findElements(locator);
	    if (elementi.isEmpty())
	    {
	        return false;
	    }
	    else
	    {
	        return elementi.get(0).isDisplayed();
	    }
	}
	
	public boolean IsVisible(By locator)
	{
	    List<WebElement> elementi = driver.findElements(locator);
	    if (elementi.isEmpty())
	    {
	        return false;
	    }
	    else
	    {
	        return elementi.get(0).isDisplayed();
	    }
	}
	
	public void countErrorjevByClassName(String className) {
		List<WebElement> vsiErrorji = driver.findElements(By.className(className));
		List<WebElement> elementi = new ArrayList<WebElement>();
		//System.out.println(vsiErrorji.size());
		
	    for ( WebElement we: vsiErrorji) {        
	       if(we.isDisplayed()) {
	    	  // System.out.println(we);
	    	   elementi.add(we);
	       }
	    }
	    System.out.println(elementi.size());
	}
	
	public void VpisEmaila() throws AWTException {
		
		// Create a new Robot instance
        Robot robot = new Robot();
        
        // Simulate pressing the "n" key
        robot.keyPress(KeyEvent.VK_N);
        robot.keyRelease(KeyEvent.VK_N);

        // Simulate pressing the "i" key
        robot.keyPress(KeyEvent.VK_I);
        robot.keyRelease(KeyEvent.VK_I);

        // Simulate pressing the "k" key
        robot.keyPress(KeyEvent.VK_K);
        robot.keyRelease(KeyEvent.VK_K);

        // Simulate pressing the "o" key
        robot.keyPress(KeyEvent.VK_O);
        robot.keyRelease(KeyEvent.VK_O);

        // Simulate pressing the "." (dot) key
        robot.keyPress(KeyEvent.VK_PERIOD);
        robot.keyRelease(KeyEvent.VK_PERIOD);

        // Simulate pressing the "s" key
        robot.keyPress(KeyEvent.VK_S);
        robot.keyRelease(KeyEvent.VK_S);

        // Simulate pressing the "a" key
        robot.keyPress(KeyEvent.VK_A);
        robot.keyRelease(KeyEvent.VK_A);

        // Simulate pressing the "p" key
        robot.keyPress(KeyEvent.VK_P);
        robot.keyRelease(KeyEvent.VK_P);

        // Simulate pressing the "a" key
        robot.keyPress(KeyEvent.VK_A);
        robot.keyRelease(KeyEvent.VK_A);

        // Simulate pressing the "c" key
        robot.keyPress(KeyEvent.VK_C);
        robot.keyRelease(KeyEvent.VK_C);

        // Simulate pressing the "@" key
        // Simulate pressing the Alt Gr key
        robot.keyPress(KeyEvent.VK_ALT_GRAPH);
        // Simulate pressing the "v" key
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_ALT_GRAPH);

        // Simulate pressing the "t" key
        robot.keyPress(KeyEvent.VK_T);
        robot.keyRelease(KeyEvent.VK_T);

        // Simulate pressing the "r" key
        robot.keyPress(KeyEvent.VK_R);
        robot.keyRelease(KeyEvent.VK_R);

        // Simulate pressing the "i" key
        robot.keyPress(KeyEvent.VK_I);
        robot.keyRelease(KeyEvent.VK_I);

        // Simulate pressing the "g" key
        robot.keyPress(KeyEvent.VK_G);
        robot.keyRelease(KeyEvent.VK_G);

        // Simulate pressing the "l" key
        robot.keyPress(KeyEvent.VK_L);
        robot.keyRelease(KeyEvent.VK_L);

        // Simulate pressing the "a" key
        robot.keyPress(KeyEvent.VK_A);
        robot.keyRelease(KeyEvent.VK_A);

        // Simulate pressing the "v" key
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);

        // Simulate pressing the "." (dot) key
        robot.keyPress(KeyEvent.VK_PERIOD);
        robot.keyRelease(KeyEvent.VK_PERIOD);

        // Simulate pressing the "s" key
        robot.keyPress(KeyEvent.VK_S);
        robot.keyRelease(KeyEvent.VK_S);

        // Simulate pressing the "i" key
        robot.keyPress(KeyEvent.VK_I);
        robot.keyRelease(KeyEvent.VK_I);

        // Simulate pressing the Enter key
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
		
	}
	
}
