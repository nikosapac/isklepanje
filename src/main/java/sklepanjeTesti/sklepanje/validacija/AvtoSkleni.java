package sklepanjeTesti.sklepanje.validacija;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import sklepanjeTesti.Funkcije;
import sklepanjeTesti.Prijave;


public class AvtoSkleni {
	
	WebDriver driver;
	String stevilo;
	
	List<WebElement> prviCount;
	List<WebElement> drugiCount;
	
	public AvtoSkleni(WebDriver driver) {
		this.driver = driver;
	}
	
	String email = "triglav.digi2@gmail.com";
	String password = "Test@123";
	
	@Test
	public void AvtoTest() throws Exception {
		
		Funkcije funk = new Funkcije(driver);
		Prijave p = new Prijave(driver);
		
		p.ItriglavPrijavaVnos(email,password);
		
		int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 17;
        Random random = new Random();
        String sasija = random.ints(leftLimit, rightLimit + 1)
        	      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
        	      .limit(targetStringLength)
        	      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
        	      .toString();
		
		String registrska = "MSMFAK5";
		String kilometri = "10000";
		String znamka = "citroen";
		String model = "c4";
		String tip = "c4";
		String firstRegistration = funk.DatumMesci(-6);
		String datumPotekZavarovanja = funk.DatumMesci(3);
		
		
		// Pogleda število plačil
		
		driver.get("https://skleni-qa.triglav.si/iTriglav3/placila");
		
		prviCount = driver.findElements(By.linkText("PLAČAJ"));
		
		System.out.println(prviCount.size());
		
		p.SklepanjePrijava();
		
		/*
		driver.get("https://skleni-qa.triglav.si/isklepanje/avto/avto_zavarovanje_1");
		Thread.sleep(1000);
		driver.findElement(By.id("regnumNum")).click();
		driver.findElement(By.id("regnumNum")).sendKeys(registrska);
		
		funk.ScrollInKlik("//*[@id=\"btnGetVehicleData\"]");
		funk.implicitWait(10);
		Thread.sleep(3500);
		funk.implicitWait(10);
		Thread.sleep(3500);
				
		funk.ScrollInKlik3(By.id("vehiclePolicy.vehicle.milageKm"));
	    Thread.sleep(500);
	    driver.findElement(By.id("vehiclePolicy.vehicle.milageKm")).sendKeys(km);
	    Thread.sleep(500);
		
		Thread.sleep(500);
		
		funk.ScrollInKlik3(By.id("vehiclePolicy.insuranceConclusion.policyStartDate"));
		
		funk.ScrollInKlik3(By.className("btn-next"));
		*/
		
		//PODATKI O VOZILU -  https://skleni-qa.triglav.si/isklepanje/avtomobilno/avto_zavarovanje_1
					
		driver.get("https://skleni-qa.triglav.si/isklepanje/avto/avto_zavarovanje_1");
		
	    funk.ScrollInKlik3(By.id("regnumNum"));
	    driver.findElement(By.id("regnumNum")).sendKeys(registrska);
	    funk.ScrollInKlik3(By.id("btnGetVehicleData"));
	    		
		Thread.sleep(3000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	    funk.ScrollInKlik3(By.xpath("(//span[@class='select2-selection__arrow'])[1]"));
	    Thread.sleep(1000);
	    driver.findElement(By.className("select2-search__field")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.className("select2-search__field")).sendKeys(znamka);
	    Thread.sleep(1000);
	    driver.findElement(By.className("select2-search__field")).sendKeys(Keys.ENTER);
	    Thread.sleep(1000);
	    funk.ScrollInKlik3(By.xpath("(//span[@class='select2-selection__arrow'])[2]"));
	    Thread.sleep(1000);
	    driver.findElement(By.className("select2-search__field")).click();
	    Thread.sleep(1000);
	    driver.findElement(By.className("select2-search__field")).sendKeys(model);
	    Thread.sleep(1000);
	    driver.findElement(By.className("select2-search__field")).sendKeys(Keys.ENTER);
	    Thread.sleep(1000);
	    funk.ScrollInKlik3(By.xpath("(//span[@class='select2-selection__arrow'])[3]"));
	    Thread.sleep(1000);
	    driver.findElement(By.className("select2-search__field")).click();
	    Thread.sleep(1000);
	    driver.findElement(By.className("select2-search__field")).sendKeys(tip);
	    Thread.sleep(1000);
	    driver.findElement(By.className("select2-search__field")).sendKeys(Keys.ENTER);
	    Thread.sleep(3000);
		
	    funk.ScrollInKlik3(By.id("vehiclePolicy.vehicle.vehicleIdentificationNumber"));
	    Thread.sleep(1000);
	    driver.findElement(By.id("vehiclePolicy.vehicle.vehicleIdentificationNumber")).sendKeys(sasija);
	    driver.findElement(By.id("vehiclePolicy.vehicle.firstRegistration")).sendKeys(firstRegistration);
	    Thread.sleep(1500);
	    			
	    funk.ScrollInKlik3(By.id("vehiclePolicy.vehicle.milageKm"));
	    Thread.sleep(1000);
	    driver.findElement(By.id("vehiclePolicy.vehicle.milageKm")).sendKeys(kilometri);
	    Thread.sleep(1000);
		
	    funk.ScrollInKlik3(By.id("vehiclePolicy.insuranceConclusion.policyStartDate"));
	    Thread.sleep(1000);
	    driver.findElement(By.id("vehiclePolicy.insuranceConclusion.policyStartDate")).click();

	    Thread.sleep(1500);
	    funk.ScrollInKlik3(By.className("checkbox--primary__icon"));
	    Thread.sleep(1000);
	    Thread.sleep(1000);
	    driver.findElement(By.id("vehiclePolicy.insuranceConclusion.policyEndDate")).sendKeys(datumPotekZavarovanja);
	    Thread.sleep(1000);
		
	    funk.ScrollInKlik3(By.className("btn-next"));
	    new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.urlToBe("https://skleni-qa.triglav.si/isklepanje/avto/avto_zavarovanje_2"));
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	    Thread.sleep(3000);
		
		// Izberite paket - https://skleni-qa.triglav.si/isklepanje/avto/avto_zavarovanje_2
		
		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.urlToBe("https://skleni-qa.triglav.si/isklepanje/avto/avto_zavarovanje_2"));

