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
import sklepanjeTesti.zdruzeniDeliAljosa.Miscellaneous;
import sklepanjeTesti.zdruzeniDeliAljosa.PersonalInfoBrezPrijave;

public class MaliPaket3Otroci {
	private WebDriver driver;
	public MaliPaket3Otroci (WebDriver driver) {
		this.driver = driver;
	}
	
	public void maliPaket3Otroci() throws InterruptedException {
		
		Date initialDate = Calendar.getInstance().getTime();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(initialDate);
        calendar.add(Calendar.DAY_OF_MONTH, +1); //add 1 day (next day)
        Date toYoungDate = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("d.M.yyyy");
        String nextDay = dateFormat.format(toYoungDate);
        System.out.println("Next day is: " + nextDay);
        
		Funkcije2 f = new Funkcije2(driver);
		
		Prijave2 p = new Prijave2(driver);
		
		Miscellaneous m = new Miscellaneous(driver);
		
		PersonalInfoBrezPrijave pr = new PersonalInfoBrezPrijave(driver);
		
		String trr = "SI56 0234 3191 0006 381";
		
		int placiloCelotnePremije = 1;
		
		//Iffy xpath's
		String finalPrice = "//ul/li[2]/h4";
		
		//does users tax number have triglav complete?
		int triglavComplete = 1; //1-yes 0-no
		
		//PODATKI OTROK:
		
		String toKeep1 = "Mali paket";
		String toKeep2 = "Veliki paket";
		String toKeep3 = "Comfort paket";
		
		String[] imeOtroka = {"Janez.jr", "Milka", "Jošt"};
		
		String[] priimOtroka = {"Janezson", "die.KUH", "Cheese"};
		
		String[] birthDateOt = {"01.12.2020", "01.12.2021", "01.12.2022"};
		
		String[] taxNumOt = {"88054063", "88112233", "88214532"};
		
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
		
		driver.findElement(By.className("submitButton")).click();
		
		//Prvi korak
		wait.until(ExpectedConditions.urlToBe("https://skleni-qa.triglav.si/isklepanje/nezgoda/nezgodno_zavarovanje_otrok_in_mladih_1"));
		
		String defDateInsStart = driver.findElement(By.id("startDate")).getAttribute("value");
		System.out.println("Actual: " + defDateInsStart);
		
		Assert.assertEquals(defDateInsStart, nextDay);
		
		driver.findElement(By.id("numPartiesInput")).clear();
		driver.findElement(By.id("numPartiesInput")).sendKeys("2");
		driver.findElement(By.xpath("//*[@id = 'number-children-bonus-info'] [@class = 'validation-success ux-hide']"));
		driver.findElement(By.className("inputNumber-plus")).click();
		driver.findElement(By.xpath("//*[@id = 'number-children-bonus-info'] [@class = 'validation-success']"));
		
		f.ScrollInKlik2(By.className("submitButton"));
		
		wait.until(ExpectedConditions.urlToBe("https://skleni-qa.triglav.si/isklepanje/nezgoda/nezgodno_zavarovanje_otrok_in_mladih_2"));
		
		f.ScrollInKlik2(By.xpath("//*[@id = 'btnPackage"+paket+"']"));
		
		driver.findElement(By.className("packages-warningMessage"));
		
		f.ScrollInKlik2(By.className("submitButton"));
		
		wait.until(ExpectedConditions.urlToBe("https://skleni-qa.triglav.si/isklepanje/nezgoda/nezgodno_zavarovanje_otrok_in_mladih_3"));
		
		pr.BrezPrijave();
		
		for(int i = 0; i <= 2; i++) {
			f.ScrollInKlik2(By.id("add-child"));
			driver.findElement(By.id("name")).sendKeys(imeOtroka[i]);
			driver.findElement(By.id("surname")).sendKeys(priimOtroka[i]);
			driver.findElement(By.id("birthday")).sendKeys(birthDateOt[i]);
			driver.findElement(By.id("taxnum")).sendKeys(taxNumOt[i]);
			f.ScrollInKlik2(By.xpath("(//*[@class = 'radio--primary__button'])["+spolOt[i]+"]"));
			if (resOt[i] == 1) {
				f.ScrollInKlik2(By.xpath("//*[@for = 'child-address']"));
				driver.findElement(By.id("childMemberAddModal")).sendKeys(Keys.PAGE_DOWN, Keys.PAGE_DOWN);
				driver.findElement(By.id("addressSearch.post_and_place")).sendKeys(postRes[i]);
				Thread.sleep(2000);
				driver.findElement(By.id("addressSearch.post_and_place")).sendKeys(Keys.ARROW_DOWN);
				Thread.sleep(2000);
				driver.findElement(By.id("addressSearch.post_and_place")).sendKeys(Keys.RETURN);
				Thread.sleep(2000);
				for (int n = 1; n <= choosingStreet[i]; n++) {
					driver.findElement(By.id("addressSearch.street")).sendKeys(Keys.ARROW_DOWN);
					if (n == choosingStreet[i]) {
						driver.findElement(By.id("addressSearch.street")).sendKeys(Keys.RETURN);
					}
				}
				Thread.sleep(2000);
				for (int n = 1; n <= choosingHouseNum[i]; n++) {
					driver.findElement(By.id("addressSearch.hnr")).sendKeys(Keys.ARROW_DOWN);
					if (n == choosingHouseNum[i]) {
						driver.findElement(By.id("addressSearch.hnr")).sendKeys(Keys.RETURN);
					}
				}
			}
			if (sportOt[i] == 1) {
				f.ScrollInKlik2(By.xpath("(//*[@class = 'radio--secondary__button'])[2]"));
			}
			f.ScrollInKlik2(By.id("addInsurerForm"));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("spinner-wrapper")));
		}
		//checking package names
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
		}
		
		String child1 = driver.findElement(By.xpath("(//*[@class = 'storedInfoCalculation-itemDataValue'])[2]")).getText();
		String child2 = driver.findElement(By.xpath("(//*[@class = 'storedInfoCalculation-itemDataValue'])[8]")).getText();
		Thread.sleep(1000);
		String child3 = driver.findElement(By.xpath("(//*[@class = 'storedInfoCalculation-itemDataValue'])[14]")).getText();
		Assert.assertEquals(child1, "Mali paket");
		Assert.assertEquals(child2, "Veliki paket");
		Assert.assertEquals(child3, "Comfort paket");
		
		driver.get("https://skleni-qa.triglav.si/isklepanje/nezgoda/nezgodno_zavarovanje_otrok_in_mladih_2");
		driver.findElement(By.id("changePackageError"));
		driver.get("https://skleni-qa.triglav.si/isklepanje/nezgoda/nezgodno_zavarovanje_otrok_in_mladih_3");
		
		f.ScrollInKlik2(By.className("submitButton"));
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
		try {
			f.ScrollInKlik2(By.xpath("//*[@for = 'alreadyInsuredLastYearConfirmPoSkadenciSwitch']"));
			System.out.println("There are people with same insurance already");
			f.ScrollInKlik2(By.className("submitButton"));
		} catch (NoSuchElementException | StaleElementReferenceException e) {
			System.out.println("There are no same persons with same insurance");
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		wait.until(ExpectedConditions.urlToBe("https://skleni-qa.triglav.si/isklepanje/nezgoda/nezgodno_zavarovanje_otrok_in_mladih_4"));
		if(triglavComplete == 1) {
			driver.findElement(By.className("icon-checked-circle"));
		} else {
			System.out.println("Checking for Triglav Complete at page_4 is: OFF ");
		}
		String pkg1 = driver.findElement(By.xpath("(//*[@class = 'package-selected-multipleItems'])[1]")).getText();
		String pkg2 = driver.findElement(By.xpath("(//*[@class = 'package-selected-multipleItems'])[2]")).getText();
		String pkg3 = driver.findElement(By.xpath("(//*[@class = 'package-selected-multipleItems'])[3]")).getText();
		//System.out.println(pkg1);
        String pkgg1 = pkg1.split(toKeep1)[0] + toKeep1;
        String pkgg2 = pkg2.split(toKeep2)[0] + toKeep2;
        String pkgg3 = pkg3.split(toKeep3)[0] + toKeep3;
		Assert.assertEquals(pkgg1, child1);
		Assert.assertEquals(pkgg2, child2);
		Assert.assertEquals(pkgg3, child3);
		
		//getting price from step_4
		String sumComparePrice = driver.findElement(By.xpath("//*[@class='informativePremiumPrice']")).getText();
		String sumComparePriceOn = sumComparePrice.replaceAll("[^0-9,]", "");
		sumComparePriceOn = sumComparePriceOn.replace(',', '.');
		Float sumBefore = Float.valueOf(sumComparePriceOn);
		System.out.println("Before value: ");
		System.out.println(sumBefore);
		
		f.ScrollInKlik2(By.id("btnSaveInfoCalc"));
		wait.until(ExpectedConditions.elementToBeClickable(By.id("email")));
		driver.navigate().back();
		wait.until(ExpectedConditions.urlToBe("https://skleni-qa.triglav.si/isklepanje/nezgoda/nezgodno_zavarovanje_otrok_in_mladih_4"));
		
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
		
		if (placiloCelotnePremije == 1) {
			driver.findElement(By.xpath("(//*[contains(@class, 'radio--secondary__button')])[1]")).click();
			driver.findElement(By.xpath("(//*[@class = 'radio-quaternary__button'])[1]")).click();
			f.ScrollInKlik2(By.xpath("//*[@for = 'soglasam-s-pogoji-celotna-premija']"));
		}
		else {
			f.ScrollInKlik2(By.xpath("(//*[contains(@class, 'radio--secondary__button')])[2]"));
			driver.findElement(By.id("paymentOptions.manualTrrNumber")).sendKeys(trr);
			f.ScrollInKlik2(By.xpath("(//*[contains(@for, 'soglasam-s-pogoji')])[2]"));
			driver.findElement(By.xpath("(//*[@class = 'radio-quaternary__button'])[3]")).click();
		}
		
		f.ScrollInKlik2(By.xpath("//*[@value = 'simulatePayment']"));
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@class, 'intro-title-green') and text() = 'Hvala!']")));
		
		m.AnketaZadnjiKorak();
	}
}
