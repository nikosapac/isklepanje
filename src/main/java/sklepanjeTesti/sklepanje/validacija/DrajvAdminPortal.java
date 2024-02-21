package sklepanjeTesti.sklepanje.validacija;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import sklepanjeTesti.Funkcije;
import sklepanjeTesti.Prijave;

public class DrajvAdminPortal {

	WebDriver driver;
	
	public DrajvAdminPortal(WebDriver driver) {
		this.driver = driver;
	}
	
	String triglavDigiUserLink = "https://zt-ca-si-np-01-euw-appsvc-drajv3-ap-qa-01.azurewebsites.net/discounts/detail/cf31dbe9-b0cc-42ed-b9e8-6689e5007b37/c4c8866a-525b-4041-a340-ab90acc4fe46";
	
	public void DrajvPortalLogin() throws Exception {
		Funkcije f = new Funkcije(driver);
		Prijave p = new Prijave(driver);
		
		driver.get("https://zt-ca-si-np-01-euw-appsvc-drajv3-ap-qa-01.azurewebsites.net/login");
		
		Thread.sleep(1000);
		f.implicitWait(10);
		
		driver.findElement(By.xpath("//*[contains(text(),'Sign in')]")).click();
		
		Thread.sleep(4000);
		
		// spremeni okno na login
		
		String currentWindowHandle = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();
        
        for (String windowHandle : windowHandles) {
            if (!windowHandle.equals(currentWindowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        
        Thread.sleep(4000);
		
        f.VpisEmaila();
        
        Thread.sleep(1500);
        
        driver.switchTo().window(currentWindowHandle);
        
        Thread.sleep(3500);
        
        f.implicitWait(20);
	}
	
	public void DashboardTest() throws Exception {
		 
        // Dashboard
        
        Thread.sleep(4000);
        
        List<WebElement> data = driver.findElements(By.className("number-label"));
        
        for(WebElement ele : data) {
        	System.out.println(ele.getText());
        	if(ele.getText() == null) {
        		throw new Exception("Prišlo je do napake pri: " + ele.toString());
        	}
        }
		
	}
	
	public void UsersTest() throws Exception {
		
		Funkcije f = new Funkcije(driver);
		Prijave p = new Prijave(driver);
        
        // Zgenerira kodo in preveri če se je shranila
        
        Thread.sleep(4000);
        
        driver.get(triglavDigiUserLink);
        
        Thread.sleep(1000);
        
        f.implicitWait(10);
        
        Thread.sleep(3000);
        
        List<WebElement> kodePred = driver.findElements(By.xpath("//*[contains(text(),'Additional code generated')]"));
        
        driver.findElement(By.xpath("//*[contains(text(),'Generate additional discount code')]")).click();
        
        Thread.sleep(1000);
        f.implicitWait(10);
        
        driver.findElement(By.xpath("//*[contains(text(),'Yes, generate additional discount code')]")).click();
                
        Thread.sleep(4000);
        f.implicitWait(10);
        
        driver.get(triglavDigiUserLink);
        
        Thread.sleep(4000);
        f.implicitWait(10);
        
        List<WebElement> ele = driver.findElements(By.xpath("//*[contains(text(),'Additional code generated')]"));
        f.implicitWait(10);

        List<WebElement> kodePo = driver.findElements(By.xpath("//*[contains(text(),'Additional code generated')]"));
        
    //    System.out.println(ele.toString());
        f.implicitWait(10);
        Thread.sleep(1000);
        WebElement odpri = ele.get(ele.size() -1);
        f.implicitWait(10);
        Thread.sleep(1000);
        odpri.click();
        
        Thread.sleep(1000);
        f.implicitWait(10);
        
        String koda = driver.findElement(By.xpath("(//*[@class='col col-3'])[2]")).getText();
        
        System.out.println(koda);
        
        if(kodePred == kodePo) {
        	throw new Exception("Prišlo je do napake pri kodi. kodePred: " + kodePred + " kodePo: " + kodePo);
        }  
    
        
	}
	
	 public void notMigratedUsersTest() throws Exception {
		 
		 Funkcije f = new Funkcije(driver);
		 Prijave p = new Prijave(driver);
		 
		 driver.get("https://zt-ca-si-np-01-euw-appsvc-drajv3-ap-qa-01.azurewebsites.net/accounts-not-migrated/");
		 
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("data-table")));

	        Thread.sleep(5000);
     	
		 List<WebElement> userji = driver.findElements(By.xpath("//*[@id=\"data-table\"]/div/div[1]/table/tbody/tr"));
		 
	//	 List<WebElement> userji = driver.findElements(By.tagName("tr"));
		 
		 System.out.println("Ne migrirani userji: " + userji.size());
		 
		 if(userji.size() == 0) {
			 throw new Exception("Ne prikaže ne migriranih uuporabnikov");
		 }
		 
     }
	
	 
	 public void tripsTest() throws Exception {
		 
		 Funkcije f = new Funkcije(driver);
		 Prijave p = new Prijave(driver);
		 
		 driver.get("https://zt-ca-si-np-01-euw-appsvc-drajv3-ap-qa-01.azurewebsites.net/trips/2936367103409352667/");
		 
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("data-table")));

