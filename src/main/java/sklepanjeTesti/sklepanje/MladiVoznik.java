package sklepanjeTesti.sklepanje;

import java.time.Duration;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import sklepanjeTesti.Funkcije;
import sklepanjeTesti.Funkcije2;
import sklepanjeTesti.Prijave;
import sklepanjeTesti.Prijave2;
import sklepanjeTesti.config.EnvironmentConfig;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class MladiVoznik {
	
		WebDriver driver;
		
		EnvironmentConfig cfg = ConfigFactory.create(EnvironmentConfig.class);
		String baseURL = cfg.baseURL();
		String itrEmail = cfg.iTriglavEmail();
		String itrPass = cfg.iTriglavPass();
		
		public MladiVoznik(WebDriver driver) {
			this.driver=driver;
		}

		@Test
		public void MladiVoznikTest() throws Exception  {
			
			Funkcije2 f = new Funkcije2(driver);
			Prijave2 p = new Prijave2(driver);
			
			//p.ItriglavPrijavaVnos("niko.sapac@triglav.si","Testgeslo123");
			p.SklepanjePrijava();
			
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			
			driver.get(baseURL + "/isklepanje/mladi/zavarovanje_mladi_voznik");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			
			driver.findElement(By.xpath("//*[text()='Prijava']")).click();
			
			f.implicitWait(10);
			
			p.ItriglavPrijavaSklepanje();
			
			f.implicitWait(10);
			
			driver.findElement(By.xpath("//*[text()='Skleni novo']")).click();
			
			f.implicitWait(10);
			
			new WebDriverWait(driver,Duration.ofSeconds(40)).until(ExpectedConditions.urlToBe(baseURL + "/isklepanje/mladi/zavarovanje_mladi_voznik_1"));
			
			// KORAK 1 -  Podatki o zavarovanju
			/*
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			
			f.ScrollInKlik("//*[text()='Datum pridobitve vozniškega dovoljenja']");
			Thread.sleep(599);
			driver.findElement(By.id("obtainedDrivingLicenseDate")).sendKeys("18.06.2007");
			*/
			
				// Izpolnjen pogoj za vožnjo
			
			f.implicitWait(10);
			f.ScrollInKlik2(By.id("drivingCondition-button"));
			f.implicitWait(10);
			driver.findElement(By.id("obtainedDrivingLicenseDate")).sendKeys("18.06.2007");
			f.implicitWait(10);
			
				// Bo mladi voznik vozil le en avto?
			
			f.ScrollInKlik2(By.id("useOfOnlyOneVehicle-button"));
			f.implicitWait(10);
			
				// Naprej
			f.implicitWait(10);
			
			f.ScrollInKlik2(By.className("submitButton"));
			
			//Thread.sleep(2000);
			
			new WebDriverWait(driver,Duration.ofSeconds(40)).until(ExpectedConditions.urlToBe(baseURL + "/isklepanje/mladi/zavarovanje_mladi_voznik_2"));

			// KORAK 2 - Izberite paket
			
			f.implicitWait(10);
			
			f.ScrollInKlik2(By.id("btnPackageBig"));
			
			f.implicitWait(10);
					
			f.ScrollInKlik2(By.className("submitButton"));
			
			new WebDriverWait(driver,Duration.ofSeconds(40)).until(ExpectedConditions.urlToBe(baseURL + "/isklepanje/mladi/zavarovanje_mladi_voznik_3"));

			// KORAK 3 MLADI VOZNIK 
			
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			
			f.ScrollInKlik("(//span[@class='checkbox--primary__icon'])[2]");
			
//			if(driver.findElement(By.id("policyHolderUI.phoneNumber")).getText().equals("")) {
//				f.ScrollInKlik2(By.id("policyHolderUI.phoneNumber"));
//				driver.findElement(By.id("policyHolderUI.phoneNumber")).sendKeys("031 532 734");
//			}
			
			f.implicitWait(10);
			
//			f.ScrollInKlik("//*[@for='potrjujem-vse']");
			
			f.implicitWait(10);
					
			f.ScrollInKlik2(By.className("submitButton"));
			
			new WebDriverWait(driver,Duration.ofSeconds(40)).until(ExpectedConditions.urlToBe(baseURL + "/isklepanje/mladi/zavarovanje_mladi_voznik_4"));

			// KORAK 4	
			
			f.ScrollInKlik("//*[@for='info-zavarovalni-paket-mobile']");
			
			f.ScrollInKlik("//*[@for='zavarovalnimi-pogoji']");
		
			f.ScrollInKlik2(By.className("submitButton"));
			
			new WebDriverWait(driver,Duration.ofSeconds(40)).until(ExpectedConditions.urlToBe(baseURL + "/isklepanje/mladi/zavarovanje_mladi_voznik_5"));
			
			// KORAK 5 - Plačilo
					
			f.implicitWait(10);
			
			driver.findElement(By.xpath("(//*[@class='radio--secondary__button'])[1]")).click();
			new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@class='radio-quaternary__button'])[1]")));
			f.implicitWait(10);
			
				// Vrsta plačila
			
			driver.findElement(By.xpath("(//*[@class='radio-quaternary__button'])[1]")).click();
			f.implicitWait(10);
						
			// Simuliraj plačilo
			
			f.ScrollInKlik2(By.xpath("//*[@id=\"mvQModel\"]/div/div/div[2]/div[3]/div[2]/button[1]"));
			
			// Validacija uspešne sklenitve
			
			f.implicitWait(10);	
			
			WebElement ele = driver.findElement(By.xpath("//*[text()='Hvala!']"));
			
			new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOf(ele));
			
			if(ele.isDisplayed() == true) {
				System.out.println("Sklenitev MladiVoznikTest je bilo uspešno");
			} else {
				throw new Exception("Sklenitev MladiVoznikTest ni bilo uspešno");
			} 
			
		}
		
		@Test
		public void MladiVoznikTestZObrocnimPlacevanjem() throws Exception  {
			
			//String email = "kolbleva@gmail.com";
			//String pass = "Geslo11!";
			
			String email = "triglav.digi@gmail.com";
			String pass = "Test@123";
			
			Funkcije2 f = new Funkcije2(driver);
			Prijave2 p = new Prijave2(driver);
			
			String trr = "SI56 0234 0176 4167 804";
			
			//p.ItriglavPrijavaVnos("niko.sapac@triglav.si","Testgeslo123");
			p.SklepanjePrijava();
			
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			
			driver.get(baseURL + "/isklepanje/mladi/zavarovanje_mladi_voznik");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			
			driver.findElement(By.xpath("//*[text()='Prijava']")).click();
			
			f.implicitWait(10);
			
			p.ItriglavPrijavaSklepanje2(email,pass);
			
			f.implicitWait(10);
			
			driver.findElement(By.xpath("//*[text()='Skleni novo']")).click();
			
			f.implicitWait(10);
			
			new WebDriverWait(driver,Duration.ofSeconds(40)).until(ExpectedConditions.urlToBe(baseURL + "/isklepanje/mladi/zavarovanje_mladi_voznik_1"));
			
			// KORAK 1 -  Podatki o zavarovanju
			/*
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			
			f.ScrollInKlik("//*[text()='Datum pridobitve vozniškega dovoljenja']");
			Thread.sleep(599);
			driver.findElement(By.id("obtainedDrivingLicenseDate")).sendKeys("18.06.2007");
			*/
			
				// Izpolnjen pogoj za vožnjo
			
			f.implicitWait(10);
			f.ScrollInKlik2(By.id("drivingCondition-button"));
			f.implicitWait(10);
			driver.findElement(By.id("obtainedDrivingLicenseDate")).sendKeys("18.06.2007");
			f.implicitWait(10);
			
			// Bo mladi voznik vozil le en avto?
			
			f.ScrollInKlik2(By.id("useOfOnlyOneVehicle-button"));
			f.implicitWait(10);
			
				// Naprej
			f.implicitWait(10);
			
			f.ScrollInKlik2(By.className("submitButton"));
			
			//Thread.sleep(2000);
			
			new WebDriverWait(driver,Duration.ofSeconds(40)).until(ExpectedConditions.urlToBe(baseURL + "/isklepanje/mladi/zavarovanje_mladi_voznik_2"));

			// KORAK 2 - Izberite paket
			
			f.implicitWait(10);
			
			f.ScrollInKlik2(By.id("btnPackageBig"));
			
			f.implicitWait(10);
					
			f.ScrollInKlik2(By.className("submitButton"));
			
			new WebDriverWait(driver,Duration.ofSeconds(40)).until(ExpectedConditions.urlToBe(baseURL + "/isklepanje/mladi/zavarovanje_mladi_voznik_3"));

			// KORAK 3 MLADI VOZNIK 
			
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			
			//f.ScrollInKlik("(//span[@class='checkbox--primary__icon'])[2]");
			
			if(driver.findElement(By.id("policyHolderUI.phoneNumber")).getText().equals("")) {
				f.ScrollInKlik2(By.id("policyHolderUI.phoneNumber"));
				driver.findElement(By.id("policyHolderUI.phoneNumber")).sendKeys("031 532 734");
			}
			
			f.implicitWait(10);
			
			f.ScrollInKlik("//*[@for='potrjujem-vse']");
			
			f.implicitWait(10);
					
			f.ScrollInKlik2(By.className("submitButton"));
			
			new WebDriverWait(driver,Duration.ofSeconds(40)).until(ExpectedConditions.urlToBe(baseURL + "/isklepanje/mladi/zavarovanje_mladi_voznik_4"));

			// KORAK 4	
			
			f.ScrollInKlik("//*[@for='info-zavarovalni-paket-mobile']");
			
			f.ScrollInKlik("//*[@for='zavarovalnimi-pogoji']");
		
			f.ScrollInKlik2(By.className("submitButton"));
			
			new WebDriverWait(driver,Duration.ofSeconds(40)).until(ExpectedConditions.urlToBe(baseURL + "/isklepanje/mladi/zavarovanje_mladi_voznik_5"));
			
			// KORAK 5 - Plačilo
					
			f.implicitWait(10);
			
			driver.findElement(By.xpath("(//*[@class='radio--secondary__button'])[2]")).click();
			f.implicitWait(10);
			
				// TRR
			
			driver.findElement(By.id("paymentOptions.manualTrrNumber")).sendKeys(trr);
			f.implicitWait(10);
			
				// pogoj
			
			f.ScrollInKlik2(By.xpath("//*[@for='soglasam-s-pogoji']"));
			f.implicitWait(10);
			
			// kartica
			
			f.ScrollInKlik("(//*[@class='radio-quaternary__button'])[3]");
			f.implicitWait(10);
						
			// Simuliraj plačilo
			
			f.ScrollInKlik2(By.xpath("//*[@id=\"mvQModel\"]/div/div/div[2]/div[3]/div[2]/button[1]"));
			
			f.implicitWait(10);	
			
			// Validacija uspešne sklenitve
			
			f.implicitWait(10);	
			
			WebElement ele = driver.findElement(By.xpath("//*[text()='Hvala!']"));
			
			new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOf(ele));
			
			if(ele.isDisplayed() == true) {
				System.out.println("Sklenitev MladiVoznikTestZObrocnimPlacevanjem je bilo uspešno");
			} else {
				throw new Exception("Sklenitev MladiVoznikTestZObrocnimPlacevanjem ni bilo uspešno");
			} 
			
		}
		
		
		public void mladiVoznikNew() throws Exception {
			String trr = "SI56 0234 0176 4167 804";
			Funkcije2 f = new Funkcije2(driver);
			Prijave2 p = new Prijave2(driver);

			// p.ItriglavPrijavaVnos("niko.sapac@triglav.si","Testgeslo123");
			p.SklepanjePrijava();

			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

			driver.get(baseURL + "/isklepanje/mladi/zavarovanje_mladi_voznik");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

			driver.findElement(By.xpath("//*[text()='Prijava']")).click();

			f.implicitWait(10);

			p.ItriglavPrijavaSklepanje();

			f.implicitWait(10);

			driver.findElement(By.xpath("//*[text()='Skleni novo']")).click();

			f.implicitWait(10);

			new WebDriverWait(driver, Duration.ofSeconds(40))
					.until(ExpectedConditions.urlToBe(baseURL + "/isklepanje/mladi/zavarovanje_mladi_voznik_1"));

			// KORAK 1 - Podatki o zavarovanju
			/*
			 * driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			 * 
			 * f.ScrollInKlik("//*[text()='Datum pridobitve vozniškega dovoljenja']");
			 * Thread.sleep(599);
			 * driver.findElement(By.id("obtainedDrivingLicenseDate")).sendKeys("18.06.2007"
			 * );
			 */

			// Izpolnjen pogoj za vožnjo

			f.implicitWait(10);
			f.ScrollInKlik2(By.id("drivingCondition-button"));
			f.implicitWait(10);
			driver.findElement(By.id("obtainedDrivingLicenseDate")).sendKeys("18.06.2007");
			f.implicitWait(10);

			// Bo mladi voznik vozil le en avto?

			f.ScrollInKlik2(By.id("useOfOnlyOneVehicle-button"));
			f.implicitWait(10);

			// Naprej
			f.implicitWait(10);

			f.ScrollInKlik2(By.className("submitButton"));

			// Thread.sleep(2000);

			new WebDriverWait(driver, Duration.ofSeconds(40))
					.until(ExpectedConditions.urlToBe(baseURL + "/isklepanje/mladi/zavarovanje_mladi_voznik_2"));

			// KORAK 2 - Izberite paket

			f.implicitWait(10);

			f.ScrollInKlik2(By.id("btnPackageBig"));

			f.implicitWait(10);

			f.ScrollInKlik2(By.className("submitButton"));

			new WebDriverWait(driver, Duration.ofSeconds(40))
					.until(ExpectedConditions.urlToBe(baseURL + "/isklepanje/mladi/zavarovanje_mladi_voznik_3"));

			// KORAK 3 MLADI VOZNIK

			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

			f.ScrollInKlik("(//span[@class='checkbox--primary__icon'])[2]");

//		if(driver.findElement(By.id("policyHolderUI.phoneNumber")).getText().equals("")) {
//			f.ScrollInKlik2(By.id("policyHolderUI.phoneNumber"));
//			driver.findElement(By.id("policyHolderUI.phoneNumber")).sendKeys("031 532 734");
//		}

			f.implicitWait(10);

//		f.ScrollInKlik("//*[@for='potrjujem-vse']");

			f.implicitWait(10);

			f.ScrollInKlik2(By.className("submitButton"));

			new WebDriverWait(driver, Duration.ofSeconds(40))
					.until(ExpectedConditions.urlToBe(baseURL + "/isklepanje/mladi/zavarovanje_mladi_voznik_4"));

			// KORAK 4

			f.ScrollInKlik("//*[@for='info-zavarovalni-paket-mobile']");

			f.ScrollInKlik("//*[@for='zavarovalnimi-pogoji']");

			f.ScrollInKlik2(By.className("submitButton"));

			new WebDriverWait(driver, Duration.ofSeconds(40))
					.until(ExpectedConditions.urlToBe(baseURL + "/isklepanje/mladi/zavarovanje_mladi_voznik_5"));

			// KORAK 5 - Plačilo

			f.implicitWait(10);
			
			driver.findElement(By.xpath("(//*[@class='radio--secondary__button'])[2]")).click();
			f.implicitWait(10);
			
				// TRR
			
			driver.findElement(By.id("paymentOptions.manualTrrNumber")).sendKeys(trr);
			f.implicitWait(10);
			
				// pogoj
			
			f.ScrollInKlik2(By.xpath("//*[@for='soglasam-s-pogoji']"));
			f.implicitWait(10);
			
			// kartica
			
			f.ScrollInKlik("(//*[@class='radio-quaternary__button'])[3]");
			f.implicitWait(10);
						
			// Simuliraj plačilo
			
			f.ScrollInKlik2(By.xpath("//*[@id=\"mvQModel\"]/div/div/div[2]/div[3]/div[2]/button[1]"));
			
			f.implicitWait(10);	
			
			// Validacija uspešne sklenitve
			
			f.implicitWait(10);	
			
			WebElement ele = driver.findElement(By.xpath("//*[text()='Hvala!']"));
			
			new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOf(ele));
			
			if(ele.isDisplayed() == true) {
				System.out.println("Sklenitev MladiVoznikTestZObrocnimPlacevanjem je bilo uspešno");
			} else {
				throw new Exception("Sklenitev MladiVoznikTestZObrocnimPlacevanjem ni bilo uspešno");
			}
		}
		
}
