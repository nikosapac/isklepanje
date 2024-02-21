package sklepanjeTesti.informativniIzracun;

import java.time.Duration;
import java.util.List;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import sklepanjeTesti.Funkcije;
import sklepanjeTesti.Funkcije2;
import sklepanjeTesti.Prijave;
import sklepanjeTesti.Prijave2;
import sklepanjeTesti.config.EnvironmentConfig;

public class InformativniIzracunPolica222 {
	WebDriver driver;
	
	
	EnvironmentConfig cfg = ConfigFactory.create(EnvironmentConfig.class);
	String baseURL = cfg.baseURL();
	String itrEmail = cfg.iTriglavEmail();
	String itrPass = cfg.iTriglavPass();
	
	public InformativniIzracunPolica222(WebDriver driver) {
		this.driver=driver;
	}
	
	public void InformativniIzracunPolicaTest() throws Exception {
		
		Funkcije2 f = new Funkcije2(driver);
		Prijave2 p = new Prijave2(driver);
		
		String datum = f.Datum2(1);
		
		p.SklepanjePrijava();
		
		//Store the ID of the original window
		String originalWindow = driver.getWindowHandle();
		
		//Check we don't have other windows open already
		assert driver.getWindowHandles().size() == 1;
		
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
		
		f.ScrollInKlik2(By.id("btnSaveInfoCalc"));
		
		f.implicitWait(10);
		
		f.waitForLoaderToFinish(driver, By.className("loader"));
		
	//	new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[contains(text(),'odpri informativni izračun')])[1]")));
	//	new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='odpri informativni izračun']")));
		new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='VSTOPI V I.TRIGLAV']")));
		
		Thread.sleep(1000);
		
		f.implicitWait(10);
		