		funk.ScrollInKlik("//*[@id=\"btnPackageSmall\"]");
		
		funk.ScrollInKlik3(By.className("btn-next"));
		
		// Podatki o sklenitelju -https://skleni-qa.triglav.si/isklepanje/avto/avto_zavarovanje_3
		
		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.urlToBe("https://skleni-qa.triglav.si/isklepanje/avto/avto_zavarovanje_3"));
		
		funk.ScrollInKlik("(//*[@class='checkbox--primary__icon'])[2]");
		Thread.sleep(500);
		funk.ScrollInKlik3(By.className("btn-next"));
		
		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.urlToBe("https://skleni-qa.triglav.si/isklepanje/avto/avto_zavarovanje_4"));
		
		// Premija in ugodnosti - https://skleni-qa.triglav.si/isklepanje/avto/avto_zavarovanje_4
		
		Thread.sleep(1000);

		funk.ScrollInKlik("//*[@for='zavarovalnimi-pogoji']");
		Thread.sleep(500);
		WebElement test = driver.findElement(By.xpath("//*[@for='info-zavarovalni-paket-mobile']"));
		new Actions(driver).moveToElement(test, 2, 2).click().perform();
		
		funk.ScrollInKlik3(By.className("btn-next"));
		
		// Plačilo - https://skleni-qa.triglav.si/isklepanje/avto/avto_zavarovanje_5
		
		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.urlToBe("https://skleni-qa.triglav.si/isklepanje/avto/avto_zavarovanje_5"));

		Thread.sleep(1000);
		
		driver.findElement(By.xpath("(//div[@class='radio--secondary__button'])[2]")).click();
		Thread.sleep(500);
		
		driver.findElement(By.xpath("(//span[@class='select2-selection__arrow'])[1]")).click();
		Thread.sleep(500);
	//	driver.findElement(By.xpath("//*[text()='9 mesečnih obrokov']")).click();
		
		driver.findElement(By.xpath("//*[text()='2 mesečna obroka']")).click();
		
		funk.ScrollInKlik3(By.xpath("(//span[@class='select2-selection__arrow'])[2]"));
		Thread.sleep(1500);
	//	driver.findElement(By.xpath("//*[text()='SI56 0234 0176 4167 804']")).click();
	//	driver.findElement(By.xpath("//*[text()='SI56 0312 5100 0483 162']")).click();
		driver.findElement(By.xpath("//*[text()='SI56 0422 2021 4943 682']")).click();
		
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("(//*[@class='select2-selection__arrow'])[2]")).click();
		
	//	funk.ScrollInKlik3(By.className("agreement-checkbox-label"));
		
		funk.ScrollInKlik("(//div[@class='radio-quaternary__button'])[4]");
		
		funk.ScrollInKlik("//*[@for='soglasam-s-pogoji']");
		
		funk.ScrollInKlik("//button[@name='simulatePayment']");
		
		Thread.sleep(1000);
		
		WebElement ele = driver.findElement(By.xpath("//*[text()='Hvala!']"));
		
		new WebDriverWait(driver,Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOf(ele));
		
		if(ele.isDisplayed() == true) {
			System.out.println("Sklenitev za avto obroke je bila uspešna");
		} else {
			throw new Exception("Sklenitev za avto obroke ni bilo uspešno");
		}
		
		Thread.sleep(500);
		
		do {
			driver.get("https://skleni-qa.triglav.si/iTriglav3/placila");
			Thread.sleep(1000);
			drugiCount = driver.findElements(By.linkText("PLAČAJ"));
			
			System.out.println("Prvi: " + prviCount.size());
			System.out.println("Drugi: " + drugiCount.size());
		}
		
		while(prviCount.size() == drugiCount.size());
		
		System.out.println("Prvi ko: " + prviCount.size());
		System.out.println("Drugi ko: " + drugiCount.size());
		
		int st = drugiCount.size() - prviCount.size();
		
		if(drugiCount.size() == prviCount.size()+1 ) {
			System.out.println("Uspešna validacija, count plačil je: " + drugiCount.size());
			System.out.println("Count plačil se je povečal za: " + st);
		}
		else  {
			throw new Exception("NEUspešna validacija, drugi count je: " + drugiCount.size() + "povečalo se je za : " + st);
		}
		
	}
	
}