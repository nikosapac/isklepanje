package sklepanjeTesti.zdruzeniDeliAljosa;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import sklepanjeTesti.Funkcije2;

public class TriglavDiscounts {
	private WebDriver driver;
	public TriglavDiscounts (WebDriver driver) {
		this.driver = driver;
	}
	
	public void MaleZivali() throws InterruptedException {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		String avtoMobilnoKupon = "PREM4012HIPI";
		String avtoMobilnoBon = "ZIV30F5PPXMU";
		
		String mrPet = "MR5343348";
		
		String mladicek = "ZIV300WXM6CZ";
		
		Funkcije2 f = new Funkcije2(driver);
		
		//Avtomobilno
		f.ScrollInKlik2(By.xpath("//*[@data-target = '#discountCarCoupon']"));
		
		driver.findElement(By.id("itbAvtomobilnoCouponCode")).sendKeys(avtoMobilnoKupon);
		driver.findElement(By.id("btnAvtomobilnoCouponAdd")).click();
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnAvtomobilnoCouponRemoveInModal")));
		} catch (Exception e) {
			System.out.println("AvtoMobilnoCupon ni deloval");
		}
		driver.findElement(By.id("itbAvtomobilnoBonCode")).sendKeys(avtoMobilnoBon);
		driver.findElement(By.id("btnAvtomobilnoBonAdd")).click();
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnAvtomobilnoBonRemoveInModal")));
		} catch (Exception e) {
			System.out.println("AvtoMobilnoBon ni deloval");
		}
		driver.findElement(By.xpath("//*[text() = 'POTRDI']")).click();
		
		//MR.pet
		f.ScrollInKlik2(By.id("btnMrPetVet"));
		driver.findElement(By.id("ipdMrPetVetCode")).sendKeys(mrPet);
		driver.findElement(By.id("btnMrPetVetAdd")).click();
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[text() = 'Pridobili ste'])[1]")));
		} catch (Exception e) {
			System.out.println("MrPet bon ni deloval");
		}
		driver.findElement(By.xpath("(//*[@aria-label='Zapri'])[13]")).click();
		
		//Mladicki
		f.ScrollInKlik2(By.id("btnAddPuppies"));
		driver.findElement(By.id("puppiesCode")).sendKeys(mladicek);
		driver.findElement(By.id("btnPuppiesAdd")).click();
		driver.findElement(By.className("help-block"));
		driver.findElement(By.xpath("(//*[@class = 'close'])[14]")).click();
		// clicking remove
		f.ScrollToElement(By.id("ipdAvtomobilnoCouponAmount"));
		driver.findElement(By.id("ipdAvtomobilnoBonAmount"));
		driver.findElement(By.id("btnAvtomobilnoCouponRemove")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("btnAvtomobilnoBonRemove")).click();
		// adding mladicki again
		f.ScrollInKlik2(By.id("btnAddPuppies"));
		driver.findElement(By.id("btnPuppiesAdd")).click();
		try {
			driver.findElement(By.xpath("(//*[text() = 'Pridobili ste'])[2]"));
		} catch (Exception e) {
			System.out.println("mladicek cupon ni deloval");
		}
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@class = 'close'])[15]")));
		//Thread.sleep(2000);
		driver.findElement(By.xpath("(//*[@class = 'close'])[15]")).click();
		
		// clicking remove
		f.ScrollInKlik2(By.id("btnMrPetVetRemove"));
		f.ScrollInKlik2(By.id("btnPuppiesRemove"));
	}
}
