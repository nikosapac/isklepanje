package sklepanjeTesti.zdruzeniDeliAljosa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class CheckingPersInfoFieldsAreNotEmpty {
	private WebDriver driver;

	public CheckingPersInfoFieldsAreNotEmpty(WebDriver driver) {
		this.driver = driver;
	}

	public void Checking() throws InterruptedException {

		WebElement spremenIme = driver.findElement(By.id("policyHolderUI.name"));
		WebElement spremenPriim = driver.findElement(By.id("policyHolderUI.surname"));
		WebElement spremenRojst = driver.findElement(By.id("policyHolderUI.birthday"));
		WebElement spremenPosta = driver.findElement(By.id("policyHolderUI.addressSearch.post_and_place"));
		WebElement ulicaNaselje = driver.findElement(By.id("policyHolderUI.addressSearch.street"));
		WebElement hisnaSt = driver.findElement(By.id("policyHolderUI.addressSearch.hnr"));
		WebElement telSt = driver.findElement(By.id("policyHolderUI.phoneNumber"));

		String Ime = spremenIme.getAttribute("value");
		Assert.assertFalse(Ime.isEmpty());

		String Priim = spremenPriim.getAttribute("value");
		Assert.assertFalse(Priim.isEmpty());

		String Rojst = spremenRojst.getAttribute("value");
		Assert.assertFalse(Rojst.isEmpty());

		String Posta = spremenPosta.getAttribute("value");
		Assert.assertFalse(Posta.isEmpty());

		String Naselje = ulicaNaselje.getAttribute("value");
		Assert.assertFalse(Naselje.isEmpty());

		String Hisna = hisnaSt.getAttribute("value");
		Assert.assertFalse(Hisna.isEmpty());

		String Tel = telSt.getAttribute("value");
		Assert.assertFalse(Tel.isEmpty());

	}
}
