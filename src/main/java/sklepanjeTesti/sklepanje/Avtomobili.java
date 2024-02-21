package sklepanjeTesti.sklepanje;

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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import sklepanjeTesti.Funkcije2;
import sklepanjeTesti.Prijave2;
import sklepanjeTesti.config.EnvironmentConfig;
import sklepanjeTesti.sklepanje.validacija.DrajvKodaValidacija;

public class Avtomobili {
	
	private WebDriver driver;
	
	EnvironmentConfig cfg = ConfigFactory.create(EnvironmentConfig.class);
	String baseURL = cfg.baseURL();
	String itrEmail = cfg.iTriglavEmail();
	String itrPass = cfg.iTriglavPass();
	
	public Avtomobili(WebDriver driver) {
		this.driver = driver;
	}
	
	// Samo mlada družina popust
	@Test	
	public void AvtomobiliTest() throws InterruptedException {

		Funkcije2 funk = new Funkcije2(driver);
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
      //datum zacetka zavarovanja  
        Calendar calendar1 = Calendar.getInstance();
        calendar1.add(Calendar.MONTH, 1); 
      //datum poteka zavarovanja
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 12);   
        
      //Šasija
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 17;
        Random random = new Random();
        String sasija = random.ints(leftLimit, rightLimit + 1)
        	      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
        	      .limit(targetStringLength)
        	      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
        	      .toString();
	        
        String test123;
        
        System.out.println("test");
        
     //Fiksne vrednosti
        String urlZavarovanja= baseURL + "/isklepanje/avto/avto_zavarovanje";
        String LDAPusername = "tsalhorvat";
	    String LDAPgeslo = "gremonapivo";
        String telefonska = "031532734";
        String registrska = "MS18-NJV";
        String znamka = "vw";
        String model = "t-roc (2017-2022)";
        String tip = "T-Roc 1,5 TSI BMT Sport (10.2018-07.2022)";
        String firstRegistration ="16.9.2020";
        String kilometri = "5000";
        String datumZacetkaZavarovanja = formatter.format(calendar1.getTime());
	    String datumPotekZavarovanja = formatter.format(calendar.getTime());
	    String ime = "Nikita";
	    String priimek = "Plej";
	    String datumRojstva = "18.08.1998";
	    String davcna = "19553170";
	    String postnaStevilka = "9000";
	    String email ="zkpr.test@triglav.si";
	    String validacijskaKoda = "9999";
	    String drajvKoda = "asd1234";
	    String imeOtroka = "Janček";
	    String priimekOtroka = "Ježek";
	    String datumRojstvaOtroka = "16.9.2020";
	    String davcnaOtroka = "65694350";
	    
	    
	  //Za scrollanje - do polovice ekrana
	    
	    int viewportHeight = ((Long) ((JavascriptExecutor) driver).executeScript("return window.innerHeight")).intValue();
	    int halfViewport = viewportHeight / 2;
                
	    Prijave2 p = new Prijave2(driver);
	  //  p.ItriglavPrijavaVnos("niko.sapac@triglav.si","Testgeslo123");
	        
		 //Odpri stran 
		    
		    p.SklepanjePrijava();
		    
		    driver.get(urlZavarovanja);
		    
		    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		    
		    if(funk.IsVisible(By.xpath("//a[text()='PRIJAVA']"))) {
		    	driver.findElement(By.xpath("//a[text()='PRIJAVA']")).click();
		    }
		    
		    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		    
		    p.ItriglavPrijavaSklepanje();
		    
		    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		    
		    funk.ScrollInKlik("//*[text()='Skleni novo']");
		    
		    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		    
		  //PODATKI O VOZILU -  https://skleni-qa.triglav.si/isklepanje/avtomobilno/avto_zavarovanje_1
		   
		    funk.ScrollInKlik2(By.id("regnumNum"));
		    driver.findElement(By.id("regnumNum")).sendKeys(registrska);
		    funk.ScrollInKlik2(By.id("btnGetVehicleData"));
		    
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			
			Thread.sleep(3000);
		    funk.ScrollInKlik2(By.xpath("(//span[@class='select2-selection__arrow'])[1]"));
		    Thread.sleep(1000);
		    driver.findElement(By.className("select2-search__field")).click();
		    Thread.sleep(2000);
		    driver.findElement(By.className("select2-search__field")).sendKeys(znamka);
		    Thread.sleep(1000);
		    driver.findElement(By.className("select2-search__field")).sendKeys(Keys.ENTER);
		    Thread.sleep(1000);
		    funk.ScrollInKlik2(By.xpath("(//span[@class='select2-selection__arrow'])[2]"));
		    Thread.sleep(1000);
		    driver.findElement(By.className("select2-search__field")).click();
		    Thread.sleep(1000);
		    driver.findElement(By.className("select2-search__field")).sendKeys(model);
		    Thread.sleep(1000);
		    driver.findElement(By.className("select2-search__field")).sendKeys(Keys.ENTER);
		    Thread.sleep(1000);
		    funk.ScrollInKlik2(By.xpath("(//span[@class='select2-selection__arrow'])[3]"));
		    Thread.sleep(1000);
		    driver.findElement(By.className("select2-search__field")).click();
		    Thread.sleep(1000);
		    driver.findElement(By.className("select2-search__field")).sendKeys(tip);
		    Thread.sleep(1000);
		    driver.findElement(By.className("select2-search__field")).sendKeys(Keys.ENTER);
		    Thread.sleep(3000);
		    
		    funk.implicitWait(10);
			
