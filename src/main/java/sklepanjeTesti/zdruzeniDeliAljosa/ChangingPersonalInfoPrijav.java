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

public class ChangingPersonalInfoPrijav {
	private WebDriver driver;

	public ChangingPersonalInfoPrijav(WebDriver driver) {
		this.driver = driver;
	}

	public void changePersonalInfo() throws InterruptedException {

		Funkcije2 f = new Funkcije2(driver);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		String eMail = "zkpr.test@triglav.si";
		String koda = "9999";
		String telNum = "+38641239778";
		String spremDavcna = "48322245";

		String osIme = "Johnov Sin";
		String osPriim = "Johnsson";
		String osRojst = "09.09.1999";
		int spol = 1; // 2-Ž ; 1-M
		String posta = "9000";
		String houseNum = "1";

		// If changes are 1. Then it will change user info
		WebElement spremenIme = driver.findElement(By.id("policyHolderUI.name"));
		WebElement spremenPriim = driver.findElement(By.id("policyHolderUI.surname"));
		WebElement spremenRojst = driver.findElement(By.id("policyHolderUI.birthday"));

		spremenIme.clear();
		spremenIme.sendKeys(osIme);

		spremenPriim.clear();
		spremenPriim.sendKeys(osPriim);

		spremenRojst.clear();
		spremenRojst.sendKeys(osRojst);

		// changing tax number info, so i dont spam my other tax number
		f.ScrollToElement(By.id("policyHolderUI.taxnum"));
		driver.findElement(By.id("policyHolderUI.taxnum")).clear();
		driver.findElement(By.id("policyHolderUI.taxnum")).sendKeys(spremDavcna);

		// Changing other stuff

		f.ScrollInKlik2(By.xpath("(//*[@class = 'radio--primary__button'])['" + spol + "']"));

		driver.findElement(By.id("policyHolderUI.addressSearch.post_and_place")).clear();
		driver.findElement(By.id("policyHolderUI.addressSearch.post_and_place")).sendKeys(posta); // Holders Postal Num.
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("ui-id-1")));
		//Thread.sleep(2000); // i can't figure out other way :( ; i figured it out^
		driver.findElement(By.id("policyHolderUI.addressSearch.post_and_place")).sendKeys(Keys.ARROW_DOWN, Keys.RETURN);
		f.ScrollInKlik2(By.id("policyHolderUI.addressSearch.street"));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("ui-id-2")));
		for (int i = 1; i <= 5; i++) {
			if (i < 5) {
				driver.findElement(By.id("policyHolderUI.addressSearch.street")).sendKeys(Keys.ARROW_DOWN);
			} else {
				driver.findElement(By.id("policyHolderUI.addressSearch.street")).sendKeys(Keys.ARROW_DOWN, Keys.RETURN);
			}
		}
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("ui-id-3")));
		driver.findElement(By.id("policyHolderUI.addressSearch.hnr")).sendKeys(houseNum, Keys.RETURN);

		// Try changing email from the one that was pulled from iTriglav3 account
		f.ScrollInKlik2(By.className("no-underlineP-link"));
		driver.findElement(By.id("unvalidatedEmail")).sendKeys(eMail);
		driver.findElement(By.id("btnPosliValidacijskoKodoNaEmail")).click();
		driver.findElement(By.id("emailValidationString")).sendKeys(koda);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("btnCheckValidacijskaKodaZaEmail")));
		f.ScrollInKlik2(By.id("btnCheckValidacijskaKodaZaEmail"));
		// Try changing Tel number from the one that was pulled from iTriglav3 account
		driver.findElement(By.id("policyHolderUI.phoneNumber")).clear();
		driver.findElement(By.id("policyHolderUI.phoneNumber")).sendKeys(telNum);

		f.ScrollToElement(By.xpath("//*[@for = 'confirmPersonalDataProcessingAproval']"));
		Actions builder = new Actions(driver); // clicking away from link, so it doesen't open PDF
		WebElement element = driver.findElement(By.xpath("//*[@for = 'confirmPersonalDataProcessingAproval']"));
		Action action = builder.moveToElement(element, -100, 0).click().build();
		action.perform();

		f.ScrollInKlik2(By.className("submitButton"));

	}
}