		driver.findElement(By.xpath("//*[text()='VSTOPI V I.TRIGLAV']")).click();
		
		
		//Wait for the new window or tab
		new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.numberOfWindowsToBe(2));
		
		
		//Loop through until we find a new window handle
		for (String windowHandle : driver.getWindowHandles()) {
		    if(!originalWindow.contentEquals(windowHandle)) {
		        driver.switchTo().window(windowHandle);
		        break;
		    }
		}
		
		new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.urlToBe(baseURL + "/iTriglav3/izracun/informativni-izracuni"));

		Thread.sleep(1000);
		
		f.implicitWait(10);
		
		// iTriglav validacija
		
		f.virtualniAsistent();
		
		Thread.sleep(1000);
		
		f.implicitWait(10);
		
		List<WebElement> nadaljuj = driver.findElements(By.xpath("//*[text()='Nadaljuj']"));
		
		System.out.println(nadaljuj.size());
		
		//Store the ID of the original window
		String originalWindow2 = driver.getWindowHandle();
		
		WebElement klik = nadaljuj.get(0);
		
		klik.click();
		
		// Infornmativni izračun validacija
		
		//Wait for the new window or tab
		new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.numberOfWindowsToBe(3));
		
		f.implicitWait(10);
			
		//Loop through until we find a new window handle
		for (String windowHandle : driver.getWindowHandles()) {
		    if(!originalWindow2.contentEquals(windowHandle) && !originalWindow.contentEquals(windowHandle) ) {
		        driver.switchTo().window(windowHandle);
		        break;
		    }
		}
		
		Thread.sleep(1500);
		
		new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.urlToBe(baseURL + "/isklepanje/dom/dom_zavarovanje_1"));
		
		f.implicitWait(10);
		
		f.waitForLoaderToFinish(driver, By.className("loader"));
		
		Thread.sleep(1500);
		
		f.implicitWait(10);
		
		// KORAK 1 - PODATKI O NEPREMIČNINI
		
		WebElement posta = driver.findElement(By.id("addressSearch.post_and_place"));
		String postaText = posta.getAttribute("value");
		
		WebElement ulica = driver.findElement(By.id("addressSearch.street"));
		String ulicaText = ulica.getAttribute("value");
		
		WebElement hisnaSt = driver.findElement(By.id("addressSearch.hnr"));
		String hisnaStText = hisnaSt.getAttribute("value");
		
		WebElement neto = driver.findElement(By.id("netoSurfaceArea"));
		String netoText = neto.getAttribute("value");
		
		WebElement bruto = driver.findElement(By.id("brutoSurfaceAreaForGUI"));
		String brutoText = bruto.getAttribute("value");
		
		WebElement leto = driver.findElement(By.id("constructionYearForGUI"));
		String letoText = leto.getAttribute("value");
		
		WebElement gradnja = driver.findElement(By.id("constructionTypeDescriptionForGUI"));
		String gradnjaText = gradnja.getAttribute("value");
		
		
		WebElement streha = driver.findElement(By.id("select2-roofRenovation-container"));
		String strehaText = streha.getText();
		
		WebElement fasada = driver.findElement(By.id("select2-facadeRenovation-container"));
		String fasadaText = fasada.getText();

		WebElement okna = driver.findElement(By.id("select2-windowRestoration-container"));
		String oknaText = okna.getText();
		
		WebElement napeljava = driver.findElement(By.id("select2-plumbingRenovation-container"));
		String napeljavaText = napeljava.getText();
		
		WebElement datumZacetka = driver.findElement(By.id("startDate"));
		String datumZacetkaText = datumZacetka.getAttribute("value");
		
		Assert.assertTrue(postaText.contains("9000 MURSKA SOBOTA"), "Napaka pri validaciji pošte: " + postaText);
		Assert.assertTrue(ulicaText.contains("MLADINSKA ULICA, MURSKA SOBOTA"), "Napaka pri validaciji ulice: " + ulicaText);
		Assert.assertTrue(hisnaStText.contains("5"), "Napaka pri validaciji hišne številke: " + hisnaStText);
		
		Assert.assertTrue(netoText.contains("220"), "Napaka pri validaciji neto površine: " + netoText);
		Assert.assertTrue(brutoText.contains("275"), "Napaka pri validaciji bruto površine: " + brutoText);
		Assert.assertTrue(letoText.contains("1918"), "Napaka pri validaciji leta izgradnje: " + letoText);
		Assert.assertTrue(gradnjaText.contains("Klasična"), "Napaka pri validaciji vrste gradnje: " + gradnjaText);
		
		Assert.assertTrue(strehaText.contains("2012 - 2013"), "Napaka pri validaciji strehe: " + strehaText);
		Assert.assertTrue(fasadaText.contains("1984 - 1988"), "Napaka pri validaciji nfasade: " + fasadaText);
		Assert.assertTrue(oknaText.contains("Pred 1989"), "Napaka pri validaciji okna: " + oknaText);
		Assert.assertTrue(napeljavaText.contains("2020 - 2021"), "Napaka pri validaciji vodovodne napeljave: " + napeljavaText);
		
		Assert.assertTrue(datumZacetkaText.contains(datum), "Napaka pri validaciji datuma začetka: " + datumZacetkaText);
		
		f.ScrollInKlik2(By.className("btn-next"));
		
		// KORAK 2
		
		new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.urlToBe(baseURL + "/isklepanje/dom/dom_zavarovanje_2"));

		f.implicitWait(10);
		
		WebElement paket = driver.findElement(By.id("btnPackageBig"));
		String paketText = paket.getText();
		
		Assert.assertTrue(paketText.contains("IZBRANO"), "Napaka pri validaciji paketa: " + paketText);
		
		f.ScrollInKlik2(By.className("btn-next"));
		
		// KORAK 3 
		
		new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.urlToBe(baseURL + "/isklepanje/dom/dom_zavarovanje_3"));
		
		f.implicitWait(10);
		
		WebElement ime = driver.findElement(By.id("policyHolderUI.name"));
		String imeText = ime.getAttribute("value");
		
		WebElement pri = driver.findElement(By.id("policyHolderUI.surname"));
		String priText = pri.getAttribute("value");
		
		WebElement datRoj = driver.findElement(By.id("policyHolderUI.birthday"));
		String datRojText = datRoj.getAttribute("value");
		
		WebElement davcna = driver.findElement(By.id("policyHolderUI.taxnum"));
		String davcnaText = davcna.getAttribute("value");
		
		WebElement spol = driver.findElement(By.id("male"));
		String spolText = spol.getAttribute("checked");
		
		
		WebElement stalBivalisce = driver.findElement(By.id("naslov-stalnega-bivalisca-da"));
		String stalBivalisceText = stalBivalisce.getAttribute("checked");
		
		
		WebElement telSt = driver.findElement(By.id("policyHolderUI.phoneNumber"));
		String telStText = telSt.getAttribute("value");
		
		WebElement mail = driver.findElement(By.id("validatedEmail"));
		String mailText = mail.getAttribute("value");
		
		WebElement pogoj = driver.findElement(By.id("confirmPersonalDataProcessingAproval"));
		String pogojText = pogoj.getAttribute("checked");
		
		Assert.assertTrue(imeText.contains("ALIJAXYZ"), "Napaka pri validaciji imena: " + imeText);
		Assert.assertTrue(priText.contains("GROCHOWSKIXYZ"), "Napaka pri validaciji priimka: " + priText);
		Assert.assertTrue(datRojText.contains("27.5.1973"), "Napaka pri validaciji datuma rojstva: " + datRojText);
		Assert.assertTrue(davcnaText.contains("70929165"), "Napaka pri validaciji davčne: " + davcnaText);
		Assert.assertTrue(spolText.contains("true"), "Napaka pri validaciji spola: " + spolText);
		
		Assert.assertTrue(stalBivalisceText.contains("true"), "Napaka pri validaciji stalnega bivališča: " + stalBivalisceText);
		
		Assert.assertTrue(telStText.contains("041805 460") || telStText.contains("031 532 734"), "Napaka pri validaciji telefonske številke: " + telStText);
		Assert.assertTrue(mailText.contains(itrEmail), "Napaka pri validaciji maila: " + mailText);
		
		Assert.assertTrue(pogojText.contains("true"), "Napaka pri validaciji pogoja: " + pogojText);
		
		f.ScrollInKlik2(By.className("btn-next"));
		
		// KORAK 4 
		
		new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.urlToBe(baseURL + "/isklepanje/dom/dom_zavarovanje_4"));
		
		f.implicitWait(10);
		
		WebElement triglavKomplet = driver.findElement(By.className("tKompletPercent"));
		String triglavKompletText = triglavKomplet.getText();
		
		Assert.assertTrue(triglavKompletText.contains("15"), "Napaka pri validaciji pogoja: " + triglavKompletText);

		driver.quit();
	}

	
}