		    funk.ScrollInKlik2(By.id("vehiclePolicy.vehicle.vehicleIdentificationNumber"));
		    Thread.sleep(1000);
		    driver.findElement(By.id("vehiclePolicy.vehicle.vehicleIdentificationNumber")).sendKeys(sasija);
		    driver.findElement(By.id("vehiclePolicy.vehicle.firstRegistration")).sendKeys(firstRegistration);
		    Thread.sleep(1500);
		    			
		    funk.ScrollInKlik2(By.id("vehiclePolicy.vehicle.milageKm"));
		    Thread.sleep(1000);
		    driver.findElement(By.id("vehiclePolicy.vehicle.milageKm")).sendKeys(kilometri);
		    Thread.sleep(1000);
			
		    funk.ScrollInKlik2(By.id("vehiclePolicy.insuranceConclusion.policyStartDate"));
		    Thread.sleep(1000);
		    driver.findElement(By.id("vehiclePolicy.insuranceConclusion.policyStartDate")).click();

		    Thread.sleep(1500);
		    funk.ScrollInKlik2(By.className("checkbox--primary__icon"));
		    Thread.sleep(1000);
		    Thread.sleep(1000);
		    driver.findElement(By.id("vehiclePolicy.insuranceConclusion.policyEndDate")).sendKeys(datumPotekZavarovanja);
		    Thread.sleep(1000);
			
