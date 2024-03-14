package sklepanjeTesti.sklepanje.maleZivali;

import java.time.Duration;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import sklepanjeTesti.Funkcije2;
import sklepanjeTesti.Prijave2;
import sklepanjeTesti.zdruzeniDeliAljosa.CheckingPersInfoFieldsAreNotEmpty;
import sklepanjeTesti.zdruzeniDeliAljosa.CheckingPricePackagesZivali;
import sklepanjeTesti.zdruzeniDeliAljosa.Miscellaneous;
import sklepanjeTesti.zdruzeniDeliAljosa.UnhappyPersonalInfo;

public class UnhapyFlow0DeletingLoggedINUsersCredentials {
	private WebDriver driver;
	public UnhapyFlow0DeletingLoggedINUsersCredentials (WebDriver driver) {
		this.driver = driver;
	}
	public void maleZivaliPrviTest() throws InterruptedException {
		
		//Predpogoj za ta test je da ima uporabnik iTriglav account z dvignjenim tirom, vnesene mora v racunu imeti osebne podatke
		
		
		// iffy stuff:
		String xpathToPackageName = "//li[1]/ul/li/p[1]";	//very iffy xpath (might not work forever)
		String textForAuthVet = "Popust za zdravljenje pri pooblaščenem veterinarju";	//Text might change, so this is also iffy
		String finalPrice = "//ul/li[3]/h4";	//xpath for final price might change (SUM price where you pick payment method)
		String trr = "SI56 0234 3191 0006 381";
		
		// Ali zelite placilo celotne premije sedaj ali obrocno
		int placiloCelotnePremije = 0; 	// 0-ne ; 1-da
		
		// Na koncu ce hocete simulirati tipko nadaljuj za zavarovanje se dodatnega hisnega ljubljencka
		int aliDodatnegaHisLjub = 1;		// 0-ne 1-da
		
		// Ali zelite sodelovati v anketi na koncu
		int zelimSodelovati = 1; // 1-da ; 0-ne
		
		//Ali ima vnešena davčna triglav komplet?
		int triglavKomplet = 1; // 1-yes ; 0-no		To bo preverilo ali so izpisani procenti na desni strani strani
		
		// asistPlus=1 Asistenca evropa ; asistPlus=0 Asistenca Slovenija
		int asistPlus = 0;	//Izberi vrsto dodatnega kritja asistenca
		
		// choose between breed & non-breed
		int pasemska = 3;	// 4-non-breed ; 3-yes-breed
		
		// Kdaj hocete vtipkati stevilko microchipa?
		int microChip = 1; // 0-type in microchip at first step ; 1-type in microchip at later step
		
		// !!!!!WARNING!!!!!! if cat is your animal DO NOT CHOOSE DNAGER=1
		//Choose between dangerous & not dangerous DOG
		int danger = 1; // 0-not dangerous animal ; 1-dangerous animal
		
		// Izberi vrsto zivali
		String Vrsta = "Pes";	// "Pes" & "Mačka"
		
		// 1-small, 2-middle, 3-lagrest packet
		int cP = 1;		// cP = choose packet
		
		// 0-choose_all, 1-choose_first_three, 2-choose_first_two, 3-leave_default, 4-uncheck_default
		int dk = 0;		// dk = dodatna kritja
		
		// 1-yes 2-no
		int PaV = 1;	// PaV = Pet Authorized Veterinarian
		
		String imeZivali = "Milka die KUH";
		String dtRojZiv = "25.10.2023";
		String mikroCip = "123456789012345";
		
		String mail = "aljosa.mlinaric@triglav.si";
		String pass = "Aljosa@0110";
		
		//initializing imported classes
		Funkcije2 f = new Funkcije2(driver);
		Prijave2 p = new Prijave2(driver);
		UnhappyPersonalInfo upi = new UnhappyPersonalInfo(driver);
		CheckingPersInfoFieldsAreNotEmpty cpifame = new CheckingPersInfoFieldsAreNotEmpty(driver);
		CheckingPricePackagesZivali cpp = new CheckingPricePackagesZivali(driver);
		Miscellaneous m = new Miscellaneous(driver);
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		p.SklepanjePrijava();	//LDAP Login
		
		driver.get("https://skleni-qa.triglav.si/isklepanje/zivali/zavarovanje_psov_in_mack");
		
		// https://skleni-qa.triglav.si/isklepanje/zivali/zavarovanje_psov_in_mack - ZAČETNI KORAK
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.elementToBeClickable(By.className("submitButton")));
		m.CheckTwoButtonsNOloginStep1();	//checking two buttons are showing when not logged in
		
