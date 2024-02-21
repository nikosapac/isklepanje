package sklepanjeTesti.sklepanje;

import java.time.Duration;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

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

public class OdpovedPotovanj {
	
	WebDriver driver;
	
	String cenaAranzmaja = "1000";
	String opisAranzmaja = "TestOpis";
	String nazivAgencije = "TestAgencija";
	
	EnvironmentConfig cfg = ConfigFactory.create(EnvironmentConfig.class);
	String baseURL = cfg.baseURL();
	String itrEmail = cfg.iTriglavEmail();
	String itrPass = cfg.iTriglavPass();

	public OdpovedPotovanj(WebDriver driver) {
		this.driver = driver;
	}
	
	@Test
	public void OdpovedPotovanjTest() throws Exception  {
		
		Funkcije2 funk = new Funkcije2(driver);
		
		Prijave2 p = new Prijave2(driver);
		p.SklepanjePrijava();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get(baseURL + "/isklepanje/odpovedpotovanj/riziko_odpovedi_intro");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.findElement(By.className("labelLeft")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.findElement(By.xpath("(//*[@class='db-icon-big'])[1]")).click();
		driver.findElement(By.className("nextStep")).click();
		
		funk.waitForLoaderToFinish(driver, By.className("loader"));
		
		// KORAK 1
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.findElement(By.xpath("//*[@for='zavOdpovedAranzmajaPriTuristicniAgencijiSwitch']")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		// Datum začetka aranžmaja

		driver.findElement(By.xpath("//*[@id=\"zoaTurAgAtts0.dateStart\"]")).sendKeys(Keys.ENTER);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		// Datum in čas nakupa aranžmaja
		
		driver.findElement(By.xpath("//*[text()='Datum in čas nakupa aranžmaja ']")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		// cena
		
		driver.findElement(By.xpath("//*[@id=\"zoaTurAgAtts0.price\"]")).sendKeys(cenaAranzmaja);
		
		// opis
		
		driver.findElement(By.xpath("//*[@id=\"zoaTurAgAtts0.opisAranzmaja\"]")).sendKeys(opisAranzmaja);
		
		// agencija
		
		driver.findElement(By.xpath("//*[@id=\"zoaTurAgAtts0.turAgName\"]")).sendKeys(nazivAgencije);
		
		
		funk.ScrollInKlik2(By.className("nextStep"));
		
		funk.waitForLoaderToFinish(driver, By.className("loader"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		// KORAK 2 
		
		funk.ScrollInKlik("(//span[@class='cs-slider'])[1]");
		funk.ScrollInKlik("(//span[@class='cs-slider'])[2]");
		
		funk.ScrollInKlik2(By.className("nextStep"));
		
		funk.waitForLoaderToFinish(driver, By.className("loader"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		// KORAK 3 
		
		if(funk.IsVisible(By.xpath("//a[text()='Prijavite se']"))) {
			driver.findElement(By.xpath("//a[text()='Prijavite se']")).click();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		p.ItriglavPrijavaSklepanje();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		funk.waitForLoaderToFinish(driver, By.className("loader"));
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		Thread.sleep(1000);
		
		funk.ScrollInKlik("(//span[@class='cs-slider'])[1]");
		funk.implicitWait(15);
		funk.ScrollInKlik("(//span[@class='cs-slider'])[2]");
		funk.implicitWait(15);
		funk.ScrollInKlik("(//span[@class='cs-slider'])[3]");
		funk.implicitWait(15);
		funk.ScrollInKlik("(//span[@class='cs-slider'])[4]");
		funk.implicitWait(15);
		funk.ScrollInKlik("(//span[@class='cs-slider'])[5]");
		funk.implicitWait(15);
		
		funk.ScrollInKlik2(By.id("preparePaymentButton"));
		
		funk.waitForLoaderToFinish(driver, By.className("loader"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		// plačilo
		
		funk.ScrollInKlik("//*[@for='e0']");
		
		funk.ScrollInKlik2(By.className("alternateNext"));
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		funk.checkSklenitev();
	}

}
