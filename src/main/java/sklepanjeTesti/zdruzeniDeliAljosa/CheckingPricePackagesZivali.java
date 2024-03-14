package sklepanjeTesti.zdruzeniDeliAljosa;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import sklepanjeTesti.Funkcije2;

public class CheckingPricePackagesZivali {
	private WebDriver driver;
	public CheckingPricePackagesZivali (WebDriver driver) {
		this.driver = driver;
	}
	
	public void checkingPriceChange(int cp, int dk, int verydangerous) throws InterruptedException {
		/*
			It summes up prices from site where you choose packages
			it throws assertion error if the numbers at the top of the page 
			are not the same
		*/
		Funkcije2 f = new Funkcije2(driver);
		
		float dod1 = 0;
		float packet = 0;
		float sum = 0;
		
		// converting , kommas to dots, strings to float values, remooving text from strings
		String standard = driver.findElement(By.id("package-price-STANDARD")).getText();
		String standardNum = standard.replaceAll("[^0-9,]", "");
		standardNum = standardNum.replace(',', '.');
		Float stanFloat = Float.valueOf(standardNum);
		
		String standardP = driver.findElement(By.id("package-price-STANDARD_P")).getText();
		String standardPNum = standardP.replaceAll("[^0-9,]", "");
		standardPNum = standardPNum.replace(',', '.');
		Float stanPFloat = Float.valueOf(standardPNum);
		
		String aboveStandard = driver.findElement(By.id("package-price-ABOVE_STANDARD")).getText();
		String aboveStandardNum = aboveStandard.replaceAll("[^0-9,]", "");
		aboveStandardNum = aboveStandardNum.replace(',', '.');
		Float aStanFloat = Float.valueOf(aboveStandardNum);
		
		
		switch (cp) {
			case 1:
				packet = stanFloat;
				break;
			case 2:
				packet = stanPFloat;
				break;
			case 3:
				packet = aStanFloat;
				break;
		}
		
		System.out.println("Cena izbranega paketa:");
		System.out.println(packet);
		System.out.println("Cene izbranih dodatnih:");
		
		Map<String, WebElement> map1 = new HashMap<>();
		Map<String, String> map2 = new HashMap<>();
		Map<String, String> map3 = new HashMap<>();
		Map<String, Float>	map4 = new HashMap<>();
		for (int i = 2; i <= 4 - dk; i++) {
			if (verydangerous == 1) {
				if (i == 4) continue;
			}
			f.ScrollToElement(By.xpath("(//*[@class='checkbox-tertiary-icon'])["+i+"]"));			//ticking all the options; incl. animal owner responsibility (not present if you choose i.e. Doberman dog)
			map1.put("dodatna" + i, driver.findElement(By.xpath("(//*[@class='checkbox-list-itemRight'])["+i+"]")));
			map2.put("dodatnaN" + i, map1.get("dodatna"+i).getText().replaceAll("[^0-9,]", ""));
			map3.put("dodatnaNN" + i, map2.get("dodatnaN" + i).replace(',', '.'));
			map4.put("dod" + i, Float.valueOf(map3.get("dodatnaNN" + i)));
			System.out.println(map4.get("dod" + i));
		}

		if (dk < 4) {
			f.ScrollToElement(By.xpath("(//*[@class='checkbox-tertiary-icon'])[1]"));
			String firstDod = driver.findElement(By.xpath("(//*[@class='checkbox-list-itemRight'])[1]")).getText();
			String firstDodNum = firstDod.replaceAll("[^0-9,]", "");
			firstDodNum = firstDodNum.replace(',', '.');
			dod1 = Float.valueOf(firstDodNum);
			System.out.println(dod1);
		}	else if (dk >= 4) {
			dod1 = 0;
		}
		
		if (verydangerous == 0) {
			sum = packet + dod1 + map4.get("dod2") + map4.get("dod3") + map4.get("dod4");
		} else {
			sum = packet + dod1 + map4.get("dod2") + map4.get("dod3");
		}
		int decimal = 2;
		float sumRound = round(sum, decimal);
		System.out.println("Sum:");
		System.out.println(sumRound);
		
		
		String compare = driver.findElement(By.id("stickyPrice")).getText();
		String compareNum = compare.replaceAll("[^0-9,]", "");
		compareNum = compareNum.replace(',', '.');
		Float compareFloat = Float.valueOf(compareNum);
		
		System.out.println("Compare Value:");
		System.out.println(compareFloat);
		
		Assert.assertEquals(sumRound, compareFloat);
		
	}

	private static float round(float sumRound, int places) {
	    if (places < 0) throw new IllegalArgumentException();
	    BigDecimal bd = new BigDecimal(Float.toString(sumRound));
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.floatValue();
	}
}
