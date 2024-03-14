package sklepanjeTesti.zdruzeniDeliAljosa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import sklepanjeTesti.Funkcije2;

public class PersonalInfoPrijavNujne {
	private WebDriver driver;
	public PersonalInfoPrijavNujne (WebDriver driver) {
		this.driver = driver;
	}
	
	public void zPrijav() throws InterruptedException {
		
		Funkcije2 f = new Funkcije2(driver);
		
		
		f.ScrollToElement(By.xpath("//*[@for = 'confirmPersonalDataProcessingAproval']"));		//we will only tick needed option, other's will stay unchecked
		
		Actions builder = new Actions(driver);			//clicking away from link, so it doesen't open PDF
		WebElement element = driver.findElement(By.xpath("//*[@for = 'confirmPersonalDataProcessingAproval']"));
		Action action = builder.moveToElement(element, -200, -10).click().build();
		action.perform();
		
		f.ScrollInKlik2(By.className("submitButton"));
	}
}
