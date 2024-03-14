package sklepanjeTesti.sklepanje;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import sklepanjeTesti.Funkcije2;
import sklepanjeTesti.Prijave2;

public class MaleZivali0KlikanjeNaprej0Neizpolnjenih {
	private WebDriver driver;
	
	public MaleZivali0KlikanjeNaprej0Neizpolnjenih (WebDriver driver) {
		this.driver = driver;
	}
	
	public void MaleZivaliDrugiTest() throws InterruptedException {
		
		//int a;
		
		int gender = 1;		// 1-male 2-female
		int pasemska = 4; // 4-nepasemska 3-pasemska
		int dangerous = 1; // 1-yes 2-no
		int catNum = 1; // Zaporedna vrsta pasme zivali macke
		
		String authVet = "yes";	// can be yes , no
		String tipZivali = "Pes";  // Possible: Pes , Mačka
		String mikroCip = "534545646465464"; //Stevilka mikrocipa
		String imeZivali = "Petra"; // ime animala
		String dtRojZiv = "25.10.2023"; // rojstvo animala
		
		Funkcije2 f = new Funkcije2(driver);
		Prijave2 p = new Prijave2(driver);
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		
		//prijava
		p.SklepanjePrijava();
		
		//get https://skleni-qa.triglav.si/isklepanje/zivali/zavarovanje_psov_in_mack
		driver.get("https://skleni-qa.triglav.si/isklepanje/zivali/zavarovanje_psov_in_mack");
		wait.until(ExpectedConditions.elementToBeClickable(By.className("submitButton")));
		//Clicks white button (not green)
		driver.findElement(By.className("submitButton")).click();
		//Wait till second site
		wait.until(ExpectedConditions.urlToBe("https://skleni-qa.triglav.si/isklepanje/zivali/zavarovanje_psov_in_mack_1"));
		
		List<Integer> numbers = new ArrayList<>();
			for (int i = 1; i <= 5; i++) {
				numbers.add(i);
			}
		Collections.shuffle(numbers);
			
			for(int i=0 ; i<=5; i++)	{
				if (i==0) {
					int a=0;	
					zivaliInfoCasi(f, a, gender, pasemska, dangerous, catNum, authVet, tipZivali, mikroCip, imeZivali, dtRojZiv);
					f.ScrollInKlik2(By.className("submitButton"));
					}
				if (i>=1) {
					int a = numbers.get(i);
					zivaliInfoCasi(f, a, gender, pasemska, dangerous, catNum, authVet, tipZivali, mikroCip, imeZivali, dtRojZiv);
					f.ScrollInKlik2(By.className("submitButton"));
					}
			}
		
		wait.until(ExpectedConditions.urlToBe("https://skleni-qa.triglav.si/isklepanje/zivali/zavarovanje_psov_in_mack_2"));	
		
	}
	
	public void zivaliInfoCasi (Funkcije2 f, int a, int gender, int pasemska, int dangerous, int catNum, String authVet, String tipZivali, String mikroCip, String imeZivali, String dtRojZiv) throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		switch (a) {
			case 0:	//Select type of animal
				f.ScrollInKlik2(By.xpath("(//*[@class = 'select2-selection__arrow'])[1]"));
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='"+tipZivali+"']")));
				driver.findElement(By.xpath("//*[text()='Pes']")).click();
				break;
			case 1: //Type in Name Of animal
				driver.findElement(By.id("pet.name")).sendKeys(imeZivali);
				break;
			case 2: //Animal birth date
				driver.findElement(By.id("pet.birthDate")).sendKeys(dtRojZiv);
				break;
			case 3: //Animal gender
				f.ScrollInKlik2(By.xpath("(//*[@class='radio--primary__button'])["+gender+"]"));
				break;
			case 4:
				if (pasemska == 4) {
				f.ScrollInKlik2(By.xpath("(//*[@class='radio--primary__button'])["+pasemska+"]"));
				}
				else if (pasemska == 3) {
					f.ScrollInKlik2(By.xpath("(//*[@class='radio--primary__button'])["+pasemska+"]"));
					if (tipZivali == "Pes") {
						f.ScrollInKlik2(By.id("select2-petdogBreed-container"));
							if (dangerous == 1) {
								driver.findElement(By.id("select2-petdogBreed-result-l2jd-54")).click();
							}
							else if (dangerous == 2) {
								driver.findElement(By.id("select2-petdogBreed-result-aoth-2")).click();
							}
							else if (tipZivali == "Mačka") {
								f.ScrollInKlik2(By.id("select2-petcatBreed-container"));
								driver.findElement(By.id("select2-petcatBreed-result-uw8p-"+catNum+"")).click();
							}		
					}
				}
				break;
			case 5:
				f.ScrollInKlik2(By.xpath("//*[@for='pet.authorizedVet-"+authVet+"']"));
				break;
	}	
	}
}
