package sklepanjeTesti.sklepanje.NezgodnoOtrokInMladih;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import sklepanjeTesti.Funkcije2;
import sklepanjeTesti.Prijave2;
import sklepanjeTesti.zdruzeniDeliAljosa.ChangingPersonalInfoPrijav;
import sklepanjeTesti.zdruzeniDeliAljosa.Miscellaneous;

public class OdstranjevanjeOtrokLogIn {
	private WebDriver driver;
	public OdstranjevanjeOtrokLogIn (WebDriver driver) {
		this.driver = driver;
	}
	
	public void maliPaket2Otroci() throws InterruptedException {
		
		Date initialDate = Calendar.getInstance().getTime();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(initialDate);
        calendar.add(Calendar.DAY_OF_MONTH, +1); //add 1 day (next day)
        Date toYoungDate = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("d.M.yyyy");
        String nextDay = dateFormat.format(toYoungDate);
        System.out.println("Next day is: " + nextDay);
        
        SimpleDateFormat dateFormat3 = new SimpleDateFormat("dd.MM.yyyy");
        String today = dateFormat3.format(initialDate);
        System.out.println("Today is: " + today);
        
        String idOsebe = "996377";
        
		Funkcije2 f = new Funkcije2(driver);
		
		Prijave2 p = new Prijave2(driver);
		
		Miscellaneous m = new Miscellaneous(driver);
		
		ChangingPersonalInfoPrijav cp = new ChangingPersonalInfoPrijav(driver);
		
		String triglavKompletNum = m.PridobiKomplet(idOsebe);
		
		String trr = "SI56 0234 3191 0006 381";
		
		int placiloCelotnePremije = 1;
		
		//Iffy xpath's
		String finalPrice = "//ul/li[2]/h4";
		
		//PODATKI OTROK:
		
		String toKeep1 = "Mali paket";
		String toKeep2 = "Veliki paket";
		
		String iTrMail = "aljosa.mlinaric@triglav.si";
		String iTrPass = "Aljosa@0110";
		
		String[] imeOtroka = {"Janez.jr", "Milka", "Jošt"};
		
		String[] priimOtroka = {"Janezson", "die.KUH", "Cheese"};
		
		String[] birthDateOt = {"01.12.2020", "01.12.2021", "01.12.2022"};
		
		String[] taxNumOt = {"85616320", "57640114", "57785341"};
		
		//IF CHILDS RESIDENCE NOT THE SAME
		String[] postRes = {"9000", "9241", "9251"};
		
		int[] choosingStreet = {3, 6, 1};
		
		int[] choosingHouseNum = {1, 3, 2};
		
		//3-M 4-Ž
		int[] spolOt = {3, 4, 3};
		
		//CHILD'S PERMENANTE RESIDENCE IS NOT THE SAME
		int[] resOt = {0, 1, 0}; //1-yes 0-no
		
		//IS CHILD REGISTERED SPORTSMAN
		int[] sportOt = {1, 0, 1};
		
		String paket = "Comfort";
		
		String[] afterPaket = {"btnPackageSmall", "btnPackageBig", "btnPackageComfort"};
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		
		p.SklepanjePrijava();
		
		driver.get("https://skleni-qa.triglav.si/isklepanje/nezgoda/nezgodno_zavarovanje_otrok_in_mladih");
		
		m.CheckTwoButtonsNOloginStep1();
		
		driver.findElement(By.className("intro-btn")).click();
		
		p.ItriglavPrijavaSklepanje2(iTrMail, iTrPass);
		
		m.ObvescanjeMessage();
		
		m.CheckOneButtonShowsLogINstep1();
		if (triglavKompletNum != null) {
			System.out.println("Triglav komplet ki je najden: " + triglavKompletNum);
		} else {
			System.out.println("triglavKompletNum is null");
		}
		driver.findElement(By.className("intro-btn")).click();
		
		//Prvi korak
		wait.until(ExpectedConditions.urlToBe("https://skleni-qa.triglav.si/isklepanje/nezgoda/nezgodno_zavarovanje_otrok_in_mladih_1"));
		
		String defDateInsStart = driver.findElement(By.id("startDate")).getAttribute("value");
		System.out.println("Actual: " + defDateInsStart);
		
		Assert.assertEquals(defDateInsStart, nextDay);
		
		driver.findElement(By.id("numPartiesInput")).clear();
		driver.findElement(By.id("numPartiesInput")).sendKeys("2");
		driver.findElement(By.xpath("//*[@id = 'number-children-bonus-info'] [@class = 'validation-success ux-hide']"));
		f.ScrollInKlik2(By.className("inputNumber-plus"));
		driver.findElement(By.xpath("//*[@id = 'number-children-bonus-info'] [@class = 'validation-success']"));
		
		f.ScrollInKlik2(By.className("submitButton"));
		
		wait.until(ExpectedConditions.urlToBe("https://skleni-qa.triglav.si/isklepanje/nezgoda/nezgodno_zavarovanje_otrok_in_mladih_2"));
		
		f.ScrollInKlik2(By.xpath("//*[@id = 'btnPackage"+paket+"']"));
		
		driver.findElement(By.className("packages-warningMessage"));
		
		f.ScrollInKlik2(By.className("submitButton"));
		
		wait.until(ExpectedConditions.urlToBe("https://skleni-qa.triglav.si/isklepanje/nezgoda/nezgodno_zavarovanje_otrok_in_mladih_3"));
		
		cp.changePersonalInfo();
		
		for(int i = 0; i <= 2; i++) {
			Thread.sleep(2000);
			f.ScrollInKlik2(By.id("add-child"));
			Thread.sleep(1000);
			driver.findElement(By.id("name")).sendKeys(imeOtroka[i]);
			driver.findElement(By.id("surname")).sendKeys(priimOtroka[i]);
			driver.findElement(By.id("birthday")).sendKeys(birthDateOt[i]);
			driver.findElement(By.id("taxnum")).sendKeys(taxNumOt[i]);
			f.ScrollInKlik2(By.xpath("(//*[@class = 'radio--primary__button'])["+spolOt[i]+"]"));
			if (resOt[i] == 1) {
				f.ScrollInKlik2(By.xpath("//*[@for = 'child-address']"));
				driver.findElement(By.id("childMemberAddModal")).sendKeys(Keys.PAGE_DOWN, Keys.PAGE_DOWN);
				Thread.sleep(1000);
				driver.findElement(By.id("addressSearch.post_and_place")).sendKeys(postRes[i]);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ui-id-7")));
				Thread.sleep(1000);
				driver.findElement(By.id("addressSearch.post_and_place")).sendKeys(Keys.ARROW_DOWN);
				Thread.sleep(2000);
				driver.findElement(By.id("addressSearch.post_and_place")).sendKeys(Keys.RETURN);
				driver.findElement(By.id("addressSearch.street")).click();
				for (int n = 1; n <= choosingStreet[i]; n++) {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ui-id-8")));
					driver.findElement(By.id("addressSearch.street")).sendKeys(Keys.ARROW_DOWN);
					if (n == choosingStreet[i]) {
						driver.findElement(By.id("addressSearch.street")).sendKeys(Keys.RETURN);
					}
				}
				driver.findElement(By.id("addressSearch.hnr")).click();
				for (int n = 1; n <= choosingHouseNum[i]; n++) {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ui-id-9")));
					driver.findElement(By.id("addressSearch.hnr")).sendKeys(Keys.ARROW_DOWN);
					if (n == choosingHouseNum[i]) {
						driver.findElement(By.id("addressSearch.hnr")).sendKeys(Keys.RETURN);
					}
				}
			}
			if (sportOt[i] == 1) {
				f.ScrollInKlik2(By.xpath("(//*[@class = 'radio--secondary__button'])[2]"));
			}
			Thread.sleep(1000);
			f.ScrollInKlik2(By.id("addInsurerForm"));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("spinner-wrapper")));
			Thread.sleep(3000);
		}
		//checking package names
		f.ScrollToElement(By.id("insurerPersonList"));
		String childOne = driver.findElement(By.xpath("(//*[@class = 'storedInfoCalculation-itemDataValue'])[2]")).getText();
		Assert.assertEquals(childOne, paket + " paket" );
		String childTwo = driver.findElement(By.xpath("(//*[@class = 'storedInfoCalculation-itemDataValue'])[8]")).getText();
		Assert.assertEquals(childTwo, paket + " paket" );
		String childThree = driver.findElement(By.xpath("(//*[@class = 'storedInfoCalculation-itemDataValue'])[14]")).getText();
		Assert.assertEquals(childThree, paket + " paket" );
		
		for (int i = 0; i <= 2; i++) {
			List<WebElement> spremeniPak = driver.findElements(By.className("change-package"));
			f.ScrollInKlik2WebEleList(spremeniPak.get(i), spremeniPak.get(i));
			f.ScrollInKlik2(By.id(afterPaket[i]));
			f.ScrollInKlik2(By.className("submitButton"));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("spinner-wrapper")));
		}
		
		f.ScrollToElement(By.id("insurerPersonList"));
		Thread.sleep(1000);
		String child1 = driver.findElement(By.xpath("(//*[@class = 'storedInfoCalculation-itemDataValue'])[2]")).getText();
		String child2 = driver.findElement(By.xpath("(//*[@class = 'storedInfoCalculation-itemDataValue'])[8]")).getText();
		String child3 = driver.findElement(By.xpath("(//*[@class = 'storedInfoCalculation-itemDataValue'])[14]")).getText();
		Assert.assertEquals(child1, "Mali paket");
		Assert.assertEquals(child2, "Veliki paket");
		Assert.assertEquals(child3, "Comfort paket");
		
		driver.get("https://skleni-qa.triglav.si/isklepanje/nezgoda/nezgodno_zavarovanje_otrok_in_mladih_2");
		driver.findElement(By.id("changePackageError"));
		driver.get("https://skleni-qa.triglav.si/isklepanje/nezgoda/nezgodno_zavarovanje_otrok_in_mladih_1");
		
		driver.findElement(By.id("numPartiesInput")).clear();
		driver.findElement(By.id("numPartiesInput")).sendKeys("2");
		f.ScrollInKlik2(By.className("submitButton"));
		driver.findElement(By.id("changePackageError"));
		driver.get("https://skleni-qa.triglav.si/isklepanje/nezgoda/nezgodno_zavarovanje_otrok_in_mladih_3");
		f.ScrollInKlik2(By.className("submitButton"));
		f.ScrollToElement(By.id("numParties.errors"));
		f.ScrollInKlik2(By.xpath("//*[@id=\"insurerPersonList\"]/div[2]/ul/li[3]/div[5]/div[2]/div[2]/div/button/img"));
		driver.findElement(By.id("insurerRemoveButton")).click();
		f.ScrollInKlik2(By.className("submitButton"));
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
		try {
			f.ScrollInKlik2(By.xpath("//*[@for = 'alreadyInsuredLastYearConfirmPoSkadenciSwitch']"));
			System.out.println("There are people with same insurance already");
			f.ScrollInKlik2(By.className("submitButton"));
		} catch (NoSuchElementException | StaleElementReferenceException e) {
			System.out.println("There are no same persons with same insurance");
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		wait.until(ExpectedConditions.urlToBe("https://skleni-qa.triglav.si/isklepanje/nezgoda/nezgodno_zavarovanje_otrok_in_mladih_4"));
		
		List<WebElement> completeCircle = driver.findElements(By.className("icon-checked-circle"));
		if(completeCircle.isEmpty()) {
			System.out.println("Triglav complete is not found");
		} else {
			System.out.println("Triglav Complete is already added");
			driver.findElement(By.className("icon-checked-circle"));
			//driver.findElement()
		}
		
		String pkg1 = driver.findElement(By.xpath("(//*[@class = 'package-selected-multipleItems'])[1]")).getText();
		String pkg2 = driver.findElement(By.xpath("(//*[@class = 'package-selected-multipleItems'])[2]")).getText();
		//System.out.println(pkg1);
        String pkgg1 = pkg1.split(toKeep1)[0] + toKeep1;
        String pkgg2 = pkg2.split(toKeep2)[0] + toKeep2;
		Assert.assertEquals(pkgg1, child1);
		Assert.assertEquals(pkgg2, child2);
		
		driver.get("https://skleni-qa.triglav.si/isklepanje/nezgoda/nezgodno_zavarovanje_otrok_in_mladih_3");
		f.ScrollInKlik2(By.xpath("(//*[@src = 'https://skleni-qa.triglav.si/triglav-web-static/optimizacijaspletneprodaje/master/dist/images/icons/q/ic-pencil.svg'])[2]"));
		driver.findElement(By.id("name")).clear();
		driver.findElement(By.id("name")).sendKeys("test");
		driver.findElement(By.id("surname")).clear();
		driver.findElement(By.id("surname")).sendKeys("testing");
		driver.findElement(By.id("childMemberAddModal")).sendKeys(Keys.PAGE_DOWN, Keys.PAGE_DOWN);
		f.ScrollInKlik2(By.xpath("(//*[@class = 'radio--primary__button'])[4]"));
		f.ScrollInKlik2(By.xpath("(//*[@class = 'radio--secondary__button'])[2]"));
		f.ScrollInKlik2(By.id("addInsurerForm"));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("spinner-wrapper")));
		
		f.ScrollInKlik2(By.className("submitButton"));
		
		driver.findElement(By.xpath("//*[text() = 'TEST TESTING']"));
		
		driver.findElement(By.xpath("//*[contains(text(), 'Brez') and contains(text(), 'poteka')]"));
		
		driver.get("https://skleni-qa.triglav.si/isklepanje/nezgoda/nezgodno_zavarovanje_otrok_in_mladih_1");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@aria-labelledby = 'select2-inuranceDuration-container']")).click();
		driver.findElement(By.xpath("//*[text() = '1 leto brez avtomatskega podaljševanja']")).click();
		
		f.ScrollInKlik2(By.className("submitButton"));
		
		driver.get("https://skleni-qa.triglav.si/isklepanje/nezgoda/nezgodno_zavarovanje_otrok_in_mladih_4");
		driver.findElement(By.xpath("//*[contains(text(),'1 leto brez avtomatskega podaljševanja')]"));
		
		//getting price from step_4
		wait.until(ExpectedConditions.urlToBe("https://skleni-qa.triglav.si/isklepanje/nezgoda/nezgodno_zavarovanje_otrok_in_mladih_4"));
		//////////////////////
		f.ScrollInKlik2(By.id("btnSaveInfoCalc"));
		driver.findElement(By.id("infoCalcExpiryDate"));
		driver.get("https://skleni-qa.triglav.si/isklepanje/nezgoda/nezgodno_zavarovanje_otrok_in_mladih");
		String dateOfSave = driver.findElement(By.xpath("//*[@id=\"nzomQModel\"]/div[1]/div[1]/div[2]/ul/li[1]/div[4]/div[2]")).getText();
		System.out.println("dateOfSave: " + dateOfSave);
		Assert.assertEquals(dateOfSave, today);
		
		driver.get("https://skleni-qa.triglav.si/isklepanje/nezgoda/nezgodno_zavarovanje_otrok_in_mladih_4");
		wait.until(ExpectedConditions.urlToBe("https://skleni-qa.triglav.si/isklepanje/nezgoda/nezgodno_zavarovanje_otrok_in_mladih_4"));
		
		calendar.add(Calendar.MONTH, +1);
	    Date InsuranceStart = calendar.getTime();
	    SimpleDateFormat dateFormat1 = new SimpleDateFormat("d.M.yyyy");
	    String Insurance2monthAfter = dateFormat1.format(InsuranceStart);
		
		driver.get("https://skleni-qa.triglav.si/isklepanje/nezgoda/nezgodno_zavarovanje_otrok_in_mladih_1");
		driver.findElement(By.id("startDate")).clear();
		driver.findElement(By.id("startDate")).sendKeys(Insurance2monthAfter);
		driver.findElement(By.id("numPartiesInput")).clear();
		driver.findElement(By.id("numPartiesInput")).sendKeys("1");
		f.ScrollInKlik2(By.className("submitButton"));
		wait.until(ExpectedConditions.urlToBe("https://skleni-qa.triglav.si/isklepanje/nezgoda/nezgodno_zavarovanje_otrok_in_mladih_2"));
		
		//driver.findElement(By.id("dataCalcDesc"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dataCalcDesc")));
		
		driver.get("https://skleni-qa.triglav.si/isklepanje/nezgoda/nezgodno_zavarovanje_otrok_in_mladih_3");
		wait.until(ExpectedConditions.urlToBe("https://skleni-qa.triglav.si/isklepanje/nezgoda/nezgodno_zavarovanje_otrok_in_mladih_3"));
		f.ScrollInKlik2(By.xpath("(//*[@src = 'https://skleni-qa.triglav.si/triglav-web-static/optimizacijaspletneprodaje/master/dist/images/icons/q/icon-x-red.svg'])[2]"));
		f.ScrollInKlik2(By.id("insurerRemoveButton"));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("spinner-wrapper")));
		f.ScrollInKlik2(By.xpath("(//*[@src = 'https://skleni-qa.triglav.si/triglav-web-static/optimizacijaspletneprodaje/master/dist/images/icons/q/icon-x-red.svg'])[1]"));
		f.ScrollInKlik2(By.id("insurerRemoveButton"));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("spinner-wrapper")));
		f.ScrollInKlik2(By.xpath("(//*[@class = 'radio--secondary__button'])[2]"));
		f.ScrollInKlik2(By.xpath("(//*[@class = 'radio--secondary__button'])[4]"));
		driver.findElement(By.id("message-sportsman"));
		f.ScrollInKlik2(By.className("submitButton"));
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
		try {
			f.ScrollInKlik2(By.xpath("//*[@for = 'alreadyInsuredLastYearConfirmPoSkadenciSwitch']"));
			System.out.println("There are people with same insurance already");
			f.ScrollInKlik2(By.className("submitButton"));
		} catch (NoSuchElementException | StaleElementReferenceException e) {
			System.out.println("There are no same persons with same insurance");
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		wait.until(ExpectedConditions.urlToBe("https://skleni-qa.triglav.si/isklepanje/nezgoda/nezgodno_zavarovanje_otrok_in_mladih_4"));
		
		driver.findElement(By.xpath("//*[contains(text(),'Comfort paket') and contains(text(),'(športnik)')]"));
		
		f.ScrollInKlik2(By.xpath("//*[@value = 'SPREMENI_PAKET']"));
		wait.until(ExpectedConditions.elementToBeClickable(By.id("btnPackageSmall")));
		f.ScrollInKlik2(By.id("btnPackageSmall"));
		f.ScrollInKlik2(By.className("submitButton"));
		
		driver.findElement(By.xpath("//*[contains(text(),'Mali paket') and contains(text(),'(športnik)')]"));
	
		String sumComparePrice = driver.findElement(By.xpath("//*[@class='informativePremiumPrice']")).getText();
		String sumComparePriceOn = sumComparePrice.replaceAll("[^0-9,]", "");
		sumComparePriceOn = sumComparePriceOn.replace(',', '.');
		Float sumBefore = Float.valueOf(sumComparePriceOn);
		System.out.println("Before value: ");
		System.out.println(sumBefore);
		
		SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd.MM.yyyy");
	    String Insurance2monthAfterCheck = dateFormat2.format(InsuranceStart);
		
	    f.ScrollInKlik2(By.xpath("//*[@id=\"informativeCalculation\"]/div[2]/div[1]/div[2]/ul/li[1]/p[2]"));
		String[] ZacZav = driver.findElement(By.xpath("//*[@id=\"informativeCalculation\"]/div[2]/div[1]/div[2]/ul/li[1]/p[2]")).getText().split(" OB ");
		System.out.println("Changed date: " + ZacZav[0]);
		Assert.assertEquals(ZacZav[0], Insurance2monthAfterCheck);
		
		f.ScrollInKlik2(By.xpath("//*[@for = 'info-zavarovalni-paket-mobile']"));
		f.ScrollInKlik2(By.xpath("//*[@for = 'zavarovalnimi-pogoji']"));
		
		f.ScrollInKlik2(By.className("submitButton"));
		wait.until(ExpectedConditions.urlToBe("https://skleni-qa.triglav.si/isklepanje/nezgoda/nezgodno_zavarovanje_otrok_in_mladih_5"));
		
		String sumFinalPrice = driver.findElement(By.xpath(finalPrice)).getText();	//Getting text value
		String sumFinalPriceOn = sumFinalPrice.replaceAll("[^0-9,]", "");	//converting to only numbers
		sumFinalPriceOn = sumFinalPriceOn.replace(',', '.');	//replacing , with .
		Float sumAfter = Float.valueOf(sumFinalPriceOn);	// saving finalPrice xpath text value as float value
		System.out.println("Acctual price: ");	
		System.out.println(sumAfter);
			//Comparing price
		Assert.assertEquals(sumBefore, sumAfter);
		
		//Insurance2monthAfter
		
		if (placiloCelotnePremije == 1) {
			m.OneTimePaymentNonRecu();
		}
		else {
			m.InstallmentPaymentHappy(trr);
		}
		
		f.ScrollInKlik2(By.xpath("//*[@value = 'simulatePayment']"));
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@class, 'intro-title-green') and text() = 'Hvala!']")));
		
		m.AnketaZadnjiKorak();
	}
}
