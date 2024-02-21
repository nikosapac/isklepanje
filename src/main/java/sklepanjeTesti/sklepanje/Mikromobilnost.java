package sklepanjeTesti.sklepanje;

import java.awt.AWTException;
import java.time.Duration;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import sklepanjeTesti.Funkcije;
import sklepanjeTesti.Funkcije2;
import sklepanjeTesti.Prijave;
import sklepanjeTesti.Prijave2;
import sklepanjeTesti.config.EnvironmentConfig;

public class Mikromobilnost {
	
	public WebDriver driver;
	
	EnvironmentConfig cfg = ConfigFactory.create(EnvironmentConfig.class);
	String baseURL = cfg.baseURL();
	
	public Mikromobilnost(WebDriver driver) {
		this.driver=driver;
	}

	@Test
	public void MikromobilnostTest() throws Exception {
														
		Funkcije2 funk = new Funkcije2(driver);
		
		int viewportHeight = ((Long) ((JavascriptExecutor) driver).executeScript("return window.innerHeight")).intValue();
	    int halfViewport = viewportHeight / 2;
		
		Prijave2 p = new Prijave2(driver);
		//p.ItriglavPrijavaVnos("niko.sapac@triglav.si","Testgeslo123");
		p.SklepanjePrijava();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		// MIKROMOBILNOST
		
		driver.get(baseURL + "/isklepanje/mikromobilnost/mikro_zavarovanje");
		Thread.sleep(1500);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.findElement(By.className("labelLeft")).click();
		Thread.sleep(500);
		
		new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.urlToBe(baseURL + "/isklepanje/mikromobilnost/mikro_zavarovanje_1"));
		
		// KORAK 1
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Thread.sleep(1000);
		
		driver.findElement(By.className("select2-float-placeholder")).click();
		Thread.sleep(1500);
		driver.findElement(By.xpath("//select[option[@value='BYCICLE']]")).sendKeys("kolo");
		Thread.sleep(1500);
		
		funk.ScrollInKlik2(By.className("nextStep"));
		
		new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.urlToBe(baseURL + "/isklepanje/mikromobilnost/mikro_zavarovanje_2"));
		
		// KORAK 2 
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		if(funk.IsVisible(By.xpath("//a[text()='Prijavite se']"))) {
		
			driver.findElement(By.xpath("//a[text()='Prijavite se']")).click();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		p.ItriglavPrijavaSklepanje();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		funk.ScrollInKlik("//*[contains(text(),'Znamka')]");
		driver.findElement(By.id("znamka")).sendKeys("TestZnamka");
		
		funk.ScrollInKlik2(By.xpath("//*[contains(text(),'Serijska številka')]"));
		Thread.sleep(500);
		driver.findElement(By.id("serialNumber")).sendKeys("TestStevilka123");
		
		funk.ScrollInKlik("(//span[@class='cs-slider'])[1]");

		funk.ScrollInKlik("(//span[@class='cs-slider'])[2]");
		funk.ScrollInKlik("(//span[@class='cs-slider'])[3]");
		
		funk.ScrollInKlik2(By.className("nextStep"));
		
		
		new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.urlToBe(baseURL + "/isklepanje/mikromobilnost/mikro_zavarovanje_3"));

		// KORAK 3
		
		Thread.sleep(1000);

		funk.ScrollInKlik("(//span[@class='cs-slider'])[1]");
		funk.ScrollInKlik("(//span[@class='cs-slider'])[2]");
		
		funk.ScrollInKlik2(By.className("nextStep"));
		
		new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.urlToBe(baseURL + "/isklepanje/mikromobilnost/mikro_zavarovanje_4"));

		// KORAK 4
		
		Thread.sleep(1000);
		
		funk.ScrollInKlik("(//*[@class='cs-slider'])[2]");
		funk.ScrollInKlik2(By.className("nextStep"));
		
		new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.urlToBe(baseURL + "/isklepanje/mikromobilnost/preparePayment"));

		// PLAČILO
		Thread.sleep(1000);
		
		funk.ScrollInKlik2(By.className("alternateNext"));
		Thread.sleep(3000);
		funk.checkSklenitev();
		
		
	}
	
	
}
