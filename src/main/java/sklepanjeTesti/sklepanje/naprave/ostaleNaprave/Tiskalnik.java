package sklepanjeTesti.sklepanje.naprave.ostaleNaprave;

import java.awt.AWTException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Random;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import sklepanjeTesti.Funkcije;
import sklepanjeTesti.Funkcije2;
import sklepanjeTesti.Prijave2;
import sklepanjeTesti.config.EnvironmentConfig;

public class Tiskalnik {
	
	WebDriver driver;
	
	public Tiskalnik(WebDriver driver) {
		this.driver = driver;
	}

	String path = System.getProperty("user.dir"); //POIŠČE LOKALNO POT DATOTEKE
	String fullpath = path + "\\slike\\slikaUpload.png";
	
	String proizvajalec = "TestProizvajalec";
	String model = "TestModel";
	String identSt = "123456789";
	String vrednost = "500";
	
	EnvironmentConfig cfg = ConfigFactory.create(EnvironmentConfig.class);
	String baseURL = cfg.baseURL();
	String itrEmail = cfg.iTriglavEmail();
	String itrPass = cfg.iTriglavPass();
	
	@Test
	public void NapraveTest() throws Exception {
		
		Funkcije2 funk = new Funkcije2(driver);
		
		Prijave2 p = new Prijave2(driver);
		
		p.SklepanjePrijava();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		String datum = funk.Datum(-2);
		
		// PREDKORAK
		
		driver.get(baseURL + "/isklepanje/elektronskeNaprave/zavarovanje_naprav_intro");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.findElement(By.className("labelLeft")).click();
		
		new WebDriverWait(driver,Duration.ofSeconds(40)).until(ExpectedConditions.urlToBe(baseURL + "/isklepanje/elektronskeNaprave/zavarovanje_naprav"));
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.findElement(By.xpath("(//*[@class='db-icon-big'])[4]")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		funk.ScrollInKlik2(By.className("nextStep"));
		
		// VRSTA NAPRAVE
		
		driver.findElement(By.xpath("(//*[@class='db-icon-big'])[3]")).click();
				
		funk.ScrollInKlik2(By.className("nextStep"));
						
		new WebDriverWait(driver,Duration.ofSeconds(40)).until(ExpectedConditions.urlToBe(baseURL + "/isklepanje/elektronskeNaprave/zavarovanje_naprav_1"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		// KORAK 1
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.findElement(By.xpath("//*[contains(text(),'Proizvajalec')]")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.id("manufacturer")).sendKeys(proizvajalec);

		driver.findElement(By.xpath("//*[contains(text(),'Model')]")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.id("model")).sendKeys(model);
		
		driver.findElement(By.xpath("//*[contains(text(),'Identifikacijska oznaka')]")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.id("serialNumber")).sendKeys(identSt);
		
		driver.findElement(By.xpath("//*[contains(text(),'Datum nakupa')]")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.id("purchaseDate")).sendKeys(datum,Keys.ENTER);
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,100)");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.id("devicePrice")).sendKeys(vrednost);
		
		// upload file
		
		funk.ScrollToElement(By.id("invoice-fileuploder"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.xpath("//*[@type='file']")).sendKeys(fullpath);
		funk.implicitWait(10);
		funk.waitForLoaderToFinish(driver, By.className("loader"));
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		funk.ScrollInKlik2(By.className("nextStep"));
		
		new WebDriverWait(driver,Duration.ofSeconds(40)).until(ExpectedConditions.urlToBe(baseURL + "/isklepanje/elektronskeNaprave/zavarovanje_naprav_2"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		// KORAK 2
		
		driver.findElement(By.xpath("//label[@for='kritje-3']")).click();
		
		funk.ScrollInKlik2(By.className("nextStep"));
		
		new WebDriverWait(driver,Duration.ofSeconds(40)).until(ExpectedConditions.urlToBe(baseURL + "/isklepanje/elektronskeNaprave/zavarovanje_naprav_3"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		// KORAK 3
		
		driver.findElement(By.xpath("//a[text()='Prijavite se']")).click();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		p.ItriglavPrijavaSklepanje();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		funk.ScrollInKlik("(//span[@class='cs-slider'])[1]");
		funk.ScrollInKlik("(//span[@class='cs-slider'])[2]");
				
		funk.ScrollInKlik2(By.className("nextStep"));
		
		new WebDriverWait(driver,Duration.ofSeconds(40)).until(ExpectedConditions.urlToBe(baseURL + "/isklepanje/elektronskeNaprave/zavarovanje_naprav_4"));

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		// KORAK 4
		
		funk.ScrollInKlik("(//span[@class='cs-slider'])[1]");
		funk.ScrollInKlik("(//span[@class='cs-slider'])[2]");
				
		funk.ScrollInKlik2(By.className("nextStep"));
		
		new WebDriverWait(driver,Duration.ofSeconds(40)).until(ExpectedConditions.urlToBe(baseURL + "/isklepanje/elektronskeNaprave/zavarovanje_naprav_5"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		// KORAK 5
		
		funk.ScrollInKlik2(By.className("nextStep"));
		
		new WebDriverWait(driver,Duration.ofSeconds(40)).until(ExpectedConditions.urlToBe(baseURL + "/isklepanje/elektronskeNaprave/preparePayment"));

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		funk.ScrollInKlik2(By.className("alternateNext"));
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		funk.checkSklenitev();
		
	}

}
