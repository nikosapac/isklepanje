package sklepanjeTesti.sklepanje.validacija;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.json.JSONObject;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import sklepanjeTesti.Funkcije;
import sklepanjeTesti.Prijave;

public class DrajvKodaValidacija {

	WebDriver driver;
	
	public static String drajvKoda;
	
	public DrajvKodaValidacija(WebDriver driver) {
		this.driver = driver;
	}
	
	public void DeleteUser() throws Exception {
		
		try {
		    SSLContext sslContext = SSLContext.getInstance("SSL");
		    TrustManager[] trustManagers = new TrustManager[] {new X509TrustManager() {
		        public X509Certificate[] getAcceptedIssuers() {
		            return new X509Certificate[0];
		        }
		        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {}
		        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {}
		    }};
		    sslContext.init(null, trustManagers, new SecureRandom());
		    HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
		} catch (NoSuchAlgorithmException | KeyManagementException e) {
		    e.printStackTrace();
		}
		
		String email = "triglav.digi2%40gmail.com";
		String url = "https://qa-prem-zt.cloud.triglav.pri/triglav-login-api/private/get-user-by-email?email=" + email;

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		// add request header
		con.setRequestProperty("User-Agent", "Mozilla/5.0");

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		if (responseCode == 200) {

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}

			in.close();

			// get ssoId
			String jsonResponse = response.toString();
			JSONObject jsonObject = new JSONObject(jsonResponse);

			String ssoId = jsonObject.getJSONObject("data").getString("ssoId");
			System.out.println("SSO ID: " + ssoId);

			// delete user by ssoid

			String urldelete = "https://qa-prem-zt.cloud.triglav.pri/triglav-login-api/private/delete-user-from-sso-db?ssoId="
					+ ssoId;

			URL objdelete = new URL(urldelete);
			HttpURLConnection condelete = (HttpURLConnection) objdelete.openConnection();

			condelete.setRequestMethod("POST");
			condelete.setRequestProperty("User-Agent", "Mozilla/5.0");
			condelete.setDoOutput(true);

			DataOutputStream wr = new DataOutputStream(condelete.getOutputStream());
			wr.flush();
			wr.close();
			

			int responseCodeDelete = condelete.getResponseCode();
			System.out.println("\nSending 'DELETE' request to URL : " + urldelete);
			System.out.println("Response Code : " + responseCodeDelete);

			// delete by oId FROM AZURE DB

			String urldeleteAzure = "https://qa-prem-zt.cloud.triglav.pri/triglav-login-api/private/delete-user-from-azure?oid="
					+ ssoId;

			URL objdeleteAzure = new URL(urldeleteAzure);
			HttpURLConnection condeleteAzure = (HttpURLConnection) objdeleteAzure.openConnection();

			condeleteAzure.setRequestMethod("POST");
			condeleteAzure.setRequestProperty("User-Agent", "Mozilla/5.0");
			condeleteAzure.setDoOutput(true);

			DataOutputStream wrAzure = new DataOutputStream(condeleteAzure.getOutputStream());
			wrAzure.flush();
			wrAzure.close();

			int responseCodeDeleteAzure = condeleteAzure.getResponseCode();
			System.out.println("\nSending 'DELETE' request to URL : " + urldeleteAzure);
			System.out.println("Response Code : " + responseCodeDeleteAzure);
		}
		
	}
	
	public void DrajvKoda() throws Exception {
		
		Funkcije f = new Funkcije(driver);
		Prijave p = new Prijave(driver);
		
		String email = "triglav.digi2@gmail.com";
		
		//  Generiranje kode
		
		driver.get("https://zt-ca-si-np-01-euw-appsvc-drajv3-ap-qa-01.azurewebsites.net/login");
		
		Thread.sleep(1000);
		f.implicitWait(10);
		
		driver.findElement(By.xpath("//*[contains(text(),'Sign in')]")).click();
		
		Thread.sleep(2000);
		
		// spremeni okno na login
		
		String currentWindowHandle = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();
        
        for (String windowHandle : windowHandles) {
            if (!windowHandle.equals(currentWindowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        
        Thread.sleep(2000);
		
        f.VpisEmaila();
        
        Thread.sleep(1500);
        
        driver.switchTo().window(currentWindowHandle);
        
        Thread.sleep(1500);
        
        f.implicitWait(20);
        
        // Vzame drajv kodo
        /*
        driver.findElement(By.xpath("//*[text()='Users']")).click();
        
        f.implicitWait(10);
        
        f.waitForLoaderToFinish(driver, By.xpath("//*[text()='Loading items...']"));
        
        f.implicitWait(10);
        
        driver.findElement(By.id("input-139")).sendKeys(email);
        
        Thread.sleep(1000);
        
        f.implicitWait(10);
        
        driver.findElement(By.id("input-139")).sendKeys(Keys.ENTER);
        
        Thread.sleep(1000);
        
        f.implicitWait(10);
        
        driver.findElement(By.xpath("(//*[@class='table-row-item'])[1]//a")).click();
        
        Thread.sleep(1000);
        
        f.implicitWait(10);
        
        driver.findElement(By.xpath("//*[text()=' See discounts ']")).click();
        */
        
        Thread.sleep(2000);
        
        driver.get("https://zt-ca-si-np-01-euw-appsvc-drajv3-ap-qa-01.azurewebsites.net/discounts/detail/bdf4eaba-b68c-40a6-809d-e01c2efa456d/b3a2b480-3c2c-42bc-9e3b-6d78a6c19bd8");
        
        Thread.sleep(1000);
        
        f.implicitWait(10);
        
        Thread.sleep(3000);
        
        driver.findElement(By.xpath("//*[contains(text(),'Generate additional discount code')]")).click();
        
        Thread.sleep(1000);
        f.implicitWait(10);
        
        driver.findElement(By.xpath("//*[contains(text(),'Yes, generate additional discount code')]")).click();
                
        Thread.sleep(2000);
        f.implicitWait(10);
        
        driver.get("https://zt-ca-si-np-01-euw-appsvc-drajv3-ap-qa-01.azurewebsites.net/discounts/detail/bdf4eaba-b68c-40a6-809d-e01c2efa456d/b3a2b480-3c2c-42bc-9e3b-6d78a6c19bd8");
        
        Thread.sleep(2000);
        f.implicitWait(10);
        
        List<WebElement> ele = driver.findElements(By.xpath("//*[contains(text(),'Additional code generated')]"));
        f.implicitWait(10);

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
        
        setDrajvKoda(koda);
        
	}
	
	@Test
	public void AvtomobiliTestDrajvKoda() throws Exception {

		Funkcije funk = new Funkcije(driver);
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
      //datum zacetka zavarovanja  
        Calendar calendar1 = Calendar.getInstance();
        calendar1.add(Calendar.MONTH, 1); 
      //datum poteka zavarovanja
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 12);   
        
      //Šasija
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 17;
        Random random = new Random();
        String sasija = random.ints(leftLimit, rightLimit + 1)
        	      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
        	      .limit(targetStringLength)
        	      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
        	      .toString();
	        
     //Fiksne vrednosti
        String urlZavarovanja= "https://skleni-qa.triglav.si/isklepanje/avto/avto_zavarovanje";
        String LDAPusername = "tsalhorvat";
	    String LDAPgeslo = "gremonapivo";
        String telefonska = "031532734";
        String registrska = "MS18-NJV";
        String znamka = "vw";
        String model = "t-roc (2017-2022)";
        String tip = "T-Roc 1,5 TSI BMT Sport (10.2018-07.2022)";
        String firstRegistration ="16.9.2020";
        String kilometri = "5000";
        String datumZacetkaZavarovanja = formatter.format(calendar1.getTime());
	    String datumPotekZavarovanja = formatter.format(calendar.getTime());
	    String ime = "Nikita";
	    String priimek = "Plej";
	    String datumRojstva = "18.08.1998";
	    String davcna = "19553170";
	    String postnaStevilka = "9000";
	    String email ="zkpr.test@triglav.si";
	    String validacijskaKoda = "9999";
	    String imeOtroka = "Janček";
	    String priimekOtroka = "Ježek";
	    String datumRojstvaOtroka = "16.9.2020";
	    String davcnaOtroka = "65694350";
	    
	    
	  //Za scrollanje - do polovice ekrana
	    
	    int viewportHeight = ((Long) ((JavascriptExecutor) driver).executeScript("return window.innerHeight")).intValue();
	    int halfViewport = viewportHeight / 2;
                
	    Prijave p = new Prijave(driver);
	  //  p.ItriglavPrijavaVnos("niko.sapac@triglav.si","Testgeslo123");
	        
		 //Odpri stran 
		    
		    p.SklepanjePrijava();
		    
		    driver.get(urlZavarovanja);
		    
		    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		    
		    if(funk.IsVisible(By.xpath("//a[text()='PRIJAVA']"))) {
		    	driver.findElement(By.xpath("//a[text()='PRIJAVA']")).click();
		    }
		    
		    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		    
		    p.ItriglavPrijavaSklepanje("triglav.digi2@gmail.com", "Test@123");
		    
		    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		    
		    funk.ScrollInKlik("//*[text()='Skleni novo']");
		    
		    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		    
		  //PODATKI O VOZILU -  https://skleni-qa.triglav.si/isklepanje/avtomobilno/avto_zavarovanje_1
		   
		    funk.ScrollInKlik3(By.id("regnumNum"));
		    driver.findElement(By.id("regnumNum")).sendKeys(registrska);
		    funk.ScrollInKlik3(By.id("btnGetVehicleData"));
		    
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			
			Thread.sleep(3000);
		    funk.ScrollInKlik3(By.xpath("(//span[@class='select2-selection__arrow'])[1]"));
		    Thread.sleep(1000);
		    driver.findElement(By.className("select2-search__field")).click();
		    Thread.sleep(2000);
		    driver.findElement(By.className("select2-search__field")).sendKeys(znamka);
		    Thread.sleep(1000);
		    driver.findElement(By.className("select2-search__field")).sendKeys(Keys.ENTER);
		    Thread.sleep(1000);
		    funk.ScrollInKlik3(By.xpath("(//span[@class='select2-selection__arrow'])[2]"));
		    Thread.sleep(1000);
		    driver.findElement(By.className("select2-search__field")).click();
		    Thread.sleep(1000);
		    driver.findElement(By.className("select2-search__field")).sendKeys(model);
		    Thread.sleep(1000);
		    driver.findElement(By.className("select2-search__field")).sendKeys(Keys.ENTER);
		    Thread.sleep(1000);
		    funk.ScrollInKlik3(By.xpath("(//span[@class='select2-selection__arrow'])[3]"));
		    Thread.sleep(1000);
		    driver.findElement(By.className("select2-search__field")).click();
		    Thread.sleep(1000);
		    driver.findElement(By.className("select2-search__field")).sendKeys(tip);
		    Thread.sleep(1000);
		    driver.findElement(By.className("select2-search__field")).sendKeys(Keys.ENTER);
		    Thread.sleep(3000);
		    
		    funk.implicitWait(10);
			
		    funk.ScrollInKlik3(By.id("vehiclePolicy.vehicle.vehicleIdentificationNumber"));
		    Thread.sleep(1000);
		    driver.findElement(By.id("vehiclePolicy.vehicle.vehicleIdentificationNumber")).sendKeys(sasija);
		    driver.findElement(By.id("vehiclePolicy.vehicle.firstRegistration")).sendKeys(firstRegistration);
		    Thread.sleep(1500);
		    			
		    funk.ScrollInKlik3(By.id("vehiclePolicy.vehicle.milageKm"));
		    Thread.sleep(1000);
		    driver.findElement(By.id("vehiclePolicy.vehicle.milageKm")).sendKeys(kilometri);
		    Thread.sleep(1000);
			
		    funk.ScrollInKlik3(By.id("vehiclePolicy.insuranceConclusion.policyStartDate"));
		    Thread.sleep(1000);
		    driver.findElement(By.id("vehiclePolicy.insuranceConclusion.policyStartDate")).click();

		    Thread.sleep(1500);
		    funk.ScrollInKlik3(By.className("checkbox--primary__icon"));
		    Thread.sleep(1000);
		    Thread.sleep(1000);
		    driver.findElement(By.id("vehiclePolicy.insuranceConclusion.policyEndDate")).sendKeys(datumPotekZavarovanja);
		    Thread.sleep(1000);
			
		    funk.ScrollInKlik3(By.className("btn-next"));
		    new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.urlToBe("https://skleni-qa.triglav.si/isklepanje/avto/avto_zavarovanje_2"));
		    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		    Thread.sleep(3000);
		    
		  //IZBERI PAKET - https://skleni-qa.triglav.si/isklepanje/avtomobilno/avto_zavarovanje_2
			  
		    Thread.sleep(1000);
		   funk.ScrollInKlik3(By.className("btn-next"));
		    Thread.sleep(3000);
		    
		    new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.urlToBe("https://skleni-qa.triglav.si/isklepanje/avto/avto_zavarovanje_3"));
		    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		    Thread.sleep(1000);
		    
		  //PODATKI O SKLENITELJU - https://skleni-qa.triglav.si/isklepanje/avtomobilno/avto_zavarovanje_3
		    
		//    funk.ScrollInKlik("(//span[@class='checkbox--primary__icon'])[2]");
		    
		    funk.ScrollToElement(By.id("policyHolderUI.phoneNumber"));
		    funk.implicitWait(10);
		    driver.findElement(By.id("policyHolderUI.phoneNumber")).sendKeys("031532734");
		    
		    funk.ScrollInKlik("//*[@for='potrjujem-vse']");
		    Thread.sleep(500);
		    funk.ScrollInKlik3(By.className("btn-next"));
		    
		    new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.urlToBe("https://skleni-qa.triglav.si/isklepanje/avto/avto_zavarovanje_4"));
		    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		    Thread.sleep(3000);
		    
		  //PREMIJA IN UGODNOSTI - https://skleni-qa.triglav.si/isklepanje/avtomobilno/avto_zavarovanje_4
		    		    
		    //POPUST DRAJV
		    
		//    funk.ScrollInKlik("(//*[@text()='Dodaj'])[2]");
		    
		    funk.ScrollInKlik("//*[@id=\"informativeCalculation\"]/div[2]/div[2]/div[2]/div[2]/a");
		    
		    Thread.sleep(1000);
		    funk.implicitWait(10);
		    
		    driver.findElement(By.id("ipdDrajvCode")).sendKeys(drajvKoda);
		    
		    funk.implicitWait(10);
		    
		    driver.findElement(By.id("btnAddDrajvCode")).click();
		    
		    funk.waitForLoaderToFinish(driver, By.className("loader"));
		    
		    Thread.sleep(2000);
		    funk.implicitWait(10);
		    
		    if(driver.getPageSource().contains("Uspešno ste dodali DRAJV kodo in prejeli")) {
		    	driver.findElement(By.xpath("//*[@id=\"drajvSuccess\"]/div/div/div/a")).click();
		    } else {
		    	throw new Exception("Prišlo je do napake pri drajv kodi");
		    }
		    
		    
		    // Pogoji
		    
		    Thread.sleep(1000);
			  
		    funk.ScrollInKlik("//*[@for='zavarovalnimi-pogoji']");
			Thread.sleep(500);
			WebElement test = driver.findElement(By.xpath("//*[@for='info-zavarovalni-paket-mobile']"));
			new Actions(driver).moveToElement(test, 2, 2).click().perform();
		    	
		    funk.ScrollInKlik3(By.className("btn-next"));
		    
		    funk.waitForLoaderToFinish(driver, By.className("loader"));
		    
		    new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.urlToBe("https://skleni-qa.triglav.si/isklepanje/avto/avto_zavarovanje_5"));
		    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		    
		  //PLAČILO - https://skleni-qa.triglav.si/isklepanje/avtomobilno/avto_zavarovanje_5
		     
		    driver.findElement(By.className("radio--secondary__button")).click();
		    Thread.sleep(1000);
		    driver.findElement(By.className("radio-quaternary__button")).click();
		    Thread.sleep(1000);
		    
		    funk.ScrollInKlik("//button[@name='simulatePayment']");
		    
		    Thread.sleep(3000);
		    		    
		    WebElement ele = driver.findElement(By.xpath("//*[text()='Hvala!']"));
			
			new WebDriverWait(driver,Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOf(ele));
			
			if(ele.isDisplayed() == true) {
				System.out.println("Sklenitev je bila uspešna");
			} else {
				System.out.println("Sklenitev NI bila uspešna");
			}    
		   
	}
	
	

	@Test
	public void AvtomobiliTestVsiPopusti() throws Exception {

		Funkcije funk = new Funkcije(driver);
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
      //datum zacetka zavarovanja  
        Calendar calendar1 = Calendar.getInstance();
        calendar1.add(Calendar.MONTH, 1); 
      //datum poteka zavarovanja
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 12);   
        
      //Šasija
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 17;
        Random random = new Random();
        String sasija = random.ints(leftLimit, rightLimit + 1)
        	      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
        	      .limit(targetStringLength)
        	      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
        	      .toString();
	        
     //Fiksne vrednosti
        String urlZavarovanja= "https://skleni-qa.triglav.si/isklepanje/avto/avto_zavarovanje";
        String LDAPusername = "tsalhorvat";
	    String LDAPgeslo = "gremonapivo";
        String telefonska = "031532734";
        String registrska = "MS18-NJV";
        String znamka = "vw";
        String model = "t-roc (2017-2022)";
        String tip = "T-Roc 1,5 TSI BMT Sport (10.2018-07.2022)";
        String firstRegistration ="16.9.2020";
        String kilometri = "5000";
        String datumZacetkaZavarovanja = formatter.format(calendar1.getTime());
	    String datumPotekZavarovanja = formatter.format(calendar.getTime());
	    String ime = "Nikita";
	    String priimek = "Plej";
	    String datumRojstva = "18.08.1998";
	    String davcna = "19553170";
	    String postnaStevilka = "9000";
	    String email ="zkpr.test@triglav.si";
	    String validacijskaKoda = "9999";
	    String imeOtroka = "Janček";
	    String priimekOtroka = "Ježek";
	    String datumRojstvaOtroka = "16.9.2020";
	    String davcnaOtroka = "65694350";
	    
	    
	  //Za scrollanje - do polovice ekrana
	    
	    int viewportHeight = ((Long) ((JavascriptExecutor) driver).executeScript("return window.innerHeight")).intValue();
	    int halfViewport = viewportHeight / 2;
                
	    Prijave p = new Prijave(driver);
	  //  p.ItriglavPrijavaVnos("niko.sapac@triglav.si","Testgeslo123");
	        
		 //Odpri stran 
		    
		    p.SklepanjePrijava();
		    
		    driver.get(urlZavarovanja);
		    
		    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		    
		    if(funk.IsVisible(By.xpath("//a[text()='PRIJAVA']"))) {
		    	driver.findElement(By.xpath("//a[text()='PRIJAVA']")).click();
		    }
		    
		    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		    
		    p.ItriglavPrijavaSklepanje("triglav.digi2@gmail.com", "Test@123");
		    
		    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		    
		    funk.ScrollInKlik("//*[text()='Skleni novo']");
		    
		    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		    
		  //PODATKI O VOZILU -  https://skleni-qa.triglav.si/isklepanje/avtomobilno/avto_zavarovanje_1
		   
		    funk.ScrollInKlik3(By.id("regnumNum"));
		    driver.findElement(By.id("regnumNum")).sendKeys(registrska);
		    funk.ScrollInKlik3(By.id("btnGetVehicleData"));
		    
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			
			Thread.sleep(3000);
		    funk.ScrollInKlik3(By.xpath("(//span[@class='select2-selection__arrow'])[1]"));
		    Thread.sleep(1000);
		    driver.findElement(By.className("select2-search__field")).click();
		    Thread.sleep(2000);
		    driver.findElement(By.className("select2-search__field")).sendKeys(znamka);
		    Thread.sleep(1000);
		    driver.findElement(By.className("select2-search__field")).sendKeys(Keys.ENTER);
		    Thread.sleep(1000);
		    funk.ScrollInKlik3(By.xpath("(//span[@class='select2-selection__arrow'])[2]"));
		    Thread.sleep(1000);
		    driver.findElement(By.className("select2-search__field")).click();
		    Thread.sleep(1000);
		    driver.findElement(By.className("select2-search__field")).sendKeys(model);
		    Thread.sleep(1000);
		    driver.findElement(By.className("select2-search__field")).sendKeys(Keys.ENTER);
		    Thread.sleep(1000);
		    funk.ScrollInKlik3(By.xpath("(//span[@class='select2-selection__arrow'])[3]"));
		    Thread.sleep(1000);
		    driver.findElement(By.className("select2-search__field")).click();
		    Thread.sleep(1000);
		    driver.findElement(By.className("select2-search__field")).sendKeys(tip);
		    Thread.sleep(1000);
		    driver.findElement(By.className("select2-search__field")).sendKeys(Keys.ENTER);
		    Thread.sleep(3000);
		    
		    funk.implicitWait(10);
			
		    funk.ScrollInKlik3(By.id("vehiclePolicy.vehicle.vehicleIdentificationNumber"));
		    Thread.sleep(1000);
		    driver.findElement(By.id("vehiclePolicy.vehicle.vehicleIdentificationNumber")).sendKeys(sasija);
		    driver.findElement(By.id("vehiclePolicy.vehicle.firstRegistration")).sendKeys(firstRegistration);
		    Thread.sleep(1500);
		    			
		    funk.ScrollInKlik3(By.id("vehiclePolicy.vehicle.milageKm"));
		    Thread.sleep(1000);
		    driver.findElement(By.id("vehiclePolicy.vehicle.milageKm")).sendKeys(kilometri);
		    Thread.sleep(1000);
			
		    funk.ScrollInKlik3(By.id("vehiclePolicy.insuranceConclusion.policyStartDate"));
		    Thread.sleep(1000);
		    driver.findElement(By.id("vehiclePolicy.insuranceConclusion.policyStartDate")).click();

		    Thread.sleep(1500);
		    funk.ScrollInKlik3(By.className("checkbox--primary__icon"));
		    Thread.sleep(1000);
		    Thread.sleep(1000);
		    driver.findElement(By.id("vehiclePolicy.insuranceConclusion.policyEndDate")).sendKeys(datumPotekZavarovanja);
		    Thread.sleep(1000);
			
		    funk.ScrollInKlik3(By.className("btn-next"));
		    new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.urlToBe("https://skleni-qa.triglav.si/isklepanje/avto/avto_zavarovanje_2"));
		    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		    Thread.sleep(3000);
		    
		  //IZBERI PAKET - https://skleni-qa.triglav.si/isklepanje/avtomobilno/avto_zavarovanje_2
			  
		    Thread.sleep(1000);
		   funk.ScrollInKlik3(By.className("btn-next"));
		    Thread.sleep(3000);
		    
		    new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.urlToBe("https://skleni-qa.triglav.si/isklepanje/avto/avto_zavarovanje_3"));
		    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		    Thread.sleep(1000);
		    
		  //PODATKI O SKLENITELJU - https://skleni-qa.triglav.si/isklepanje/avtomobilno/avto_zavarovanje_3
		    
		//    funk.ScrollInKlik("(//span[@class='checkbox--primary__icon'])[2]");
		    
		    funk.ScrollToElement(By.id("policyHolderUI.phoneNumber"));
		    funk.implicitWait(10);
		    driver.findElement(By.id("policyHolderUI.phoneNumber")).sendKeys("031532734");
		    
		    funk.ScrollInKlik("(//*[@class='checkbox--primary__icon'])[2]");
		    Thread.sleep(500);
		    funk.ScrollInKlik3(By.className("btn-next"));
		    
		    new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.urlToBe("https://skleni-qa.triglav.si/isklepanje/avto/avto_zavarovanje_4"));
		    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		    Thread.sleep(3000);
		    
		  //PREMIJA IN UGODNOSTI - https://skleni-qa.triglav.si/isklepanje/avtomobilno/avto_zavarovanje_4
		    		    
		    //POPUST DRAJV
		    
		//    funk.ScrollInKlik("(//*[@text()='Dodaj'])[2]");
		    
		    funk.ScrollInKlik("//*[@id=\"informativeCalculation\"]/div[2]/div[2]/div[2]/div[2]/a");
		    
		    Thread.sleep(1000);
		    funk.implicitWait(10);
		    
		    driver.findElement(By.id("ipdDrajvCode")).sendKeys(drajvKoda);
		    
		    funk.implicitWait(10);
		    
		    driver.findElement(By.id("btnAddDrajvCode")).click();
		    
		    funk.waitForLoaderToFinish(driver, By.className("loader"));
		    
		    Thread.sleep(2000);
		    funk.implicitWait(10);
		    
		    if(driver.getPageSource().contains("Uspešno ste dodali DRAJV kodo in prejeli")) {
		    	driver.findElement(By.xpath("//*[@id=\"drajvSuccess\"]/div/div/div/a")).click();
		    } else {
		    	throw new Exception("Prišlo je do napake pri drajv kodi");
		    }
		    
		    Thread.sleep(2000);
		    
		  //POPUST MLADA DRUŽINA
		    
		   funk.ScrollInKlik3(By.id("btnCarVnosOtroka"));
			Thread.sleep(1000);
			driver.findElement(By.id("name")).sendKeys(imeOtroka);
		    driver.findElement(By.id("surname")).sendKeys(priimekOtroka);
		    driver.findElement(By.id("birthday")).sendKeys(datumRojstvaOtroka);
		    driver.findElement(By.id("taxnum")).sendKeys(davcnaOtroka);
		    Thread.sleep(1000);
			
		    Thread.sleep(1000);
		    funk.ScrollInKlik3(By.id("saveChildData"));
		    
		    funk.waitForLoaderToFinish(driver, By.className("loader"));
		    Thread.sleep(1000);
		    driver.findElement(By.xpath("//*[@id=\"carChildSuccess\"]/div/div/div/a")).click();		
		    
		    // Pogoji
		    
		    Thread.sleep(1000);
			  
		    funk.ScrollInKlik("//*[@for='zavarovalnimi-pogoji']");
			Thread.sleep(500);
			WebElement test = driver.findElement(By.xpath("//*[@for='info-zavarovalni-paket-mobile']"));
			new Actions(driver).moveToElement(test, 2, 2).click().perform();
		    	
		    funk.ScrollInKlik3(By.className("btn-next"));
		    
		    funk.waitForLoaderToFinish(driver, By.className("loader"));
		    
		    new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.urlToBe("https://skleni-qa.triglav.si/isklepanje/avto/avto_zavarovanje_5"));
		    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		    
		  //PLAČILO - https://skleni-qa.triglav.si/isklepanje/avtomobilno/avto_zavarovanje_5
		     
		    driver.findElement(By.className("radio--secondary__button")).click();
		    Thread.sleep(1000);
		    driver.findElement(By.className("radio-quaternary__button")).click();
		    Thread.sleep(1000);
		    
		    funk.ScrollInKlik("//button[@name='simulatePayment']");
		    
		    Thread.sleep(3000);
		    		    
		    WebElement ele = driver.findElement(By.xpath("//*[text()='Hvala!']"));
			
			new WebDriverWait(driver,Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOf(ele));
			
			if(ele.isDisplayed() == true) {
				System.out.println("Sklenitev je bila uspešna");
			} else {
				System.out.println("Sklenitev NI bila uspešna");
			}    
		   
	}
	
		

	public static String getDrajvKoda() {
		return drajvKoda;
	}

	public void setDrajvKoda(String drajvKoda) {
		DrajvKodaValidacija.drajvKoda = drajvKoda;
	}
	
	
}




