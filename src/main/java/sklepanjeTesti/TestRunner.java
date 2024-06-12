package sklepanjeTesti;

import java.io.File;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.model.Log;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import sklepanjeTesti.config.EnvironmentConfig;
import sklepanjeTesti.dbHandler.DatabaseQueryTestEnv;
import sklepanjeTesti.sklepanje.naprave.belaTehnika.Hladilnik;
import sklepanjeTesti.sklepanje.naprave.belaTehnika.KuhalnaPlosca;
import sklepanjeTesti.sklepanje.naprave.belaTehnika.KuhinjskaNapa;
import sklepanjeTesti.sklepanje.naprave.belaTehnika.Pecica;
import sklepanjeTesti.sklepanje.naprave.belaTehnika.PomivalniStroj;
import sklepanjeTesti.sklepanje.naprave.belaTehnika.PralniStroj;
import sklepanjeTesti.sklepanje.naprave.belaTehnika.PralnoSusilniStroj;
import sklepanjeTesti.sklepanje.naprave.belaTehnika.Stedilnik;
import sklepanjeTesti.sklepanje.naprave.belaTehnika.SusilniStroj;
import sklepanjeTesti.sklepanje.naprave.belaTehnika.VinskaVitrina;
import sklepanjeTesti.sklepanje.naprave.belaTehnika.Zmrzovalnik;
import sklepanjeTesti.sklepanje.naprave.ostaleNaprave.AvdioVideoPredvajalnik;
import sklepanjeTesti.sklepanje.naprave.ostaleNaprave.Fotoaparat;
import sklepanjeTesti.sklepanje.naprave.ostaleNaprave.Kamera;
import sklepanjeTesti.sklepanje.naprave.ostaleNaprave.Monitor;
import sklepanjeTesti.sklepanje.naprave.ostaleNaprave.NamizniRacunalnik;
import sklepanjeTesti.sklepanje.naprave.ostaleNaprave.NavigacijskaNaprava;
import sklepanjeTesti.sklepanje.naprave.ostaleNaprave.OpticniCitalnik;
import sklepanjeTesti.sklepanje.naprave.ostaleNaprave.Projektor;
import sklepanjeTesti.sklepanje.naprave.ostaleNaprave.SkleniNapraveIgralnaKonzola;
import sklepanjeTesti.sklepanje.naprave.ostaleNaprave.Snemalnik;
import sklepanjeTesti.sklepanje.naprave.ostaleNaprave.Streznik;
import sklepanjeTesti.sklepanje.naprave.ostaleNaprave.Televizor;
import sklepanjeTesti.sklepanje.naprave.ostaleNaprave.Tiskalnik;
import sklepanjeTesti.sklepanje.naprave.prenosneNaprave.PametnaUra;
import sklepanjeTesti.sklepanje.naprave.prenosneNaprave.PrenosniRacunalnik;
import sklepanjeTesti.sklepanje.naprave.prenosneNaprave.TablicniInPrenosniVEnem;
import sklepanjeTesti.sklepanje.naprave.prenosneNaprave.TablicniRacunalnik;
import sklepanjeTesti.sklepanje.potovanjeTujina.PotovanjeTujina_Skupinsko_Neprijavljen;

import sklepanjeTesti.sklepanje.*;

public class TestRunner {
	
	public static List<Log> logi = new ArrayList<>();
	public static String testName;
	public static Date testStartTime;
	public static Date testEndTime;
	public static Status testStatus;
	
	public ExtentReports extent;
	public ExtentTest logger;
	public ExtentSparkReporter spark;

	
	public static int passedSteps = 0;
	public static int failedSteps = 0;
	public static int skippedSteps = 0;
	
	public static int passedTests = 0;
	public static int failedTests = 0;
	public static int skippedTests = 0;
	public static int allTests = 0;
	
	public static List<String> neuspesni = new ArrayList<>();
	
	EnvironmentConfig cfg = ConfigFactory.create(EnvironmentConfig.class);
	    
    public static List<com.aventstack.extentreports.model.Test> testList;  
    
    protected static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();
        
    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
    	
    	spark = new ExtentSparkReporter(System.getProperty("user.dir") + "/reporti/SklepanjeReport.html");
    	
