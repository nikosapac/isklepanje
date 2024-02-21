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

import sklepanjeTesti.*;
import sklepanjeTesti.config.EnvironmentConfig;

public class Dom {

	WebDriver driver;
	
//	String email = "niko.sapac@triglav.si";
//	String password = "Testgeslo123";
	
	//String email = "triglav.digi@gmail.com";
	//String password = "Test@123";
	EnvironmentConfig cfg = ConfigFactory.create(EnvironmentConfig.class);

	String baseURL = cfg.baseURL();
	String email = cfg.iTriglavEmail();
	String password = cfg.iTriglavPass();
	
	public Dom(WebDriver driver) {
		this.driver = driver;
	}
	
	public void DomQTest() throws Exception {
		
		Funkcije2 f = new Funkcije2(driver);
		Prijave2 p = new Prijave2(driver);
		
		p.SklepanjePrijava();
		
		f.implicitWait(10);
		
		driver.get(baseURL + "/isklepanje/dom/dom_zavarovanje");
		
		f.implicitWait(10);

		driver.findElement(By.xpath("//*[text()='Prijava']")).click();
		
		f.implicitWait(10);
		
		p.ItriglavPrijavaSklepanje();
		
		f.implicitWait(10);
		
	//	driver.findElement(By.xpath("//*[text()='Nadaljuj brez prijave']")).click();
				
		f.implicitWait(10);
		
		// Izberite vrsto nepremičnine
		
		f.ScrollInKlik2(By.id("domType1"));
		
		// Izberite vrsto hiše
		
		Thread.sleep(1000);
				
		f.implicitWait(10);
		
		driver.findElement(By.id("domHouseType1")).click();
		
		// Izberite, kaj želite zavarovati
		
		Thread.sleep(1000);
				
		f.implicitWait(10);
		
		driver.findElement(By.id("domInsuranceHouseType3")).click();
		
		f.implicitWait(10);
		
		f.ScrollInKlik2(By.className("submitButton"));
		
		f.waitForLoaderToFinish(driver, By.className("loader"));
		
		f.implicitWait(10);
		
		// KORAK 1 - Podatki o nepremičnini
		
			// Vnesite naslov nepremičnine
		
		f.implicitWait(30);
		
		driver.findElement(By.id("addressSearch.btnResetAddressForm")).click();
		
		f.implicitWait(10);
		Thread.sleep(500);
		
		f.ScrollToElement(By.id("addressSearch.post_and_place"));
		Thread.sleep(2000);
		f.implicitWait(10);
		driver.findElement(By.id("addressSearch.post_and_place")).sendKeys("9000");
		Thread.sleep(1500);
		f.implicitWait(10);
		driver.findElement(By.xpath("//*[text()='9000 MURSKA SOBOTA']")).click();
		
		Thread.sleep(2500);
		
		f.ScrollToElement(By.id("addressSearch.street"));
		Thread.sleep(2000);
		f.implicitWait(10);
		driver.findElement(By.id("addressSearch.street")).sendKeys("MLADINSKA ULICA");
		Thread.sleep(1500);
		f.implicitWait(10);
		driver.findElement(By.xpath("//*[text()='MLADINSKA ULICA, MURSKA SOBOTA']")).click();
		
		Thread.sleep(2500);
		
		f.ScrollToElement(By.id("addressSearch.hnr"));
		Thread.sleep(2000);
		f.implicitWait(10);
		driver.findElement(By.id("addressSearch.hnr")).sendKeys("5");
		Thread.sleep(1500);
		f.implicitWait(10);
		driver.findElement(By.xpath("//*[text()='5']")).click();
		
		f.implicitWait(10);
		
		f.waitForLoaderToFinish(driver, By.className("loader"));
		
		Thread.sleep(3500);
		
		f.implicitWait(30);
		
		f.ScrollInKlik2(By.className("btn-next"));
		
	//	f.waitForLoaderToFinish(driver, By.className("loader"));
		
		new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.urlToBe(baseURL + "/isklepanje/dom/dom_zavarovanje_2"));
		
