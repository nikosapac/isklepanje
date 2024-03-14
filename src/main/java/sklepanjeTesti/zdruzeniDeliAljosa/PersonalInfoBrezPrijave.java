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

public class PersonalInfoBrezPrijave {
	private WebDriver driver;
	public PersonalInfoBrezPrijave (WebDriver driver) {
		this.driver = driver;
	}
	
	public void BrezPrijave() throws InterruptedException {
	    BrezPrijave(null);
	}
	
	public void BrezPrijave(Integer onlyNecessery) throws InterruptedException {
		
		// onlyNecessery can be 1-ON 2-OFF or empty (default is ON)
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.elementToBeClickable(By.className("submitButton")));
		
		Funkcije2 f = new Funkcije2(driver);
		
		String eMail = "zkpr.test@triglav.si";
		String koda = "9999";
		String telNum = "000000000";
		
		String policyHolder = "aljoooooooo";
		String policyHolderSurname = "mlinnnnnnnn";
		String policyHolderBirthday = "22.12.1999";
		String taxnum = "51018535";
		String posta = "9000";
		String houseNum = "1";
		
		driver.findElement(By.id("policyHolderUI.name")).sendKeys(policyHolder);  //Holder name
		
		driver.findElement(By.id("policyHolderUI.surname")).sendKeys(policyHolderSurname);	//Holder Surname
		
		driver.findElement(By.id("policyHolderUI.birthday")).sendKeys(policyHolderBirthday);	//Holder birth date
		
		driver.findElement(By.id("policyHolderUI.taxnum")).sendKeys(taxnum);	//Holder "davcna" TaxNum
		
		f.ScrollInKlik2(By.xpath("(//*[@class = 'radio--primary__button'])[1]"));	//Holder's gender
		
		driver.findElement(By.id("policyHolderUI.addressSearch.post_and_place")).sendKeys(posta);	//Holders Postal Num.
		
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("ui-id-1")));
		//Thread.sleep(2000); //i can't figure out other way :(
		
		driver.findElement(By.id("policyHolderUI.addressSearch.post_and_place")).sendKeys(Keys.ARROW_DOWN, Keys.RETURN);	//Choosing correct Postal num. from dropdown menu
		
		f.ScrollInKlik2(By.id("policyHolderUI.addressSearch.street"));
		
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("ui-id-2")));
		
		for (int i = 1; i<=5; i++) {
			if (i<5) { 
				driver.findElement(By.id("policyHolderUI.addressSearch.street")).sendKeys(Keys.ARROW_DOWN);	//going through listed options for street
			}
			else {
				driver.findElement(By.id("policyHolderUI.addressSearch.street")).sendKeys(Keys.ARROW_DOWN, Keys.RETURN);	//pressing return on correct option
			}	
		}
		
		driver.findElement(By.id("policyHolderUI.addressSearch.hnr")).sendKeys(houseNum, Keys.RETURN);	//picking first option ("1" in our case)
		
		f.ScrollInKlik2(By.id("unvalidatedEmail"));
		
		driver.findElement(By.id("unvalidatedEmail")).sendKeys(eMail);	//sending email
		
		driver.findElement(By.id("btnPosliValidacijskoKodoNaEmail")).click();
		
		driver.findElement(By.id("emailValidationString")).sendKeys(koda);	//Validation code
		
		f.ScrollInKlik2(By.id("btnCheckValidacijskaKodaZaEmail"));
		
		driver.findElement(By.id("policyHolderUI.phoneNumber")).sendKeys(telNum);	//Tel. num
		
		f.ScrollToElement(By.xpath("//*[@for = 'informacije']"));		//we will only tick needed option, other's will stay unchecked
		
		if (onlyNecessery == null || onlyNecessery == 1) {
			Actions builder = new Actions(driver);			//clicking away from link, so it doesen't open PDF
			WebElement element = driver.findElement(By.xpath("//*[@for = 'informacije']"));
			Action action = builder.moveToElement(element, -200, -10).click().build();
			action.perform();
		}	
		else if (onlyNecessery == 0) {
			f.ScrollInKlik2(By.xpath("//*[@for = 'potrjujem-vse']"));
		}
		
		f.ScrollInKlik2(By.className("submitButton"));
		
	}
}
