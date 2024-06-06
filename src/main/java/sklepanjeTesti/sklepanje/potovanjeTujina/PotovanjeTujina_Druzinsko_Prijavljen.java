package sklepanjeTesti.sklepanje.potovanjeTujina;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import sklepanjeTesti.Funkcije2;
import sklepanjeTesti.Prijave2;

// HAPPPY FLOW - Test prijavlene osebe, ki sklene druzinsko zavarovanje ( trajanje 8 dni, Paket B , zavarovanec se bo v tujini ukvarjal z rizičnimi športi... DA)

public class PotovanjeTujina_Druzinsko_Prijavljen {
	private WebDriver driver;

	public PotovanjeTujina_Druzinsko_Prijavljen(WebDriver driver) {
		this.driver = driver;
	}

	public void PotovanjeTujinaDruzinskoPrijavljenTest() throws InterruptedException {

		Funkcije2 f = new Funkcije2(driver);

		Prijave2 p = new Prijave2(driver);

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		String ime = "Franc";
		String priimek = "Novakovic";
		String datumRojstva = "18.08.1998";
		String davcna = "19553170";
		String postnaStevilka = "9000";
		String telefonska = "0300000001";
		String ulica = "Partizanska ulica";
		String hisnaStevilka = "1";
		String email = "zkpr.test@triglav.si";
		String validacijskaKoda = "9999";

		String imeDruzisnkegaClana = "Janez";
		String priimekDruzisnkegaClana = "Novak";
		String datumRojstvaDruzinskegaClana = "28.10.1998";

		String naknadnaprijavaEmail = "jan.volovsek@triglav.si";
		String naknadnaprijavaGeslo = "Jan@test_1";

		// PRIJAVA

		p.SklepanjePrijava();

		// KORAK 1
		// ("https://skleni-qa.triglav.si/isklepanje/tujina/potovanje_zavarovanje")

		driver.get("https://skleni-qa.triglav.si/isklepanje/tujina/potovanje_zavarovanje");

		wait.until(ExpectedConditions.elementToBeClickable(By.className("intro-btn")));

		driver.findElement(By.className("intro-btn")).click();

		// PRIJAVA

		driver.findElement(By.id("email")).sendKeys(naknadnaprijavaEmail);

		Thread.sleep(1000);

		driver.findElement(By.id("Password")).sendKeys(naknadnaprijavaGeslo);

		driver.findElement(By.id("next")).click();

		// KORAK 2
		// ("https://skleni-qa.triglav.si/isklepanje/tujina/potovanje_zavarovanje")

		wait.until(ExpectedConditions.urlToBe("https://skleni-qa.triglav.si/isklepanje/tujina/potovanje_zavarovanje"));

		int viewportHeight = ((Long) ((JavascriptExecutor) driver).executeScript("return window.innerHeight"))
				.intValue();
		int halfViewport = viewportHeight / 2;

		WebElement posamicno = driver.findElement(By.xpath("(//*[@class='insuranceOptionsSelect-itemTitle'])[1]"));
		int posamicno_Position = posamicno.getLocation().getY();
		((JavascriptExecutor) driver).executeScript("window.scroll(0, " + (posamicno_Position - halfViewport) + ");");
		Thread.sleep(1000);
		((JavascriptExecutor) driver).executeScript("window.scroll(0,400)");

		driver.findElement(By.id("turisticnoPolicy.insuranceIntro.zptInsuranceType2")).click();

		f.ScrollInKlik2(By.className("submitButton"));

		// KORAK 3
		// ("https://skleni-qa.triglav.si/isklepanje/tujina/potovanje_zavarovanje_1")

		wait.until(
				ExpectedConditions.urlToBe("https://skleni-qa.triglav.si/isklepanje/tujina/potovanje_zavarovanje_1"));

		Thread.sleep(200);

		driver.findElement(By.className("select2-selection__arrow")).click();

		Thread.sleep(400);

		driver.findElement(By.xpath("(//*[@class='select2-results__option'])[4]")).click();

		f.ScrollInKlik2(By.className("submitButton"));

		Thread.sleep(400);

		// KORAK 4
		// ("https://skleni-qa.triglav.si/isklepanje/tujina/potovanje_zavarovanje_2")

		wait.until(
				ExpectedConditions.urlToBe("https://skleni-qa.triglav.si/isklepanje/tujina/potovanje_zavarovanje_2"));

		f.ScrollInKlik2(By.className("inactiveText"));

		f.ScrollInKlik2(By.id("btnPackageBig"));

		f.ScrollInKlik2(By.className("submitButton"));

		// KORAK 5
		// ("https://skleni-qa.triglav.si/isklepanje/tujina/potovanje_zavarovanje_3")

		wait.until(
				ExpectedConditions.urlToBe("https://skleni-qa.triglav.si/isklepanje/tujina/potovanje_zavarovanje_3"));

		driver.findElement(By.id("policyHolderUI.name")).clear();

		driver.findElement(By.id("policyHolderUI.surname")).clear();

		WebElement imeSklenitelja = driver.findElement(By.id("policyHolderUI.name"));
		int imeSklenitelja_Position = imeSklenitelja.getLocation().getY();
		((JavascriptExecutor) driver)
				.executeScript("window.scroll(0, " + (imeSklenitelja_Position - halfViewport) + ");");
		Thread.sleep(1000);
		driver.findElement(By.id("policyHolderUI.name")).sendKeys(ime);

		WebElement priimekSklenitelja = driver.findElement(By.id("policyHolderUI.surname"));
		int priimekSklenitelja_Position = priimekSklenitelja.getLocation().getY();
		((JavascriptExecutor) driver)
				.executeScript("window.scroll(0, " + (priimekSklenitelja_Position - halfViewport) + ");");
		Thread.sleep(1000);
		driver.findElement(By.id("policyHolderUI.surname")).sendKeys(priimek);

		WebElement datumRojstvaSklenitelja = driver.findElement(By.id("policyHolderUI.birthday"));
		int datumRojstvaSklenitelja_Position = datumRojstvaSklenitelja.getLocation().getY();
		((JavascriptExecutor) driver)
				.executeScript("window.scroll(0, " + (datumRojstvaSklenitelja_Position - halfViewport) + ");");
		Thread.sleep(1000);
		driver.findElement(By.id("policyHolderUI.birthday")).sendKeys(datumRojstva);

		WebElement davcnaSklenitelja = driver.findElement(By.id("policyHolderUI.taxnum"));
		int davcnaSklenitelja_Position = davcnaSklenitelja.getLocation().getY();
		((JavascriptExecutor) driver)
				.executeScript("window.scroll(0, " + (davcnaSklenitelja_Position - halfViewport) + ");");
		Thread.sleep(1000);
		driver.findElement(By.id("policyHolderUI.taxnum")).sendKeys(davcna);
		Thread.sleep(1000);

		WebElement podatkispol = driver.findElement(By.id("female"));
		int podatkispol_Position = podatkispol.getLocation().getY();
		((JavascriptExecutor) driver).executeScript("window.scroll(0, " + (podatkispol_Position - halfViewport) + ");");
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//*[@class='radio--primary__button'])[2]")).click();
		Thread.sleep(1000);

		WebElement postaNaslov = driver.findElement(By.id("policyHolderUI.addressSearch.post_and_place"));
		int postaNaslov_Position = postaNaslov.getLocation().getY();
		((JavascriptExecutor) driver).executeScript("window.scroll(0, " + (postaNaslov_Position - halfViewport) + ");");
		Thread.sleep(1000);
		driver.findElement(By.id("policyHolderUI.addressSearch.post_and_place")).sendKeys(postnaStevilka);
		Thread.sleep(2000);
		f.implicitWait(10);
		driver.findElement(By.id("policyHolderUI.addressSearch.post_and_place")).sendKeys(Keys.DOWN, Keys.ENTER);
		Thread.sleep(1000);

		WebElement ulicaNaslov = driver.findElement(By.id("policyHolderUI.addressSearch.street"));
		int ulicaNaslov_Position = ulicaNaslov.getLocation().getY();
		((JavascriptExecutor) driver).executeScript("window.scroll(0, " + (ulicaNaslov_Position - halfViewport) + ");");
		Thread.sleep(3000);
		f.implicitWait(10);
		driver.findElement(By.id("policyHolderUI.addressSearch.street")).sendKeys(ulica);
		Thread.sleep(3000);
		f.implicitWait(10);
		driver.findElement(By.id("policyHolderUI.addressSearch.street")).sendKeys(Keys.DOWN, Keys.ENTER);
		Thread.sleep(3000);

		f.implicitWait(10);

		WebElement hisnaStevilkaNaslov = driver.findElement(By.id("policyHolderUI.addressSearch.hnr"));
		int hisnaStevilkaNaslov_Position = hisnaStevilkaNaslov.getLocation().getY();
		((JavascriptExecutor) driver)
				.executeScript("window.scroll(0, " + (hisnaStevilkaNaslov_Position - halfViewport) + ");");
		Thread.sleep(1000);
		f.implicitWait(10);
		driver.findElement(By.id("policyHolderUI.addressSearch.hnr")).sendKeys(hisnaStevilka);
		Thread.sleep(2000);
		f.implicitWait(10);
		driver.findElement(By.id("policyHolderUI.addressSearch.hnr")).sendKeys(Keys.DOWN, Keys.ENTER);
		Thread.sleep(2000);

		WebElement podatkitelefonska = driver.findElement(By.id("policyHolderUI.phoneNumber"));
		int podatkitelefonska_Position = podatkitelefonska.getLocation().getY();
		((JavascriptExecutor) driver)
				.executeScript("window.scroll(0, " + (podatkitelefonska_Position - halfViewport) + ");");
		Thread.sleep(1000);
		driver.findElement(By.id("policyHolderUI.phoneNumber")).sendKeys(telefonska);
		Thread.sleep(1000);
		
		WebElement potrjujemvse = driver.findElement(By.xpath("//label[@for='potrjujem-vse']"));
		int potrjujemvse_Position = potrjujemvse.getLocation().getY();
		((JavascriptExecutor) driver)
				.executeScript("window.scroll(0, " + (potrjujemvse_Position - halfViewport) + ");");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//label[@for='potrjujem-vse']")).click();
		Thread.sleep(1000);
		
		f.ScrollInKlik2(By.xpath("(//*[@class='radio--secondary__button'])[2]"));
		
		// Počaka in preveri, če se pojavi besedilo
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement txt = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Ukvarjanje z rizičnimi športi pomeni povečano nevarnost za morebitne škodne dogodke, zato se premija poviša za 50 %.']")));

        if (txt.isDisplayed()) {
        	f.ScrollInKlik2(By.id("getInsurerModal"));
        } else {
            throw new RuntimeException("Ni se izpisal text \"Ukvarjanje z rizičnimi športi pomeni povečano nevarnost za morebitne škodne dogodke, zato se premija poviša za 50 %.\" na strani: https://skleni-qa.triglav.si/isklepanje/tujina/potovanje_zavarovanje_3");
        }

	
		// DODAJANJE DRUZINSKEGA CLANA

		// Open the modal

		driver.findElement(By.id("name")).sendKeys(imeDruzisnkegaClana);

		Thread.sleep(500);

		driver.findElement(By.id("surname")).sendKeys(priimekDruzisnkegaClana);

		Thread.sleep(500);

		driver.findElement(By.id("birthday")).sendKeys(datumRojstvaDruzinskegaClana);

		Thread.sleep(500);

		driver.findElement(By.xpath("(//*[@class='radio--primary__button'])[3]")).click();

		Thread.sleep(1000);

		f.ScrollInKlik2(By.id("select2-familyRelationship-container"));

		Thread.sleep(1000);

		f.ScrollInKlik2(By.id("addInsurerForm"));

		Thread.sleep(1000);

		WebElement naprej3 = driver.findElement(By.className("btn-next"));
		int naprej3_Position = naprej3.getLocation().getY();
		((JavascriptExecutor) driver).executeScript("window.scroll(0, " + (naprej3_Position - halfViewport) + ");");
		Thread.sleep(1000);
		driver.findElement(By.className("btn-next")).click();
		Thread.sleep(1000);

		// KORAK 6
		// ("https://skleni-qa.triglav.si/isklepanje/tujina/potovanje_zavarovanje_4")

		wait.until(
				ExpectedConditions.urlToBe("https://skleni-qa.triglav.si/isklepanje/tujina/potovanje_zavarovanje_4"));

		Thread.sleep(1000);

		f.ScrollInKlik("//*[@for='zavarovalnimi-pogoji']");
		Thread.sleep(500);
		WebElement test = driver.findElement(By.xpath("//*[@for='info-zavarovalni-paket-mobile']"));
		new Actions(driver).moveToElement(test, 2, 2).click().perform();

		f.ScrollInKlik2(By.className("submitButton"));

		// PLAČILO -
		// https://skleni-qa.triglav.si/isklepanje/avtomobilno/potovanje_zavarovanje_5

		wait.until(
				ExpectedConditions.urlToBe("https://skleni-qa.triglav.si/isklepanje/tujina/potovanje_zavarovanje_5"));

		f.ScrollInKlik("//button[@name='simulatePayment']");

		Thread.sleep(3000);

		WebElement ele = driver.findElement(By.xpath("//*[text()='Hvala!']"));

		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOf(ele));

		if (ele.isDisplayed()) {
			System.out.println("Sklenitev je bila uspesna - TEST: PotovanjeTujina_Druzinsko_Neprijavljen");
		} else {
			throw new RuntimeException("Sklenitev NI bila uspesna - TEST: PotovanjeTujina_Druzinsko_Neprijavljen");
		}
	}

}
