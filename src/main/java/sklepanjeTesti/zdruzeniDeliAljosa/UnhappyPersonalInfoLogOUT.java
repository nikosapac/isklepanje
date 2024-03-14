package sklepanjeTesti.zdruzeniDeliAljosa;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import sklepanjeTesti.Funkcije2;

public class UnhappyPersonalInfoLogOUT {
	private WebDriver driver;

	public UnhappyPersonalInfoLogOUT(WebDriver driver) {
		this.driver = driver;
	}

	public void Unhappy() throws InterruptedException {

		Funkcije2 f = new Funkcije2(driver);
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.elementToBeClickable(By.className("submitButton")));

		String telNum = "000000000";

		// personalne informacije
		String osIme = "MR. Jonh Jr.";
		String osPriim = "Johnsson";
		String osRojst = "09.09.1999";
		String posta = "9000";
		String houseNum = "1";
		String davcna = "88123456";
		String mail = "zkpr.test@triglav.si";
		String verfCode = "9999";
		
		String napVrednost = "Napačna vrednost.";
		String addressErrors = "Preden nadaljujete, pravilno vnesite naslov. Ob vnosu prvih črk v vsako polje se prikaže nabor možnosti. Izberite svojo in jo potrdite.";
		String userErrors = "Podatek je obvezen za nadaljevanje!";
		String telNumErrors = "vnesite vašo mobilno številko.";

		for (int s = 1; s <= 10; s++) {
			WebElement spremenIme = driver.findElement(By.id("policyHolderUI.name"));
			WebElement spremenPriim = driver.findElement(By.id("policyHolderUI.surname"));
			WebElement spremenRojst = driver.findElement(By.id("policyHolderUI.birthday"));
			WebElement taxNum = driver.findElement(By.id("policyHolderUI.taxnum"));
			WebElement spremenPosta = driver.findElement(By.id("policyHolderUI.addressSearch.post_and_place"));
			WebElement ulicaNaselje = driver.findElement(By.id("policyHolderUI.addressSearch.street"));
			WebElement hisnaSt = driver.findElement(By.id("policyHolderUI.addressSearch.hnr"));
			WebElement telSt = driver.findElement(By.id("policyHolderUI.phoneNumber"));
			WebElement blank = driver.findElement(By.xpath("//html"));
			WebElement eMail = driver.findElement(By.id("unvalidatedEmail"));
			WebElement eMailVerf = driver.findElement(By.id("emailValidationString"));
			
			spremenIme.clear();
			if (s != 1) {
				spremenIme.sendKeys(osIme);
			}
			spremenPriim.clear();
			if (s != 2) {
				spremenPriim.sendKeys(osPriim);
			}
			spremenRojst.clear();
			if (s != 3) {
				spremenRojst.sendKeys(osRojst);
			}
			taxNum.clear();
			if (s != 4) {
				taxNum.sendKeys(davcna);
			}
			if (s % 2 == 0) {
				f.ScrollInKlik2(By.xpath("(//*[@class = 'radio--primary__button'])[1]"));
			}	else {
				f.ScrollInKlik2(By.xpath("(//*[@class = 'radio--primary__button'])[2]"));
			}
			
			if (s == 5) {
				f.ScrollInKlik2WebEl(spremenPosta);
				spremenPosta.sendKeys(posta); // Holders Postal Num.
				wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("ui-id-1")));
				spremenPosta.sendKeys(Keys.ARROW_DOWN, Keys.RETURN);
				ulicaNaselje.sendKeys(Keys.ESCAPE);
				blank.click();
			} // f.ScrollInKlik2WebEl(ulicaNaselje);
			if (s == 6) {
				f.ScrollInKlik2WebEl(ulicaNaselje);
				wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("ui-id-2")));
				ulicaNaselje.sendKeys(Keys.ARROW_DOWN, Keys.RETURN); // pressing return on correct option
				hisnaSt.sendKeys(Keys.ESCAPE);
				blank.click();
			} // f.ScrollInKlik2WebEl(hisnaSt);
			if (s == 7) {
				f.ScrollInKlik2WebEl(hisnaSt);
				hisnaSt.sendKeys(houseNum, Keys.RETURN);
			}
			if (s == 1) {
				eMail.sendKeys(mail);
				driver.findElement(By.id("btnPosliValidacijskoKodoNaEmail")).click();
				eMailVerf.sendKeys("4432");
				eMailVerf.sendKeys(Keys.RETURN);
				driver.findElement(By.className("help-block"));
				eMailVerf.clear();
				eMailVerf.sendKeys(verfCode);
				driver.findElement(By.id("btnCheckValidacijskaKodaZaEmail")).click();
			}
			telSt.clear();
			if (s != 7) {
				// f.ScrollInKlik2WebEl(telSt);
				telSt.sendKeys(telNum);
			}

			if (s == 1) {
				f.ScrollToElement(By.xpath("//*[@for = 'informacije']")); // we will only tick needed option, other's will stay unchecked																						
																									
																									
				Actions builder = new Actions(driver); // clicking away from link, so it doesen't open PDF
				WebElement element = driver.findElement(By.xpath("//*[@for = 'informacije']"));
				Action action = builder.moveToElement(element, -200, -10).click().build();
				action.perform();
				// Thread.sleep(5000);
			}
			if (s == 8) {
				spremenIme.clear();
				spremenIme.sendKeys("@%(!)°(!)%@");
			} else if (s == 9) {
				spremenIme.sendKeys("Jože");
			}
			if (s == 9) {
				spremenPriim.clear();
				spremenPriim.sendKeys("@%(!)°(!)%@");
			}

			f.ScrollInKlik2(By.className("submitButton"));

			if (s >= 1 && s <= 3) {
				driver.findElement(By.xpath("//*[text() = '" + userErrors + "']"));
			} else if (s >= 4 && s <= 5) {
				driver.findElement(By.xpath("//*[text() = '" + addressErrors + "']"));
			} else if (s == 7) {
				driver.findElement(By.xpath("//*[contains(text(), '" + telNumErrors + "')]"));
			} else if (s >= 8 && s <= 9) {
				driver.findElement(By.xpath("//*[text() = '" + napVrednost + "']"));
			}
		}
	}
}