    	extent = new ExtentReports();
		    	
    	extent.attachReporter(spark);
		
		logger = extent.createTest("isklepanje");	
		
		deleteAllImagesFromSlike();
				
    }
    	
	
	@BeforeMethod(alwaysRun = true)
	public void setup() throws Exception {
				
		WebDriver driver = BrowserManager.doBrowserSetup("chrome");

        threadLocalDriver.set(driver);
                
	    
	}
	
	/*/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////*/
	/*///////////////////////////////////////////// NAPRAVE ////////////////////////////////////////////////////*/
	/*↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓*/
	
	// Bela tehnika
	
	@Test(groups = {"Premoz","Naprave"},retryAnalyzer = RerunTest.class)
	public void Hladilnik() throws Exception {

		WebDriver driver = threadLocalDriver.get();
	    
	    Hladilnik hlad = new Hladilnik(driver);
	    hlad.NapraveTest();
	    
	    driver.quit();
	    threadLocalDriver.remove();
	}
	
	@Test(groups = {"Premoz","Naprave"},retryAnalyzer = RerunTest.class)
	public void KuhalnaPlosca() throws Exception {

		WebDriver driver = threadLocalDriver.get();
	    
	    KuhalnaPlosca hlad = new KuhalnaPlosca(driver);
	    hlad.NapraveTest();
	    
	    driver.quit();
	    threadLocalDriver.remove();
	}
	
	@Test(groups = {"Premoz","Naprave"},retryAnalyzer = RerunTest.class)
	public void KuhinjskaNapa() throws Exception {

		WebDriver driver = threadLocalDriver.get();
	    
	    KuhinjskaNapa hlad = new KuhinjskaNapa(driver);
	    hlad.NapraveTest();
	    
	    driver.quit();
	    threadLocalDriver.remove();
	}
	
	@Test(groups = {"Premoz","Naprave"},retryAnalyzer = RerunTest.class)
	public void Pecica() throws Exception {

		WebDriver driver = threadLocalDriver.get();
	    
	    Pecica hlad = new Pecica(driver);
	    hlad.NapraveTest();
	    
	    driver.quit();
	    threadLocalDriver.remove();
	}
	
	@Test(groups = {"Premoz","Naprave"},retryAnalyzer = RerunTest.class)
	public void PomivalniStroj() throws Exception {

		WebDriver driver = threadLocalDriver.get();

	    PomivalniStroj hlad = new PomivalniStroj(driver);
	    hlad.NapraveTest();
	    
	    driver.quit();
	    threadLocalDriver.remove();
	}
	
	@Test(groups = {"Premoz","Naprave"},retryAnalyzer = RerunTest.class)
	public void PralniStroj() throws Exception {

		WebDriver driver = threadLocalDriver.get();

	    PralniStroj hlad = new PralniStroj(driver);
	    hlad.NapraveTest();
	    
	    driver.quit();
	    threadLocalDriver.remove();
	}
	
	@Test(groups = {"Premoz","Naprave"},retryAnalyzer = RerunTest.class)
	public void PralnoSusilniStroj() throws Exception {

		WebDriver driver = threadLocalDriver.get();

	    PralnoSusilniStroj hlad = new PralnoSusilniStroj(driver);
	    hlad.NapraveTest();
	    
	    driver.quit();
	    threadLocalDriver.remove();
	}
	
	@Test(groups = {"Premoz","Naprave"},retryAnalyzer = RerunTest.class)
	public void Stedilnik() throws Exception {

		WebDriver driver = threadLocalDriver.get();
    
	    Stedilnik hlad = new Stedilnik(driver);
	    hlad.NapraveTest();
	    
	    driver.quit();
	    threadLocalDriver.remove();
	}
	
	@Test(groups = {"Premoz","Naprave"},retryAnalyzer = RerunTest.class)
	public void SusilniStroj() throws Exception {

		WebDriver driver = threadLocalDriver.get();
	    
	    SusilniStroj hlad = new SusilniStroj(driver);
	    hlad.NapraveTest();
	    
	    driver.quit();
	    threadLocalDriver.remove();
	}
	
	@Test(groups = {"Premoz","Naprave"},retryAnalyzer = RerunTest.class)
	public void VinskaVitrina() throws Exception {

		WebDriver driver = threadLocalDriver.get();
	    
	    VinskaVitrina hlad = new VinskaVitrina(driver);
	    hlad.NapraveTest();
	    
	    driver.quit();
	    threadLocalDriver.remove();
	}
	
	@Test(groups = {"Premoz","Naprave"},retryAnalyzer = RerunTest.class)
	public void Zmrzovalnik() throws Exception {

		WebDriver driver = threadLocalDriver.get();
	    
	    Zmrzovalnik hlad = new Zmrzovalnik(driver);
	    hlad.NapraveTest();
	    
	    driver.quit();
	    threadLocalDriver.remove();
	}
	
	// Ostale naprave
	
	@Test(groups = {"Premoz","Naprave"},retryAnalyzer = RerunTest.class)
	public void AvdioVideo() throws Exception {

		WebDriver driver = threadLocalDriver.get();

	    AvdioVideoPredvajalnik hlad = new AvdioVideoPredvajalnik(driver);
	    hlad.NapraveTest();
	    
	    driver.quit();
	    threadLocalDriver.remove();
	}
	
	@Test(groups = {"Premoz","Naprave"},retryAnalyzer = RerunTest.class)
	public void Fotoaparat() throws Exception {

		WebDriver driver = threadLocalDriver.get();

	    Fotoaparat hlad = new Fotoaparat(driver);
	    hlad.NapraveTest();
	    
	    driver.quit();
	    threadLocalDriver.remove();
	}
	
	@Test(groups = {"Premoz","Naprave"},retryAnalyzer = RerunTest.class)
	public void Kamera() throws Exception {

		WebDriver driver = threadLocalDriver.get();

	    Kamera hlad = new Kamera(driver);
	    hlad.NapraveTest();
	    
	    driver.quit();
	    threadLocalDriver.remove();
	}
	
	@Test(groups = {"Premoz","Naprave"},retryAnalyzer = RerunTest.class)
	public void Monitor() throws Exception {

		WebDriver driver = threadLocalDriver.get();

	    Monitor hlad = new Monitor(driver);
	    hlad.NapraveTest();
	    
	    driver.quit();
	    threadLocalDriver.remove();
	}
	
	@Test(groups = {"Premoz","Naprave"},retryAnalyzer = RerunTest.class)
	public void NamizniRacunalnik() throws Exception {

		WebDriver driver = threadLocalDriver.get();

	    NamizniRacunalnik hlad = new NamizniRacunalnik(driver);
	    hlad.NapraveTest();
	    
	    driver.quit();
	    threadLocalDriver.remove();
	}
	
	@Test(groups = {"Premoz","Naprave"},retryAnalyzer = RerunTest.class)
	public void NavigacijskaNaprava() throws Exception {

		WebDriver driver = threadLocalDriver.get();

	    NavigacijskaNaprava hlad = new NavigacijskaNaprava(driver);
	    hlad.NapraveTest();
	    
	    driver.quit();
	    threadLocalDriver.remove();
	}
	
	@Test(groups = {"Premoz","Naprave"},retryAnalyzer = RerunTest.class)
	public void OpticniCitalnik() throws Exception {

		WebDriver driver = threadLocalDriver.get();

	    OpticniCitalnik hlad = new OpticniCitalnik(driver);
	    hlad.NapraveTest();
	    
	    driver.quit();
	    threadLocalDriver.remove();
	}
	
	@Test(groups = {"Premoz","Naprave"},retryAnalyzer = RerunTest.class)
	public void Projektor() throws Exception {

		WebDriver driver = threadLocalDriver.get();

	    Projektor hlad = new Projektor(driver);
	    hlad.NapraveTest();
	    
	    driver.quit();
	    threadLocalDriver.remove();
	}
	
	@Test(groups = {"Premoz","Naprave"},retryAnalyzer = RerunTest.class)
	public void SkleniNapraveIgralnaKonzola() throws Exception {

		WebDriver driver = threadLocalDriver.get();

	    SkleniNapraveIgralnaKonzola hlad = new SkleniNapraveIgralnaKonzola(driver);
	    hlad.NapraveTest();
	    
	    driver.quit();
	    threadLocalDriver.remove();
	}
	
	@Test(groups = {"Premoz","Naprave"},retryAnalyzer = RerunTest.class)
	public void Snemalnik() throws Exception {

		WebDriver driver = threadLocalDriver.get();

	    Snemalnik hlad = new Snemalnik(driver);
	    hlad.NapraveTest();
	    
	    driver.quit();
	    threadLocalDriver.remove();
	}
	
	@Test(groups = {"Premoz","Naprave"},retryAnalyzer = RerunTest.class)
	public void Streznik() throws Exception {

		WebDriver driver = threadLocalDriver.get();

	    Streznik hlad = new Streznik(driver);
	    hlad.NapraveTest();
	    
	    driver.quit();
	    threadLocalDriver.remove();
	}
	
	@Test(groups = {"Premoz","Naprave"},retryAnalyzer = RerunTest.class)
	public void Televizor() throws Exception {

		WebDriver driver = threadLocalDriver.get();

	    Televizor hlad = new Televizor(driver);
	    hlad.NapraveTest();
	    
	    driver.quit();
	    threadLocalDriver.remove();
	}
	
	@Test(groups = {"Premoz","Naprave"},retryAnalyzer = RerunTest.class)
	public void Tiskalnik() throws Exception {

		WebDriver driver = threadLocalDriver.get();

	    Tiskalnik hlad = new Tiskalnik(driver);
	    hlad.NapraveTest();
	    
	    driver.quit();
	    threadLocalDriver.remove();
	}
	
	
	// Prenosne naprave
	
	@Test(groups = {"Premoz","Naprave"},retryAnalyzer = RerunTest.class)
	public void PametnaUra() throws Exception {

		WebDriver driver = threadLocalDriver.get();

        PametnaUra hlad = new PametnaUra(driver);
	    hlad.NapraveTest();
	    
	    driver.quit();
	    threadLocalDriver.remove();
	}
	
	
	@Test(groups = {"Premoz","Naprave"},retryAnalyzer = RerunTest.class)
	public void PrenosniRacunalnik() throws Exception {

		WebDriver driver = threadLocalDriver.get();

	    PrenosniRacunalnik hlad = new PrenosniRacunalnik(driver);
	    hlad.NapraveTest();
	    
	    driver.quit();
	    threadLocalDriver.remove();
	}
	
	@Test(groups = {"Premoz","Naprave"},retryAnalyzer = RerunTest.class)
	public void TablicniInPrenosniVEnem() throws Exception {

		WebDriver driver = threadLocalDriver.get();

	    TablicniInPrenosniVEnem hlad = new TablicniInPrenosniVEnem(driver);
	    hlad.NapraveTest();
	    
	    driver.quit();
	    threadLocalDriver.remove();
	}
	
	@Test(groups = {"Premoz","Naprave"},retryAnalyzer = RerunTest.class)
	public void TablicniRacunalnik() throws Exception {

		WebDriver driver = threadLocalDriver.get();

	    TablicniRacunalnik hlad = new TablicniRacunalnik(driver);
	    hlad.NapraveTest();
	    
	    driver.quit();
	    threadLocalDriver.remove();
	}
						
	
	/*/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////*/
	/*///////////////////////////////////////////// POTOVANJE V TUJINO ////////////////////////////////////////////////////*/
	/*↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓*/
	
	
	
	
	/////////////////////////////*/ POSAMIČNO ZAVAROVANJE /*//////////////////////////////
	
	
	//  ↓ potovanje v tujino posamicno - prijavljen ( trajanje 2 dni, Paket B )↓ 
	