		driver.findElement(By.className("btn-primary")).click();
		
		p.ItriglavPrijavaSklepanje2(mail, pass);		//prijava v iTriglav account
		
		Thread.sleep(1000);	//crucial part of code, if deleted wont find correct URL when "Obvescanje site" shows. Otherwise it will work 90% of the time.
		String urlObv = driver.getCurrentUrl();												// Uporabnik mora imeti izkljuceno elek. obv. v accountu
		System.out.println(urlObv);
		if(urlObv.equals("https://skleni-qa.triglav.si/TriglavLogin/e-komuniciranje")){		//ce se sedanji url ujema z tem, se je okno za Elek. Obv. prikazalo
			driver.findElement(By.id("submitButton")).click();
		}	else {
			System.out.println("Okno za aktiviranje Elektronskega Obvescanja se tokrat ni prikazalo");
		}
		
		wait.until(ExpectedConditions.elementToBeClickable(By.className("submitButton")));
		driver.findElement(By.className("submitButton")).click();
		// KORAK 1 - https://skleni-qa.triglav.si/isklepanje/zivali/zavarovanje_psov_in_mack_1
		
		wait.until(ExpectedConditions.urlToBe("https://skleni-qa.triglav.si/isklepanje/zivali/zavarovanje_psov_in_mack_1"));
	    	//Clicking back button
		f.ScrollInKlik2(By.xpath("//*[text() = 'Nazaj']"));
		wait.until(ExpectedConditions.urlToBe("https://skleni-qa.triglav.si/isklepanje/zivali/zavarovanje_psov_in_mack"));
		m.CheckOneButtonShowsLogINstep1();	//checking if only one button is showing when logged in
		f.ScrollInKlik2(By.className("submitButton"));
		wait.until(ExpectedConditions.urlToBe("https://skleni-qa.triglav.si/isklepanje/zivali/zavarovanje_psov_in_mack_1"));
		
		driver.findElement(By.className("submitButton"));
		isDateElementNotClickable(driver);	//checking date element is not clickable
		
			//Typing in animal info
		String yesNO = null;
		int verydangerous = 0;
		int zzz = 0;
		if (pasemska == 3) {
			zzz = 6;
		} else {
			zzz = 5;
		}
		
