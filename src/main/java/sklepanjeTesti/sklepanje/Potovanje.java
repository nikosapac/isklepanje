package sklepanjeTesti.sklepanje;

import java.time.Duration;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import sklepanjeTesti.Funkcije;
import sklepanjeTesti.Funkcije2;
import sklepanjeTesti.Prijave;
import sklepanjeTesti.Prijave2;
import sklepanjeTesti.config.EnvironmentConfig;

public class Potovanje {
	
	WebDriver driver;
	
	String email = "niko.sapac@triglav.si";
	String password = "Testgeslo123";
	
	EnvironmentConfig cfg = ConfigFactory.create(EnvironmentConfig.class);
	String baseURL = cfg.baseURL();
	String itrEmail = cfg.iTriglavEmail();
	String itrPass = cfg.iTriglavPass();
	
	public Potovanje(WebDriver driver) {
		this.driver = driver;
	}
	
	public void PotovanjeQTest() throws Exception {
		
		Funkcije2 f = new Funkcije2(driver);
		Prijave2 p = new Prijave2(driver); 
		
		p.SklepanjePrijava();
		
		driver.get(baseURL + "/isklepanje/tujina/potovanje_zavarovanje");
		
		driver.findElement(By.xpath("//*[text()='Prijava']")).click();
		
		f.implicitWait(10);
		
		p.ItriglavPrijavaSklepanje();
		
		f.implicitWait(10);
		
		f.ScrollInKlik2(By.id("turisticnoPolicy.insuranceIntro.zptInsuranceType1"));
		
		f.ScrollInKlik2(By.className("submitButton"));
		
		f.implicitWait(10);
		
		// KORAK 1 - Podatki o zavarovanju	
		
		f.ScrollInKlik2(By.className("submitButton"));
		
		f.implicitWait(10);

		// KORAK 2 - Izbira paketa
		
		f.ScrollInKlik2(By.id("btnPackageSmall"));
		
		f.ScrollInKlik2(By.className("submitButton"));
		
		f.implicitWait(10);
		
		// KORAK 3 - Podatki o sklenitelju
		
		f.ScrollInKlik2(By.className("checkbox--primary__icon"));
		
		f.implicitWait(10);
		
		f.ScrollInKlik2(By.className("submitButton"));
		
		Thread.sleep(1000);
		
		f.implicitWait(10);
		
		if(driver.getPageSource().contains("Seznanjen sem, da se bo ustvarila nova polica, če bo postopek sklepanja izpeljan do konca in premija plačana.")) {
			f.implicitWait(10);
			f.ScrollInKlik2(By.className("cs-slider"));
			f.implicitWait(10);
			f.ScrollInKlik2(By.className("submitButton"));
		}
		
		f.implicitWait(10);
		
		// KORAK 4 - Premija in ugodnosti
		
		f.ScrollInKlik2(By.xpath("//*[@for='zavarovalnimi-pogoji']"));
		
		WebElement test = driver.findElement(By.xpath("//*[@for='info-zavarovalni-paket-mobile']"));
		new Actions(driver).moveToElement(test, 2, 2).click().perform();
		
		f.ScrollInKlik2(By.className("submitButton"));
		
		f.implicitWait(10);
		
		// KORAK 5 - Plačilo
		
		f.ScrollInKlik2(By.className("radio--secondary__button"));
		
		f.implicitWait(10);
		
		driver.findElement(By.xpath("(//*[@class='radio-quaternary__button'])[1]")).click();
		
		Thread.sleep(1000);
		f.implicitWait(10);
		/*
		f.ScrollInKlik2(By.xpath("(//*[@class='select2-selection__arrow'])[1]"));
		Thread.sleep(1500);
		f.implicitWait(10);
		
		driver.findElement(By.xpath("//*[text()='SI56 0234 0176 4167 804']")).click();
		
		f.implicitWait(10);
		
		driver.findElement(By.xpath("(//*[@class='select2-selection__arrow'])[1]")).click();
		
		f.implicitWait(10);
		*/
		f.ScrollInKlik2(By.xpath("//*[@for='soglasam-s-pogoji-celotna-premija']"));
		
		f.implicitWait(10);
		
		f.ScrollInKlik2(By.className("submitButton"));
		
		WebElement ele = driver.findElement(By.xpath("//*[text()='Hvala!']"));
			
		new WebDriverWait(driver,Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOf(ele));
		
		if(ele.isDisplayed() == true) {
			System.out.println("Sklenitev potovanja je bilo uspešno");
		} else {
			throw new Exception("Sklenitev potovanja ni bilo uspešno");
		}    
		
	}
}