//		@Test(groups = {"Premoz","Potovanje"},retryAnalyzer = RerunTest.class
//			)
//		public void PotovanjeTujina_Posamicno_Prijavljen() throws Exception {
//		
//		WebDriver driver = threadLocalDriver.get();
//	    
//	    PotovanjeTujina_Posamicno_Prijavljen ptPP = new PotovanjeTujina_Posamicno_Prijavljen(driver);
//	    ptPP.PotovanjeTujinaPosamicnoPrijavljenTest();
//	    
//	    driver.quit();
//	    threadLocalDriver.remove();
//	}  
	
	
	
	//  ↓ potovanje v tujino posamicno - neprijavljen ( trajanje 3 dni, Paket A )↓ 
	
//		@Test(groups = {"Premoz","Potovanje"},retryAnalyzer = RerunTest.class
//				)
//			public void PotovanjeTujina_Posamicno_Neprijavljen() throws Exception {
//			
//			WebDriver driver = threadLocalDriver.get();
//		    
//		    PotovanjeTujina_Posamicno_Neprijavljen ptPN = new PotovanjeTujina_Posamicno_Neprijavljen(driver);
//		    ptPN.PotovanjeTujinaPosamicnoTest();
//		    
//		    driver.quit();
//		    threadLocalDriver.remove();
//		}  

	
	
	//  ↓ potovanje v tujino posamicno - naknadna prijava ( trajanje 5 dni + se boste v tujini ukvarjali z različnimi športi..."DA", Paket C )↓
	