		for(int z = 0; z <= zzz; z++) {
			
			if (z == 6) {
				driver.findElement(By.id("domesticUseOnlyError"));
				driver.findElement(By.xpath("//*[text() = 'Zapri']")).click();
			}
			
			if (z == 1) {
				f.ScrollInKlik2(By.xpath("(//*[@class = 'select2-selection__arrow'])[1]"));
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='"+Vrsta+"']")));
				driver.findElement(By.xpath("//*[text()='"+Vrsta+"']")).click();
			}	
		if ( z >= 2 && z <= zzz )	{
			driver.findElement(By.id("pet.name")).clear();
			if (z != 2) {
				driver.findElement(By.id("pet.name")).sendKeys(imeZivali);
			}
			driver.findElement(By.id("pet.birthDate")).clear();
			if (z != 3)	{
				driver.findElement(By.id("pet.birthDate")).sendKeys(dtRojZiv);
			}	
		f.ScrollInKlik2(By.xpath("(//*[@class='radio--primary__button'])[1]"));
			
			
		if (microChip == 0) {
			driver.findElement(By.id("pet.microchipNum")).sendKeys(mikroCip);
		}	
		
		f.ScrollInKlik2(By.xpath("(//*[@class='radio--primary__button'])["+pasemska+"]"));
			
			int zz = 4;
			//choosing breed & dangerous breed
				if (pasemska == 3) {
					driver.findElement(By.id("select2-petdogBreed-container")).click();
					driver.findElement(By.xpath("//*[text()='Izberite pasmo psa']")).click();
					zz = 5;
						if (z != 4 && danger == 0) {
							verydangerous = 0; //will need later
							driver.findElement(By.id("select2-petdogBreed-container")).click();
							driver.findElement(By.xpath("//*[text()='Afganistanski hrt']")).click();
							}
						else if (z != 4 && danger == 1) {
							verydangerous = 1; //will need later
							driver.findElement(By.id("select2-petdogBreed-container")).click();
							driver.findElement(By.xpath("//*[text()='Doberman']")).click();
						}
					}
			
				if (z == zz)	{			//authorized veterinarian
					if (PaV == 1) {			//yesNO can be yes / no
						yesNO = "yes";
					} else {
						yesNO = "no";
					}
					f.ScrollInKlik2(By.xpath("//*[@for='pet.authorizedVet-"+yesNO+"']"));
				}
			
			if (z >= zz) {
				f.ScrollToElement(By.xpath("(//*[@class='radio--primary__button'])["+pasemska+"]"));
				driver.findElement(By.xpath("//*[@for = 'pet.domesticUseOnly']")).click();
			}
			
		}
			//Prerverjanje opozoril, ki se prikazejo
		f.ScrollInKlik2(By.className("submitButton"));
			switch (z) {
				case 0:
					driver.findElement(By.xpath("//*[text() = 'Izberite vrsto živali.']"));
					break;
				case 2:
					driver.findElement(By.xpath("//*[text() = 'Vpišite ime živali.']"));
					driver.findElement(By.xpath("//*[text() = 'Izberite, ali želite 10 % popust in boste žival zdravili pri pooblaščenem veterinarju.']"));
				break;
				case 3:
					driver.findElement(By.xpath("//*[text() = 'Vpišite datum rojstva živali.']"));
					break;
				case 4:
					if (pasemska == 3) {
						driver.findElement(By.xpath("//*[text() = 'Izberite pasmo psa.']"));
						break;
					}
				default:
					break;
			}
		}	
		
		
		
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
		
				//klikanje ustreznih dodatnih zavarovanj, (eno manj, ce pes nevarna pasma)
			for (int i = 2; i<=4-dk; i++) {	
				if (verydangerous == 1) {
					if (i == 4) continue;
				}
				f.ScrollInKlik2(By.xpath("(//*[@class='checkbox-tertiary-icon'])["+i+"]"));			//ticking all the options; incl. animal owner responsibility (not present if you choose i.e. Doberman dog)
			}
			if(dk == 4) {
				f.ScrollInKlik2(By.xpath("(//*[@class='checkbox-tertiary-icon'])[1]"));				//untick box 1 if dk 4
			}
				//ce hocemo izbrati asistenco plus
			if(asistPlus == 1) {
				f.ScrollInKlik2(By.xpath("//*[@for='pet.asistenceType-no']"));
			}
			
		f.ScrollInKlik2(By.className("submitButton"));
			
			//ce je mikrochip na drugi strani nastavljen na 1 se ga tu vnese
			if (microChip==1) {
					driver.findElement(By.id("pet.microchipNum"));
					driver.findElement(By.id("pet.microchipNum")).sendKeys(mikroCip);
					driver.findElement(By.id("microchipSubmit")).click();
			}
			
		//personal info
		
		//checking if personal info is already typed in
		cpifame.Checking();	
		
		//Typing in personal info
		upi.Unhappy();	
				
		//going through sumedup numbers 
			wait.until(ExpectedConditions.urlToBe("https://skleni-qa.triglav.si/isklepanje/zivali/zavarovanje_psov_in_mack_4"));
			f.ScrollInKlik2(By.className("btn-prev"));
			wait.until(ExpectedConditions.urlToBe("https://skleni-qa.triglav.si/isklepanje/zivali/zavarovanje_psov_in_mack_3"));
			f.ScrollInKlik2(By.className("submitButton"));
			wait.until(ExpectedConditions.urlToBe("https://skleni-qa.triglav.si/isklepanje/zivali/zavarovanje_psov_in_mack_4"));
			
			if (triglavKomplet == 1) {
				driver.findElement(By.xpath("(//*[@class='tKompletPercent'])[1]"));
			}	
			String actualPackageText = driver.findElement(By.xpath(xpathToPackageName)).getText();
			System.out.println(actualPackageText);
			Assert.assertEquals(actualPackageText, packet);
				// checking (pooblaščeni veterinar)
				if (yesNO == "yes") {
					driver.findElement(By.xpath("//*[contains(@class, 'discount-title-wrap-mobile') and contains(text(), '"+textForAuthVet+"')]"));
				}
				
				// checking elements (dodatna zavarovanja) in preveri razne stvari glede na nastavitve zgornjih integerov
				switch (dk) {
				case 0:
					driver.findElement(By.xpath("//*[contains(text(), 'Stroški iskanja izginule ali ukradene živali')]"));
					if (asistPlus == 1) {
						driver.findElement(By.xpath("//*[contains(text(), 'Asistenca (Evropa)')]"));
					}
					else {
						driver.findElement(By.xpath("//*[contains(text(), 'Asistenca (Slovenija)')]"));
					}
					if (danger == 0) {
						driver.findElement(By.xpath("//*[contains(text(), 'Odgovornost lastnika živali')]"));
					}
					driver.findElement(By.xpath("//*[contains(text(), 'Pogin živali')]"));				
					break;
				case 1:
					driver.findElement(By.xpath("//*[contains(text(), 'Asistenca (Slovenija)')]"));
					if (danger == 0) {
						driver.findElement(By.xpath("//*[contains(text(), 'Odgovornost lastnika živali')]"));
					}
					driver.findElement(By.xpath("//*[contains(text(), 'Pogin živali')]"));	
					break;
				case 3:
					if (danger == 0) {
					driver.findElement(By.xpath("//*[contains(text(), 'Odgovornost lastnika živali')]"));
					}
					driver.findElement(By.xpath("//*[contains(text(), 'Pogin živali')]"));
					break;
				}
			
				//Checking first price
			String sumComparePrice = driver.findElement(By.xpath("//*[@class='informativePremiumPrice']")).getText();
			String sumComparePriceOn = sumComparePrice.replaceAll("[^0-9,]", "");
			sumComparePriceOn = sumComparePriceOn.replace(',', '.');
			Float sumBefore = Float.valueOf(sumComparePriceOn);
			
			f.ScrollInKlik2(By.id("btnSaveInfoCalc"));
			driver.findElement(By.xpath("//*[text() = 'Informativni izračun je shranjen v i.triglav račun.']"));
			
			f.ScrollInKlik2(By.xpath("//*[@value = 'SPREMENI_PAKET']"));
			wait.until(ExpectedConditions.urlToBe("https://skleni-qa.triglav.si/isklepanje/zivali/zavarovanje_psov_in_mack_2"));
			cpp.checkingPriceChange(cP, dk, verydangerous);
			f.ScrollInKlik2(By.className("submitButton"));
			
			for (int i = 1; i <= 3; i++) {
					if (i == 1) {
						f.ScrollInKlik2(By.xpath("//*[@for = 'info-zavarovalni-paket-mobile']"));
					} else if (i == 2) {
						f.ScrollInKlik2(By.xpath("//*[@for = 'info-zavarovalni-paket-mobile']"));
						f.ScrollInKlik2(By.xpath("//*[@for = 'zavarovalnimi-pogoji']"));
					} else {
						f.ScrollInKlik2(By.xpath("//*[@for = 'info-zavarovalni-paket-mobile']"));
					}
				f.ScrollInKlik2(By.className("submitButton"));
			}
			
				//Simulate Payment
			wait.until(ExpectedConditions.urlToBe("https://skleni-qa.triglav.si/isklepanje/zivali/zavarovanje_psov_in_mack_5"));
				//Checking second price
			String sumFinalPrice = driver.findElement(By.xpath(finalPrice)).getText();	//Getting text value
			String sumFinalPriceOn = sumFinalPrice.replaceAll("[^0-9,]", "");	//converting to only numbers
			sumFinalPriceOn = sumFinalPriceOn.replace(',', '.');	//replacing , with .
			Float sumAfter = Float.valueOf(sumFinalPriceOn);	// saving finalPrice xpath text value as float value
				//Comparing price
			Assert.assertEquals(sumBefore, sumAfter);
			
				// Placilo na obroke, ali takoj
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
			
				//Waiting till green text "Hvala!"
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@class, 'intro-title-green') and text() = 'Hvala!']")));
				
				//Zelim sodelovati v anketi
			if (zelimSodelovati == 1) {
				driver.findElement(By.id("anketaLink")).click();
				ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
				driver.switchTo().window(newTab.get(1));
				driver.findElements(By.className("survey__question"));
				driver.close();
				driver.switchTo().window(newTab.get(0));
			}
			
				//Ali hocete zavarovati se dodatnega hisnega ljubljencka
			if (aliDodatnegaHisLjub == 1) {
				driver.findElement(By.xpath("//*[@href = 'zavarovanje_psov_in_mack']")).click();
				wait.until(ExpectedConditions.urlToBe("https://skleni-qa.triglav.si/isklepanje/zivali/zavarovanje_psov_in_mack"));
			}
			
		}
	
	public boolean isDateElementNotClickable(WebDriver driver) {
		try {
    		WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(2)); // 2 seconds timeout
    		shortWait.until(ExpectedConditions.elementToBeClickable(By.className("form-input")));
    		return false; // Element is clickable
    	} catch (Exception e) {
    		return true; // Element is not clickable
    	}
	}
				
	}
