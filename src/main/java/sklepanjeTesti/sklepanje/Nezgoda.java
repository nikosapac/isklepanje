package sklepanjeTesti.sklepanje;

import java.time.Duration;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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

public class Nezgoda {
	
	WebDriver driver;
	
	String imeOtrok = "TestIme";
	String pri = "TestPriimek";
	String datRoj = "07.03.2001";
	String davcnaOtrok = "70794162";
	
	String imeOtrokDva = "TestImeDva";
	String priDva = "TestPriimekDva";
	String datRojDva = "17.07.2006";
	String davcnaOtrokDva = "19553170";
	
	String ime = "Niko";
	String priimek = "Sapač";
	String datumRojstva = "15.01.2001";
	String davcna = "84741945";
	String postnaStevilka = "9000";
	String ulica = "Mladinska ulica";
	String hisnaStevilka = "15";
	String telefonska = "031 532 734";
	String email = "zkpr.test@triglav.si";
	String validacijskaKoda = "9999";
	String trr = "SI56 0234 0176 4167 804";
	
	EnvironmentConfig cfg = ConfigFactory.create(EnvironmentConfig.class);
	String baseURL = cfg.baseURL();
	String itrEmail = cfg.iTriglavEmail();
	String itrPass = cfg.iTriglavPass();
	
	public Nezgoda(WebDriver driver) {
		this.driver = driver;
	}
	
	public void NezgodaQTest() throws Exception {
		
		Funkcije2 f = new Funkcije2(driver);
		Prijave2 p = new Prijave2(driver);
		
		p.SklepanjePrijava();
		f.implicitWait(10);
		
		driver.get(baseURL + "/isklepanje/nezgoda/nezgodno-zavarovanje-otrok-in-mladih");
		f.implicitWait(10);
		driver.findElement(By.xpath("//*[text()='Prijava']")).click();
		f.implicitWait(10);
		p.ItriglavPrijavaSklepanje();
		
		new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.textToBe(By.className("intro-description"), "S prijavo v i.triglav ste poenostavili postopek sklepanja zavarovanja, saj bodo vaši podatki in popusti preneseni avtomatično."));
		
		Thread.sleep(1000);
		
		f.implicitWait(10);
		
	//	driver.findElement(By.xpath("//*[contains(text(),'Skleni novo')]"));
		f.ScrollInKlik2(By.className("intro-btn"));
		
		// KORAK 1 - Podatki o zavarovanju
		
