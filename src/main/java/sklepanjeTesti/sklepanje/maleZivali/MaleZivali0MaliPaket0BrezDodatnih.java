package sklepanjeTesti.sklepanje.maleZivali;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import sklepanjeTesti.Funkcije2;
import sklepanjeTesti.Prijave2;
import sklepanjeTesti.zdruzeniDeliAljosa.Miscellaneous;
import sklepanjeTesti.zdruzeniDeliAljosa.PersonalInfoBrezPrijave;
import sklepanjeTesti.zdruzeniDeliAljosa.TriglavDiscounts;

public class MaleZivali0MaliPaket0BrezDodatnih {
	private WebDriver driver;
	public MaleZivali0MaliPaket0BrezDodatnih (WebDriver driver) {
		this.driver = driver;
	}
	public void maleZivaliPrviTest() throws InterruptedException {
		
		// iffy stuff:
		String xpathToPackageName = "//li[1]/ul/li/p[1]";	//very iffy xpath (might not work forever)
		String textForAuthVet = "Popust za zdravljenje pri pooblaščenem veterinarju";	//Text might change, so this is also iffy
		String finalPrice = "//ul/li[3]/h4";	//xpath for final price might change (SUM price where you pick payment method)
		
		// 1-small, 2-middle, 3-lagrest packet
		int cP = 1;		// cP = choose packet
		
		// 0-choose_all, 1-choose_first_three, 2-choose_first_two, 3-leave_default, 4-uncheck_default
		int dk = 4;		// dk = dodatna kritja
		
		// 1-yes 2-no
		int PaV = 1;	// PaV = Pet Authorized Veterinarian
		
		String imeZivali = "Test ime živali";
		String dtRojZiv = "25.10.2023";
		String mikroCip = "123456789012345";
		String trr = "SI56 0234 3191 0006 381";
		
		Funkcije2 f = new Funkcije2(driver);
		
		Prijave2 p = new Prijave2(driver);
		
		TriglavDiscounts d = new TriglavDiscounts(driver);
		
		PersonalInfoBrezPrijave info = new PersonalInfoBrezPrijave(driver);
		
		Miscellaneous m = new Miscellaneous(driver);
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		p.SklepanjePrijava();
		
		driver.get("https://skleni-qa.triglav.si/isklepanje/zivali/zavarovanje_psov_in_mack");
		
		// https://skleni-qa.triglav.si/isklepanje/zivali/zavarovanje_psov_in_mack - ZAČETNI KORAK
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.elementToBeClickable(By.className("submitButton")));
		
		driver.findElement(By.className("submitButton")).click();
		
		// KORAK 1 - https://skleni-qa.triglav.si/isklepanje/zivali/zavarovanje_psov_in_mack_1
		
			// Podatki o živali
		wait.until(ExpectedConditions.urlToBe("https://skleni-qa.triglav.si/isklepanje/zivali/zavarovanje_psov_in_mack_1"));
		
		f.ScrollInKlik2(By.xpath("(//*[@class = 'select2-selection__arrow'])[1]"));
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Pes']")));
		
		driver.findElement(By.xpath("//*[text()='Pes']")).click();	
		
		driver.findElement(By.id("pet.name")).sendKeys(imeZivali);
		
		driver.findElement(By.id("pet.birthDate")).sendKeys(dtRojZiv);
				
		f.ScrollInKlik2(By.xpath("(//*[@class='radio--primary__button'])[1]"));
		
		driver.findElement(By.id("pet.microchipNum")).sendKeys(mikroCip);
		
		driver.findElement(By.xpath("(//*[@class='radio--primary__button'])[4]")).click();
		
			//authorized veterinarian
		String yesNO;	//yesNO can be yes / no
			if (PaV == 1) {
				yesNO = "yes";
			}
			else {
				yesNO = "no";
			}
		
		f.ScrollInKlik2(By.xpath("//*[@for='pet.authorizedVet-"+yesNO+"']"));
		
		f.ScrollInKlik2(By.className("submitButton"));
		
			// Izbira paketa
		wait.until(ExpectedConditions.urlToBe("https://skleni-qa.triglav.si/isklepanje/zivali/zavarovanje_psov_in_mack_2"));
		
		WebElement packetSelect = driver.findElement(By.xpath("(//*[contains(@class, 'js-btnSelectPackage2')])["+cP+"]"));	//WebElemet with xpath, selected depending on variable cP
		
		f.ScrollInKlik2WebEl(packetSelect);	//Click WebElement (I ADDED THE CODE TO FUNKCIJE2)
		