	        Thread.sleep(4000);
		 
		 List<WebElement> userji = driver.findElements(By.xpath("//*[@id=\"data-table\"]/div/div[1]/table/tbody/tr"));
		 
	//	 List<WebElement> userji = driver.findElements(By.tagName("tr"));
		 
		 System.out.println("Trips userji: " + userji.size());
		 
		 if(userji.size() == 0) {
			 throw new Exception("Ne prikaže ne tripov uporabnika");
		 }
		 
		 WebElement user = userji.get(0);
		 
		 user.click();
		 
		 Thread.sleep(7000);
		 f.implicitWait(10);
		 
		 List<WebElement> data = driver.findElements(By.className("text--primary"));
	        
        for(WebElement ele : data) {
        	System.out.println(ele.getText());
        	if(ele.getText() == null) {
        		throw new Exception("Prišlo je do napake pri: " + ele.toString());
        	}
        }
		 
	 }
	 
	 public void contestsRewards() throws Exception {
		 
		 Funkcije f = new Funkcije(driver);
		 Prijave p = new Prijave(driver);
		 
		 driver.get("https://zt-ca-si-np-01-euw-appsvc-drajv3-ap-qa-01.azurewebsites.net/contests/rewards");
		 
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("data-table")));

	     Thread.sleep(4000);
		 
	     List<WebElement> rewards = driver.findElements(By.xpath("//*[@id=\"data-table\"]/div/div[1]/table/tbody/tr"));
	     
	     System.out.println("Rewards " + rewards.size());
	     
	     if(rewards.size() == 0) {
			 throw new Exception("Ne prikaže rewards");
		 }
	     
	     WebElement reward = rewards.get(0);
		 
		 reward.click();
		 
		 Thread.sleep(7000);
		 f.implicitWait(10);
	     
		 List<WebElement> data = driver.findElements(By.className("text--primary"));
	        
	        for(WebElement ele : data) {
	        	System.out.println(ele.getText());
	        	if(ele.getText() == null) {
	        		throw new Exception("Prišlo je do napake pri: " + ele.toString());
	        	}
	        }    
	 }
	 
	 public void contestsRewardsPool() throws Exception {
		 
		 Funkcije f = new Funkcije(driver);
		 
		 driver.get("https://zt-ca-si-np-01-euw-appsvc-drajv3-ap-qa-01.azurewebsites.net/contests/rewards-pool");
		 
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("data-table")));

	     Thread.sleep(4000);
		 
	     List<WebElement> rewardsPool = driver.findElements(By.xpath("//*[@id=\"data-table\"]/div/div[1]/table/tbody/tr"));
	     
	     System.out.println("Rewards pool" + rewardsPool.size());
	     
	     if(rewardsPool.size() == 0) {
			 throw new Exception("Ne prikaže rewardsPool");
		 }
	     
	     WebElement reward = rewardsPool.get(0);
		 
		 reward.click();
		 
		 Thread.sleep(7000);
		 f.implicitWait(10);
	     
		 List<WebElement> data = driver.findElements(By.className("text--primary"));
	        
	        for(WebElement ele : data) {
	        	System.out.println(ele.getText());
	        	if(ele.getText() == null) {
	        		throw new Exception("Prišlo je do napake pri: " + ele.toString());
	        	}
	        }    
		 
	 }
	 
	 public void contestsTemplates() throws Exception {
		 
		 Funkcije f = new Funkcije(driver);
		 
		 driver.get("https://zt-ca-si-np-01-euw-appsvc-drajv3-ap-qa-01.azurewebsites.net/contests/templates");
		 
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("data-table")));

	     Thread.sleep(4000);
		 
	     List<WebElement> templates = driver.findElements(By.xpath("//*[@id=\"data-table\"]/div/div[1]/table/tbody/tr"));
	     
	     System.out.println("templates: " + templates.size());
	     
	     if(templates.size() == 0) {
			 throw new Exception("Ne prikaže templates");
		 }
	     
	     WebElement reward = templates.get(0);
		 
		 reward.click();
		 
		 Thread.sleep(7000);
		 f.implicitWait(10);
	     
		 List<WebElement> data = driver.findElements(By.className("text--primary"));
	        
	        for(WebElement ele : data) {
	        	System.out.println(ele.getText());
	        	if(ele.getText() == null) {
	        		throw new Exception("Prišlo je do napake pri: " + ele.toString());
	        	}
	        }    
		 
	 }
	 
	 public void contests() throws Exception {
		 
		 Funkcije f = new Funkcije(driver);
		 
		 driver.get("https://zt-ca-si-np-01-euw-appsvc-drajv3-ap-qa-01.azurewebsites.net/contests/contests");
		 
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("data-table")));

	     Thread.sleep(4000);
		 
	     List<WebElement> contests = driver.findElements(By.xpath("//*[@id=\"data-table\"]/div/div[1]/table/tbody/tr"));
	     
	     System.out.println("contests: " + contests.size());
	     
	     if(contests.size() == 0) {
			 throw new Exception("Ne prikaže contests");
		 }
	     
	     WebElement contest = contests.get(0);
		 
	     contest.click();
		 
		 Thread.sleep(7000);
		 f.implicitWait(10);
	     
		 List<WebElement> data = driver.findElements(By.className("text--primary"));
	        
	        for(WebElement ele : data) {
	        	System.out.println(ele.getText());
	        	if(ele.getText() == null) {
	        		throw new Exception("Prišlo je do napake pri: " + ele.toString());
	        	}
	        }  
		 
	 }
	 
	 public void badges() throws Exception {
		 
		 Funkcije f = new Funkcije(driver);
		 
		 driver.get("https://zt-ca-si-np-01-euw-appsvc-drajv3-ap-qa-01.azurewebsites.net/badges/");
		 
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("data-table")));

	     Thread.sleep(4000);
		 
	     List<WebElement> badges = driver.findElements(By.xpath("//*[@id=\"data-table\"]/div/div[1]/table/tbody/tr"));
	     
	     System.out.println("badges: " + badges.size());
	     
	     if(badges.size() == 0) {
			 throw new Exception("Ne prikaže badges");
		 }
	     
	     WebElement badge = badges.get(0);
		 
	     badge.click();
		 
		 Thread.sleep(7000);
		 f.implicitWait(10);
	     
		 List<WebElement> data = driver.findElements(By.className("text--primary"));
	        
	        for(WebElement ele : data) {
	        	System.out.println(ele.getText());
	        	if(ele.getText() == null) {
	        		throw new Exception("Prišlo je do napake pri: " + ele.toString());
	        	}
	        }  
		 
	 }
	 
	 public void discounts() throws Exception {
		 
		 Funkcije f = new Funkcije(driver);
		 
		 driver.get("https://zt-ca-si-np-01-euw-appsvc-drajv3-ap-qa-01.azurewebsites.net/discounts/codes/list");
		 
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("data-table")));

	     Thread.sleep(4000);
		 
	     List<WebElement> discounts = driver.findElements(By.xpath("//*[@id=\"data-table\"]/div/div[1]/table/tbody/tr"));
	     
	     System.out.println("discounts: " + discounts.size());
	     
	     if(discounts.size() == 0) {
			 throw new Exception("Ne prikaže discounts");
		 }
	     
	     WebElement discount = discounts.get(0);
		 
	     discount.click();
		 
		 Thread.sleep(7000);
		 f.implicitWait(10);
	     
		 List<WebElement> data = driver.findElements(By.className("text--primary"));
	        
	        for(WebElement ele : data) {
	        	System.out.println(ele.getText());
	        	if(ele.getText() == null) {
	        		throw new Exception("Prišlo je do napake pri: " + ele.toString());
	        	}
	        }  
		 
	 }
	 
	 public void news() throws Exception {
		 
		 Funkcije f = new Funkcije(driver);
		 
		 driver.get("https://zt-ca-si-np-01-euw-appsvc-drajv3-ap-qa-01.azurewebsites.net/news/");
		 
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("data-table")));

	     Thread.sleep(4000);
		 
	     List<WebElement> news = driver.findElements(By.xpath("//*[@id=\"data-table\"]/div/div[1]/table/tbody/tr"));
	     
	     System.out.println("news: " + news.size());
	     
	     if(news.size() == 0) {
			 throw new Exception("Ne prikaže news");
		 }
	     
	     WebElement discount = news.get(0);
		 
	     discount.click();
		 
		 Thread.sleep(7000);
		 f.implicitWait(10);
	     
		 List<WebElement> data = driver.findElements(By.className("text--primary"));
	        
	        for(WebElement ele : data) {
	        	System.out.println(ele.getText());
	        	if(ele.getText() == null) {
	        		throw new Exception("Prišlo je do napake pri: " + ele.toString());
	        	}
	        }  
		 
	 }
	 
	 public void pushNotifications() throws Exception {
		 
		 Funkcije f = new Funkcije(driver);
		 
		 driver.get("https://zt-ca-si-np-01-euw-appsvc-drajv3-ap-qa-01.azurewebsites.net/push-notification/");
		 
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("data-table")));

	     Thread.sleep(4000);
		 
	     List<WebElement> pushNotifications = driver.findElements(By.xpath("//*[@id=\"data-table\"]/div/div[1]/table/tbody/tr"));
	     
	     System.out.println("pushNotifications: " + pushNotifications.size());
	     
	     if(pushNotifications.size() == 0) {
			 throw new Exception("Ne prikaže pushNotifications");
		 }
	     
	     WebElement discount = pushNotifications.get(0);
		 
	     discount.click();
		 
		 Thread.sleep(7000);
		 f.implicitWait(10);
	     
		 List<WebElement> data = driver.findElements(By.className("text--primary"));
	        
	        for(WebElement ele : data) {
	        	System.out.println(ele.getText());
	        	if(ele.getText() == null) {
	        		throw new Exception("Prišlo je do napake pri: " + ele.toString());
	        	}
	        }  
		 
	 }
	 
	
}
