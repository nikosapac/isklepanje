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

// HAPPPY FLOW - Test neprijavljene osebe, ki sklene skupinsko zavarovanje z NAKNADNO PRIJAVO

public class PotovanjeTujina_Skupinsko_NaknadnaPrijava {
	private WebDriver driver;

	public PotovanjeTujina_Skupinsko_NaknadnaPrijava(WebDriver driver) {
		this.driver = driver;
	}

	public void PotovanjeTujinaSkupinskoNaknadnaPrijavaTest() throws InterruptedException {

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

		String naknadnaprijavaEmail = "jan.volovsek@triglav.si";
		String naknadnaprijavaGeslo = "Jan@test_1";

		String imeZavarovanca = "Janez";
		String priimekZavarovanca = "Novak";
		String datumRojstvaZavarovanca = "28.10.1998";

		String imeZavarovanca2 = "Martin";
		String priimekZavarovanca2 = "Krpan";
		String datumRojstvaZavarovanca2 = "20.09.1992";

		String imeZavarovanca3 = "Lojze";
		String priimekZavarovanca3 = "Močnik";
		String datumRojstvaZavarovanca3 = "28.11.1993";

		// PRIJAVA

		p.SklepanjePrijava();

		// KORAK 1
		// ("https://skleni-qa.triglav.si/isklepanje/tujina/potovanje_zavarovanje")

		driver.get("https://skleni-qa.triglav.si/isklepanje/tujina/potovanje_zavarovanje");

		wait.until(ExpectedConditions.elementToBeClickable(By.className("submitButton")));

		driver.findElement(By.className("submitButton")).click();

		// KORAK 2
		// ("https://skleni-qa.triglav.si/isklepanje/tujina/potovanje_zavarovanje_vrsta")

		wait.until(ExpectedConditions
				.urlToBe("https://skleni-qa.triglav.si/isklepanje/tujina/potovanje_zavarovanje_vrsta"));

		int viewportHeight = ((Long) ((JavascriptExecutor) driver).executeScript("return window.innerHeight"))
				.intValue();
		int halfViewport = viewportHeight / 2;

		WebElement skupinsko = driver.findElement(By.xpath("(//*[@class='insuranceOptionsSelect-itemTitle'])[1]"));
		int skupinsko_Position = skupinsko.getLocation().getY();
		((JavascriptExecutor) driver).executeScript("window.scroll(0, " + (skupinsko_Position - halfViewport) + ");");
		Thread.sleep(1000);
		((JavascriptExecutor) driver).executeScript("window.scroll(0,400)");

		driver.findElement(By.id("turisticnoPolicy.insuranceIntro.zptInsuranceType3")).click();

		driver.findElement(By.className("submitButton")).click();

		// KORAK 3
		// ("https://skleni-qa.triglav.si/isklepanje/tujina/potovanje_zavarovanje_1")

		wait.until(
				ExpectedConditions.urlToBe("https://skleni-qa.triglav.si/isklepanje/tujina/potovanje_zavarovanje_1"));

		Thread.sleep(200);

		driver.findElement(By.className("inputNumber-plus")).click();

		Thread.sleep(200);

		f.ScrollToElement(By.id("select2-turisticnoPolicyinsurancetravelDuration-container"));

		f.ScrollInKlik2(By.id("select2-turisticnoPolicyinsurancetravelDuration-container"));

		driver.findElement(By.xpath("//*[text() = '30 dni']")).click();

		f.ScrollInKlik2(By.id("select2-turisticnoPolicyinsurancetravelDuration-container"));

		Thread.sleep(400);

		f.ScrollToElement(By.className("submitButton"));

		f.ScrollInKlik2(By.className("submitButton"));

		// KORAK 4
		// ("https://skleni-qa.triglav.si/isklepanje/tujina/potovanje_zavarovanje_2")

		wait.until(
				ExpectedConditions.urlToBe("https://skleni-qa.triglav.si/isklepanje/tujina/potovanje_zavarovanje_2"));

		f.ScrollInKlik2(By.className("inactiveText"));

		f.ScrollInKlik2(By.id("btnPackageBigPlus"));

		f.ScrollInKlik2(By.className("submitButton"));

		// KORAK 5
		// ("https://skleni-qa.triglav.si/isklepanje/tujina/potovanje_zavarovanje_3")

		// NAKNADNA PRIJAVA

		driver.findElement(By.className("btn-uppercase")).click();

		driver.findElement(By.id("email")).sendKeys(naknadnaprijavaEmail);

		Thread.sleep(1000);

		driver.findElement(By.id("Password")).sendKeys(naknadnaprijavaGeslo);

		driver.findElement(By.id("next")).click();

		// PRIJAVLJEN

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

		// OPEN MODAL

		// DODAJANJE ZAVAROVANCA 1

		f.ScrollInKlik2(By.id("getInsurerModal"));

		driver.findElement(By.id("name")).sendKeys(imeZavarovanca);

		Thread.sleep(500);

		driver.findElement(By.id("surname")).sendKeys(priimekZavarovanca);

		Thread.sleep(500);

		driver.findElement(By.id("birthday")).sendKeys(datumRojstvaZavarovanca);

		Thread.sleep(500);

		driver.findElement(By.xpath("(//*[@class='radio--primary__button'])[3]")).click();

		Thread.sleep(1000);

		WebElement postaNaslov2 = driver.findElement(By.id("addressSearch.post_and_place"));
		int postaNaslov2_Position = postaNaslov2.getLocation().getY();
		((JavascriptExecutor) driver)
				.executeScript("window.scroll(0, " + (postaNaslov2_Position - halfViewport) + ");");
		Thread.sleep(1000);
		driver.findElement(By.id("addressSearch.post_and_place")).sendKeys(postnaStevilka);
		Thread.sleep(2000);
		f.implicitWait(10);
		driver.findElement(By.id("addressSearch.post_and_place")).sendKeys(Keys.DOWN, Keys.ENTER);
		Thread.sleep(1000);

		WebElement ulicaNaslov2 = driver.findElement(By.id("addressSearch.street"));
		int ulicaNaslov2_Position = ulicaNaslov2.getLocation().getY();
		((JavascriptExecutor) driver)
				.executeScript("window.scroll(0, " + (ulicaNaslov2_Position - halfViewport) + ");");
		Thread.sleep(3000);
		f.implicitWait(10);
		driver.findElement(By.id("addressSearch.street")).sendKeys(ulica);
		Thread.sleep(3000);
		f.implicitWait(10);
		driver.findElement(By.id("addressSearch.street")).sendKeys(Keys.DOWN, Keys.ENTER);
		Thread.sleep(3000);

		f.implicitWait(10);

		WebElement hisnaStevilkaNaslov2 = driver.findElement(By.id("policyHolderUI.addressSearch.hnr"));
		int hisnaStevilkaNaslov2_Position = hisnaStevilkaNaslov2.getLocation().getY();
		((JavascriptExecutor) driver)
				.executeScript("window.scroll(0, " + (hisnaStevilkaNaslov2_Position - halfViewport) + ");");
		Thread.sleep(1000);
		f.implicitWait(10);
		driver.findElement(By.id("addressSearch.hnr")).sendKeys(hisnaStevilka);
		Thread.sleep(2000);
		f.implicitWait(10);
		driver.findElement(By.id("addressSearch.hnr")).sendKeys(Keys.DOWN, Keys.ENTER);
		Thread.sleep(2000);

		Thread.sleep(1000);

		f.ScrollInKlik2(By.id("addInsurerForm"));

		Thread.sleep(2000);

		// DODAJANJE ZAVAROVANCA 2

		f.ScrollInKlik2(By.id("getInsurerModal"));

		driver.findElement(By.id("name")).sendKeys(imeZavarovanca2);

		Thread.sleep(500);

		driver.findElement(By.id("surname")).sendKeys(priimekZavarovanca2);

		Thread.sleep(500);

		driver.findElement(By.id("birthday")).sendKeys(datumRojstvaZavarovanca2);

		Thread.sleep(500);

		driver.findElement(By.xpath("(//*[@class='radio--primary__button'])[3]")).click();

		Thread.sleep(1000);

		WebElement postaNaslov3 = driver.findElement(By.id("addressSearch.post_and_place"));
		int postaNaslov3_Position = postaNaslov3.getLocation().getY();
		((JavascriptExecutor) driver)
				.executeScript("window.scroll(0, " + (postaNaslov3_Position - halfViewport) + ");");
		Thread.sleep(1000);
		driver.findElement(By.id("addressSearch.post_and_place")).sendKeys(postnaStevilka);
		Thread.sleep(2000);
		f.implicitWait(10);
		driver.findElement(By.id("addressSearch.post_and_place")).sendKeys(Keys.DOWN, Keys.ENTER);
		Thread.sleep(1000);

		WebElement ulicaNaslov3 = driver.findElement(By.id("addressSearch.street"));
		int ulicaNaslov_Position3 = ulicaNaslov3.getLocation().getY();
		((JavascriptExecutor) driver)
				.executeScript("window.scroll(0, " + (ulicaNaslov_Position3 - halfViewport) + ");");
		Thread.sleep(3000);
		f.implicitWait(10);
		driver.findElement(By.id("addressSearch.street")).sendKeys(ulica);
		Thread.sleep(3000);
		f.implicitWait(10);
		driver.findElement(By.id("addressSearch.street")).sendKeys(Keys.DOWN, Keys.DOWN, Keys.ENTER);
		Thread.sleep(3000);

		f.implicitWait(10);

		WebElement hisnaStevilkaNaslov3 = driver.findElement(By.id("policyHolderUI.addressSearch.hnr"));
		int hisnaStevilkaNaslov_Position3 = hisnaStevilkaNaslov3.getLocation().getY();
		((JavascriptExecutor) driver)
				.executeScript("window.scroll(0, " + (hisnaStevilkaNaslov_Position3 - halfViewport) + ");");
		Thread.sleep(1000);
		f.implicitWait(10);
		driver.findElement(By.id("addressSearch.hnr")).sendKeys(hisnaStevilka);
		Thread.sleep(2000);
		f.implicitWait(10);
		driver.findElement(By.id("addressSearch.hnr")).sendKeys(Keys.DOWN, Keys.DOWN, Keys.ENTER);
		Thread.sleep(2000);

		Thread.sleep(1000);

		f.ScrollInKlik2(By.id("addInsurerForm"));

		Thread.sleep(2000);

		// DODAJANJE ZAVAROVANCA 2

		f.ScrollInKlik2(By.id("getInsurerModal"));

		driver.findElement(By.id("name")).sendKeys(imeZavarovanca3);

		Thread.sleep(500);

		driver.findElement(By.id("surname")).sendKeys(priimekZavarovanca3);

		Thread.sleep(500);

		driver.findElement(By.id("birthday")).sendKeys(datumRojstvaZavarovanca3);

		Thread.sleep(500);

		driver.findElement(By.xpath("(//*[@class='radio--primary__button'])[3]")).click();

		Thread.sleep(1000);

		WebElement postaNaslov4 = driver.findElement(By.id("addressSearch.post_and_place"));
		int postaNaslov4_Position = postaNaslov4.getLocation().getY();
		((JavascriptExecutor) driver)
				.executeScript("window.scroll(0, " + (postaNaslov4_Position - halfViewport) + ");");
		Thread.sleep(1000);
		driver.findElement(By.id("addressSearch.post_and_place")).sendKeys(postnaStevilka);
		Thread.sleep(2000);
		f.implicitWait(10);
		driver.findElement(By.id("addressSearch.post_and_place")).sendKeys(Keys.DOWN, Keys.ENTER);
		Thread.sleep(1000);

		WebElement ulicaNaslov4 = driver.findElement(By.id("addressSearch.street"));
		int ulicaNaslov4_Position = ulicaNaslov4.getLocation().getY();
		((JavascriptExecutor) driver)
				.executeScript("window.scroll(0, " + (ulicaNaslov4_Position - halfViewport) + ");");
		Thread.sleep(3000);
		f.implicitWait(10);
		driver.findElement(By.id("addressSearch.street")).sendKeys(ulica);
		Thread.sleep(3000);
		f.implicitWait(10);
		driver.findElement(By.id("addressSearch.street")).sendKeys(Keys.DOWN, Keys.DOWN, Keys.ENTER);
		Thread.sleep(3000);

		f.implicitWait(10);

		WebElement hisnaStevilkaNaslov4 = driver.findElement(By.id("policyHolderUI.addressSearch.hnr"));
		int hisnaStevilkaNaslov4_Position = hisnaStevilkaNaslov4.getLocation().getY();
		((JavascriptExecutor) driver)
				.executeScript("window.scroll(0, " + (hisnaStevilkaNaslov4_Position - halfViewport) + ");");
		Thread.sleep(1000);
		f.implicitWait(10);
		driver.findElement(By.id("addressSearch.hnr")).sendKeys(hisnaStevilka);
		Thread.sleep(2000);
		f.implicitWait(10);
		driver.findElement(By.id("addressSearch.hnr")).sendKeys(Keys.DOWN, Keys.DOWN, Keys.ENTER);
		Thread.sleep(2000);

		Thread.sleep(1000);

		f.ScrollInKlik2(By.id("addInsurerForm"));

		Thread.sleep(2000);

		// NAPREJ GUMB

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

		driver.findElement(By.className("radio--secondary__button")).click();
		Thread.sleep(1000);
		driver.findElement(By.className("radio-quaternary__button")).click();
		Thread.sleep(1000);

		f.ScrollInKlik("//button[@name='simulatePayment']");

		Thread.sleep(3000);

		WebElement ele = driver.findElement(By.xpath("//*[text()='Hvala!']"));

		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOf(ele));

		if (ele.isDisplayed()) {
			System.out.println("Sklenitev je bila uspešna - TEST: PotovanjeTujina_Skupinsko_NaknadnaPrijava");
		} else {
			throw new RuntimeException("Sklenitev NI bila uspešna - TEST: PotovanjeTujina_Skupinsko_NaknadnaPrijava");
		}
	}

}
