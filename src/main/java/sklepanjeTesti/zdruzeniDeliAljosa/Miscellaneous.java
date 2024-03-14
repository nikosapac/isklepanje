package sklepanjeTesti.zdruzeniDeliAljosa;

import static org.testng.Assert.fail;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import sklepanjeTesti.Funkcije2;

public class Miscellaneous {
	private WebDriver driver;
	private Funkcije2 f;
	
	public Miscellaneous (WebDriver driver) {
		this.driver = driver;
		f = new Funkcije2(driver);
	}
	
	//use only if NOT logged in
	public void CheckTwoButtonsNOloginStep1() throws InterruptedException {
		List<WebElement> buttons = driver.findElements(By.xpath("//div[1]/div/div/div/*[contains(text(), 'Prijava') or contains(text(), 'prijave')]"));
		if (buttons.size() == 2 && buttons.get(0).isDisplayed()) {
			System.out.println("Good, two buttons are showing (not logged in)");
		} else if (buttons.size() == 1 && buttons.get(0).isDisplayed()) {
			throw new AssertionError("Error, only one button is showing (not logged in)");
		}
	}
	
	public void CheckOneButtonShowsLogINstep1() throws InterruptedException {
		List<WebElement> buttons = driver.findElements(By.xpath("//div[1]/div/div/div/*[contains(text(), 'Prijava') or contains(text(), 'prijave') or contains (text(), 'Skleni novo')]"));
		if (buttons.size() == 1 && buttons.get(0).isDisplayed()) {
			System.out.println("Good, one button is showing (logged in)");
		} else if (buttons.size() == 2 && buttons.get(0).isDisplayed()) {
			throw new AssertionError("Error, two buttons are showing (logged in)");
		}
	}
	
	public void InstallmentPaymentSad(String trr) throws InterruptedException {
		f.ScrollInKlik2(By.xpath("(//*[contains(@class, 'radio--secondary__button')])[2]"));
		driver.findElement(By.id("paymentOptions.manualTrrNumber")).sendKeys(trr);
		f.ScrollInKlik2(By.xpath("(//*[@class = 'radio-quaternary__button'])[3]"));
		f.ScrollInKlik2(By.xpath("//*[@value = 'simulatePayment']"));
		driver.findElement(By.id("paymentOptions.soglasjeZaDirektnoObremenitev.errors"));
		f.ScrollInKlik2(By.xpath("(//*[contains(@for, 'soglasam-s-pogoji')])[2]"));
		driver.findElement(By.id("paymentOptions.manualTrrNumber")).clear();
		driver.findElement(By.id("paymentOptions.manualTrrNumber")).sendKeys("SI123456789101112");
		f.ScrollInKlik2(By.xpath("//*[@value = 'simulatePayment']"));
		driver.findElement(By.id("paymentOptions.manualTrrNumber.errors"));
		driver.findElement(By.id("paymentOptions.manualTrrNumber")).clear();
		driver.findElement(By.id("paymentOptions.manualTrrNumber")).sendKeys(trr);
		f.ScrollInKlik2(By.xpath("//*[@value = 'simulatePayment']"));
	}
	
	public void OneTimePayment() throws InterruptedException {
		driver.findElement(By.xpath("(//*[contains(@class, 'radio--secondary__button')])[1]")).click();
		driver.findElement(By.xpath("(//*[@class = 'radio-quaternary__button'])[1]")).click();
		f.ScrollInKlik2(By.xpath("//*[@for = 'soglasam-s-pogoji-celotna-premija']"));
		f.ScrollInKlik2(By.xpath("//*[@value = 'simulatePayment']"));
	}
	
	public void OneTimePaymentNonRecu() throws InterruptedException {
		driver.findElement(By.xpath("(//*[contains(@class, 'radio--secondary__button')])[1]")).click();
		driver.findElement(By.xpath("(//*[@class = 'radio-quaternary__button'])[1]")).click();
	}	
	
	public void InstallmentPaymentHappy(String trr) throws InterruptedException {
		f.ScrollInKlik2(By.xpath("(//*[contains(@class, 'radio--secondary__button')])[2]"));
		driver.findElement(By.id("paymentOptions.manualTrrNumber")).sendKeys(trr);
		f.ScrollInKlik2(By.className("form-checkbox"));
		driver.findElement(By.xpath("(//*[@class = 'radio-quaternary__button'])[3]")).click();
	}
	
