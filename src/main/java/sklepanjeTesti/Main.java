package sklepanjeTesti;

import java.util.Hashtable;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import sklepanjeTesti.informativniIzracun.*;
import sklepanjeTesti.sklepanje.*;
import sklepanjeTesti.sklepanje.validacija.DrajvAdminPortal;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Main {
	
	public WebDriver driver;
	public RemoteWebDriver remoteDriver;
	//String downloadFilepath = "/path/to/download";
	String path = System.getProperty("user.dir"); 
	String fullpath = path + "\\dokumenti";
	
	@BeforeMethod
	public void setUp() {
				
		WebDriverManager.chromedriver().setup();
		
		Map<String, Object> preferences = new Hashtable<String, Object>();
		preferences.put("profile.default_content_settings.popups", 0);
		preferences.put("download.prompt_for_download", "false");
		preferences.put("download.default_directory", fullpath);
		
		// disable flash and the PDF viewer
		preferences.put("plugins.plugins_disabled", new String[]{
		    "Adobe Flash Player", "Chrome PDF Viewer"});
		
		ChromeOptions options = new ChromeOptions();
		
		options.setExperimentalOption("prefs", preferences);
		
		options.addArguments("--start-maximized");
		options.addArguments("--remote-allow-origins=*");
	//	options.addArguments("--headless=new");
    //  options.addArguments("window-size=1920,1080");
													
		driver = new ChromeDriver(options);
		
	//	RemoteWebDriver remoteDriver = (RemoteWebDriver) driver;
    //    remoteDriver.setFileDetector(new LocalFileDetector());
		
	}	
		/*
	@Test
	public void SklepanjeTesti() throws Exception {
		
		AvtoSkleni avt = new AvtoSkleni(driver);
	//	avt.AvtoTest();
		
		OdpovedPotovanj odp = new OdpovedPotovanj(driver);
	//	odp.OdpovedPotovanjTest();
		
		MaleZivali mz = new MaleZivali(driver);
	//	mz.MaleZivaliTest();
		
		Avtomobili avto = new Avtomobili(driver);
	//	avto.AvtomobiliTest();
	//	avto.AvtomobiliTestBrezPrijave();
		
		Motor mot = new Motor(driver);
		//mot.MotorTest();
		
		AvtoSkleni as = new AvtoSkleni(driver);
	//	as.AvtoTest();
		
		Nezgoda nez = new Nezgoda(driver);
	//	nez.NezgodaTest();
	//	nez.Nezgoda2Otroka();
		
		MladiVoznik mv = new MladiVoznik(driver);
	//	mv.MladiVoznikTest();
		
		OsebnaZascita ozz = new OsebnaZascita(driver);
	//	ozz.OsebnaZascitaPosamicno();
	//	ozz.OsebnaZascitaDruzinsko();
		
		Mikromobilnost222 mikro2 = new Mikromobilnost222(driver);
	//	mikro2.MikromobilnostTest();
		
		Mikromobilnost mik = new Mikromobilnost(driver);
	//	mik.MikromobilnostTest();
		
		Dom q = new Dom(driver);
	//	q.DomQTest();
		
		Potovanje pot = new Potovanje(driver);
	//	pot.PotovanjeQTest();
		
		AvtoSkleni obr = new AvtoSkleni(driver);
	//	obr.AvtoTest();
		
		InformativniIzracunPolica ifp = new InformativniIzracunPolica(driver);
	//	ifp.InformativniIzracunPolicaTest();
		
		InformativniIzracunValidacija ifv = new InformativniIzracunValidacija(driver);
	//	ifv.Validacija();
		
		DrajvKodaValidacija koda = new DrajvKodaValidacija(driver);
	//	koda.DrajvKoda();
	//	koda.AvtomobiliTestVsiPopusti();
	//	avto.AvtomobiliTestVsiPopusti();
		
	//	InformativniIzracunPolica2 ifzr = new InformativniIzracunPolica2(driver);
	//	ifzr.InformativniIzracunPolicaTest();
		
		DrajvAdminPortal port = new DrajvAdminPortal(driver);
	//	port.DrajvPortalLogin();
	//	port.DashboardTest();
	//	port.UsersTest();
	//	port.notMigratedUsersTest();
	//	port.tripsTest();
		
		MobilniTelefoni mt = new MobilniTelefoni(driver);
	//	mt.NapraveTest();
		
		Hladilnik hlad = new Hladilnik(driver);
	//	hlad.HladilnikTest();
		
		DrajvAdminPortal drajv = new DrajvAdminPortal(driver);
	//	drajv.DrajvPortalLogin();
	//	drajv.DashboardTest();
	//	drajv.UsersTest();
	//	drajv.notMigratedUsersTest();
	//	drajv.tripsTest();	
	//	drajv.contestsRewards();
	//	drajv.contestsRewardsPool();
	//	drajv.contestsTemplates();
	//	drajv.contests();
	//	drajv.badges();
	//	drajv.discounts();
	//	drajv.news();
	//	drajv.pushNotifications();
		
		
	}	
*/
	@Test
	public void drajvTesti() throws Exception {
		
		DrajvAdminPortal drajv = new DrajvAdminPortal(driver);
//		drajv.DrajvPortalLogin();
//		drajv.DashboardTest();
//		drajv.UsersTest();
//		drajv.notMigratedUsersTest();
//		drajv.tripsTest();	
//		drajv.contestsRewards();
//		drajv.contestsRewardsPool();
//		drajv.contestsTemplates();
//		drajv.contests();
//		drajv.badges();
//		drajv.discounts();
//		drajv.news();
//		drajv.pushNotifications();
		
	}
	
	@AfterMethod
	public void quit() {
		//driver.close();
	}
	
}
