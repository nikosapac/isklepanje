package sklepanjeTesti.sklepanje;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

public class MaleZivali {
	
	private WebDriver driver;
	
	EnvironmentConfig cfg = ConfigFactory.create(EnvironmentConfig.class);
	
	String baseURL = cfg.baseURL();
	String email = cfg.iTriglavEmail();
	String password = cfg.iTriglavPass();
	
	public MaleZivali(WebDriver driver) {
		this.driver = driver;
	}
	
	/*

	@Test
	public void MaleZivaliTest() throws Exception  {
		
		
		Funkcije2 f = new Funkcije2(driver);
		Prijave2 p = new Prijave2(driver);
		
		//p.ItriglavPrijavaVnos("niko.sapac@triglav.si","Testgeslo123");
		
		p.SklepanjePrijava();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//datum rojstva zivali        
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -4);
		
		//Fiksne vrednosti
        String urlZavarovanja= baseURL + "/isklepanje/zivali/pets_zavarovanje";
	    String ime = "Nikita";
	    String priimek = "Plej";
	    String datumRojstva = "18.08.1998";
	    String davcna = "19553170";
	    String postnaStevilka = "9000";
	    String email ="zkpr.test@triglav.si";
	    String validacijskaKoda = "9999";
	    String imeZivali = "Miki";
	    String datumRojstvaZivali = formatter.format(calendar.getTime());
	    String mikroCip = "147852989957103";
	    // String veterinaryDiscountCoupon ="123test";
	    String mrPetCode = "MR5343348";
	    String trrRacuna = "SI56031251000483162";
	    
	  //Za scrollanje - do polovice ekrana
	    int viewportHeight = ((Long) ((JavascriptExecutor) driver).executeScript("return window.innerHeight")).intValue();
	    int halfViewport = viewportHeight / 2;
        
      //odpri stran
        driver.get(urlZavarovanja);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        
        
        //prijava
        
        if(f.IsVisible(By.xpath("//a[text()='Prijava']"))) {
    	    
	    	driver.findElement(By.xpath("//a[text()='Prijava']")).click();
	    }
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	    
	    p.ItriglavPrijavaSklepanje();
	    
	    f.implicitWait(30);
                
      // Skleni novo        
	    driver.findElement(By.className("submitButton")).click();
	    f.implicitWait(25);
        

	    	// Vrsta živali
	    
	    f.ScrollInKlik2(By.xpath("(//*[@class='select2-selection__arrow'])[1]"));
	    f.implicitWait(15);
	    driver.findElement(By.xpath("//*[text()='Pes']")).click();
	    f.implicitWait(10);
	    driver.findElement(By.xpath("(//*[@class='select2-selection__arrow'])[1]")).click();
	    
	    	// Ime živali
	    
	    f.implicitWait(10);
	    driver.findElement(By.id("pet.name")).sendKeys(imeZivali);
	    
	    	// Datum rojstva živali
	    
	    f.implicitWait(10);
	    driver.findElement(By.id("pet.birthDate")).sendKeys(datumRojstvaZivali);
	    
	    
	    	// Spol živali
	    
	    f.implicitWait(10);
	    f.ScrollInKlik2(By.xpath("//*[@for='pet.gender-yes']"));
	    
	    	// Številka mikročipa
	    
	    f.implicitWait(10);
	    driver.findElement(By.id("pet.microchipNum")).sendKeys(mikroCip);
	    
	    	// Žival je
	    
	    f.implicitWait(10);
	    f.ScrollInKlik2(By.xpath("//*[@for='pet.isPureBreed-yes']"));
	    
	    	// Pasma psa
	    
	    f.ScrollInKlik2(By.xpath("(//*[@class='select2-selection__arrow'])[2]"));
	    f.implicitWait(15);
	    driver.findElement(By.xpath("//*[text()='Afganistanski hrt']")).click();
	    f.implicitWait(10);
	    driver.findElement(By.xpath("(//*[@class='select2-selection__arrow'])[2]")).click();
	    
	    	// 10 % dodatnega popusta
	    
	    f.implicitWait(10);
	    f.ScrollInKlik2(By.xpath("//*[@for='pet.authorizedVet-yes']"));
	    
	    	// Naprej
	    
	    f.implicitWait(10);
	    f.ScrollInKlik2(By.className("submitButton"));
	    f.implicitWait(30);
	    
	    //Koda za popust

	    
	    // Izbira paketa
	    
	    f.ScrollInKlik2(By.className("submitButton"));
	    f.implicitWait(25);
	    
      //PODATKI O SKLENITELJU - https://skleni-qa.triglav.si/isklepanje/zivali/pets_zavarovanje_2

	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	    
	    //f.ScrollInKlik2(By.xpath("(//span[@class='cs-slider'])[1]"));
	    //f.ScrollInKlik2(By.xpath("(//span[@class='cs-slider'])[2]"));
	   	    
	    f.ScrollInKlik2(By.className("checkbox--primary__icon"));
	    
	    f.implicitWait(15);
	   
	    f.ScrollInKlik2(By.className("nextStep"));
	    
	    f.waitForLoaderToFinish(driver, By.className("loader"));	    
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	    
	    
    	//PONUDBA IN POPUSTI - https://skleni-qa.triglav.si/isklepanje/zivali/pets_zavarovanje_3
		//MR. PET POPUST
    	driver.findElement(By.xpath("//label[@for='chk-box-mrPet']")).click();
    	driver.findElement(By.id("ipdMrPetCode")).sendKeys(mrPetCode);
    	driver.findElement(By.id("btnAddMrPetCode")).click();
    	
    	f.waitForLoaderToFinish(driver, By.className("loader"));	    
    	
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	    new WebDriverWait(driver,Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[@class='cs-slider'])[1]")));
	    Thread.sleep(1000);
	    f.ScrollInKlik2(By.xpath("(//span[@class='cs-slider'])[1]"));	
	    f.ScrollInKlik2(By.xpath("(//span[@class='cs-slider'])[2]"));
	    
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	    
	    f.ScrollInKlik2(By.className("nextStep"));
	    	
		f.waitForLoaderToFinish(driver, By.className("loader"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//SKLENITEV - https://skleni-qa.triglav.si/isklepanje/zivali/pets_zavarovanje_4
		
		f.ScrollInKlik2(By.xpath("//*[text()='Plačilo z UPN nalogom']"));
		
		f.ScrollInKlik("(//span[@class='cs-slider'])[2]");
		
		f.ScrollInKlik2(By.className("nextStep"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//PLACILO - https://skleni-qa.triglav.si/isklepanje/zivali/preparePayment
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		f.ScrollInKlik2(By.className("alternateNext"));
	
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		f.checkSklenitev();
		
		
	}
	
	*/
	