	public void ObvescanjeMessage() throws InterruptedException {
		Thread.sleep(1000);	//crucial part of code, if deleted wont find correct URL when "Obvescanje site" shows. Otherwise it will work 90% of the time.
		String urlObv = driver.getCurrentUrl();												// Uporabnik mora imeti izkljuceno elek. obv. v accountu
		System.out.println("Checking for obvescanje URL: " + urlObv);
		if(urlObv.equals("https://skleni-qa.triglav.si/TriglavLogin/e-komuniciranje")){		//ce se sedanji url ujema z tem, se je okno za Elek. Obv. prikazalo
			driver.findElement(By.id("submitButton")).click();
		}	else {
			System.out.println("Okno za aktiviranje Elektronskega Obvescanja se tokrat ni prikazalo");
		}
	}
	
	public void AnketaZadnjiKorak() throws InterruptedException {
		driver.findElement(By.id("anketaLink")).click();
		Thread.sleep(2000);
		ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(newTab.get(1));
		driver.findElements(By.className("survey__question"));
		driver.close();
		driver.switchTo().window(newTab.get(0));
	}
	
	public void DeakTrComplet(String komplet) {
		
		//Default string:
		String defKomplet = "00229918";
		
		if (komplet == null || komplet.isEmpty()) {
			System.out.println("No string given, using default");
			komplet = defKomplet;
		} else {
			System.out.println("Komplet string found");
		}
		
		try (CloseableHttpClient client = HttpClients.createDefault()) {
			HttpGet request = new HttpGet(new URI("http://predprem.triglav.pri/InsureWebServices/restapi/triglav-package/packages/" + komplet + "/deactivate?comment=testiranje"));
		    request.setHeader("Content-Type", "application/json");
		    request.setHeader("x-api-key", "xUfQC9CZ7Rm03G7SxVYzzU6Qw38RTXVS");
		    request.setHeader("Cookie", "X-Worker-Id=11232,worker_id=11232; _tracking_cookie_=371ea25.612e888edd2be");

		    try (CloseableHttpResponse response = client.execute(request)) {
		        if(response.getStatusLine().getStatusCode() == 200) {
			        String responseString = EntityUtils.toString(response.getEntity(), "UTF-8");
			        System.out.println("Response Content : " + responseString);
		        	System.out.println("Sucess");
		        } else {
		        	System.out.println("Error, API response Code : " + response.getStatusLine().getStatusCode());
		        	Assert.fail();
		        }
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		} catch (IOException | URISyntaxException e) {
		    e.printStackTrace();
		}
	}
	
	public String PridobiKomplet(String idOsebe) {
		//You can use that, so if triglav complete is null it doesent exist, but if it returnes some number it does, you can then use the returned number. 
		
    	String idOsebeDef = "996377";
    	String packetIdFinal = null;
        
    	if (idOsebe == null || idOsebe.isEmpty()) {
			System.out.println("No idOsebe given, using default");
			idOsebe = idOsebeDef;
		} else {
			System.out.println("idOsebe string found");
		}
    	
        try {
            SSLContext sslContext = SSLContext.getInstance("SSL");
            TrustManager[] trustAllCerts = new TrustManager[] {
                new X509TrustManager() {
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() { return new X509Certificate[0]; }
                    public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) { }
                    public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) { }
                }
            };
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            CloseableHttpClient client = HttpClients.custom().setSSLContext(sslContext).build();

            HttpGet request = new HttpGet(new URI("https://qa-prem-zt.cloud.triglav.pri/epremoz/api/triglav-komplet/getTriglavKompletListForPerson?prnIdNum=" + idOsebe));
            try (CloseableHttpResponse response = client.execute(request)) {
                String responseString = EntityUtils.toString(response.getEntity(), "UTF-8");
                if(response.getStatusLine().getStatusCode() == 200) {
                	if (responseString.contains("packetid")) {
	                    String[] packetIdBegin = responseString.split("\"packetid\":\"");
	                    packetIdFinal = packetIdBegin[1].substring(0, packetIdBegin[1].indexOf("\""));
	                    System.out.println("Packet id: " + packetIdFinal);
	                    System.out.println("Success, getting komplet ID");
                	} else {
                		System.out.println("no triglav komplet exist for given ID");
                	}
                } else {
                    System.out.println("Error, API response Code : " + response.getStatusLine().getStatusCode());
                    Assert.fail();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (URISyntaxException | NoSuchAlgorithmException | KeyManagementException e) {
            e.printStackTrace();
        }
        return packetIdFinal;
	}
	
	public void KlikAwayAgreement() {
		Actions builder = new Actions(driver); // clicking away from link, so it doesen't open PDF
		WebElement element = driver.findElement(By.xpath("//*[@for = 'informacije']"));
		Action action = builder.moveToElement(element, -200, -10).click().build();
		action.perform();
	}
}