//		@Test(groups = {"Premoz","Potovanje"},retryAnalyzer = RerunTest.class
//				)
//			public void PotovanjeTujina_Posamicno_NaknadnaPrijava() throws Exception {
//		
//			WebDriver driver = threadLocalDriver.get();
//	    
//	   	    PotovanjeTujina_Posamicno_NaknadnaPrijava ptPNP = new PotovanjeTujina_Posamicno_NaknadnaPrijava(driver);
//	    	ptPNP.PotovanjeTujinaPosamicnoNaknadnaPrijavaTest();
//	    
//	    	driver.quit();
//	    	threadLocalDriver.remove();
//		}  
	
	
		

	
	//////////////////////////*/ DRUZINSKO ZAVAROVANJE /*//////////////////////////

	
	//  ↓ potovanje v tujino druzinsko - prijavljen ( trajanje 8 dni, Paket B , zavarovanec se bo v tujini ukvarjal z rizičnimi športi..."DA" )↓
	
//		 @Test(groups = {"Premoz","Potovanje"},retryAnalyzer = RerunTest.class
//				)
//		 	public void PotovanjeTujina_Druzinsko_Prijavljen() throws Exception {
//			 
//			WebDriver driver = threadLocalDriver.get();
//	
//			PotovanjeTujina_Druzinsko_Prijavljen ptDP = new PotovanjeTujina_Druzinsko_Prijavljen(driver);
//			ptDP.PotovanjeTujinaDruzinskoPrijavljenTest();
//	
//			driver.quit();
//			threadLocalDriver.remove();
//		}	  

	
	
	//  ↓ potovanje v tujino druzinsko - neprijavljen ( trajanje 1 leto + "Manj kot 90 dni", Paket C )↓  NEDELA
	
