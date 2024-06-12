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

// HAPPPY FLOW - Test prijavljene osebe, ki sklene skupinsko zavarovanje ( preverim večkrat če se cena ujema z ceno na hover down prikazu premije, 
//				 dodam dodatno zavarovanje odpovedi turističnih potovanj - 3.010 EUR, dodam kupon avtomobilno, preverim ce se je dodal triglav komplet,
//				 plačilo na obroke + FLIK plačilo)
public class PotovanjeTujina_Testiranje_Funkcionalnostih_ENA {
	private WebDriver driver;

	public PotovanjeTujina_Testiranje_Funkcionalnostih_ENA(WebDriver driver) {
		this.driver = driver;
	}

	public void PotovanjeTujinaTestiranjeFunkcionalnostihTestENA() throws InterruptedException {

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

		String akcijaAvtomobilno = "PREM40CP3OAK";

		String TRR = "SI56290000050502228";

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
		((JavascriptExecutor) driver).executeScript("window.scroll(0,800)");

		driver.findElement(By.id("turisticnoPolicy.insuranceIntro.zptInsuranceType1")).click();

		f.ScrollInKlik2(By.className("submitButton"));

		// KORAK 3
		// ("https://skleni-qa.triglav.si/isklepanje/tujina/potovanje_zavarovanje_1")

		wait.until(
				ExpectedConditions.urlToBe("https://skleni-qa.triglav.si/isklepanje/tujina/potovanje_zavarovanje_1"));

		Thread.sleep(200);

		driver.findElement(By.className("select2-selection__arrow")).click();

		Thread.sleep(400);

		f.ScrollInKlik2(By.xpath("(//*[@class='radio--secondary__button'])[4]"));

		// Počaka in preveri, če se pojavi besedilo
		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement txt = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
				"//*[text()='Ukvarjanje z rizičnimi športi ali drugimi rizičnimi aktivnostmi pomeni povečano nevarnost za morebitne škodne dogodke, zato se premija poviša za 50 %.']")));

		if (txt.isDisplayed()) {
			f.ScrollInKlik2(By.className("submitButton"));
		} else {
			throw new RuntimeException(
					"Ni se izpisal text \"Ukvarjanje z rizičnimi športi ali drugimi rizičnimi aktivnostmi pomeni...\" na strani: https://skleni-qa.triglav.si/isklepanje/tujina/potovanje_zavarovanje_1");
		}

		// KORAK 4
		// ("https://skleni-qa.triglav.si/isklepanje/tujina/potovanje_zavarovanje_2")

		wait.until(
				ExpectedConditions.urlToBe("https://skleni-qa.triglav.si/isklepanje/tujina/potovanje_zavarovanje_2"));

		f.ScrollInKlik2(By.className("inactiveText"));

		f.ScrollInKlik2(By.id("btnPackageBigPlus"));

		Thread.sleep(100);

		f.ScrollInKlik2(By.id("btnPackageBig"));

		Thread.sleep(100);

		f.ScrollInKlik2(By.id("btnPackageSmall"));

		Thread.sleep(100);

		f.ScrollInKlik2(By.id("btnPackageBigPlus"));

		Thread.sleep(200);

		f.ScrollInKlik2(By.xpath("(//*[@class='radio--secondary__button'])[2]"));

		Thread.sleep(100);

		f.ScrollInKlik2(By.xpath("(//*[@class='radio--primary__button'])[2]"));

		Thread.sleep(200);

		// Primerja ali se ceni ujemata pri ceni paketa in hover down prikazu zneska
		// premije za PAKET C
		// Poišče prvi element cene
		WebElement priceElement1 = driver.findElement(By.cssSelector("p.premiumHeader--v2-price#stickyPrice"));
		String price1 = priceElement1.getText().replace("Skupaj znesek:", "").trim();

		// Poišče drugi element cene
		WebElement priceElement2 = driver.findElement(By.cssSelector("h4.package-price#package-price-C"));
		String price2 = priceElement2.getText().replace("/leto", "").trim();

		// Primerjajte ceni
		if (price1.equals(price2)) {
			System.out.println(price1);
			System.out.println(price2);
			System.out.println("ceni se ujemata");

		} else {
			throw new RuntimeException(
					"Ceni se ne ujemata pri hover down premiji z ceno paketa na strani: https://skleni-qa.triglav.si/isklepanje/tujina/potovanje_zavarovanje_2");
		}

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

		WebElement podatkispol = driver.findElement(By.id("male"));
		int podatkispol_Position = podatkispol.getLocation().getY();
		((JavascriptExecutor) driver).executeScript("window.scroll(0, " + (podatkispol_Position - halfViewport) + ");");
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//*[@class='radio--primary__button'])[1]")).click();
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

		WebElement naprej3 = driver.findElement(By.className("btn-next"));
		int naprej3_Position = naprej3.getLocation().getY();
		((JavascriptExecutor) driver).executeScript("window.scroll(0, " + (naprej3_Position - halfViewport) + ");");
		Thread.sleep(1000);
		driver.findElement(By.className("btn-next")).click();
		Thread.sleep(3000);

		f.ScrollInKlik2(By.className("cs-slider"));

		WebElement naprej4 = driver.findElement(By.className("btn-next"));
		int naprej4_Position = naprej4.getLocation().getY();
		((JavascriptExecutor) driver).executeScript("window.scroll(0, " + (naprej3_Position - halfViewport) + ");");
		Thread.sleep(1000);
		driver.findElement(By.className("btn-next")).click();
		Thread.sleep(3000);

		// KORAK 6
		// ("https://skleni-qa.triglav.si/isklepanje/tujina/potovanje_zavarovanje_4")

		wait.until(
				ExpectedConditions.urlToBe("https://skleni-qa.triglav.si/isklepanje/tujina/potovanje_zavarovanje_4"));

		Thread.sleep(1000);

		WebElement firstPriceElement = driver.findElement(By.id("stickyPrice"));
		String firstPriceText = firstPriceElement.getText();

		// Izlušči številko cene
		String firstPrice = firstPriceText.replaceAll("[^0-9,]", "").trim();

		// Poišče drugi element cene
		WebElement secondPriceElement = driver
				.findElement(By.cssSelector(".package-total__price .informativePremiumPrice"));
		String secondPrice = secondPriceElement.getText().trim();

		// Primerja ceni
		if (firstPrice.equals(secondPrice)) {
			System.out.println(firstPrice);
			System.out.println(secondPrice);
			System.out.println("Ceni se ujemata");
		} else {
			throw new RuntimeException(
					"Ceni med hover down zneskom premije ceno na racunu se ne ujemata na strani - https://skleni-qa.triglav.si/isklepanje/tujina/potovanje_zavarovanje_4");
		}

		Thread.sleep(200);

		// Preveri ce je dodan triglav komplet popust
		WebElement element = driver.findElement(By.className("discount-text"));

		// Preverim, ali v besedilu obstaja fraza "Prejeli ste"
		String text = element.getText();
		if (text.contains("Prejeli ste")) {
			System.out.println("Triglav komplet je bil prejet.");
		} else {
			throw new RuntimeException("Triglav komplet ni bil prejet.");
		}

		// Dodajanje popusta avtomobilno
		f.ScrollInKlik2(By.className("card-secondary-btnWrapper"));

		driver.findElement(By.id("itbCouponCode")).sendKeys(akcijaAvtomobilno);

		driver.findElement(By.id("btnAvtomobilnoDiscountCode")).click();

		Thread.sleep(200);

		f.ScrollInKlik2(By.xpath("/html/body/div[12]/div/div/div/a"));

		Thread.sleep(200);

		// ponovno preveri ce se ceni ujemata po POPUSTA akcije avtomobilno
		WebElement premiumPriceElement = driver.findElement(By.id("stickyPrice"));
		String premiumPriceText = premiumPriceElement.getText();

		// izluscim ceno premije
		String premiumPrice = premiumPriceText.replaceAll("[^0-9,]", "").trim();

		// Pridobim drugi element cene
		WebElement totalPriceElement = driver
				.findElement(By.cssSelector(".package-total__price .informativePremiumPrice"));
		String totalPriceText = totalPriceElement.getText().trim();

		// Primerjam ceni
		if (premiumPrice.equals(totalPriceText)) {
			System.out.println(premiumPrice);
			System.out.println(totalPriceText);
			System.out.println("Ceni se ujemata");
		} else {
			throw new RuntimeException(
					"Ceni med hover down zneskom premije in ceno na računu se ne ujemata na strani - https://skleni-qa.triglav.si/isklepanje/tujina/potovanje_zavarovanje_4");
		}

		Thread.sleep(300);

		// brišem ven bon avtomobilno
		f.ScrollInKlik2(By.className("card-secondary-btnWrapper"));

		// Želite spremeniti paket? ---> DA

		f.ScrollInKlik2(By.className("change-package"));

		// me preusmeri nazaj na stran, kjer zamenjam paket v Paket A
		// ("https://skleni-qa.triglav.si/isklepanje/tujina/potovanje_zavarovanje_2")
		f.ScrollInKlik2(By.id("btnPackageBig"));

		Thread.sleep(200);

		// pridem nazaj na stran
		// ("https://skleni-qa.triglav.si/isklepanje/tujina/potovanje_zavarovanje_4")
		f.ScrollInKlik2(By.className("submitButton"));

		f.ScrollInKlik("//*[@for='zavarovalnimi-pogoji']");
		Thread.sleep(500);
		WebElement test = driver.findElement(By.xpath("//*[@for='info-zavarovalni-paket-mobile']"));
		new Actions(driver).moveToElement(test, 2, 2).click().perform();

		f.ScrollInKlik2(By.className("submitButton"));

		// PLAČILO -
		// https://skleni-qa.triglav.si/isklepanje/avtomobilno/potovanje_zavarovanje_5

		wait.until(
				ExpectedConditions.urlToBe("https://skleni-qa.triglav.si/isklepanje/tujina/potovanje_zavarovanje_5"));
		

		f.ScrollToElement(By.xpath("(//*[@class='radio--secondary__button'])[2]"));
		