	public void MaleZivaliQTest() throws Exception {
		
		Funkcije2 f = new Funkcije2(driver);
		Prijave2 p = new Prijave2(driver);
		
		//datum rojstva zivali        
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -4);
				
		//Fiksne vrednosti
        String urlZavarovanja= baseURL + "/isklepanje/zivali/pets_zavarovanje";
	    String ime = "Nikita";
	    String priimek = "Plej";
	    String datumRojstva = "18.08.1998";
	    String davcna = "19553170";
	    String postnaStevilka = "9000";
	    String email ="zkpr.test@triglav.si";
	    String validacijskaKoda = "9999";
	    String imeZivali = "Miki";
	    String datumRojstvaZivali = formatter.format(calendar.getTime());
	    String mikroCip = "147852989957103";
	    // String veterinaryDiscountCoupon ="123test";
	    String mrPetCode = "MR5343348";
	    String trrRacuna = "SI56031251000483162";
		
		// Prijava
		p.SklepanjePrijava();
		
		// Začetni korak
		driver.get(baseURL + "/isklepanje/zivali/zavarovanje_psov_in_mack");
		f.implicitWait(15);
		
		driver.findElement(By.xpath("//*[text()='Prijava']")).click();
		f.implicitWait(15);
	
		p.ItriglavPrijavaSklepanje();
		
		new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Skleni novo')]")));
		f.implicitWait(15);
		
