package sklepanjeTesti.sklepanje.NezgodnoOtrokInMladih;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import sklepanjeTesti.Funkcije2;
import sklepanjeTesti.Prijave2;
import sklepanjeTesti.zdruzeniDeliAljosa.PersonalInfoBrezPrijave;

public class MaliPaketSebe {
	private WebDriver driver;
	public MaliPaketSebe (WebDriver driver) {
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
		
		PersonalInfoBrezPrijave pr = new PersonalInfoBrezPrijave(driver);
		
		String trr = "SI56 0234 3191 0006 381";
		
		int placiloCelotnePremije = 1;
		
		Integer onlyNecessery = 0;
		
		//Iffy xpath's
		String finalPrice = "//ul/li[2]/h4";
		
		//does users tax number have triglav complete?
		int triglavComplete = 0; //1-yes 0-no Ni mozno pri zavarovanju ene osebe (cena premajhna)
		
		String paket = "Small";	// Small , Comfort
		
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
		driver.findElement(By.id("numPartiesInput")).sendKeys("1");
		driver.findElement(By.xpath("//*[@id = 'number-children-bonus-info'] [@class = 'validation-success ux-hide']"));
		
		f.ScrollInKlik2(By.className("submitButton"));
		
		wait.until(ExpectedConditions.urlToBe("https://skleni-qa.triglav.si/isklepanje/nezgoda/nezgodno_zavarovanje_otrok_in_mladih_2"));
		
		f.ScrollInKlik2(By.xpath("//*[@id = 'btnPackage"+paket+"']"));
		
		f.ScrollInKlik2(By.className("submitButton"));
		
		wait.until(ExpectedConditions.urlToBe("https://skleni-qa.triglav.si/isklepanje/nezgoda/nezgodno_zavarovanje_otrok_in_mladih_3"));
		
		pr.BrezPrijave(onlyNecessery);
		
		f.ScrollInKlik2(By.xpath("(//*[@class = 'radio--secondary__button'])[2]"));
		
		f.ScrollInKlik2(By.xpath("(//*[@class = 'radio--secondary__button'])[4]"));
		
		f.ScrollInKlik2(By.className("submitButton"));
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
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
		
		driver.findElement(By.id("anketaLink")).click();
		ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(newTab.get(1));
		driver.findElements(By.className("survey__question"));
		driver.close();
		driver.switchTo().window(newTab.get(0));
	}
}
