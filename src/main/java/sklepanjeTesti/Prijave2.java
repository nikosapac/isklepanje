package sklepanjeTesti;

import java.time.Duration;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import sklepanjeTesti.config.EnvironmentConfig;

public class Prijave2 {
	
	WebDriver driver;
	
	EnvironmentConfig cfg = ConfigFactory.create(EnvironmentConfig.class);
	String baseUrl = cfg.baseURL();
	
	String email = cfg.iTriglavEmail();
	String password = cfg.iTriglavPass();
	
	public Prijave2(WebDriver driver) {
		this.driver=driver;
	}
		
	public void ItriglavPrijavaSklepanje() throws InterruptedException {
		
		Thread.sleep(1500);
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		
	//	driver.findElement(By.id("email")).click();
	//	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(By.id("email")));
		driver.findElement(By.id("email")).sendKeys(email);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
	//	driver.findElement(By.id("Password")).click();
	//	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.id("Password")).sendKeys(password);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.findElement(By.id("next")).click();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
	}
	
	public void ItriglavPrijavaSklepanje2(String email2, String password2) throws InterruptedException {
		
		Thread.sleep(1500);
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		
	//	driver.findElement(By.id("email")).click();
	//	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(By.id("email")));
		driver.findElement(By.id("email")).sendKeys(email2);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
	//	driver.findElement(By.id("Password")).click();
	//	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.id("Password")).sendKeys(password2);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.findElement(By.id("next")).click();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
	}
	

	
	public void SklepanjePrijava() throws InterruptedException {
		
		Funkcije funk = new Funkcije(driver);
		
		driver.get(baseUrl + "/isklepanje/ldap-login");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		
	//	driver.findElement(By.xpath("//*[@id=\"onetrust-close-btn-container\"]/button")).click();
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.findElement(By.id("username")).click();
		Thread.sleep(500);
		driver.findElement(By.id("username")).sendKeys("TSNSAPAC");
		Thread.sleep(500);
		
		funk.implicitWait(10);
		Thread.sleep(1000);
		
		if(funk.IsVisible(By.xpath("//*[@class='s-alert-close']")) == true) {
			driver.findElement(By.xpath("//*[@class='s-alert-close']")).click();
		} 
		
		funk.implicitWait(10);
		Thread.sleep(1000);
		
		if(funk.IsVisible(By.xpath("//*[@id=\"onetrust-close-btn-container\"]/button")) == true) {
			driver.findElement(By.xpath("//*[@id=\"onetrust-close-btn-container\"]/button")).click();
		}
				
		funk.ScrollInKlik3(By.id("password"));
		Thread.sleep(500);
		driver.findElement(By.id("password")).sendKeys("0c.08I8b");
		Thread.sleep(500);
				
		Thread.sleep(1000);
		
		driver.findElement(By.className("nextStep")).click();
		
		Thread.sleep(1000);
	}
	
	public void ItriglavPrijavaVnos() throws InterruptedException {

		Funkcije f = new Funkcije(driver);
		
		driver.get(baseUrl + "/iTriglav3");
		
		Thread.sleep(2000);
		
		f.implicitWait(15);
		
		WebElement inputElement = driver.findElement(By.id("email"));
		
		// Use WebDriverWait to wait for the element to be both visible and enabled (ready to accept text)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(inputElement));
        wait.until(ExpectedConditions.elementToBeClickable(inputElement));
		
	//	driver.findElement(By.id("email")).click();
	//	Thread.sleep(500);
		driver.findElement(By.id("email")).sendKeys(email);
		Thread.sleep(500);
		f.implicitWait(15);
		
	//	driver.findElement(By.id("Password")).click();
	//	Thread.sleep(500);
		driver.findElement(By.id("Password")).sendKeys(password);
		Thread.sleep(500);
		f.implicitWait(15);
		
		driver.findElement(By.id("next")).click();
		
		//Thread.sleep(5000);
		f.implicitWait(30);
		
		f.Cookiji("//*[@id=\"onetrust-close-btn-container\"]/button");
		
	}
	

}