		String packet;	//string packet selected depending on variable cP
			if (cP == 1) {
				packet = "Mali paket";
			}
			else if (cP == 2) {
				packet = "Veliki paket";
			}
			else {
				packet = "Comfort paket";
			}
		
		
		wait.until(ExpectedConditions.elementToBeClickable(By.id("btnPackageSmall")));
		
		
			for (int i = 2; i<=4-dk; i++) {	
				f.ScrollInKlik2(By.xpath("(//*[@class='checkbox-tertiary-icon'])["+i+"]"));			//ticking all the options; incl. animal owner responsibility (not present if you choose i.e. Doberman dog)
			}
			if(dk == 4) {
				f.ScrollInKlik2(By.xpath("(//*[@class='checkbox-tertiary-icon'])[1]"));				//untick box 1 if dk 4
			}
		
		f.ScrollInKlik2(By.className("submitButton"));
		
			// Owners personal info
		
		wait.until(ExpectedConditions.urlToBe("https://skleni-qa.triglav.si/isklepanje/zivali/zavarovanje_psov_in_mack_3"));
		info.BrezPrijave();
		
			//going through sumedup numbers
		wait.until(ExpectedConditions.urlToBe("https://skleni-qa.triglav.si/isklepanje/zivali/zavarovanje_psov_in_mack_4"));
		
		d.MaleZivali();
		
		String actualPackageText = driver.findElement(By.xpath(xpathToPackageName)).getText();
		System.out.println(actualPackageText);
		Assert.assertEquals(actualPackageText, packet);
			// checking (pooblaščeni veterinar)
			if (yesNO == "yes") {
				driver.findElement(By.xpath("//*[contains(@class, 'discount-title-wrap-mobile') and contains(text(), '"+textForAuthVet+"')]"));
			}
			
			// checking elements (dodatna zavarovanja)
			switch (dk) {
			case 0:
				driver.findElement(By.xpath("//*[contains(text(), 'Stroški iskanja izginule ali ukradene živali')]"));
				driver.findElement(By.xpath("//*[contains(text(), 'Asistenca (Slovenija)')]"));
				driver.findElement(By.xpath("//*[contains(text(), 'Odgovornost lastnika živali')]"));
				driver.findElement(By.xpath("//*[contains(text(), 'Pogin živali')]"));				
				break;
			case 1:
				driver.findElement(By.xpath("//*[contains(text(), 'Asistenca (Slovenija)')]"));
				driver.findElement(By.xpath("//*[contains(text(), 'Odgovornost lastnika živali')]"));
				driver.findElement(By.xpath("//*[contains(text(), 'Pogin živali')]"));	
				break;
			case 3:
				driver.findElement(By.xpath("//*[contains(text(), 'Odgovornost lastnika živali')]"));
				driver.findElement(By.xpath("//*[contains(text(), 'Pogin živali')]"));
				break;
			}
		
			//Checking first price
		String sumComparePrice = driver.findElement(By.xpath("//*[@class='informativePremiumPrice']")).getText();
		String sumComparePriceOn = sumComparePrice.replaceAll("[^0-9,]", "");
		sumComparePriceOn = sumComparePriceOn.replace(',', '.');
		Float sumBefore = Float.valueOf(sumComparePriceOn);
		
		f.ScrollInKlik2(By.xpath("//*[@for = 'info-zavarovalni-paket-mobile']"));
		
		f.ScrollInKlik2(By.xpath("//*[@for = 'zavarovalnimi-pogoji']"));
		
		f.ScrollInKlik2(By.className("submitButton"));
		
			//Simulate Payment
		wait.until(ExpectedConditions.urlToBe("https://skleni-qa.triglav.si/isklepanje/zivali/zavarovanje_psov_in_mack_5"));
			//Checking second price
		String sumFinalPrice = driver.findElement(By.xpath(finalPrice)).getText();
		String sumFinalPriceOn = sumFinalPrice.replaceAll("[^0-9,]", "");
		sumFinalPriceOn = sumFinalPriceOn.replace(',', '.');
		Float sumAfter = Float.valueOf(sumFinalPriceOn);
			//Comparing price
		Assert.assertEquals(sumBefore, sumAfter);
		
		m.InstallmentPaymentSad(trr);
		/*
		driver.findElement(By.xpath("(//*[contains(@class, 'radio--secondary__button')])[1]")).click();
		
		driver.findElement(By.xpath("(//*[@class = 'radio-quaternary__button'])[1]")).click();
		
		f.ScrollInKlik2(By.xpath("//*[@for = 'soglasam-s-pogoji-celotna-premija']"));
		
		f.ScrollInKlik2(By.xpath("//*[@value = 'simulatePayment']"));
		*/
			//Waiting till green text "Hvala!"
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@class, 'intro-title-green') and text() = 'Hvala!']")));
		
	}
}