		f.implicitWait(10);
		
		// KORAK 2 - Izbira paketa
		
		f.implicitWait(20);
		
		f.ScrollInKlik2(By.xpath("(//*[@class='checkbox-tertiary-icon'])[2]"));
		
		f.implicitWait(10);
		
		f.ScrollInKlik2(By.className("btn-next"));
		
	//	f.waitForLoaderToFinish(driver, By.className("loader"));
		
		new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.urlToBe(baseURL + "/isklepanje/dom/dom_zavarovanje_3"));
		
		f.implicitWait(10);
		
		// KORAK 3 - Podatki o sklenitelju
		/*
		driver.findElement(By.xpath("//*[text()='Prijava']")).click();
		
		f.implicitWait(10);
		
		p.ItriglavPrijavaSklepanje();
		
		f.implicitWait(10);
		*/
		f.ScrollInKlik2(By.className("checkbox--primary__icon"));
		
		f.implicitWait(10);
		
		f.ScrollInKlik2(By.className("btn-next"));
		
		//f.waitForLoaderToFinish(driver, By.className("loader"));
		
		new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.urlToBe(baseURL + "/isklepanje/dom/dom_zavarovanje_4"));
		
		f.implicitWait(10);
		
		// KORAK 4 - Premija in ugodnosti
		
		//f.ScrollInKlik2(By.xpath("//*[@for='zavarovalnimi-pogoji']"));
		/*
		Thread.sleep(1000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // Adjust the timeout as needed
		WebElement elementToClick = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@for='info-zavarovalni-paket-mobile']")));
		
	//	WebElement ele = driver.findElement(By.xpath("//*[@for='info-zavarovalni-paket-mobile']"));
		new Actions(driver).moveToElement(elementToClick, 1, 1).click().perform();
		*/
		
		Thread.sleep(1000);
		f.ScrollInKlik("//*[@for='zavarovalnimi-pogoji']");
		Thread.sleep(500);
		WebElement test = driver.findElement(By.xpath("//*[@for='info-zavarovalni-paket-mobile']"));
		new Actions(driver).moveToElement(test, 2, 2).click().perform();
		
		f.ScrollInKlik2(By.className("btn-next"));
		
	//	f.waitForLoaderToFinish(driver, By.className("loader"));
		
		new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.urlToBe(baseURL + "/isklepanje/dom/dom_zavarovanje_5"));
		
		f.implicitWait(10);
		
		// KORAK 5 - Plačilo
		
		driver.findElement(By.xpath("(//*[@class='radio--secondary__button'])[1]")).click();
		
		f.implicitWait(10);
		
		driver.findElement(By.xpath("//*[@for='kreditna-kartica']")).click();
		
		f.implicitWait(10);
		/*
		f.ScrollInKlik2(By.xpath("(//*[@class='select2-selection__arrow'])[1]"));
		
		f.implicitWait(10);
		
		driver.findElement(By.xpath("//*[text()='SI56 0234 0176 4167 804']")).click();
		
		f.implicitWait(10);
		
		f.ScrollInKlik2(By.xpath("(//*[@class='select2-selection__arrow'])[1]"));
		
		f.implicitWait(10);
		*/
		f.ScrollInKlik2(By.xpath("//*[@for='soglasam-s-pogoji-celotna-premija']"));
		
		f.implicitWait(10);
		
		f.ScrollInKlik2(By.className("btn-next"));
		
		f.waitForLoaderToFinish(driver, By.className("loader"));
		
	//	f.checkSklenitev();
		
		WebElement ele2 = driver.findElement(By.xpath("//*[text()='Hvala!']"));
		
		new WebDriverWait(driver,Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOf(ele2));
		
		if(ele2.isDisplayed() == true) {
			System.out.println("Sklenitev doma je bila uspešna");
		} else {
			 throw new Exception("Sklenitev doma NI bila uspešna");
		}	
		
	}
}