		driver.findElement(By.className("submitButton")).click();
		f.implicitWait(15);
		f.waitForLoaderToFinish(driver, By.className("loader"));
		f.implicitWait(15);
		new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.urlToBe(baseURL + "/isklepanje/zivali/zavarovanje_psov_in_mack_1"));
		f.implicitWait(15);
		
		// KORAK 1 - Podatki o zavarovanju
		
			// Podatki o živali
		
				// Vrsta živali
		
		new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@class='select2-selection__arrow'])[1]")));
		f.ScrollInKlik2(By.xpath("(//*[@class='select2-selection__arrow'])[1]"));
		f.implicitWait(15);
		new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Pes']")));
		f.implicitWait(10);
		driver.findElement(By.xpath("//*[text()='Pes']")).click();
		f.implicitWait(10);
		driver.findElement(By.xpath("(//*[@class='select2-selection__arrow'])[1]")).click();
				
				// Ime živali
		
		new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfElementLocated(By.id("pet.name")));
		f.implicitWait(10);
		driver.findElement(By.id("pet.name")).sendKeys(imeZivali);
		
				// Datum rojstva živali
		
		new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfElementLocated(By.id("pet.birthDate")));
		f.implicitWait(10);
		driver.findElement(By.id("pet.birthDate")).sendKeys(datumRojstvaZivali);
		f.implicitWait(15);
		
				// Spol živali	- Samec
		
		f.ScrollInKlik2(By.xpath("(//*[@class='radio--primary__button'])[2]"));
		f.implicitWait(10);
		driver.findElement(By.id("pet.microchipNum")).sendKeys(mikroCip);
		f.implicitWait(10);
		
				// Žival je	- Nepasemska
		
		f.ScrollInKlik2(By.xpath("(//*[@class='radio--primary__button'])[4]"));
		f.implicitWait(10);
		
				// 10 % popusta za zdravljenje pri pooblaščenem veterinarju	- DA
		
		f.ScrollInKlik2(By.xpath("//*[@for='pet.authorizedVet-yes']"));
		f.implicitWait(10);	
		
				// Naprej
		
		f.ScrollInKlik2(By.className("submitButton"));
		f.waitForLoaderToFinish(driver, By.className("loader"));
		new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.urlToBe(baseURL + "/isklepanje/zivali/zavarovanje_psov_in_mack_2"));
		
		// KORAK 2 - Izbira paketa
		
				// Veliki paket
		
		f.ScrollInKlik2(By.className("submitButton"));
		f.waitForLoaderToFinish(driver, By.className("loader"));
		new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.urlToBe(baseURL + "/isklepanje/zivali/zavarovanje_psov_in_mack_3"));
		
		// KORAK 3 - Podatki o sklenitelju
				
			// Pogoji
		
		f.implicitWait(10);
		f.ScrollInKlik2(By.className("checkbox--primary__icon"));
		
			// Naprej
		
		f.ScrollInKlik2(By.className("submitButton"));
		f.waitForLoaderToFinish(driver, By.className("loader"));
		new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.urlToBe(baseURL + "/isklepanje/zivali/zavarovanje_psov_in_mack_4"));
		
		
		// KORAK 4 - Premija in ugodnosti
		
			// 10 % popust za član mr.pet kluba
		
		f.implicitWait(10);
		
		new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(By.id("btnMrPetVet")));
		f.ScrollInKlik2(By.id("btnMrPetVet"));
		new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(By.id("ipdMrPetVetCode")));
		Thread.sleep(1000);
		f.implicitWait(10);
		driver.findElement(By.id("ipdMrPetVetCode")).sendKeys(mrPetCode);
		f.implicitWait(10);
		driver.findElement(By.id("btnMrPetVetAdd")).click();
		f.waitForLoaderToFinish(driver, By.className("loader"));
		new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Pridobili ste']")));
		driver.findElement(By.xpath("//*[@id=\"discountMrPetVetSuccess\"]/div/div/div/a")).click();		
		f.implicitWait(10);
		
			// Pogoji
		
		f.ScrollInKlik2(By.xpath("//*[@for='info-zavarovalni-paket-mobile']"));
		driver.findElement(By.xpath("//*[@for='zavarovalnimi-pogoji']")).click();
		f.implicitWait(10);
		
		f.ScrollInKlik2(By.className("submitButton"));
		f.waitForLoaderToFinish(driver, By.className("loader"));
		new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.urlToBe(baseURL + "/isklepanje/zivali/zavarovanje_psov_in_mack_5"));
		
		// KORAK 5 - Plačilo
		
		f.implicitWait(10);
		
		driver.findElement(By.xpath("(//*[@class='radio--secondary__button'])[1]")).click();
		new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@class='radio-quaternary__button'])[1]")));
		f.implicitWait(10);
		
			// Vrsta plačila
		
		driver.findElement(By.xpath("(//*[@class='radio-quaternary__button'])[1]")).click();
		f.implicitWait(10);
		
			// Pogoj
		
	//	f.ScrollInKlik2(By.xpath("//*[for='soglasam-s-pogoji-celotna-premija']"));
		f.ScrollInKlik2(By.className("agreement-checkbox-label"));
		f.implicitWait(10);
		
		// Simuliraj plačilo
		
		f.ScrollInKlik2(By.xpath("//*[@id=\"petQModel\"]/div[2]/div[2]/button[1]"));
		
		// Validacija uspešne sklenitve
		
		f.implicitWait(10);	
		
		WebElement ele = driver.findElement(By.xpath("//*[text()='Hvala!']"));
		
		new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOf(ele));
		
		if(ele.isDisplayed() == true) {
			System.out.println("Sklenitev MaleZivaliQTest je bilo uspešno");
		} else {
			throw new Exception("Sklenitev MaleZivaliQTest ni bilo uspešno");
		}
		
		
	}

}