		new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.urlToBe(baseURL + "/isklepanje/nezgoda/nezgodno_zavarovanje_otrok_in_mladih_1"));
		
		f.implicitWait(10);
		
		f.ScrollInKlik2(By.className("submitButton"));
		
		// KORAK 2 - Izbira paketa
		
		new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.urlToBe(baseURL + "/isklepanje/nezgoda/nezgodno_zavarovanje_otrok_in_mladih_2"));
		
		f.implicitWait(10);
		
		f.ScrollInKlik2(By.className("submitButton"));
		f.implicitWait(10);
		/*
		new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Nadaljuj']")));
		f.implicitWait(10);
		driver.findElement(By.xpath("//*[text()='Nadaljuj']")).click();
		f.implicitWait(10);
		*/
		// KORAK 3 - Podatki o sklenitelju
		
		new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.urlToBe(baseURL + "/isklepanje/nezgoda/nezgodno_zavarovanje_otrok_in_mladih_3"));
		
		f.implicitWait(20);
		
		f.ScrollInKlik2(By.id("add-child"));
		
		f.implicitWait(10);
		
		// doda 1 otroka
		
		Otrok();
		
		// pogoj
		
		f.implicitWait(10);
		
		f.ScrollInKlik2(By.xpath("(//*[@class='checkbox--primary__icon'])[1]"));
		
		f.implicitWait(10);
		
		f.ScrollInKlik2(By.className("submitButton"));
		
		Thread.sleep(2000);
		
		f.implicitWait(10);
		
		if(f.IsVisible(By.className("cs-slider"))) {
			f.ScrollInKlik2(By.className("cs-slider"));
			f.implicitWait(10);
			f.ScrollInKlik2(By.className("submitButton"));
		} else {
			f.implicitWait(10);
		}
		
		// KORAK 4 - Premija in ugodnosti
		
		new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.urlToBe(baseURL + "/isklepanje/nezgoda/nezgodno_zavarovanje_otrok_in_mladih_4"));

		f.implicitWait(10);
		
		f.ScrollInKlik("//*[@for='zavarovalnimi-pogoji']");
		Thread.sleep(500);
		WebElement test = driver.findElement(By.xpath("//*[@for='info-zavarovalni-paket-mobile']"));
		new Actions(driver).moveToElement(test, 2, 2).click().perform();
		
		f.ScrollInKlik2(By.className("submitButton"));
	
		// KORAK 5 - Plačilo
		
		new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.urlToBe(baseURL + "/isklepanje/nezgoda/nezgodno_zavarovanje_otrok_in_mladih_5"));

		f.implicitWait(10);
		
		f.ScrollInKlik2(By.xpath("//*[@class='checkbox--primary__icon']"));
		
		Thread.sleep(4000);
		
		f.implicitWait(10);
		
		driver.findElement(By.className("monthlyInstallment")).sendKeys("SI56 0234 0176 4167 804");
		
		f.implicitWait(10);
		
		f.ScrollInKlik2(By.xpath("//*[@for='soglasam-s-pogoji-obroki']"));
		
		f.implicitWait(10);
		
		f.ScrollInKlik2(By.xpath("(//*[@class='radio-quaternary__button'])[3]"));
		
		f.implicitWait(10);
		
	//	f.ScrollInKlik2(By.xpath("(//*[@class='submitButton'])[1]"));
		
		f.ScrollInKlik("//button[@name='simulatePayment']");
		
		WebElement ele = driver.findElement(By.xpath("//*[text()='Hvala!']"));
		
		new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOf(ele));
		
		if(ele.isDisplayed() == true) {
			System.out.println("Sklenitev NezgodaQTest je bilo uspešno");
		} else {
			throw new Exception("Sklenitev NezgodaQTest ni bilo uspešno");
		}  
		
	}
	
	
	public void NezgodaQTest2Otroka() throws Exception {
		
		Funkcije2 f = new Funkcije2(driver);
		Prijave2 p = new Prijave2(driver);
		
		p.SklepanjePrijava();
		f.implicitWait(10);
		
		driver.get(baseURL + "/isklepanje/nezgoda/nezgodno-zavarovanje-otrok-in-mladih");
		f.implicitWait(10);
		driver.findElement(By.xpath("//*[text()='Prijava']")).click();
		f.implicitWait(10);
		p.ItriglavPrijavaSklepanje();
		
		new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.textToBe(By.className("intro-description"), "S prijavo v i.triglav ste poenostavili postopek sklepanja zavarovanja, saj bodo vaši podatki in popusti preneseni avtomatično."));
		
		Thread.sleep(1000);
		
		f.implicitWait(10);
		
	//	driver.findElement(By.xpath("//*[contains(text(),'Skleni novo')]"));
		f.ScrollInKlik2(By.className("intro-btn"));
		
		// KORAK 1 - Podatki o zavarovanju
		
		new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.urlToBe(baseURL + "/isklepanje/nezgoda/nezgodno_zavarovanje_otrok_in_mladih_1"));
		
		f.implicitWait(10);
		
		f.ScrollInKlik2(By.className("inputNumber-plus"));
		
		f.implicitWait(10);
		
		f.ScrollInKlik2(By.className("submitButton"));
		
		// KORAK 2 - Izbira paketa
		
		new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.urlToBe(baseURL + "/isklepanje/nezgoda/nezgodno_zavarovanje_otrok_in_mladih_2"));
		
		f.implicitWait(10);
		
		f.ScrollInKlik2(By.className("submitButton"));
		f.implicitWait(10);
		/*
		new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Nadaljuj']")));
		f.implicitWait(10);
		driver.findElement(By.xpath("//*[text()='Nadaljuj']")).click();
		f.implicitWait(10);
		*/
		// KORAK 3 - Podatki o sklenitelju
		
		new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.urlToBe(baseURL + "/isklepanje/nezgoda/nezgodno_zavarovanje_otrok_in_mladih_3"));
		
		f.implicitWait(10);
		
		f.ScrollInKlik2(By.id("add-child"));
		
		f.implicitWait(10);
		
		// doda 1 otroka
		
		Otrok();
		
		// doda 2 otroka
		
		f.implicitWait(10);
		
		f.ScrollInKlik2(By.id("add-child"));
		
		f.implicitWait(10);
		
		Otrok2();
		
		f.implicitWait(10);
		
		// pogoj
		
		f.implicitWait(10);
		
		f.ScrollInKlik2(By.xpath("(//*[@class='checkbox--primary__icon'])[1]"));
		
		f.implicitWait(10);
		
		f.ScrollInKlik2(By.className("submitButton"));
		
		f.implicitWait(10);
		
		if(f.IsVisible(By.className("cs-slider"))) {
			f.ScrollInKlik2(By.className("cs-slider"));
			f.implicitWait(10);
			f.ScrollInKlik2(By.className("submitButton"));
		} else {
			f.implicitWait(10);
		}
		
		// KORAK 4 - Premija in ugodnosti
		
		new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.urlToBe(baseURL + "/isklepanje/nezgoda/nezgodno_zavarovanje_otrok_in_mladih_4"));

		f.implicitWait(10);
		
		f.ScrollInKlik("//*[@for='zavarovalnimi-pogoji']");
		Thread.sleep(500);
		WebElement test = driver.findElement(By.xpath("//*[@for='info-zavarovalni-paket-mobile']"));
		new Actions(driver).moveToElement(test, 2, 2).click().perform();
		
		f.ScrollInKlik2(By.className("submitButton"));
	
		// KORAK 5 - Plačilo
		
		new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.urlToBe(baseURL + "/isklepanje/nezgoda/nezgodno_zavarovanje_otrok_in_mladih_5"));

		f.implicitWait(10);
		
		f.ScrollInKlik2(By.xpath("//*[@class='checkbox--primary__icon']"));
		
		Thread.sleep(4000);
		
		f.implicitWait(10);
		
		driver.findElement(By.className("monthlyInstallment")).sendKeys("SI56 0234 0176 4167 804");
		
		/*
		f.ScrollInKlik2(By.xpath("(//*[@class='select2-selection__arrow'])[2]"));
		
		Thread.sleep(6000);
		
		f.implicitWait(10);
		
		
		
		//driver.findElement(By.xpath("//*[text()='SI56 0234 0176 4167 804']")).click();
		//WebElement ele = driver.findElement(By.xpath("//*[contains(text(),'SI56 0234 0176 4167 804')]"));
		//driver.findElement(By.xpath("//*[contains(text(),'SI56 0234 0176 4167 804')]")).click();
		//new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(ele));
		//ele.click();
		//driver.findElement(By.xpath("//*[text()='SI56 0234 0176 4167 804']")).click();
		*/
		f.implicitWait(10);
		
		f.ScrollInKlik2(By.xpath("//*[@for='soglasam-s-pogoji-obroki']"));
		
		f.implicitWait(10);
		
		f.ScrollInKlik2(By.xpath("(//*[@class='radio-quaternary__button'])[3]"));
		
		f.implicitWait(10);
		
	//	f.ScrollInKlik2(By.xpath("(//*[@class='submitButton'])[1]"));
		
		f.ScrollInKlik("//button[@name='simulatePayment']");
		
		//f.checkSklenitev();
		
		WebElement ele = driver.findElement(By.xpath("//*[text()='Hvala!']"));
		
		new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOf(ele));
		
		if(ele.isDisplayed() == true) {
			System.out.println("Sklenitev NezgodaQTest2Otroka je bilo uspešno");
		} else {
			throw new Exception("Sklenitev NezgodaQTest2Otroka ni bilo uspešno");
		}  
		
	}
	
	public void Otrok() throws InterruptedException {
		
		Funkcije2 funk = new Funkcije2(driver);
		
		Thread.sleep(2000);
		
		driver.findElement(By.id("name")).sendKeys(imeOtrok);
		
		driver.findElement(By.id("surname")).sendKeys(pri);
		
		driver.findElement(By.id("birthday")).sendKeys(datRoj);
		
		driver.findElement(By.id("taxnum")).sendKeys(davcnaOtrok);
		
		driver.findElement(By.xpath("(//*[@class='radio--primary__button'])[3]")).click();
		
		Thread.sleep(1000);
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		Thread.sleep(1000);
		
		funk.implicitWait(10);
		
		funk.ScrollInKlik2(By.xpath("//*[text()='Shrani']"));
		
		funk.waitForLoaderToFinish(driver, By.className("loader"));
		
	}
	
	public void Otrok2() throws InterruptedException {
		
		Funkcije2 funk = new Funkcije2(driver);
		
		//Dodamo otroka 2
		Thread.sleep(2000);
		driver.findElement(By.id("name")).sendKeys(imeOtrokDva);
		driver.findElement(By.id("surname")).sendKeys(priDva);
		driver.findElement(By.id("birthday")).sendKeys(datRojDva);
		driver.findElement(By.id("taxnum")).sendKeys(davcnaOtrokDva);
		
		driver.findElement(By.xpath("(//*[@class='radio--primary__button'])[3]")).click();
		
		Thread.sleep(1000);
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		Thread.sleep(1000);
		
		funk.implicitWait(10);
		
		funk.ScrollInKlik2(By.xpath("//*[text()='Shrani']"));
		
		funk.waitForLoaderToFinish(driver, By.className("loader"));
				
	}
	
	
}



