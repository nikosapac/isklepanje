package sklepanjeTesti;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Prijave {
	
	WebDriver driver;
	
	public Prijave(WebDriver driver) {
		this.driver=driver;
	}
	
	public void ItriglavPrijava() throws InterruptedException {
		// iTriglav prijava
		
		Funkcije funk = new Funkcije(driver);
		
		driver.get("https://skleni-qa.triglav.si/TriglavLogin/odjava");
		driver.navigate().to("https://skleni-qa.triglav.si/iTriglav3/");
		Thread.sleep(1000);
		
		driver.findElement(By.id("email")).clear();
		Thread.sleep(500);
		driver.findElement(By.id("email")).click();
		Thread.sleep(500);
		driver.findElement(By.id("email")).sendKeys("niko.sapac@gmail.com");
		Thread.sleep(500);
		
		driver.findElement(By.id("Password")).clear();
		Thread.sleep(500);
		driver.findElement(By.id("Password")).click();
		Thread.sleep(500);
		driver.findElement(By.id("Password")).sendKeys("Testgeslo1");
		Thread.sleep(500);
		
		driver.findElement(By.id("next")).click();
		
		Thread.sleep(5000);
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Moja zavarovanja']")));
		
		if(funk.IsVisible(By.xpath("//*[@id=\"onetrust-close-btn-container\"]/button")) == true) {
			driver.findElement(By.xpath("//*[@id=\"onetrust-close-btn-container\"]/button")).click();
		}
		
		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"onetrust-close-btn-container\"]/button")));
	//	Thread.sleep(4000);
		//funk.Cookiji("//*[@id=\"onetrust-close-btn-container\"]/button");
		//driver.findElement(By.className("close-control")).click();
		//WebElement element = driver.findElement(By.xpath("/html/body/div[8]/div[2]/div/div/div[3]/div[2]/div[1]/div/div/form/div/div[4]/a[2]"));
		
		//if(element.isDisplayed()) {
		//	element.click();
		//	Thread.sleep(500);
		//}
		
		//driver.findElement(By.xpath("/html/body/div[11]/div[2]/div/div[1]/div/div[2]/div/button[2]")).click();
		
		//driver.findElement(By.xpath("/html/body/div[14]/div[2]/div/div[1]/div/div[2]/div/button[2]")).click();
	}
	
	public void ItriglavPrijavaTestni() throws InterruptedException {
		
		Funkcije funk = new Funkcije(driver);
		
		// iTriglav prijava
		driver.get("https://skleni-qa.triglav.si/iTriglav3");
		//driver.navigate().to("https://skleni-qa.triglav.si/iTriglav3/");
		Thread.sleep(1000);
		
		driver.findElement(By.id("email")).clear();
		Thread.sleep(500);
		driver.findElement(By.id("email")).click();
		Thread.sleep(500);
		driver.findElement(By.id("email")).sendKeys("sandra.skrlec-marter@triglav.si");
		Thread.sleep(500);

		driver.findElement(By.id("Password")).clear();
		Thread.sleep(500);
		driver.findElement(By.id("Password")).click();
		Thread.sleep(500);
		driver.findElement(By.id("Password")).sendKeys("Test@123");
		Thread.sleep(500);

		driver.findElement(By.id("next")).click();
		
		Thread.sleep(5000);
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[4]/header[1]/div[3]/div[1]/div[3]/ul[2]/li[1]/a")));
		Thread.sleep(4000);
		
		funk.Cookiji("//*[@id=\"onetrust-close-btn-container\"]/button");
		
		//WebElement element = driver.findElement(By.xpath("/html/body/div[8]/div[2]/div/div/div[3]/div[2]/div[1]/div/div/form/div/div[4]/a[2]"));
		
		//if(element.isDisplayed()) {
		//	element.click();
		//	Thread.sleep(500);
		//}
		
		//driver.findElement(By.xpath("/html/body/div[11]/div[2]/div/div[1]/div/div[2]/div/button[2]")).click();
	}
	
	public void ItriglavPrijavaVnos(String mail, String geslo) throws InterruptedException {

		Funkcije funk = new Funkcije(driver);
		
		// iTriglav prijava
		driver.get("https://skleni-qa.triglav.si/iTriglav3");
		//driver.navigate().to("https://skleni-qa.triglav.si/iTriglav3/");
		
		Thread.sleep(5000);

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		
		driver.findElement(By.id("email")).clear();
		Thread.sleep(500);
		driver.findElement(By.id("email")).click();
		Thread.sleep(500);
		driver.findElement(By.id("email")).sendKeys(mail);
		Thread.sleep(500);
		
		driver.findElement(By.id("Password")).clear();
		Thread.sleep(500);
		driver.findElement(By.id("Password")).click();
		Thread.sleep(500);
		driver.findElement(By.id("Password")).sendKeys(geslo);
		Thread.sleep(500);
	
		
		driver.findElement(By.id("next")).click();
		
		//Thread.sleep(5000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		funk.Cookiji("//*[@id=\"onetrust-close-btn-container\"]/button");
		
			
	}
	
	public void ItriglavPrijavaSklepanje(String mail, String geslo) throws InterruptedException {
		
		Thread.sleep(1500);
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		
	//	driver.findElement(By.id("email")).click();
	//	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(By.id("email")));
		driver.findElement(By.id("email")).sendKeys(mail);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
	//	driver.findElement(By.id("Password")).click();
	//	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.id("Password")).sendKeys(geslo);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.findElement(By.id("next")).click();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
	}
	
	public void ItriglavPrijavaVnos2(String mail, String geslo) throws InterruptedException {
		Thread.sleep(2000);
		
		// iTriglav prijava
		driver.get("https://skleni-qa.triglav.si/iTriglav3");
		//driver.navigate().to("https://skleni-qa.triglav.si/iTriglav3/");
		Thread.sleep(1000);
		
		driver.findElement(By.id("email")).click();
		Thread.sleep(500);
		driver.findElement(By.id("email")).sendKeys(mail);
		Thread.sleep(500);
		
		driver.findElement(By.id("Password")).click();
		Thread.sleep(500);
		driver.findElement(By.id("Password")).sendKeys(geslo);
		Thread.sleep(500);
	
		driver.findElement(By.id("next")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//WebElement ele = driver.findElement(By.linkText("Pristopi"));
		new WebDriverWait(driver,Duration.ofSeconds(240)).until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Pristopi")));
		Thread.sleep(1000);
		
	}
	
	public void SklepanjePrijava() throws InterruptedException {
		
		Funkcije funk = new Funkcije(driver);
		
		driver.get("https://skleni-qa.triglav.si/isklepanje/ldap-login");
		
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
		
		//driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[1]/div/div[2]/div/button[2]")).click();
		
		funk.ScrollInKlik3(By.id("password"));
		Thread.sleep(500);
		driver.findElement(By.id("password")).sendKeys("0c.08I8b");
		Thread.sleep(500);
				
		Thread.sleep(1000);
		
		driver.findElement(By.className("nextStep")).click();
		
		Thread.sleep(1000);
	}
	
	
	

}
