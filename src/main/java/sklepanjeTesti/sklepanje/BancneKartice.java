package sklepanjeTesti.sklepanje;

import java.time.Duration;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

//import io.qameta.allure.Step;
import sklepanjeTesti.Funkcije;
import sklepanjeTesti.Funkcije2;
import sklepanjeTesti.Prijave;
import sklepanjeTesti.Prijave2;
import sklepanjeTesti.TestRunnerLocal;
import sklepanjeTesti.config.EnvironmentConfig;

public class BancneKartice extends TestRunnerLocal {
	
	private WebDriver driver;
	EnvironmentConfig cfg = ConfigFactory.create(EnvironmentConfig.class);
	String baseURL = cfg.baseURL();
	
	public BancneKartice(WebDriver driver) {
		this.driver = driver;
	}
	
	@Test
	public void BancneKarticeTest() throws Exception {
		
		Funkcije2 funk = new Funkcije2(driver);
		Prijave2 p = new Prijave2(driver);
		/*
		BancneKarticeKorak1();
		
		funk.implicitWait(10);
		
		BancneKarticeKorak2();
		
		funk.implicitWait(10);
		
		BancneKarticeKorak3();
		
		checkSklenitev();
		*/
				
		
		//p.ItriglavPrijavaVnos("niko.sapac@triglav.si","Testgeslo123");
		p.SklepanjePrijava();
				
		String url = baseURL + "/isklepanje/kartice/kartice_zavarovanje";

		driver.get(url);
		Thread.sleep(500);
		
		driver.findElement(By.className("labelLeft")).click();
		
		// KORAK 1
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		
		funk.ScrollInKlik2(By.className("nextStep"));
				
		// KORAK 2
		
		funk.ScrollInKlik("(//*[@class='cs-slider'])[1]");
		funk.ScrollInKlik("(//*[@class='cs-slider'])[2]");
		
		funk.ScrollInKlik2(By.className("nextStep"));
		
		funk.waitForLoaderToFinish(driver, By.className("loader"));
		
		// KORAK 3 
		
		if(funk.IsVisible(By.xpath("//a[text()='Prijavite se']"))) {
			driver.findElement(By.xpath("//a[text()='Prijavite se']")).click();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		p.ItriglavPrijavaSklepanje();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		funk.ScrollInKlik("(//*[@class='cs-slider'])[1]");
		funk.ScrollInKlik("(//*[@class='cs-slider'])[2]");
		
		funk.ScrollInKlik2(By.id("preparePaymentButton"));
		
		funk.waitForLoaderToFinish(driver, By.className("loader"));
		
		funk.ScrollInKlik2(By.className("alternateNext"));

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		funk.checkSklenitev();
		
		
	}
	
	
	
	/*
	@Step("KORAK 1")
	public void BancneKarticeKorak1() throws InterruptedException {
		
		Funkcije2 funk = new Funkcije2(driver);
		Prijave2 p = new Prijave2(driver);
		
		//p.ItriglavPrijavaVnos("niko.sapac@triglav.si","Testgeslo123");
		p.SklepanjePrijava();
				
		String url = baseURL + "/isklepanje/kartice/kartice_zavarovanje";

		driver.get(url);
		Thread.sleep(500);
		
		driver.findElement(By.className("labelLeft")).click();
		
		// KORAK 1
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		
		funk.ScrollInKlik2(By.className("nextStep"));
		
	}
	
	@Step("KORAK 2")
	public void BancneKarticeKorak2() throws InterruptedException {
		
		Funkcije2 funk = new Funkcije2(driver);
		
		funk.ScrollInKlik("(//*[@class='cs-slider'])[1]");
		funk.ScrollInKlik("(//*[@class='cs-slider'])[2]");
		
		funk.ScrollInKlik2(By.className("nextStep"));
		
		funk.waitForLoaderToFinish(driver, By.className("loader"));
		
	}
	
	@Step("KORAK 3")
	public void BancneKarticeKorak3() throws Exception {
		
		Funkcije2 funk = new Funkcije2(driver);
		Prijave2 p = new Prijave2(driver);
		
		// KORAK 3 
		
		if(funk.IsVisible(By.xpath("//a[text()='Prijavite se']"))) {
			driver.findElement(By.xpath("//a[text()='Prijavite se']")).click();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		p.ItriglavPrijavaSklepanje();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		funk.ScrollInKlik("(//*[@class='cs-slider'])[1]");
		funk.ScrollInKlik("(//*[@class='cs-slider'])[2]");
		
		funk.ScrollInKlik2(By.id("preparePaymentButton"));
		
		funk.waitForLoaderToFinish(driver, By.className("loader"));
		
		funk.ScrollInKlik2(By.className("alternateNext"));

	}
	
	@Step("Preveri sklenitev zavarovanja")
	public void checkSklenitev() throws Exception {
		
		Funkcije2 funk = new Funkcije2(driver);
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		funk.checkSklenitev();
		
	}
	*/
}
