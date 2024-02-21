package sklepanjeTesti.sklepanje;

import java.time.Duration;
import java.util.Random;

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

public class Motor {
	
	WebDriver driver;
	
	EnvironmentConfig cfg = ConfigFactory.create(EnvironmentConfig.class);
	String baseURL = cfg.baseURL();
	String itrEmail = cfg.iTriglavEmail();
	String itrPass = cfg.iTriglavPass();
	
	public Motor(WebDriver driver) {
		this.driver = driver;
	}
	
	public void MotorTest() throws InterruptedException {
		Prijave2 p = new Prijave2(driver);
		Funkcije2 f = new Funkcije2(driver);
		
		int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 17;
		Random random = new Random();
		String sasija = random.ints(leftLimit, rightLimit + 1)
      	      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
      	      .limit(targetStringLength)
      	      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
      	      .toString();
		
		String znamka = "honda";
		String model = "Motorno kolo (1970-9999)";
		String tip = "ADV 350 (ABS+TCS+smart kovček)";
		String MNZ = "Kolo z motorjem";
		String vrsta = "dvokolesa";
		String prvaRegistracija = f.DatumMesci(-1);
		String registerska = "MK32ABF";
		String km = "10000";
		String zacetekZavarovanja = f.Datum(7);
		
		//p.ItriglavPrijavaVnos("niko.sapac@triglav.si","Testgeslo123");
		p.SklepanjePrijava();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get(baseURL + "/isklepanje/motor/zavarovanje_motorja");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		if(f.IsVisible(By.xpath("//a[text()='Prijava']"))) {
		
			driver.findElement(By.xpath("//a[text()='Prijava']")).click();
			
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		p.ItriglavPrijavaSklepanje();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		Thread.sleep(1500);
		
		f.ScrollInKlik("//a[text()='Skleni novo']");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		// Sklenite novo zavarovanje - https://skleni-qa.triglav.si/isklepanje/motor/zavarovanje_motorja
		
		// Podatki o vašem vozilu - https://skleni-qa.triglav.si/isklepanje/motor/zavarovanje_motorja_1
		
		f.ScrollInKlik2(By.xpath("(//span[@class='select2-selection__arrow'])[1]"));
		Thread.sleep(1000);
		driver.findElement(By.className("select2-search__field")).click();
		driver.findElement(By.className("select2-search__field")).sendKeys(znamka);
		Thread.sleep(1000);
		driver.findElement(By.className("select2-search__field")).sendKeys(Keys.ENTER);
		Thread.sleep(1000);
		f.waitForLoaderToFinish(driver, By.className("loader"));
		Thread.sleep(1000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		f.ScrollInKlik2(By.xpath("(//span[@class='select2-selection__arrow'])[2]"));
		Thread.sleep(1000);
		driver.findElement(By.className("select2-search__field")).click();
		driver.findElement(By.className("select2-search__field")).sendKeys(model);
		Thread.sleep(1000);
		driver.findElement(By.className("select2-search__field")).sendKeys(Keys.ENTER);
		Thread.sleep(1000);
		f.waitForLoaderToFinish(driver, By.className("loader"));
		Thread.sleep(1000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		f.ScrollInKlik2(By.xpath("(//span[@class='select2-selection__arrow'])[3]"));
		Thread.sleep(1000);
		driver.findElement(By.className("select2-search__field")).click();
		driver.findElement(By.className("select2-search__field")).sendKeys(tip);
		Thread.sleep(1000);
		driver.findElement(By.className("select2-search__field")).sendKeys(Keys.ENTER);		
		Thread.sleep(1000);
	//	driver.findElement(By.xpath("//*[text()='NAPREJ']")).click();
		f.implicitWait(20);
		f.waitForLoaderToFinish(driver, By.className("loader"));
		Thread.sleep(1000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Thread.sleep(1500);
		f.ScrollInKlik2(By.id("vehiclePolicy.vehicle.vehicleIdentificationNumber"));
		driver.findElement(By.id("vehiclePolicy.vehicle.vehicleIdentificationNumber")).sendKeys(sasija);
		
		f.ScrollInKlik2(By.id("vehiclePolicy.vehicle.firstRegistration"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.id("vehiclePolicy.vehicle.firstRegistration")).clear();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.id("vehiclePolicy.vehicle.firstRegistration")).sendKeys(prvaRegistracija,Keys.ENTER);
		Thread.sleep(2000);
		f.ScrollInKlik2(By.id("vehiclePolicy.vehicle.vehicleRegistrationNumber"));
		driver.findElement(By.id("vehiclePolicy.vehicle.vehicleRegistrationNumber")).sendKeys(registerska);
		
		f.ScrollInKlik2(By.id("vehiclePolicy.vehicle.milageKm"));
		driver.findElement(By.id("vehiclePolicy.vehicle.milageKm")).sendKeys(km);
		
		f.ScrollInKlik2(By.id("vehiclePolicy.insuranceConclusion.policyStartDate"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.id("vehiclePolicy.insuranceConclusion.policyStartDate")).clear();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.id("vehiclePolicy.insuranceConclusion.policyStartDate")).sendKeys(zacetekZavarovanja);
		
		f.ScrollInKlik2(By.className("btn-next"));
		
		new WebDriverWait(driver,Duration.ofSeconds(20)).until(ExpectedConditions.urlToBe(baseURL + "/isklepanje/motor/zavarovanje_motorja_2"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		// KORAK 2
		// Izbira paketa - https://skleni-qa.triglav.si/isklepanje/motor/zavarovanje_motorja_2
		
		f.ScrollInKlik2(By.className("btn-next"));
		
		new WebDriverWait(driver,Duration.ofSeconds(20)).until(ExpectedConditions.urlToBe(baseURL + "/isklepanje/motor/zavarovanje_motorja_3"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//	KORAK 3
		// Podatki o sklenitelju - https://skleni-qa.triglav.si/isklepanje/motor/zavarovanje_motorja_3
		
		f.ScrollInKlik2(By.xpath("(//*[@class='checkbox--primary__icon'])[2]"));
		f.implicitWait(10);
		f.ScrollInKlik2(By.className("btn-next"));
		
		new WebDriverWait(driver,Duration.ofSeconds(20)).until(ExpectedConditions.urlToBe(baseURL + "/isklepanje/motor/zavarovanje_motorja_4"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		// Premija in ugodnosti - https://skleni-qa.triglav.si/isklepanje/motor/zavarovanje_motorja_4
				
		
		WebElement pogoji = driver.findElement(By.xpath("//label[@for='info-zavarovalni-paket-mobile']"));
		
		
		f.ScrollInKlik("//*[@for='zavarovalnimi-pogoji']");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebElement test = driver.findElement(By.xpath("//*[@for='info-zavarovalni-paket-mobile']"));
		new Actions(driver).moveToElement(test, 2, 2).click().perform();
		
		f.ScrollInKlik2(By.className("btn-next"));
		
		new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.urlToBe(baseURL + "/isklepanje/motor/zavarovanje_motorja_5"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		// Plačilo - https://skleni-qa.triglav.si/isklepanje/motor/zavarovanje_motorja_5
		
		driver.findElement(By.xpath("(//div[@class='radio--secondary__button'])[1]")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.className("radio-quaternary__button")).click();
		
		f.ScrollInKlik("//button[@name='simulatePayment']");
	    
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	    
	    
	    WebElement ele = driver.findElement(By.xpath("//*[text()='Hvala!']"));
		
		new WebDriverWait(driver,Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOf(ele));
		
		if(ele.isDisplayed() == true) {
			System.out.println("Sklenitev je bila uspešna");
		} else {
			System.out.println("Sklenitev NI bila uspešna");
		}
		
	}

}