//		@Test(groups = {"Premoz","Potovanje"},retryAnalyzer = RerunTest.class
//  		     )
//		public void PotovanjeTujina_Druzisnko_Neprijavljen() throws Exception {
//		
//			WebDriver driver = threadLocalDriver.get();
//	    
//	   		PotovanjeTujina_Druzinsko_Neprijavljen ptDN = new PotovanjeTujina_Druzinsko_Neprijavljen(driver);
//	    	ptDN.PotovanjeTujinaDruzinskoTest();
//	    
//	   		driver.quit();
//	    	threadLocalDriver.remove();
//		}  
	

	
	//  ↓ potovanje v tujino druzinsko - naknadna prijava ( trajanje 1 leto + "90 ali več dni", Paket A )↓
	
//		 @Test(groups = {"Premoz","Potovanje"},retryAnalyzer = RerunTest.class)
//		 	public void PotovanjeTujina_Druzinsko_NaknadnaPrijava() throws Exception {
//			 
//			WebDriver driver = threadLocalDriver.get();
//    
//    		PotovanjeTujina_Druzinsko_NaknadnaPrijava ptDNP = new PotovanjeTujina_Druzinsko_NaknadnaPrijava(driver);
//    		ptDNP.PotovanjeTujinaDruzinskoNadoknadnaPrijavaTest();
//    
//    		driver.quit();
//    		threadLocalDriver.remove();
//		}	  

	
	
	
	
	//////////////////////////*/ SKUPINSKO ZAVAROVANJE /*//////////////////////////

	
	//  ↓ potovanje v tujino skupinsko - neprijavljen ( trajanje 15 dni, Paket A, "število zavarovancev = 2" )↓
	
	@Test(groups = { "Premoz", "Potovanje" })
	public void PotovanjeTujina_Skupinsko_Neprijavljen() throws Exception {

		WebDriver driver = threadLocalDriver.get();

		PotovanjeTujina_Skupinsko_Neprijavljen ptSN = new PotovanjeTujina_Skupinsko_Neprijavljen(driver);
		ptSN.PotovanjeTujinaSkupinskoTest();

		driver.quit();
		threadLocalDriver.remove();
	}
	
	
	
	
	//  ↓ potovanje v tujino skupinsko - prijavljen ( trajanje 21 dni, Paket B, sklenitelj je tudi zavarovanec + se bo v tujini okvarjal s športom, "število zavarovancev = 3" )↓
	