//		Thread.sleep(200);
//		
//		driver.findElement(By.xpath("//*[text()='Strinjam se, da mi v prihodnje ob zapadlosti premije Zavarovalnica Triglav, d.d., pošilja plačilne naloge (UPN QR), ki jih bom poravnal v predvidenem roku.']")).click();	
//
	Thread.sleep(200);
		
		f.ScrollInKlik2(By.xpath("(//*[@class='radio--secondary__button'])[2]"));

		Thread.sleep(2000);
		
		driver.findElement(By.id("paymentOptions.manualTrrNumber")).sendKeys(TRR);
		
		Thread.sleep(200);
		
		driver.findElement(By.xpath("//*[text()='Soglašam s pogoji plačila s trajnikom']")).click();	
		
		// Spletno plačilo s Flik
		f.ScrollInKlik2(By.xpath("(//*[@class='radio-quaternary__button'])[4]"));
		
		Thread.sleep(200);
		
		f.ScrollInKlik("//button[@name='simulatePayment']");

		Thread.sleep(3000);

		WebElement ele = driver.findElement(By.xpath("//*[text()='Hvala!']"));

		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOf(ele));

		if (ele.isDisplayed()) {
			System.out.println("Sklenitev je bila uspesna - TEST: PotovanjeTujina_Skupinsko_NaknadnaPrijava");
		} else {
			throw new RuntimeException("Sklenitev NI bila uspesna - TEST: PotovanjeTujina_Skupinsko_NaknadnaPrijava");
		}
	}

}