		    funk.ScrollInKlik2(By.className("btn-next"));
		    new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.urlToBe(baseURL + "/isklepanje/avto/avto_zavarovanje_2"));
		    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		    Thread.sleep(3000);
		    
		  //IZBERI PAKET - https://skleni-qa.triglav.si/isklepanje/avtomobilno/avto_zavarovanje_2
			  
		    Thread.sleep(1000);
		   funk.ScrollInKlik2(By.className("btn-next"));
		    Thread.sleep(3000);
		    
		    new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.urlToBe(baseURL + "/isklepanje/avto/avto_zavarovanje_3"));
		    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		    Thread.sleep(1000);
		    
		  //PODATKI O SKLENITELJU - https://skleni-qa.triglav.si/isklepanje/avtomobilno/avto_zavarovanje_3
		    
		    funk.ScrollInKlik("(//span[@class='checkbox--primary__icon'])[2]");
		    Thread.sleep(500);
		    funk.ScrollInKlik2(By.className("btn-next"));
		    
		    new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.urlToBe(baseURL + "/isklepanje/avto/avto_zavarovanje_4"));
		    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		    Thread.sleep(3000);
		    
		  //PREMIJA IN UGODNOSTI - https://skleni-qa.triglav.si/isklepanje/avtomobilno/avto_zavarovanje_4
		    		    
		    //POPUST MLADA DRUŽINA
		    
		   funk.ScrollInKlik2(By.id("btnCarVnosOtroka"));
			Thread.sleep(1000);
			driver.findElement(By.id("name")).sendKeys(imeOtroka);
		    driver.findElement(By.id("surname")).sendKeys(priimekOtroka);
		    driver.findElement(By.id("birthday")).sendKeys(datumRojstvaOtroka);
		    driver.findElement(By.id("taxnum")).sendKeys(davcnaOtroka);
		    Thread.sleep(2000);
			funk.implicitWait(10);
		    funk.ScrollInKlik2(By.id("saveChildData"));
		    
		    funk.waitForLoaderToFinish(driver, By.className("loader"));
		    Thread.sleep(1000);
		    driver.findElement(By.xpath("//*[@id=\"carChildSuccess\"]/div/div/div/a")).click();		    
		    
		    // Pogoji
		    
		    Thread.sleep(1000);
			  
		    funk.ScrollInKlik("//*[@for='zavarovalnimi-pogoji']");
			Thread.sleep(500);
			WebElement test = driver.findElement(By.xpath("//*[@for='info-zavarovalni-paket-mobile']"));
			new Actions(driver).moveToElement(test, 2, 2).click().perform();
		    	
		    funk.ScrollInKlik2(By.className("btn-next"));
		    
		    funk.waitForLoaderToFinish(driver, By.className("loader"));
		    
		    new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.urlToBe(baseURL + "/isklepanje/avto/avto_zavarovanje_5"));
		    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		    
		  //PLAČILO - https://skleni-qa.triglav.si/isklepanje/avtomobilno/avto_zavarovanje_5
		     
		    driver.findElement(By.className("radio--secondary__button")).click();
		    Thread.sleep(1000);
		    driver.findElement(By.className("radio-quaternary__button")).click();
		    Thread.sleep(1000);
		    
		    funk.ScrollInKlik("//button[@name='simulatePayment']");
		    
		    Thread.sleep(3000);
		    		    
		    WebElement ele = driver.findElement(By.xpath("//*[text()='Hvala!']"));
			
			new WebDriverWait(driver,Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOf(ele));
			
			if(ele.isDisplayed() == true) {
				System.out.println("Sklenitev je bila uspešna");
			} else {
				System.out.println("Sklenitev NI bila uspešna");
			}    
		   
	}
	
	
	@Test
	public void AvtomobiliTestVsiPopusti() throws Exception {
		
		String drajvKoda = DrajvKodaValidacija.drajvKoda;

		Funkcije2 funk = new Funkcije2(driver);
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
      //datum zacetka zavarovanja  
        Calendar calendar1 = Calendar.getInstance();
        calendar1.add(Calendar.MONTH, 1); 
      //datum poteka zavarovanja
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 12);   
        
      //Šasija
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 17;
        Random random = new Random();
        String sasija = random.ints(leftLimit, rightLimit + 1)
        	      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
        	      .limit(targetStringLength)
        	      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
        	      .toString();
	        
     //Fiksne vrednosti
        String urlZavarovanja= baseURL + "/isklepanje/avto/avto_zavarovanje";
        String LDAPusername = "tsalhorvat";
	    String LDAPgeslo = "gremonapivo";
        String telefonska = "031532734";
        String registrska = "MS DRAJV";
        String znamka = "vw";
        String model = "t-roc (2017-2022)";
        String tip = "T-Roc 1,5 TSI BMT Sport (10.2018-07.2022)";
        String firstRegistration ="16.9.2020";
        String kilometri = "5000";
        String datumZacetkaZavarovanja = formatter.format(calendar1.getTime());
	    String datumPotekZavarovanja = formatter.format(calendar.getTime());
	    String ime = "Nikita";
	    String priimek = "Plej";
	    String datumRojstva = "18.08.1998";
	    String davcna = "19553170";
	    String postnaStevilka = "9000";
	    String email ="zkpr.test@triglav.si";
	    String validacijskaKoda = "9999";
	    String imeOtroka = "Janček";
	    String priimekOtroka = "Ježek";
	    String datumRojstvaOtroka = "16.9.2020";
	    String davcnaOtroka = "65694350";
	    
	    
	  //Za scrollanje - do polovice ekrana
	    
	    int viewportHeight = ((Long) ((JavascriptExecutor) driver).executeScript("return window.innerHeight")).intValue();
	    int halfViewport = viewportHeight / 2;
                
	    Prijave2 p = new Prijave2(driver);
	  //  p.ItriglavPrijavaVnos("niko.sapac@triglav.si","Testgeslo123");
	        
		 //Odpri stran 
		    
		    p.SklepanjePrijava();
		    
		    driver.get(urlZavarovanja);
		    
		    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		    
		    if(funk.IsVisible(By.xpath("//a[text()='PRIJAVA']"))) {
		    	driver.findElement(By.xpath("//a[text()='PRIJAVA']")).click();
		    }
		    
		    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		    
		    p.ItriglavPrijavaSklepanje2("triglav.digi2@gmail.com", "Test@123");
		    
		    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		    
		    funk.ScrollInKlik("//*[text()='Skleni novo']");
		    
		    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		    
		  //PODATKI O VOZILU -  https://skleni-qa.triglav.si/isklepanje/avtomobilno/avto_zavarovanje_1
		   
		    funk.ScrollInKlik2(By.id("regnumNum"));
		    driver.findElement(By.id("regnumNum")).sendKeys(registrska);
		    funk.ScrollInKlik2(By.id("btnGetVehicleData"));
		    
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			
			Thread.sleep(3000);
		    funk.ScrollInKlik2(By.xpath("(//span[@class='select2-selection__arrow'])[1]"));
		    Thread.sleep(1000);
		    driver.findElement(By.className("select2-search__field")).click();
		    Thread.sleep(2000);
		    driver.findElement(By.className("select2-search__field")).sendKeys(znamka);
		    Thread.sleep(1000);
		    driver.findElement(By.className("select2-search__field")).sendKeys(Keys.ENTER);
		    Thread.sleep(1000);
		    funk.ScrollInKlik2(By.xpath("(//span[@class='select2-selection__arrow'])[2]"));
		    Thread.sleep(1000);
		    driver.findElement(By.className("select2-search__field")).click();
		    Thread.sleep(1000);
		    driver.findElement(By.className("select2-search__field")).sendKeys(model);
		    Thread.sleep(1000);
		    driver.findElement(By.className("select2-search__field")).sendKeys(Keys.ENTER);
		    Thread.sleep(1000);
		    funk.ScrollInKlik2(By.xpath("(//span[@class='select2-selection__arrow'])[3]"));
		    Thread.sleep(1000);
		    driver.findElement(By.className("select2-search__field")).click();
		    Thread.sleep(1000);
		    driver.findElement(By.className("select2-search__field")).sendKeys(tip);
		    Thread.sleep(1000);
		    driver.findElement(By.className("select2-search__field")).sendKeys(Keys.ENTER);
		    Thread.sleep(3000);
		    
		    funk.implicitWait(10);
			
		    funk.ScrollInKlik2(By.id("vehiclePolicy.vehicle.vehicleIdentificationNumber"));
		    Thread.sleep(1000);
		    driver.findElement(By.id("vehiclePolicy.vehicle.vehicleIdentificationNumber")).sendKeys(sasija);
		    driver.findElement(By.id("vehiclePolicy.vehicle.firstRegistration")).sendKeys(firstRegistration);
		    Thread.sleep(1500);
		    			
		    funk.ScrollInKlik2(By.id("vehiclePolicy.vehicle.milageKm"));
		    Thread.sleep(1000);
		    driver.findElement(By.id("vehiclePolicy.vehicle.milageKm")).sendKeys(kilometri);
		    Thread.sleep(1000);
			
		    funk.ScrollInKlik2(By.id("vehiclePolicy.insuranceConclusion.policyStartDate"));
		    Thread.sleep(1000);
		    driver.findElement(By.id("vehiclePolicy.insuranceConclusion.policyStartDate")).click();

		    Thread.sleep(1500);
		    funk.ScrollInKlik2(By.className("checkbox--primary__icon"));
		    Thread.sleep(1000);
		    Thread.sleep(1000);
		    driver.findElement(By.id("vehiclePolicy.insuranceConclusion.policyEndDate")).sendKeys(datumPotekZavarovanja);
		    Thread.sleep(1000);
			
		    funk.ScrollInKlik2(By.className("btn-next"));
		    new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.urlToBe(baseURL + "/isklepanje/avto/avto_zavarovanje_2"));
		    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		    Thread.sleep(3000);
		    
		  //IZBERI PAKET - https://skleni-qa.triglav.si/isklepanje/avtomobilno/avto_zavarovanje_2
			  
		    Thread.sleep(1000);
		   funk.ScrollInKlik2(By.className("btn-next"));
		    Thread.sleep(3000);
		    
		    new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.urlToBe(baseURL + "/isklepanje/avto/avto_zavarovanje_3"));
		    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		    Thread.sleep(1000);
		    
		  //PODATKI O SKLENITELJU - https://skleni-qa.triglav.si/isklepanje/avtomobilno/avto_zavarovanje_3
		    
		//    funk.ScrollInKlik("(//span[@class='checkbox--primary__icon'])[2]");
		    
		    funk.ScrollToElement(By.id("policyHolderUI.phoneNumber"));
		    funk.implicitWait(10);
		    driver.findElement(By.id("policyHolderUI.phoneNumber")).sendKeys("031532734");
		    
		    funk.ScrollInKlik("(//*[@class='checkbox--primary__icon'])[2]");
		    Thread.sleep(500);
		    funk.ScrollInKlik2(By.className("btn-next"));
		    
		    new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.urlToBe(baseURL + "/isklepanje/avto/avto_zavarovanje_4"));
		    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		    Thread.sleep(3000);
		    
		  //PREMIJA IN UGODNOSTI - https://skleni-qa.triglav.si/isklepanje/avtomobilno/avto_zavarovanje_4
		    		    
		    //POPUST DRAJV
		    
		//    funk.ScrollInKlik("(//*[@text()='Dodaj'])[2]");
		    
		    funk.ScrollInKlik("//*[@id=\"informativeCalculation\"]/div[2]/div[2]/div[2]/div[2]/a");
		    
		    Thread.sleep(1000);
		    funk.implicitWait(10);
		    
		    driver.findElement(By.id("ipdDrajvCode")).sendKeys(drajvKoda);
		    
		    funk.implicitWait(10);
		    
		    driver.findElement(By.id("btnAddDrajvCode")).click();
		    
		    funk.waitForLoaderToFinish(driver, By.className("loader"));
		    
		    Thread.sleep(2000);
		    funk.implicitWait(10);
		    
		    if(driver.getPageSource().contains("Uspešno ste dodali DRAJV kodo in prejeli")) {
		    	driver.findElement(By.xpath("//*[@id=\"drajvSuccess\"]/div/div/div/a")).click();
		    } else {
		    	throw new Exception("Prišlo je do napake pri drajv kodi");
		    }
		    
		    Thread.sleep(2000);
		    
		  //POPUST MLADA DRUŽINA
		    /*
		   funk.ScrollInKlik2(By.id("btnCarVnosOtroka"));
			Thread.sleep(1000);
			driver.findElement(By.id("name")).sendKeys(imeOtroka);
		    driver.findElement(By.id("surname")).sendKeys(priimekOtroka);
		    driver.findElement(By.id("birthday")).sendKeys(datumRojstvaOtroka);
		    driver.findElement(By.id("taxnum")).sendKeys(davcnaOtroka);
			
		    Thread.sleep(2000);
			funk.implicitWait(10);
		    funk.ScrollInKlik2(By.id("saveChildData"));
		    
		    funk.waitForLoaderToFinish(driver, By.className("loader"));
		    Thread.sleep(1000);
		    driver.findElement(By.xpath("//*[@id=\"carChildSuccess\"]/div/div/div/a")).click();		
		    */
		    // Pogoji
		    
		    Thread.sleep(1000);
			  
		    funk.ScrollInKlik("//*[@for='zavarovalnimi-pogoji']");
			Thread.sleep(500);
			WebElement test = driver.findElement(By.xpath("//*[@for='info-zavarovalni-paket-mobile']"));
			new Actions(driver).moveToElement(test, 2, 2).click().perform();
		    	
		    funk.ScrollInKlik2(By.className("btn-next"));
		    
		    funk.waitForLoaderToFinish(driver, By.className("loader"));
		    
		    new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.urlToBe(baseURL + "/isklepanje/avto/avto_zavarovanje_5"));
		    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		    
		  //PLAČILO - https://skleni-qa.triglav.si/isklepanje/avtomobilno/avto_zavarovanje_5
		     
		    driver.findElement(By.className("radio--secondary__button")).click();
		    Thread.sleep(1000);
		    driver.findElement(By.className("radio-quaternary__button")).click();
		    Thread.sleep(1000);
		    
		    funk.ScrollInKlik("//button[@name='simulatePayment']");
		    
		    Thread.sleep(3000);
		    		    
		    WebElement ele = driver.findElement(By.xpath("//*[text()='Hvala!']"));
			
			new WebDriverWait(driver,Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOf(ele));
			
			if(ele.isDisplayed() == true) {
				System.out.println("Sklenitev je bila uspešna");
			} else {
				System.out.println("Sklenitev NI bila uspešna");
			}    
		   
	}
	
	
	@Test
	public void AvtomobiliTestBrezPrijave() throws Exception {
		
		Funkcije2 funk = new Funkcije2(driver);
		Prijave2 p = new Prijave2(driver);
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
		// datum zacetka zavarovanja - danes + 30 dni ----> popravek na 29dni
		Calendar calendar1 = Calendar.getInstance();
		calendar1.add(Calendar.DATE, 29);
		// datum poteka zavarovanja - danes + 60 dni
		Calendar calendar2 = Calendar.getInstance();
		calendar2.add(Calendar.DATE, 60);

		// Šasija
		int leftLimit = 48; // numeral '0'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 17;
		Random random = new Random();
		String sasija = random.ints(leftLimit, rightLimit + 1).filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
				.limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();

		// Fiksne vrednosti
		String urlZavarovanja = baseURL + "/isklepanje/avto/avto_zavarovanje";
		String LDAPusername = "tsalhorvat";
		String LDAPgeslo = "gremonapivo";
		String telefonska = "031532734";
		String registrska = "MS18-NJV";
		String znamka = "vw";
		String model = "t-roc (2017-2022)";
		String tip = "T-Roc 1,5 TSI BMT Sport (10.2018-07.2022)";
		String firstRegistration = "16.9.2020";
		String kilometri = "5000";
		String datumZacetkaZavarovanja = formatter.format(calendar1.getTime());
		String datumPotekZavarovanja = formatter.format(calendar2.getTime());
		String ime = "Nikita";
		String priimek = "Plej";
		String datumRojstva = "18.08.1998";
		String davcna = "19553170";
		String postnaStevilka = "9000";
		String ulica = "Partizanska ulica";
		String hisnaStevilka = "1";
		String email = "zkpr.test@triglav.si";
		String validacijskaKoda = "9999";
		// String drajvKoda = "asd1234";
		String imeOtroka = "Janček";
		String priimekOtroka = "Ježek";
		String datumRojstvaOtroka = "16.9.2020";
		String davcnaOtroka = "65694350";

		// Za scrollanje - do polovice ekrana
		int viewportHeight = ((Long) ((JavascriptExecutor) driver).executeScript("return window.innerHeight"))
				.intValue();
		int halfViewport = viewportHeight / 2;

		// odpri stran
		/*
		driver.get(urlZavarovanja);
		Thread.sleep(5000);

		// Sprejmi piškotke
		WebElement cookieBanner = driver.findElement(By.id("onetrust-banner-sdk"));
		WebElement acceptButton = cookieBanner.findElement(By.id("onetrust-accept-btn-handler"));
		acceptButton.click();
		Thread.sleep(3000);

		// LDAP geslo
		driver.findElement(By.id("username")).sendKeys(LDAPusername);
		driver.findElement(By.id("password")).sendKeys(LDAPgeslo);
		driver.findElement(By.className("nextStep")).click();
		Thread.sleep(3000);
		*/
		
		p.SklepanjePrijava();
		
		driver.get(urlZavarovanja);

		// SKLENI NOVO
		/*
		WebElement skleniNovo = driver
				.findElement(By.xpath("//*[@id=\"carQModel\"]/div[2]/div/div/div[1]/div/div[3]/a"));
		int skleniNovo_Position = skleniNovo.getLocation().getY();
		((JavascriptExecutor) driver).executeScript("window.scroll(0, " + (skleniNovo_Position - halfViewport) + ");");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[text()='Skleni novo']")).click();
		Thread.sleep(3000);
		*/
		funk.ScrollInKlik("//*[text()='Skleni novo']");

		// PODATKI O VOZILU -
		// https://skleni-qa.triglav.si/isklepanje/avtomobilno/avto_zavarovanje_1
		WebElement registrskaStevilka = driver.findElement(By.id("regnumNum"));
		int registrskaStevilka_Position = registrskaStevilka.getLocation().getY();
		((JavascriptExecutor) driver)
				.executeScript("window.scroll(0, " + (registrskaStevilka_Position - halfViewport) + ");");
		Thread.sleep(1000);
		driver.findElement(By.id("regnumNum")).sendKeys(registrska);
		driver.findElement(By.id("btnGetVehicleData")).click();
		funk.waitForLoaderToFinish(driver, By.className("loader"));
		Thread.sleep(3000);
		funk.implicitWait(15);

		// znamka vozila
		funk.ScrollInKlik2(By.xpath("(//*[@class='select2-selection__arrow'])[1]"));
		Thread.sleep(1000);
		driver.findElement(By.className("select2-search__field")).click();
		Thread.sleep(1000);
		driver.findElement(By.className("select2-search__field")).sendKeys(znamka);
		Thread.sleep(1000);
		driver.findElement(By.className("select2-search__field")).sendKeys(Keys.ENTER);
		Thread.sleep(1000);
		funk.waitForLoaderToFinish(driver, By.className("loader"));
		Thread.sleep(1000);
		// model vozila
		/*
		WebElement modelVozila = driver
				.findElement(By.xpath("//*[@id=\"vehicleData\"]/div[2]/div[2]/div[2]/div[2]/span"));
		int modelVozila_Position = modelVozila.getLocation().getY();
		((JavascriptExecutor) driver).executeScript("window.scroll(0, " + (modelVozila_Position - halfViewport) + ");");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"vehicleData\"]/div[2]/div[2]/div[2]/div[2]/span")).click();
		*/
		funk.ScrollInKlik2(By.xpath("(//*[@class='select2-selection__arrow'])[2]"));
		Thread.sleep(1000);
		driver.findElement(By.className("select2-search__field")).click();
		Thread.sleep(1000);
		driver.findElement(By.className("select2-search__field")).sendKeys(model);
		Thread.sleep(1000);
		driver.findElement(By.className("select2-search__field")).sendKeys(Keys.ENTER);
		Thread.sleep(1000);
		funk.waitForLoaderToFinish(driver, By.className("loader"));
		Thread.sleep(1000);
		// tip vozila
		/*
		WebElement tipVozila = driver
				.findElement(By.xpath("//*[@id=\"vehicleData\"]/div[2]/div[2]/div[2]/div[3]/span"));
		int tipVozila_Position = tipVozila.getLocation().getY();
		((JavascriptExecutor) driver).executeScript("window.scroll(0, " + (tipVozila_Position - halfViewport) + ");");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"vehicleData\"]/div[2]/div[2]/div[2]/div[3]/span")).click();
		*/
		funk.ScrollInKlik2(By.xpath("(//*[@class='select2-selection__arrow'])[3]"));
		Thread.sleep(1000);
		driver.findElement(By.className("select2-search__field")).click();
		Thread.sleep(1000);
		driver.findElement(By.className("select2-search__field")).sendKeys(tip);
		Thread.sleep(1000);
		driver.findElement(By.className("select2-search__field")).sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		funk.waitForLoaderToFinish(driver, By.className("loader"));
		Thread.sleep(1000);
		funk.implicitWait(10);
		
		// Serijska stevilka
		WebElement vehicleIdentificationNumber = driver
				.findElement(By.id("vehiclePolicy.vehicle.vehicleIdentificationNumber"));
		int vehicleIdentificationNumber_Position = vehicleIdentificationNumber.getLocation().getY();
		((JavascriptExecutor) driver)
				.executeScript("window.scroll(0, " + (vehicleIdentificationNumber_Position - halfViewport) + ");");
		Thread.sleep(1000);
		driver.findElement(By.id("vehiclePolicy.vehicle.vehicleIdentificationNumber")).sendKeys(sasija);
		Thread.sleep(1000);
		
		// Datum prve registracije
		WebElement vehicleFirstRegistration = driver
				.findElement(By.id("vehiclePolicy.vehicle.firstRegistration"));
		int vehicleFirstRegistration_Position = vehicleFirstRegistration.getLocation().getY();
		((JavascriptExecutor) driver)
				.executeScript("window.scroll(0, " + (vehicleFirstRegistration_Position - halfViewport) + ");");
		Thread.sleep(1000);
		driver.findElement(By.id("vehiclePolicy.vehicle.firstRegistration")).sendKeys(firstRegistration);
		Thread.sleep(1000);
		
		// Število prevoženih km
		WebElement prevozeniKm = driver.findElement(By.id("vehiclePolicy.vehicle.milageKm"));
		int prevozeniKm_Position = prevozeniKm.getLocation().getY();
		((JavascriptExecutor) driver).executeScript("window.scroll(0, " + (prevozeniKm_Position - halfViewport) + ");");
		Thread.sleep(1000);
		driver.findElement(By.id("vehiclePolicy.vehicle.milageKm")).sendKeys(kilometri);
		Thread.sleep(1000);

		// Datum zacetka zavarovanja
		
		WebElement zacetekZavarovanja = driver.findElement(By.id("vehiclePolicy.insuranceConclusion.policyStartDate"));
		int zacetekZavarovanja_Position = zacetekZavarovanja.getLocation().getY();
		((JavascriptExecutor) driver)
				.executeScript("window.scroll(0, " + (zacetekZavarovanja_Position - halfViewport) + ");");
		Thread.sleep(1000);
		driver.findElement(By.id("vehiclePolicy.insuranceConclusion.policyStartDate"))
				.sendKeys(datumZacetkaZavarovanja);
		Thread.sleep(1000);
		
		/*
		funk.ScrollInKlik2(By.id("vehiclePolicy.insuranceConclusion.policyStartDate"));
		Thread.sleep(1000);
		driver.findElement(By.id("vehiclePolicy.insuranceConclusion.policyStartDate"))
				.clear();
		Thread.sleep(1000);
		driver.findElement(By.id("vehiclePolicy.insuranceConclusion.policyStartDate"))
				.sendKeys(datumZacetkaZavarovanja);
		Thread.sleep(1000);
		//driver.findElement(By.id("vehiclePolicy.insuranceConclusion.policyStartDate"))
			//	.sendKeys(Keys.ENTER);
		driver.findElement(By.xpath("(//*[@class='icon-calendar'])[2]")).click();
		Thread.sleep(1000);
	//	driver.findElement(By.id("vehiclePolicy.insuranceConclusion.policyStartDate"))
		//.click();
*/
		// Datum konca zavarovanja
		funk.ScrollInKlik2(By.className("checkbox--primary__icon"));
		Thread.sleep(1000);
		driver.findElement(By.id("vehiclePolicy.insuranceConclusion.policyEndDate")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("vehiclePolicy.insuranceConclusion.policyEndDate")).clear();
		driver.findElement(By.id("vehiclePolicy.insuranceConclusion.policyEndDate")).sendKeys(datumPotekZavarovanja);
		Thread.sleep(1000);

		// Naprej
		WebElement naprej = driver.findElement(By.className("btn-next"));
		int naprej_Position = naprej.getLocation().getY();
		((JavascriptExecutor) driver).executeScript("window.scroll(0, " + (naprej_Position - halfViewport) + ");");
		Thread.sleep(1000);
		driver.findElement(By.className("btn-next")).click();
		Thread.sleep(3000);

		// IZBERI PAKET - primarne izbire
		// https://skleni-qa.triglav.si/isklepanje/avtomobilno/avto_zavarovanje_2
		WebElement naprej2 = driver.findElement(By.className("btn-next"));
		int naprej2_Position = naprej2.getLocation().getY();
		((JavascriptExecutor) driver).executeScript("window.scroll(0, " + (naprej2_Position - halfViewport) + ");");
		Thread.sleep(1000);
		driver.findElement(By.className("btn-next")).click();
		Thread.sleep(3000);

		// PODATKI O SKLENITELJU -
		// https://skleni-qa.triglav.si/isklepanje/avtomobilno/avto_zavarovanje_3

		WebElement imeSklenitelja = driver.findElement(By.id("policyHolderUI.name"));
		int imeSklenitelja_Position = imeSklenitelja.getLocation().getY();
		((JavascriptExecutor) driver).executeScript("window.scroll(0, " + (imeSklenitelja_Position - halfViewport) + ");");
		Thread.sleep(1000);		
		driver.findElement(By.id("policyHolderUI.name")).sendKeys(ime);
		
		//Priimek
		WebElement priimekSklenitelja = driver.findElement(By.id("policyHolderUI.surname"));
		int priimekSklenitelja_Position = priimekSklenitelja.getLocation().getY();
		((JavascriptExecutor) driver).executeScript("window.scroll(0, " + (priimekSklenitelja_Position - halfViewport) + ");");
		Thread.sleep(1000);		
		driver.findElement(By.id("policyHolderUI.surname")).sendKeys(priimek);
		
		//Datum rojstva
		WebElement datumRojstvaSklenitelja = driver.findElement(By.id("policyHolderUI.birthday"));
		int datumRojstvaSklenitelja_Position = datumRojstvaSklenitelja.getLocation().getY();
		((JavascriptExecutor) driver).executeScript("window.scroll(0, " + (datumRojstvaSklenitelja_Position - halfViewport) + ");");
		Thread.sleep(1000);		
		driver.findElement(By.id("policyHolderUI.birthday")).sendKeys(datumRojstva);
		
		//Davčna
		WebElement davcnaSklenitelja = driver.findElement(By.id("policyHolderUI.taxnum"));
		int davcnaSklenitelja_Position = davcnaSklenitelja.getLocation().getY();
		((JavascriptExecutor) driver).executeScript("window.scroll(0, " + (davcnaSklenitelja_Position - halfViewport) + ");");
		Thread.sleep(1000);		
		driver.findElement(By.id("policyHolderUI.taxnum")).sendKeys(davcna);
		Thread.sleep(1000);
		
		//spol - ženski
		WebElement podatkispol = driver.findElement(By.id("female"));
		int podatkispol_Position = podatkispol.getLocation().getY();
		((JavascriptExecutor) driver).executeScript("window.scroll(0, " + (podatkispol_Position - halfViewport) + ");");
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//*[@class='radio--primary__button'])[2]")).click();
		Thread.sleep(1000);
		
		//naslov - posta
		WebElement postaNaslov = driver.findElement(By.id("policyHolderUI.addressSearch.post_and_place"));
		int postaNaslov_Position = postaNaslov.getLocation().getY();
		((JavascriptExecutor) driver)
				.executeScript("window.scroll(0, " + (postaNaslov_Position - halfViewport) + ");");
		Thread.sleep(1000);
		driver.findElement(By.id("policyHolderUI.addressSearch.post_and_place")).sendKeys(postnaStevilka);
		Thread.sleep(2000);
		funk.implicitWait(10);
		driver.findElement(By.id("policyHolderUI.addressSearch.post_and_place")).sendKeys(Keys.DOWN, Keys.ENTER);
		Thread.sleep(1000);
		
		//naslov - ulica
		WebElement ulicaNaslov = driver.findElement(By.id("policyHolderUI.addressSearch.street"));
		int ulicaNaslov_Position = ulicaNaslov.getLocation().getY();
		((JavascriptExecutor) driver)
				.executeScript("window.scroll(0, " + (ulicaNaslov_Position - halfViewport) + ");");
		Thread.sleep(3000);
		funk.implicitWait(10);
		driver.findElement(By.id("policyHolderUI.addressSearch.street")).sendKeys(ulica);
		Thread.sleep(3000);
		funk.implicitWait(10);
		driver.findElement(By.id("policyHolderUI.addressSearch.street")).sendKeys(Keys.DOWN, Keys.ENTER);
		Thread.sleep(3000);
		
		funk.implicitWait(10);
		
		// naslov - hišna številka
		WebElement hisnaStevilkaNaslov = driver.findElement(By.id("policyHolderUI.addressSearch.hnr"));
		int hisnaStevilkaNaslov_Position = hisnaStevilkaNaslov.getLocation().getY();
		((JavascriptExecutor) driver)
				.executeScript("window.scroll(0, " + (hisnaStevilkaNaslov_Position - halfViewport) + ");");
		Thread.sleep(1000);
		funk.implicitWait(10);
		driver.findElement(By.id("policyHolderUI.addressSearch.hnr")).sendKeys(hisnaStevilka);
		Thread.sleep(2000);
		funk.implicitWait(10);
		driver.findElement(By.id("policyHolderUI.addressSearch.hnr")).sendKeys(Keys.DOWN, Keys.ENTER);
		Thread.sleep(2000);
		
		//Email naslov in validacija
		WebElement podatkiemail = driver.findElement(By.id("unvalidatedEmail"));
		int podatkiemail_Position = podatkiemail.getLocation().getY();
		((JavascriptExecutor) driver)
				.executeScript("window.scroll(0, " + (podatkiemail_Position - halfViewport) + ");");
		Thread.sleep(1000);
		driver.findElement(By.id("unvalidatedEmail")).sendKeys(email);
		driver.findElement(By.id("btnPosliValidacijskoKodoNaEmail")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("emailValidationString")).sendKeys(validacijskaKoda + Keys.ENTER);
		Thread.sleep(1500);
		
		//Telefonska številka
		WebElement podatkitelefonska = driver.findElement(By.id("policyHolderUI.phoneNumber"));
		int podatkitelefonska_Position = podatkitelefonska.getLocation().getY();
		((JavascriptExecutor) driver)
				.executeScript("window.scroll(0, " + (podatkitelefonska_Position - halfViewport) + ");");
		Thread.sleep(1000);
		driver.findElement(By.id("policyHolderUI.phoneNumber")).sendKeys(telefonska);
		Thread.sleep(1000);
		
		//Potrditev pogojev
		WebElement potrjujemvse = driver
				.findElement(By.xpath("//label[@for='potrjujem-vse']"));
		int potrjujemvse_Position = potrjujemvse.getLocation().getY();
		((JavascriptExecutor) driver)
				.executeScript("window.scroll(0, " + (potrjujemvse_Position - halfViewport) + ");");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//label[@for='potrjujem-vse']")).click();
		Thread.sleep(1000);
	//  funk.ScrollInKlik("(//span[@class='checkbox--primary__icon'])[2]");
		
		//Naprej
		WebElement naprej3 = driver.findElement(By.className("btn-next"));
		int naprej3_Position = naprej3.getLocation().getY();
		((JavascriptExecutor) driver).executeScript("window.scroll(0, " + (naprej3_Position - halfViewport) + ");");
		Thread.sleep(1000);
		driver.findElement(By.className("btn-next")).click();
		Thread.sleep(3000);
		
		// POPUST MLADA DRUŽINA
		//https://skleni-qa.triglav.si/isklepanje/avto/avto_zavarovanje_4
		
		funk.implicitWait(10);
		
		driver.findElement(By.xpath("(//*[@class='checkbox--primary__icon'])[1]")).click();
		driver.findElement(By.id("btnTkConsentAccepted")).click();
		funk.implicitWait(10);
		funk.waitForLoaderToFinish(driver, By.className("loader"));
		funk.implicitWait(10);
		driver.findElement(By.xpath("//*[@id=\"tkConsentSuccess\"]/div/div/div[1]/a")).click();
		funk.implicitWait(10);
		
		WebElement mladaDruzinaPopust = driver
				.findElement(By.id("btnCarVnosOtroka"));
		int mladaDruzinaPopust_Position = mladaDruzinaPopust.getLocation().getY();
		((JavascriptExecutor) driver)
				.executeScript("window.scroll(0, " + (mladaDruzinaPopust_Position - halfViewport) + ");");
		Thread.sleep(1000);
		driver.findElement(By.id("btnCarVnosOtroka")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("name")).sendKeys(imeOtroka);
		driver.findElement(By.id("surname")).sendKeys(priimekOtroka);
		driver.findElement(By.id("birthday")).sendKeys(datumRojstvaOtroka);
		driver.findElement(By.id("taxnum")).sendKeys(davcnaOtroka);
		Thread.sleep(1000);
		WebElement mladaDruzinaPopustDodaj = driver.findElement(By.id("saveChildData"));
		int mladaDruzinaPopustDodaj_Position = mladaDruzinaPopustDodaj.getLocation().getY();
		((JavascriptExecutor) driver)
				.executeScript("window.scroll(0, " + (mladaDruzinaPopustDodaj_Position - halfViewport) + ");");
		Thread.sleep(3000);
		funk.implicitWait(10);
		driver.findElement(By.id("saveChildData")).click();
		Thread.sleep(3000);
		funk.waitForLoaderToFinish(driver, By.className("loader"));
	    Thread.sleep(2000);
	    funk.implicitWait(10);
	    funk.ScrollInKlik2(By.xpath("//*[@id=\"carChildSuccess\"]/div/div/div/a"));		
		
		//Sprejem pogojev
		Thread.sleep(1000);
		funk.ScrollInKlik("//*[@for='zavarovalnimi-pogoji']");
		Thread.sleep(500);
		WebElement test = driver.findElement(By.xpath("//*[@for='info-zavarovalni-paket-mobile']"));
		new Actions(driver).moveToElement(test, 2, 2).click().perform();
		
		//naprej
		WebElement naprej4 = driver.findElement(By.className("btn-next"));
		int naprej4_Position = naprej4.getLocation().getY();
		((JavascriptExecutor) driver).executeScript("window.scroll(0, " + (naprej4_Position - halfViewport) + ");");
		Thread.sleep(1000);
		driver.findElement(By.className("btn-next")).click();
		Thread.sleep(3000);

		// PLAČILO -
		// https://skleni-qa.triglav.si/isklepanje/avtomobilno/avto_zavarovanje_5
		driver.findElement(By.xpath("(//*[@class='radio--secondary__button'])[1]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//*[@class='radio-quaternary__button'])[1]"))
				.click();
		 funk.ScrollInKlik("//button[@name='simulatePayment']");

		// PREVERI NASLOV STRANI
		
		WebElement ele = driver.findElement(By.xpath("//*[text()='Hvala!']"));
			
		new WebDriverWait(driver,Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOf(ele));
		
		if(ele.isDisplayed() == true) {
			System.out.println("Sklenitev avtomobila je bila uspešna");
		} else {
			 throw new Exception("Sklenitev avtomobila NI bila uspešna");
		}	
	}

}