//			@Test(groups = { "Premoz", "Potovanje" }, retryAnalyzer = RerunTest.class)	
//			public void PotovanjeTujina_Skupinsko_Prijavljen() throws Exception {
//	
//			WebDriver driver = threadLocalDriver.get();
//	
//			PotovanjeTujina_Skupinsko_Prijavljen ptSP = new PotovanjeTujina_Skupinsko_Prijavljen(driver);
//			ptSP.PotovanjeTujinaSkupinskoPrijavljenTest();
//	
//		 	driver.quit();
//			threadLocalDriver.remove();
//	}    

	
	
	//  ↓ potovanje v tujino skupinsko - naknadna prijava  ( trajanje 30 dni, Paket C, zavarovana oseba se bo ukvarjala z športi..."DA" (v modalu), "število zavarovancev = 4" )↓
//	
//		@Test(groups = { "Premoz", "Potovanje" }, retryAnalyzer = RerunTest.class)
//			public void PotovanjeTujina_Skupinsko_NaknadnaPrijava() throws Exception {
//	
//			WebDriver driver = threadLocalDriver.get();
//   
//  		PotovanjeTujina_Skupinsko_NaknadnaPrijava ptSNP = new PotovanjeTujina_Skupinsko_NaknadnaPrijava(driver);
//  		ptSNP.PotovanjeTujinaSkupinskoNaknadnaPrijavaTest();
//   
//  	 	driver.quit();
//   		threadLocalDriver.remove();
//	}    

		
		
		
		
	//////////////////////////*/ PREVERJANJE CE SO RAZLICNE KOMPONENTE FUNKCIONALNE /*//////////////////////////
	
		
		//  ↓ potovanje v tujino posamicno prijavljen TESTIRANJE FUNKCIONALNOSTIH ENA ( preverim večkrat če se cena ujema z ceno na hover down prikazu premije, 
		//	 dodam dodatno zavarovanje odpovedi turističnih potovanj - 3.010 EUR, dodam kupon avtomobilno, preverim ce se je dodal triglav komplet, plačilo na obroke + FLIK plačilo)↓
		
//		@Test(groups = { "Premoz", "Potovanje" }, retryAnalyzer = RerunTest.class)
//			public void PotovanjeTujina_Testiranje_Funkcionalnostih_ENA() throws Exception {
//	
//			WebDriver driver = threadLocalDriver.get();
//   
// 		PotovanjeTujina_Testiranje_Funkcionalnostih_ENA ptTF = new PotovanjeTujina_Testiranje_Funkcionalnostih_ENA(driver);
//  		ptTF.PotovanjeTujinaTestiranjeFunkcionalnostihTestENA();
//   
//  	 	driver.quit();
//   		threadLocalDriver.remove();
//	}    
		
	
		
	//  ↓ potovanje v tujino posamicno prijavljen TESTIRANJE FUNKCIONALNOSTIH DVA ( trajanje 60 dni, obcija želim nadaljevati kasneje označena, plačilo na obroke )↓
		
