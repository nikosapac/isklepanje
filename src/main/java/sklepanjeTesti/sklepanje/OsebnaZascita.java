package sklepanjeTesti.sklepanje;

import java.time.Duration;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
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

public class OsebnaZascita {
	
	WebDriver driver;

	EnvironmentConfig cfg = ConfigFactory.create(EnvironmentConfig.class);
	String baseURL = cfg.baseURL();
	String itrEmail = cfg.iTriglavEmail();
	String itrPass = cfg.iTriglavPass();
	
	public OsebnaZascita(WebDriver driver) {
		this.driver = driver;
	}

	@Test
	public void OsebnaZascitaPosamicno() throws Exception {
		
		Funkcije2 funk = new Funkcije2(driver);
		Prijave2 p = new Prijave2(driver);
		
		p.SklepanjePrijava();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get(baseURL + "/isklepanje/osebnazascita/osebno_zavarovanje_intro");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.findElement(By.className("labelLeft")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.findElement(By.className("icon-person")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.findElement(By.className("nextStep")).click();
		
		funk.waitForLoaderToFinish(driver, By.className("loader"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		// KORAK 1
		
		funk.ScrollInKlik2(By.className("nextStep"));
		
		funk.waitForLoaderToFinish(driver, By.className("loader"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		// KORAK 2 
		
		if(funk.IsVisible(By.xpath("//a[text()='Prijavite se']"))) {
			driver.findElement(By.xpath("//a[text()='Prijavite se']")).click();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		p.ItriglavPrijavaSklepanje();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		funk.ScrollInKlik("(//span[@class='cs-slider'])[1]");
		funk.ScrollInKlik("(//span[@class='cs-slider'])[2]");
	//	funk.ScrollInKlik("(//span[@class='cs-slider'])[3]");
		
		funk.ScrollInKlik2(By.className("nextStep"));
		
		funk.waitForLoaderToFinish(driver, By.className("loader"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));	
		
		// KORAK 3
		
		funk.ScrollInKlik("(//span[@class='cs-slider'])[1]");
		funk.ScrollInKlik("(//span[@class='cs-slider'])[2]");
		
		funk.ScrollInKlik2(By.className("nextStep"));
		
		funk.waitForLoaderToFinish(driver, By.className("loader"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		// KORAK 4
		
		funk.ScrollInKlik("//*[@for='e3']");
		
		funk.ScrollInKlik("(//span[@class='cs-slider'])[2]");
		
		funk.ScrollInKlik2(By.className("nextStep"));
		
		funk.waitForLoaderToFinish(driver, By.className("loader"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		funk.ScrollInKlik("//label[@for='e0']");
		
		funk.ScrollInKlik2(By.className("alternateNext"));
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		funk.checkSklenitev();
		
	}
	
	@Test
	public void OsebnaZascitaDruzinsko() throws Exception  {
		
		String ime = "TestIme";
		String pri = "TestPriimek";
		String datumRojstva = "12.2.2000";
		String davcna = "70794162";
		
		Funkcije2 funk = new Funkcije2(driver);
		Prijave2 p = new Prijave2(driver);
		
		p.SklepanjePrijava();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get(baseURL + "/isklepanje/osebnazascita/osebno_zavarovanje_intro");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.findElement(By.className("labelLeft")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.findElement(By.className("icon-family")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.findElement(By.className("nextStep")).click();
		
		funk.waitForLoaderToFinish(driver, By.className("loader"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		// KORAK 1
		
		funk.ScrollInKlik2(By.className("nextStep"));
		
		funk.waitForLoaderToFinish(driver, By.className("loader"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		// KORAK 2 
		
		if(funk.IsVisible(By.xpath("//a[text()='Prijavite se']"))) {
			driver.findElement(By.xpath("//a[text()='Prijavite se']")).click();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		p.ItriglavPrijavaSklepanje();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		// Družinski član
		
		funk.ScrollInKlik2(By.id("btnVnosZavarovancev"));
		
		funk.implicitWait(10);
		Thread.sleep(1000);
		driver.findElement(By.id("name")).sendKeys(ime);
		driver.findElement(By.id("surname")).sendKeys(pri);
		driver.findElement(By.id("birthday")).sendKeys(datumRojstva);
		driver.findElement(By.id("taxnum")).sendKeys(davcna);
		
		driver.findElement(By.xpath("(//*[@class='labelLeft'])[2]")).click();
		
		funk.implicitWait(10);
		
		driver.findElement(By.className("select2-selection__arrow")).click();
		Thread.sleep(1500);
		driver.findElement(By.xpath("//*[@id=\"familyRelationship\"]/option[2]")).click();
		funk.implicitWait(10);
		driver.findElement(By.className("select2-selection__arrow")).click();
		funk.implicitWait(10);
		Thread.sleep(1000);
		driver.findElement(By.id("saveOsebaData")).click();
		
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[@class='cs-slider'])[1]")));
		
		funk.implicitWait(10);
		
		funk.ScrollInKlik("(//span[@class='cs-slider'])[1]");
		funk.ScrollInKlik("(//span[@class='cs-slider'])[2]");
		//funk.ScrollInKlik("(//span[@class='cs-slider'])[3]");
		
		funk.ScrollInKlik2(By.className("nextStep"));
		
		funk.waitForLoaderToFinish(driver, By.className("loader"));
		
		Thread.sleep(2000);
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));	
		
		// KORAK 3
		
		funk.ScrollInKlik("(//span[@class='cs-slider'])[1]");
		funk.ScrollInKlik("(//span[@class='cs-slider'])[2]");
		
		funk.ScrollInKlik2(By.className("nextStep"));
		
		funk.waitForLoaderToFinish(driver, By.className("loader"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		// KORAK 4
		
		funk.ScrollInKlik("//*[@for='e3']");
		
		funk.ScrollInKlik("(//span[@class='cs-slider'])[2]");
		
		funk.ScrollInKlik2(By.className("nextStep"));
		
		funk.waitForLoaderToFinish(driver, By.className("loader"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		funk.ScrollInKlik("//label[@for='e0']");
		
		funk.ScrollInKlik2(By.className("alternateNext"));
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		funk.checkSklenitev();
		
	}
}