//		@Test(groups = { "Premoz", "Potovanje" }, retryAnalyzer = RerunTest.class)
//			public void PotovanjeTujina_Testiranje_Funkcionalnostih_DVA() throws Exception {
//	
//			WebDriver driver = threadLocalDriver.get();
//   
//  		PotovanjeTujina_Testiranje_Funkcionalnostih_DVA ptTF = new PotovanjeTujina_Testiranje_Funkcionalnostih_DVA(driver);
//  		ptTF.PotovanjeTujinaTestiranjeFunkcionalnostihTestDVA();
//   
//  	 	driver.quit();
//   		threadLocalDriver.remove();
//	}    

	
	/*/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////*/
	/*///////////////////////////////////////////// Bančne Kartice ////////////////////////////////////////////////////*/
	/*↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓*/
	
//	
//	@Test(groups = {"Premoz", "BancneKartice"},retryAnalyzer = RerunTest.class)
//	public void BancneKartice() throws Exception {
//		
//		WebDriver driver = threadLocalDriver.get();
//	    long threadId = Thread.currentThread().getId();
//	    String sessionId = ((ChromeDriver) driver).getSessionId().toString();
//	    System.out.println("Thread ID: " + threadId + ", Session ID: " + sessionId);
//	    
//	    BancneKartice bk = new BancneKartice(driver);
//	    bk.BancneKarticeTest();
//	    
//	    driver.quit();
//	    threadLocalDriver.remove();
//	}
	
	
	public static String getScreenshot(WebDriver driver, String screenshotName) throws Exception {
        String dateName = new SimpleDateFormat("dd.MM.yyyy HH.mm.ss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "/slike/"+screenshotName+dateName+".png";
		File finalDestination = new File(destination);
		FileHandler.copy(source, finalDestination);
		return destination;
	}
	
	public static String fullScreenshot(WebDriver driver, String screenshotName) throws Exception {
	    // Capture the full-page screenshot
	    Screenshot screenshot = new AShot()
	            .shootingStrategy(ShootingStrategies.viewportPasting(100))
	            .takeScreenshot(driver);
	    
	    // Define the file path and name for the screenshot
	    String dateName = new SimpleDateFormat("dd.MM.yyyy HH.mm.ss").format(new Date());
	    String destination = System.getProperty("user.dir") + "/slike/" + screenshotName + dateName + ".png";
	    
	    // Save the screenshot to the specified destination
	    ImageIO.write(screenshot.getImage(), "PNG", new File(destination));
	    
	    return destination;
	}
	
	//get thread-safe driver
    public static WebDriver getDriver(){
        return threadLocalDriver.get();
    }
    
	public void deleteAllImagesFromSlike() {
		
		// delete all images except for slikaUpload.png (used in test)
		
		String packagePath = System.getProperty("user.dir") + "/slike";
		String keepFile = "slikaUpload.png";
		
		Path directory = Paths.get(packagePath.replace("/", File.separator));
		
		
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(directory, "*.{png}")){
			
			for (Path file : stream) {
				if(!file.getFileName().toString().equals(keepFile)) {
					Files.delete(file);
					System.out.println("Deleted: " + file);
				}
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
		
	@AfterMethod(alwaysRun = true)
	public void getResult(ITestResult result) throws Exception{
		
		WebDriver driver = threadLocalDriver.get();
		
		if (result.getAttribute("retry") != null) {
	       
	        ExtentTestManager.getTest().info("Test was retried and passed on retry");
	        return;
	    }
		
		if(result.getStatus() == ITestResult.FAILURE) {
				
				logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case FAILED", ExtentColor.RED));
				logger.log(Status.INFO, MarkupHelper.createLabel(result.getThrowable() + " - Test Case FAILED", ExtentColor.RED));
													
				String screenshotPath = getScreenshot(driver, result.getName());		
				logger.info("Screenshot RETRY napake za: " + result.getName() + " URL napake "  + " je: " + driver.getCurrentUrl(), MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());				
				
				neuspesni.add(result.getName());
				
				failedSteps++;
												
			}
			
			else if(result.getStatus() == ITestResult.SKIP ) {
				logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - Test Case SKIPPED", ExtentColor.ORANGE));
				logger.log(Status.INFO, MarkupHelper.createLabel(result.getThrowable() + result.getName() + " - Test Case SKIPPED", ExtentColor.ORANGE));

				String screenshotPath = getScreenshot(driver, result.getName());	 
				logger.info("Screenshot RETRY napake za: " + result.getName() + " URL napake "  + " je: " + driver.getCurrentUrl(), MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());				
				
				skippedSteps++;
				
			}
			
			else if(result.getStatus() == ITestResult.SUCCESS)
			{
				logger.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" - Test Case PASSED", ExtentColor.GREEN));
				passedSteps++;
				
			}
				
		if (driver != null) {
	        try {
	        //	getDriver().quit();
	        } catch (Exception e) {
	            System.out.println("An error occurred while closing the WebDriver: " + e);
	        } finally {
	        	threadLocalDriver.remove(); // Remove the WebDriver instance from the ThreadLocal after quitting.
	        }
	    }
	    
	}
	
	@AfterSuite(alwaysRun = true)
	public void afterSuite() {
		try {
			extent.flush();
			
			testName = logger.getModel().getName();
			testStartTime = logger.getModel().getStartTime();
			testEndTime = logger.getModel().getEndTime();
			testStatus = logger.getStatus();
			
			//testList = htmlReporter.getTestList();
			
			testList = spark.getReport().getTestList();
			
			setTestName(testName);
			setTestStartTime(testStartTime);
			setTestEndTime(testEndTime);
			setTestStatus(testStatus);
			setPassedSteps(passedSteps);
			setFailedSteps(failedSteps);
			setSkippedSteps(skippedSteps);
			setTestList(testList);
			setNeuspesni(neuspesni);
			setTestStatus(testStatus);
						        
			
	        DatabaseQueryTestEnv db = new DatabaseQueryTestEnv();
	        db.isklepanjeTestsData();
			
//			ReportBackuper bk = new ReportBackuper();
//			bk.backUpReport();
		}
		catch(Exception e) {
	      //  logger2.error("An error occurred during the @AfterSuite configuration: ", e);
			System.out.println("Prišlo je do napake v aftersuite: " + e);
	    }
	}
	
	public static List<com.aventstack.extentreports.model.Test> getTestList() {
		return testList;
	}

	public void setTestList(List<com.aventstack.extentreports.model.Test> testList) {
		TestRunner.testList = testList;
	}

	public static int getPassedSteps() {
		return passedSteps;





	}


	public void setPassedSteps(int passedSteps) {
		TestRunner.passedSteps = passedSteps;
	}


	public static int getFailedSteps() {
		return failedSteps;
	}


	public void setFailedSteps(int failedSteps) {
		TestRunner.failedSteps = failedSteps;
	}


	public static int getSkippedSteps() {
		return skippedSteps;
	}


	public void setSkippedSteps(int skippedSteps) {
		TestRunner.skippedSteps = skippedSteps;
	}
	
	public static String getTestName() {
		return testName;
	}


	public void  setTestName(String testName) {
		TestRunner.testName = testName;
	}


	public static Date getTestStartTime() {
		return testStartTime;
	}


	public void setTestStartTime(Date testStartTime) {
		TestRunner.testStartTime = testStartTime;
	}


	public static Date getTestEndTime() {
		return testEndTime;
	}


	public void setTestEndTime(Date testEndTime) {
		TestRunner.testEndTime = testEndTime;
	}


	public static Status getTestStatus() {
		return testStatus;
	}

	public void setTestStatus(Status testStatus) {
		TestRunner.testStatus = testStatus;
	}

	public static int getPassedTests() {
		return passedTests;
	}

	public static void setPassedTests(int passedTests) {
		TestRunner.passedTests = passedTests;
	}

	public static int getFailedTests() {
		return failedTests;
	}

	public static void setFailedTests(int failedTests) {
		TestRunner.failedTests = failedTests;
	}

	public static int getSkippedTests() {
		return skippedTests;
	}

	public static void setSkippedTests(int skippedTests) {
		TestRunner.skippedTests = skippedTests;
	}

	public static int getAllTests() {
		return allTests;
	}

	public static void setAllTests(int allTests) {
		TestRunner.allTests = allTests;
	}


	public static List<String> getNeuspesni() {
		return neuspesni;
	}


	public static void setNeuspesni(List<String> neuspesni) {
		TestRunner.neuspesni = neuspesni;
	}

}
