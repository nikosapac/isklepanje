package sklepanjeTesti;

import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

import javax.imageio.ImageIO;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.AWTException;
import java.awt.Graphics2D;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chromium.ChromiumDriverLogLevel;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.service.DriverService;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

import org.testng.SkipException;
import org.testng.annotations.*;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.model.Log;
//import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import sklepanjeTesti.config.EnvironmentConfig;
import sklepanjeTesti.informativniIzracun.*;
import sklepanjeTesti.sklepanje.*;
import sklepanjeTesti.sklepanje.naprave.*;
import sklepanjeTesti.sklepanje.naprave.belaTehnika.*;
import sklepanjeTesti.sklepanje.naprave.ostaleNaprave.*;
import sklepanjeTesti.sklepanje.naprave.prenosneNaprave.*;
import sklepanjeTesti.sklepanje.validacija.*;
import sklepanjeTesti.dbHandler.DatabaseQueryTestEnv;
import sklepanjeTesti.sklepanje.potovanjeTujina.*;

public class TestRunnerLocal {
	
	public static List<Log> logi = new ArrayList<>();
	public static String testName;
	public static Date testStartTime;
	public static Date testEndTime;
	public static Status testStatus;
	
	public ExtentReports extent;
	public ExtentTest logger;
//	public ExtentHtmlReporter  htmlReporter;
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

	
	//WebDriver driver;
	
	//private static Map<String, WebDriver> drivers = new HashMap<>();
	    
    public static List<com.aventstack.extentreports.model.Test> testList;  
    
    protected static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();
    protected static ThreadLocal<WebDriver> threadLocalDriver2 = new ThreadLocal<>();
    
    private static final Logger logger2 = LoggerFactory.getLogger(TestRunnerLocal.class);
        
    List<Status> statusHierarchy = Arrays.asList(
  //          Status.FATAL,
            Status.FAIL,
 //           Status.ERROR,
            Status.PASS,
            Status.WARNING,
            Status.SKIP,
//            Status.DEBUG,
            Status.INFO
    ); 
        
    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
    	
//    	htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/reporti/SklepanjeReport.html");
//		htmlReporter.setAppendExisting(true);
    	spark = new ExtentSparkReporter(System.getProperty("user.dir") + "/reporti/SklepanjeReport.html");
    	
    	extent = new ExtentReports();
		
		//extent.config().statusConfigurator().setStatusHierarchy(statusHierarchy);
		
		//extent.attachReporter(htmlReporter);
    	
    	extent.attachReporter(spark);
		
		logger = extent.createTest("isklepanje");
//		
				
    }
    	
	
	@BeforeMethod(alwaysRun = true)
	public void setup() throws Exception {
				
		WebDriver driver = BrowserManager.doBrowserSetup("chrome");
        //set driver
        threadLocalDriver.set(driver);
                
	    
	}
	
	
	// Bela tehnika
	
	@Test(groups = {"Premoz","Naprave"},retryAnalyzer = RerunTest.class)
	public void Hladilnik() throws Exception {

		WebDriver driver = threadLocalDriver.get();
	//	logger.assignCategory("Bančne kartice");
	    long threadId = Thread.currentThread().getId();
	    String sessionId = ((ChromeDriver) driver).getSessionId().toString();
	    System.out.println("Thread ID: " + threadId + ", Session ID: " + sessionId);
	    

	    
	    Hladilnik hlad = new Hladilnik(driver);
	    hlad.NapraveTest();
	    
	    driver.quit();
	    threadLocalDriver.remove();
	}
	
	@Test(groups = {"Premoz","Naprave"},retryAnalyzer = RerunTest.class)
	public void KuhalnaPlosca() throws Exception {

		WebDriver driver = threadLocalDriver.get();
	//	logger.assignCategory("Bančne kartice");
	    long threadId = Thread.currentThread().getId();
	    String sessionId = ((ChromeDriver) driver).getSessionId().toString();
	    System.out.println("Thread ID: " + threadId + ", Session ID: " + sessionId);
	    

	    
	    KuhalnaPlosca hlad = new KuhalnaPlosca(driver);
	    hlad.NapraveTest();
	    
	    driver.quit();
	    threadLocalDriver.remove();
	}
	
	@Test(groups = {"Premoz","Naprave"},retryAnalyzer = RerunTest.class)
	public void KuhinjskaNapa() throws Exception {

		WebDriver driver = threadLocalDriver.get();
	//	logger.assignCategory("Bančne kartice");
	    long threadId = Thread.currentThread().getId();
	    String sessionId = ((ChromeDriver) driver).getSessionId().toString();
	    System.out.println("Thread ID: " + threadId + ", Session ID: " + sessionId);
	    

	    
	    KuhinjskaNapa hlad = new KuhinjskaNapa(driver);
	    hlad.NapraveTest();
	    
	    driver.quit();
	    threadLocalDriver.remove();
	}
	
	@Test(groups = {"Premoz","Naprave"},retryAnalyzer = RerunTest.class)
	public void Pecica() throws Exception {

		WebDriver driver = threadLocalDriver.get();
	//	logger.assignCategory("Bančne kartice");
	    long threadId = Thread.currentThread().getId();
	    String sessionId = ((ChromeDriver) driver).getSessionId().toString();
	    System.out.println("Thread ID: " + threadId + ", Session ID: " + sessionId);
	    

	    
	    Pecica hlad = new Pecica(driver);
	    hlad.NapraveTest();
	    
	    driver.quit();
	    threadLocalDriver.remove();
	}
	
	@Test(groups = {"Premoz","Naprave"},retryAnalyzer = RerunTest.class)
	public void PomivalniStroj() throws Exception {

		WebDriver driver = threadLocalDriver.get();
	//	logger.assignCategory("Bančne kartice");
	    long threadId = Thread.currentThread().getId();
	    String sessionId = ((ChromeDriver) driver).getSessionId().toString();
	    System.out.println("Thread ID: " + threadId + ", Session ID: " + sessionId);
	    

	    
	    PomivalniStroj hlad = new PomivalniStroj(driver);
	    hlad.NapraveTest();
	    
	    driver.quit();
	    threadLocalDriver.remove();
	}
	
	@Test(groups = {"Premoz","Naprave"},retryAnalyzer = RerunTest.class)
	public void PralniStroj() throws Exception {

		WebDriver driver = threadLocalDriver.get();
	//	logger.assignCategory("Bančne kartice");
	    long threadId = Thread.currentThread().getId();
	    String sessionId = ((ChromeDriver) driver).getSessionId().toString();
	    System.out.println("Thread ID: " + threadId + ", Session ID: " + sessionId);
	    

	    
	    PralniStroj hlad = new PralniStroj(driver);
	    hlad.NapraveTest();
	    
	    driver.quit();
	    threadLocalDriver.remove();
	}
	
	@Test(groups = {"Premoz","Naprave"},retryAnalyzer = RerunTest.class)
	public void PralnoSusilniStroj() throws Exception {

		WebDriver driver = threadLocalDriver.get();
	//	logger.assignCategory("Bančne kartice");
	    long threadId = Thread.currentThread().getId();
	    String sessionId = ((ChromeDriver) driver).getSessionId().toString();
	    System.out.println("Thread ID: " + threadId + ", Session ID: " + sessionId);
	    	    
	    PralnoSusilniStroj hlad = new PralnoSusilniStroj(driver);
	    hlad.NapraveTest();
	    
	    driver.quit();
	    threadLocalDriver.remove();
	}
	
	@Test(groups = {"Premoz","Naprave"},retryAnalyzer = RerunTest.class)
	public void Stedilnik() throws Exception {

		WebDriver driver = threadLocalDriver.get();
	//	logger.assignCategory("Bančne kartice");
	    long threadId = Thread.currentThread().getId();
	    String sessionId = ((ChromeDriver) driver).getSessionId().toString();
	    System.out.println("Thread ID: " + threadId + ", Session ID: " + sessionId);
	    

	    
	    Stedilnik hlad = new Stedilnik(driver);
	    hlad.NapraveTest();
	    
	    driver.quit();
	    threadLocalDriver.remove();
	}
	
	@Test(groups = {"Premoz","Naprave"},retryAnalyzer = RerunTest.class)
	public void SusilniStroj() throws Exception {

		WebDriver driver = threadLocalDriver.get();
	//	logger.assignCategory("Bančne kartice");
	    long threadId = Thread.currentThread().getId();
	    String sessionId = ((ChromeDriver) driver).getSessionId().toString();
	    System.out.println("Thread ID: " + threadId + ", Session ID: " + sessionId);
	    

	    
	    SusilniStroj hlad = new SusilniStroj(driver);
	    hlad.NapraveTest();
	    
	    driver.quit();
	    threadLocalDriver.remove();
	}
	
	@Test(groups = {"Premoz","Naprave"},retryAnalyzer = RerunTest.class)
	public void VinskaVitrina() throws Exception {

		WebDriver driver = threadLocalDriver.get();
	//	logger.assignCategory("Bančne kartice");
	    long threadId = Thread.currentThread().getId();
	    String sessionId = ((ChromeDriver) driver).getSessionId().toString();
	    System.out.println("Thread ID: " + threadId + ", Session ID: " + sessionId);
	    

	    
	    VinskaVitrina hlad = new VinskaVitrina(driver);
	    hlad.NapraveTest();
	    
	    driver.quit();
	    threadLocalDriver.remove();
	}
	
	@Test(groups = {"Premoz","Naprave"},retryAnalyzer = RerunTest.class)
	public void Zmrzovalnik() throws Exception {

		WebDriver driver = threadLocalDriver.get();
	//	logger.assignCategory("Bančne kartice");
	    long threadId = Thread.currentThread().getId();
	    String sessionId = ((ChromeDriver) driver).getSessionId().toString();
	    System.out.println("Thread ID: " + threadId + ", Session ID: " + sessionId);
	    

	    
	    Zmrzovalnik hlad = new Zmrzovalnik(driver);
	    hlad.NapraveTest();
	    
	    driver.quit();
	    threadLocalDriver.remove();
	}
	
	// Ostale naprave
	
	@Test(groups = {"Premoz","Naprave"},retryAnalyzer = RerunTest.class)
	public void AvdioVideo() throws Exception {

		WebDriver driver = threadLocalDriver.get();
	//	logger.assignCategory("Bančne kartice");
	    long threadId = Thread.currentThread().getId();
	    String sessionId = ((ChromeDriver) driver).getSessionId().toString();
	    System.out.println("Thread ID: " + threadId + ", Session ID: " + sessionId);
	    

	    
	    AvdioVideoPredvajalnik hlad = new AvdioVideoPredvajalnik(driver);
	    hlad.NapraveTest();
	    
	    driver.quit();
	    threadLocalDriver.remove();
	}
	
	@Test(groups = {"Premoz","Naprave"},retryAnalyzer = RerunTest.class)
	public void Fotoaparat() throws Exception {

		WebDriver driver = threadLocalDriver.get();
	//	logger.assignCategory("Bančne kartice");
	    long threadId = Thread.currentThread().getId();
	    String sessionId = ((ChromeDriver) driver).getSessionId().toString();
	    System.out.println("Thread ID: " + threadId + ", Session ID: " + sessionId);
	    

	    
	    Fotoaparat hlad = new Fotoaparat(driver);
	    hlad.NapraveTest();
	    
	    driver.quit();
	    threadLocalDriver.remove();
	}
	
	@Test(groups = {"Premoz","Naprave"},retryAnalyzer = RerunTest.class)
	public void Kamera() throws Exception {

		WebDriver driver = threadLocalDriver.get();
	//	logger.assignCategory("Bančne kartice");
	    long threadId = Thread.currentThread().getId();
	    String sessionId = ((ChromeDriver) driver).getSessionId().toString();
	    System.out.println("Thread ID: " + threadId + ", Session ID: " + sessionId);
	    

	    
	    Kamera hlad = new Kamera(driver);
	    hlad.NapraveTest();
	    
	    driver.quit();
	    threadLocalDriver.remove();
	}
	
	@Test(groups = {"Premoz","Naprave"},retryAnalyzer = RerunTest.class)
	public void Monitor() throws Exception {

		WebDriver driver = threadLocalDriver.get();
	//	logger.assignCategory("Bančne kartice");
	    long threadId = Thread.currentThread().getId();
	    String sessionId = ((ChromeDriver) driver).getSessionId().toString();
	    System.out.println("Thread ID: " + threadId + ", Session ID: " + sessionId);
	    

	    
	    Monitor hlad = new Monitor(driver);
	    hlad.NapraveTest();
	    
	    driver.quit();
	    threadLocalDriver.remove();
	}
	
	@Test(groups = {"Premoz","Naprave"},retryAnalyzer = RerunTest.class)
	public void NamizniRacunalnik() throws Exception {

		WebDriver driver = threadLocalDriver.get();
	//	logger.assignCategory("Bančne kartice");
	    long threadId = Thread.currentThread().getId();
	    String sessionId = ((ChromeDriver) driver).getSessionId().toString();
	    System.out.println("Thread ID: " + threadId + ", Session ID: " + sessionId);
	    

	    
	    NamizniRacunalnik hlad = new NamizniRacunalnik(driver);
	    hlad.NapraveTest();
	    
	    driver.quit();
	    threadLocalDriver.remove();
	}
	
	@Test(groups = {"Premoz","Naprave"},retryAnalyzer = RerunTest.class)
	public void NavigacijskaNaprava() throws Exception {

		WebDriver driver = threadLocalDriver.get();
	//	logger.assignCategory("Bančne kartice");
	    long threadId = Thread.currentThread().getId();
	    String sessionId = ((ChromeDriver) driver).getSessionId().toString();
	    System.out.println("Thread ID: " + threadId + ", Session ID: " + sessionId);
	    

	    
	    NavigacijskaNaprava hlad = new NavigacijskaNaprava(driver);
	    hlad.NapraveTest();
	    
	    driver.quit();
	    threadLocalDriver.remove();
	}
	
	@Test(groups = {"Premoz","Naprave"},retryAnalyzer = RerunTest.class)
	public void OpticniCitalnik() throws Exception {

		WebDriver driver = threadLocalDriver.get();
	//	logger.assignCategory("Bančne kartice");
	    long threadId = Thread.currentThread().getId();
	    String sessionId = ((ChromeDriver) driver).getSessionId().toString();
	    System.out.println("Thread ID: " + threadId + ", Session ID: " + sessionId);
	    

	    
	    OpticniCitalnik hlad = new OpticniCitalnik(driver);
	    hlad.NapraveTest();
	    
	    driver.quit();
	    threadLocalDriver.remove();
	}
	
	@Test(groups = {"Premoz","Naprave"},retryAnalyzer = RerunTest.class)
	public void Projektor() throws Exception {

		WebDriver driver = threadLocalDriver.get();
	//	logger.assignCategory("Bančne kartice");
	    long threadId = Thread.currentThread().getId();
	    String sessionId = ((ChromeDriver) driver).getSessionId().toString();
	    System.out.println("Thread ID: " + threadId + ", Session ID: " + sessionId);
	    

	    
	    Projektor hlad = new Projektor(driver);
	    hlad.NapraveTest();
	    
	    driver.quit();
	    threadLocalDriver.remove();
	}
	
	@Test(groups = {"Premoz","Naprave"},retryAnalyzer = RerunTest.class)
	public void SkleniNapraveIgralnaKonzola() throws Exception {

		WebDriver driver = threadLocalDriver.get();
	//	logger.assignCategory("Bančne kartice");
	    long threadId = Thread.currentThread().getId();
	    String sessionId = ((ChromeDriver) driver).getSessionId().toString();
	    System.out.println("Thread ID: " + threadId + ", Session ID: " + sessionId);
	    

	    
	    SkleniNapraveIgralnaKonzola hlad = new SkleniNapraveIgralnaKonzola(driver);
	    hlad.NapraveTest();
	    
	    driver.quit();
	    threadLocalDriver.remove();
	}
	
	@Test(groups = {"Premoz","Naprave"},retryAnalyzer = RerunTest.class)
	public void Snemalnik() throws Exception {

		WebDriver driver = threadLocalDriver.get();
	//	logger.assignCategory("Bančne kartice");
	    long threadId = Thread.currentThread().getId();
	    String sessionId = ((ChromeDriver) driver).getSessionId().toString();
	    System.out.println("Thread ID: " + threadId + ", Session ID: " + sessionId);
	    

	    
	    Snemalnik hlad = new Snemalnik(driver);
	    hlad.NapraveTest();
	    
	    driver.quit();
	    threadLocalDriver.remove();
	}
	
	@Test(groups = {"Premoz","Naprave"},retryAnalyzer = RerunTest.class)
	public void Streznik() throws Exception {

		WebDriver driver = threadLocalDriver.get();
	//	logger.assignCategory("Bančne kartice");
	    long threadId = Thread.currentThread().getId();
	    String sessionId = ((ChromeDriver) driver).getSessionId().toString();
	    System.out.println("Thread ID: " + threadId + ", Session ID: " + sessionId);
	    

	    
	    Streznik hlad = new Streznik(driver);
	    hlad.NapraveTest();
	    
	    driver.quit();
	    threadLocalDriver.remove();
	}
	
	@Test(groups = {"Premoz","Naprave"},retryAnalyzer = RerunTest.class)
	public void Televizor() throws Exception {

		WebDriver driver = threadLocalDriver.get();
	//	logger.assignCategory("Bančne kartice");
	    long threadId = Thread.currentThread().getId();
	    String sessionId = ((ChromeDriver) driver).getSessionId().toString();
	    System.out.println("Thread ID: " + threadId + ", Session ID: " + sessionId);
	    

	    
	    Televizor hlad = new Televizor(driver);
	    hlad.NapraveTest();
	    
	    driver.quit();
	    threadLocalDriver.remove();
	}
	
	@Test(groups = {"Premoz","Naprave"},retryAnalyzer = RerunTest.class)
	public void Tiskalnik() throws Exception {

		WebDriver driver = threadLocalDriver.get();
	//	logger.assignCategory("Bančne kartice");
	    long threadId = Thread.currentThread().getId();
	    String sessionId = ((ChromeDriver) driver).getSessionId().toString();
	    System.out.println("Thread ID: " + threadId + ", Session ID: " + sessionId);
	    

	    
	    Tiskalnik hlad = new Tiskalnik(driver);
	    hlad.NapraveTest();
	    
	    driver.quit();
	    threadLocalDriver.remove();
	}
	
	
	// Prenosne naprave
	
	@Test(groups = {"Premoz","Naprave"},retryAnalyzer = RerunTest.class)
	public void PametnaUra() throws Exception {

		WebDriver driver = threadLocalDriver.get();
	//	logger.assignCategory("Bančne kartice");
	    long threadId = Thread.currentThread().getId();
	    String sessionId = ((ChromeDriver) driver).getSessionId().toString();
	    System.out.println("Thread ID: " + threadId + ", Session ID: " + sessionId);
	    

	
        PametnaUra hlad = new PametnaUra(driver);
	    hlad.NapraveTest();
	    
	    driver.quit();
	    threadLocalDriver.remove();
	}
	
	
	@Test(groups = {"Premoz","Naprave"},retryAnalyzer = RerunTest.class)
	public void PrenosniRacunalnik() throws Exception {

		WebDriver driver = threadLocalDriver.get();
	//	logger.assignCategory("Bančne kartice");
	    long threadId = Thread.currentThread().getId();
	    String sessionId = ((ChromeDriver) driver).getSessionId().toString();
	    System.out.println("Thread ID: " + threadId + ", Session ID: " + sessionId);
	    

	
	    PrenosniRacunalnik hlad = new PrenosniRacunalnik(driver);
	    hlad.NapraveTest();
	    
	    driver.quit();
	    threadLocalDriver.remove();
	}
	
	@Test(groups = {"Premoz","Naprave"},retryAnalyzer = RerunTest.class)
	public void TablicniInPrenosniVEnem() throws Exception {

		WebDriver driver = threadLocalDriver.get();
	//	logger.assignCategory("Bančne kartice");
	    long threadId = Thread.currentThread().getId();
	    String sessionId = ((ChromeDriver) driver).getSessionId().toString();
	    System.out.println("Thread ID: " + threadId + ", Session ID: " + sessionId);
	    

	
	    TablicniInPrenosniVEnem hlad = new TablicniInPrenosniVEnem(driver);
	    hlad.NapraveTest();
	    
	    driver.quit();
	    threadLocalDriver.remove();
	}
	
	@Test(groups = {"Premoz","Naprave"},retryAnalyzer = RerunTest.class)
	public void TablicniRacunalnik() throws Exception {

		WebDriver driver = threadLocalDriver.get();
	//	logger.assignCategory("Bančne kartice");
	    long threadId = Thread.currentThread().getId();
	    String sessionId = ((ChromeDriver) driver).getSessionId().toString();
	    System.out.println("Thread ID: " + threadId + ", Session ID: " + sessionId);
	    

	
	    TablicniRacunalnik hlad = new TablicniRacunalnik(driver);
	    hlad.NapraveTest();
	    
	    driver.quit();
	    threadLocalDriver.remove();
	}
			
	// Testi za premoženje:
		
	
//	@Test(groups = {"Premoz"},retryAnalyzer = RerunTest.class)
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
	
	
	
	/*
	@Test(groups = {"Premoz"},retryAnalyzer = RerunTest.class)
	public void NezgodaQ() throws Exception {

		WebDriver driver = threadLocalDriver.get();
	//	logger.assignCategory("Bančne kartice");
	    long threadId = Thread.currentThread().getId();
	    String sessionId = ((ChromeDriver) driver).getSessionId().toString();
	    System.out.println("Thread ID: " + threadId + ", Session ID: " + sessionId);
	    
	    
	    Nezgoda nez = new Nezgoda(driver);
	    nez.NezgodaQTest();
	    
	    driver.quit();
	    threadLocalDriver.remove();
	}	
	
	@Test(groups = {"Premoz"},retryAnalyzer = RerunTest.class)
	public void BancneKartice() throws Exception {
		
		WebDriver driver = threadLocalDriver.get();
	    long threadId = Thread.currentThread().getId();
	    String sessionId = ((ChromeDriver) driver).getSessionId().toString();
	    System.out.println("Thread ID: " + threadId + ", Session ID: " + sessionId);
	    
	    BancneKartice bk = new BancneKartice(driver);
	    bk.BancneKarticeTest();
	    
	    driver.quit();
	    threadLocalDriver.remove();
	}
	
	@Test(retryAnalyzer = RerunTest.class, groups = {"Premoz"})
	public void Dom() throws Exception {
		
		//WebDriver driver = drivers.get("DomTest");
		WebDriver driver = threadLocalDriver.get();
	//	logger.assignCategory("Dom");
		long threadId = Thread.currentThread().getId();
		String sessionId = ((ChromeDriver) driver).getSessionId().toString();
		System.out.println("Thread ID: " + threadId + ", Session ID: " + sessionId);
		
		Dom d = new Dom(driver);
		d.DomQTest();
		
		driver.quit();
		threadLocalDriver.remove();
	}
	
	@Test(retryAnalyzer = RerunTest.class, groups = {"Premoz"})
	public void Mikromobilnost() throws Exception {
		//WebDriver driver = drivers.get("MikromobilnostTest");
		WebDriver driver = threadLocalDriver.get();
	//	logger.assignCategory("Mikromobilnost");
		long threadId = Thread.currentThread().getId();
		//WebDriver driver = getDriver();
	    String sessionId = ((ChromeDriver) driver).getSessionId().toString();
	    System.out.println("Thread ID: " + threadId + ", Session ID: " + sessionId);
	    
	    Mikromobilnost mikro = new Mikromobilnost(driver);
	    mikro.MikromobilnostTest();
	    
	    driver.quit();
	    threadLocalDriver.remove();
	}
	
	// ne zaganja
	@Test(retryAnalyzer = RerunTest.class, groups = {"Premoz","Other"})
	public void Avtomobili() throws Exception {
		//WebDriver driver = drivers.get("AvtomobiliTest");
		WebDriver driver = threadLocalDriver.get();
	//	logger.assignCategory("Avtomobili");
		long threadId = Thread.currentThread().getId();
	    String sessionId = ((ChromeDriver) driver).getSessionId().toString();
	    System.out.println("Thread ID: " + threadId + ", Session ID: " + sessionId);

	    Avtomobili avto = new Avtomobili(driver);
	    DrajvKodaValidacija drajv = new DrajvKodaValidacija(driver);
	    drajv.DrajvKoda();
	    avto.AvtomobiliTestVsiPopusti();
	    
	    driver.quit();
	    threadLocalDriver.remove();
	}
	
	@Test(retryAnalyzer = RerunTest.class, groups = {"Premoz"})
	public void AvtomobiliBrezPrijave() throws Exception {
		//WebDriver driver = drivers.get("AvtomobiliTest");
		WebDriver driver = threadLocalDriver.get();
	//	logger.assignCategory("Avtomobili");
		long threadId = Thread.currentThread().getId();
	    String sessionId = ((ChromeDriver) driver).getSessionId().toString();
	    System.out.println("Thread ID: " + threadId + ", Session ID: " + sessionId);

	    Avtomobili avto = new Avtomobili(driver);
	    avto.AvtomobiliTestBrezPrijave();
	    				
		driver.quit();
		threadLocalDriver.remove();
	}
	
	@Test(retryAnalyzer = RerunTest.class, groups = {"Premoz"})
	public void MaleZivali() throws Exception {
	//	WebDriver driver = drivers.get("MaleZivaliTest");
		WebDriver driver = threadLocalDriver.get();
	//	logger.assignCategory("Male živali");
		long threadId = Thread.currentThread().getId();
	//	WebDriver driver = getDriver();
	    String sessionId = ((ChromeDriver) driver).getSessionId().toString();
	    System.out.println("Thread ID: " + threadId + ", Session ID: " + sessionId);

	    MaleZivali mz = new MaleZivali(driver);
	    mz.MaleZivaliQTest();
	 
	 	driver.quit();
	 	threadLocalDriver.remove();
	}
	
	@Test(retryAnalyzer = RerunTest.class, groups = {"Premoz"})
	public void MladiVoznik() throws Exception {
		//WebDriver driver = drivers.get("MladiVoznikTest");
		WebDriver driver = threadLocalDriver.get();
	//	logger.assignCategory("Mladi voznik");
		long threadId = Thread.currentThread().getId();
	//	WebDriver driver = getDriver();
	    String sessionId = ((ChromeDriver) driver).getSessionId().toString();
	    System.out.println("Thread ID: " + threadId + ", Session ID: " + sessionId);

	    MladiVoznik mv = new MladiVoznik(driver);
	    mv.MladiVoznikTest();
	    
	    driver.quit();
	    threadLocalDriver.remove();
	     
	}

	@Test(retryAnalyzer = RerunTest.class, groups = {"Premoz"})
	public void Motor() throws InterruptedException {
		//WebDriver driver = drivers.get("MotorTest");
		WebDriver driver = threadLocalDriver.get();
	//	logger.assignCategory("Motor");
		long threadId = Thread.currentThread().getId();
		//WebDriver driver = getDriver();
	    String sessionId = ((ChromeDriver) driver).getSessionId().toString();
	    System.out.println("Thread ID: " + threadId + ", Session ID: " + sessionId);

	    Motor mot = new Motor(driver);
	    mot.MotorTest();
	    
	    driver.quit();
	    threadLocalDriver.remove();
	}
	
	
	@Test(retryAnalyzer = RerunTest.class, groups = {"Premoz"})
	public void OdpovedPotovanj() throws Exception {
	//	WebDriver driver = drivers.get("OdpovedPotovanjTest");
		WebDriver driver = threadLocalDriver.get();
	//	logger.assignCategory("Odpoved potovanj");
		long threadId = Thread.currentThread().getId();
		//WebDriver driver = getDriver();
	    String sessionId = ((ChromeDriver) driver).getSessionId().toString();
	    System.out.println("Thread ID: " + threadId + ", Session ID: " + sessionId);

	    OdpovedPotovanj op = new OdpovedPotovanj(driver);
	    op.OdpovedPotovanjTest();
	    
	    driver.quit();
	    threadLocalDriver.remove();
	     
	}
	
	@Test(retryAnalyzer = RerunTest.class, groups = {"Premoz"})
	public void OsebnaZascitaPosamicno() throws Exception {
	//	WebDriver driver = drivers.get("OsebnaZascitaTest");
		WebDriver driver = threadLocalDriver.get();
	//	logger.assignCategory("Osebna zaščita");
		long threadId = Thread.currentThread().getId();
	//	WebDriver driver = getDriver();
	    String sessionId = ((ChromeDriver) driver).getSessionId().toString();
	    System.out.println("Thread ID: " + threadId + ", Session ID: " + sessionId);
	    
	    OsebnaZascita oz = new OsebnaZascita(driver);
	    oz.OsebnaZascitaPosamicno();
	    
	    driver.quit();
	    threadLocalDriver.remove();
	    
	}
	
	@Test(retryAnalyzer = RerunTest.class, groups = {"Premoz"})
	public void OsebnaZascitaDruzinsko() throws Exception {
	//	WebDriver driver = drivers.get("OsebnaZascitaTest");
		WebDriver driver = threadLocalDriver.get();
	//	logger.assignCategory("Osebna zaščita");
		long threadId = Thread.currentThread().getId();
	//	WebDriver driver = getDriver();
	    String sessionId = ((ChromeDriver) driver).getSessionId().toString();
	    System.out.println("Thread ID: " + threadId + ", Session ID: " + sessionId);

	    OsebnaZascita oz = new OsebnaZascita(driver);
	    oz.OsebnaZascitaDruzinsko();
	    
	    driver.quit();
	    threadLocalDriver.remove();
	    
	}
	
	@Test(retryAnalyzer = RerunTest.class, groups = {"Premoz"})
	public void Potovanje() throws Exception {
		//WebDriver driver = drivers.get("PotovanjeTest");
		WebDriver driver = threadLocalDriver.get();
	//	logger.assignCategory("Potovanje");
		long threadId = Thread.currentThread().getId();
		//WebDriver driver = getDriver();
	    String sessionId = ((ChromeDriver) driver).getSessionId().toString();
	    System.out.println("Thread ID: " + threadId + ", Session ID: " + sessionId);

	    Potovanje pot = new Potovanje(driver);
	    pot.PotovanjeQTest();
	    
	    driver.quit();
	    threadLocalDriver.remove();
		
	}

	@Test(retryAnalyzer = RerunTest.class, groups = {"Premoz"})
	public void Naprave() throws Exception {
		//WebDriver driver = drivers.get("NapraveTest");
		WebDriver driver = threadLocalDriver.get();
	//	logger.assignCategory("Naprave");
		long threadId = Thread.currentThread().getId();
		String sessionId = ((ChromeDriver) driver).getSessionId().toString();
	    System.out.println("Thread ID: " + threadId + ", Session ID: " + sessionId);
	    
	    MobilniTelefoni mob = new MobilniTelefoni(driver);
	    mob.NapraveTest();
	    
	    driver.quit();
	    threadLocalDriver.remove();
	    
	}
	
	
	@Test(groups = {"Premoz"},retryAnalyzer = RerunTest.class)
	public void InformativniIzracunValidacija() throws Exception {
		//WebDriver driver = drivers.get("InformativniIzracunValidacijaInSklenitev");
		WebDriver driver = threadLocalDriver.get();
	//	logger.assignCategory("Informativni izračun");
		long threadId = Thread.currentThread().getId();
	//	WebDriver driver = getDriver();
		String sessionId = ((ChromeDriver) driver).getSessionId().toString();
		System.out.println("Thread ID: " + threadId + ", Session ID: " + sessionId);
		
		InformativniIzracunPolica222 izr = new InformativniIzracunPolica222(driver);
		izr.InformativniIzracunPolicaTest();
		
		driver.quit();
		threadLocalDriver.remove();
				
	}
	/*
	@Test(priority=16,retryAnalyzer = RerunTest.class, groups = {"Premoz"})
	public void AvtoObroki() throws Exception {
		
		WebDriver driver = threadLocalDriver.get();
	//	logger.assignCategory("Avto obroki");
		long threadId = Thread.currentThread().getId();
		String sessionId = ((ChromeDriver) driver).getSessionId().toString();
	    System.out.println("Thread ID: " + threadId + ", Session ID: " + sessionId);
	    
	    AvtoSkleni as = new AvtoSkleni(driver);
	    as.AvtoTest();
	    
	    driver.quit();
	    threadLocalDriver.remove();
		
	}
	*/
	
	
	
	
	/*/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////*/
	/*///////////////////////////////////////////// POTOVANJE V TUJINO ////////////////////////////////////////////////////*/
	/*↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓*/
	
	
	
	
	/////////////////////////////*/ POSAMIČNO ZAVAROVANJE /*//////////////////////////////
	
	
	//  ↓ potovanje v tujino posamicno - prijavljen ( trajanje 2 dni, Paket B )↓ 
	
//		@Test(groups = {"Premoz"}
//		  ,retryAnalyzer = RerunTest.class
//			)
//		public void PotovanjeTujina_Posamicno_Prijavljen() throws Exception {
//		
//		WebDriver driver = threadLocalDriver.get();
//	    long threadId = Thread.currentThread().getId();
//	    String sessionId = ((ChromeDriver) driver).getSessionId().toString();
//	    System.out.println("Thread ID: " + threadId + ", Session ID: " + sessionId);
//	    
//	    PotovanjeTujina_Posamicno_Prijavljen ptPP = new PotovanjeTujina_Posamicno_Prijavljen(driver);
//	    ptPP.PotovanjeTujinaPosamicnoPrijavljenTest();
//	    
//	    driver.quit();
//	    threadLocalDriver.remove();
//	}  
	
	
	
	//  ↓ potovanje v tujino posamicno - neprijavljen ( trajanje 3 dni, Paket A )↓ 
	
//		@Test(groups = {"Premoz"}
//			,retryAnalyzer = RerunTest.class
//				)
//			public void PotovanjeTujina_Posamicno_Neprijavljen() throws Exception {
//			
//			WebDriver driver = threadLocalDriver.get();
//		    long threadId = Thread.currentThread().getId();
//		    String sessionId = ((ChromeDriver) driver).getSessionId().toString();
//		    System.out.println("Thread ID: " + threadId + ", Session ID: " + sessionId);
//		    
//		    PotovanjeTujina_Posamicno_Neprijavljen ptPN = new PotovanjeTujina_Posamicno_Neprijavljen(driver);
//		    ptPN.PotovanjeTujinaPosamicnoTest();
//		    
//		    driver.quit();
//		    threadLocalDriver.remove();
//		}  

	
	
	//  ↓ potovanje v tujino posamicno - naknadna prijava ( trajanje 5 dni + se boste v tujini ukvarjali z različnimi športi..."DA", Paket C )↓
	
//		@Test(groups = {"Premoz"}
//			  ,retryAnalyzer = RerunTest.class
//				)
//			public void PotovanjeTujina_Posamicno_NaknadnaPrijava() throws Exception {
//		
//			WebDriver driver = threadLocalDriver.get();
//	    	long threadId = Thread.currentThread().getId();
//	   		String sessionId = ((ChromeDriver) driver).getSessionId().toString();
//	    	System.out.println("Thread ID: " + threadId + ", Session ID: " + sessionId);
//	    
//	   	    PotovanjeTujina_Posamicno_NaknadnaPrijava ptPNP = new PotovanjeTujina_Posamicno_NaknadnaPrijava(driver);
//	    	ptPNP.PotovanjeTujinaPosamicnoNaknadnaPrijavaTest();
//	    
//	    	driver.quit();
//	    	threadLocalDriver.remove();
//		}  
	
	
		

	
	//////////////////////////*/ DRUZINSKO ZAVAROVANJE /*//////////////////////////

	
	//  ↓ potovanje v tujino druzinsko - prijavljen ( trajanje 8 dni, Paket B , zavarovanec se bo v tujini ukvarjal z rizičnimi športi..."DA" )↓
	
//		 @Test(groups = {"Premoz"}
//		 	,retryAnalyzer = RerunTest.class
//				)
//		 	public void PotovanjeTujina_Druzinsko_Prijavljen() throws Exception {
//			 
//			WebDriver driver = threadLocalDriver.get();
//			long threadId = Thread.currentThread().getId();
//			String sessionId = ((ChromeDriver) driver).getSessionId().toString();
//			System.out.println("Thread ID: " + threadId + ", Session ID: " + sessionId);
//	
//			PotovanjeTujina_Druzinsko_Prijavljen ptDP = new PotovanjeTujina_Druzinsko_Prijavljen(driver);
//			ptDP.PotovanjeTujinaDruzinskoPrijavljenTest();
//	
//			driver.quit();
//			threadLocalDriver.remove();
//		}	  

	
	
	//  ↓ potovanje v tujino druzinsko - neprijavljen ( trajanje 1 leto + "Manj kot 90 dni", Paket C )↓  NEDELA
	
//		@Test(groups = {"Premoz"}
//	 		,retryAnalyzer = RerunTest.class
//  		     )
//		public void PotovanjeTujina_Druzisnko_Neprijavljen() throws Exception {
//		
//			WebDriver driver = threadLocalDriver.get();
//	    	long threadId = Thread.currentThread().getId();
//	    	String sessionId = ((ChromeDriver) driver).getSessionId().toString();
//	   		System.out.println("Thread ID: " + threadId + ", Session ID: " + sessionId);
//	    
//	   		PotovanjeTujina_Druzinsko_Neprijavljen ptDN = new PotovanjeTujina_Druzinsko_Neprijavljen(driver);
//	    	ptDN.PotovanjeTujinaDruzinskoTest();
//	    
//	   		driver.quit();
//	    	threadLocalDriver.remove();
//		}  
	

	
	//  ↓ potovanje v tujino druzinsko - naknadna prijava ( trajanje 1 leto + "90 ali več dni", Paket A )↓
	
//		 @Test(groups = {"Premoz"}
//		 	 ,retryAnalyzer = RerunTest.class
//				)
//		 	public void PotovanjeTujina_Druzinsko_NaknadnaPrijava() throws Exception {
//			 
//			WebDriver driver = threadLocalDriver.get();
//    		long threadId = Thread.currentThread().getId();
//    		String sessionId = ((ChromeDriver) driver).getSessionId().toString();
//    		System.out.println("Thread ID: " + threadId + ", Session ID: " + sessionId);
//    
//    		PotovanjeTujina_Druzinsko_NaknadnaPrijava ptDNP = new PotovanjeTujina_Druzinsko_NaknadnaPrijava(driver);
//    		ptDNP.PotovanjeTujinaDruzinskoNadoknadnaPrijavaTest();
//    
//    		driver.quit();
//    		threadLocalDriver.remove();
//		}	  

	
	
	
	
	//////////////////////////*/ SKUPINSKO ZAVAROVANJE /*//////////////////////////

	
	//  ↓ potovanje v tujino skupinsko - neprijavljen ( trajanje 15 dni, Paket A, "število zavarovancev = 2" )↓
	
		@Test(groups = {"Premoz"})
			public void PotovanjeTujina_Skupinsko_Neprijavljen() throws Exception {
		
			WebDriver driver = threadLocalDriver.get();
	   		long threadId = Thread.currentThread().getId();
	   		String sessionId = ((ChromeDriver) driver).getSessionId().toString();
	   		System.out.println("Thread ID: " + threadId + ", Session ID: " + sessionId);
	    
	    	PotovanjeTujina_Skupinsko_Neprijavljen ptSN = new PotovanjeTujina_Skupinsko_Neprijavljen(driver);
	    	ptSN.PotovanjeTujinaSkupinskoTest();
	    
	   	 	driver.quit();
	    	threadLocalDriver.remove();
	}  
	
	
	
	
	//  ↓ potovanje v tujino skupinsko - prijavljen ( trajanje 21 dni, Paket B, sklenitelj je tudi zavarovanec + se bo v tujini okvarjal s športom, "število zavarovancev = 3" )↓
	
//		@Test(groups = {"Premoz"}
//			,retryAnalyzer = RerunTest.class
//			)
//			public void PotovanjeTujina_Skupinsko_Prijavljen() throws Exception {
//	
//			WebDriver driver = threadLocalDriver.get();
//			long threadId = Thread.currentThread().getId();
//			String sessionId = ((ChromeDriver) driver).getSessionId().toString();
//			System.out.println("Thread ID: " + threadId + ", Session ID: " + sessionId);
//	
//			PotovanjeTujina_Skupinsko_Prijavljen ptSP = new PotovanjeTujina_Skupinsko_Prijavljen(driver);
//			ptSP.PotovanjeTujinaSkupinskoPrijavljenTest();
//	
//		 	driver.quit();
//			threadLocalDriver.remove();
//	}    

	
	
	//  ↓ potovanje v tujino skupinsko - naknadna prijava  ( trajanje 30 dni, Paket C, zavarovana oseba se bo ukvarjala z športi..."DA" (v modalu), "število zavarovancev = 4" )↓
//	
//		@Test(groups = {"Premoz"}
//			 ,retryAnalyzer = RerunTest.class
//			)
//			public void PotovanjeTujina_Skupinsko_NaknadnaPrijava() throws Exception {
//	
//			WebDriver driver = threadLocalDriver.get();
//  		long threadId = Thread.currentThread().getId();
//  		String sessionId = ((ChromeDriver) driver).getSessionId().toString();
//  		System.out.println("Thread ID: " + threadId + ", Session ID: " + sessionId);
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
		
//		@Test(groups = {"Premoz"}
//			,retryAnalyzer = RerunTest.class
//			)
//			public void PotovanjeTujina_Testiranje_Funkcionalnostih_ENA() throws Exception {
//	
//			WebDriver driver = threadLocalDriver.get();
//  		long threadId = Thread.currentThread().getId();
//  		String sessionId = ((ChromeDriver) driver).getSessionId().toString();
//  		System.out.println("Thread ID: " + threadId + ", Session ID: " + sessionId);
//   
// 		PotovanjeTujina_Testiranje_Funkcionalnostih_ENA ptTF = new PotovanjeTujina_Testiranje_Funkcionalnostih_ENA(driver);
//  		ptTF.PotovanjeTujinaTestiranjeFunkcionalnostihTestENA();
//   
//  	 	driver.quit();
//   		threadLocalDriver.remove();
//	}    
		
	
		
	//  ↓ potovanje v tujino posamicno prijavljen TESTIRANJE FUNKCIONALNOSTIH DVA ( trajanje 60 dni, obcija želim nadaljevati kasneje označena, plačilo na obroke )↓
		
//		@Test(groups = {"Premoz"}
//			//,retryAnalyzer = RerunTest.class
//			)
//			public void PotovanjeTujina_Testiranje_Funkcionalnostih_DVA() throws Exception {
//	
//			WebDriver driver = threadLocalDriver.get();
//  		long threadId = Thread.currentThread().getId();
//  		String sessionId = ((ChromeDriver) driver).getSessionId().toString();
//  		System.out.println("Thread ID: " + threadId + ", Session ID: " + sessionId);
//   
//  		PotovanjeTujina_Testiranje_Funkcionalnostih_DVA ptTF = new PotovanjeTujina_Testiranje_Funkcionalnostih_DVA(driver);
//  		ptTF.PotovanjeTujinaTestiranjeFunkcionalnostihTestDVA();
//   
//  	 	driver.quit();
//   		threadLocalDriver.remove();
//	}    


	
	/*↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑*/
	/*////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////*/
	/*////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
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
			//	String fullScreenshotPath = fullScreenshot(driver, result.getName());
				logger.info("Screenshot RETRY napake za: " + result.getName() + " URL napake "  + " je: " + driver.getCurrentUrl(), MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());				
				
				neuspesni.add(result.getName());
				
				failedSteps++;
												
			}
			
			else if(result.getStatus() == ITestResult.SKIP ) {
				logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - Test Case SKIPPED", ExtentColor.ORANGE));
				logger.log(Status.INFO, MarkupHelper.createLabel(result.getThrowable() + result.getName() + " - Test Case SKIPPED", ExtentColor.ORANGE));

				String screenshotPath = getScreenshot(driver, result.getName());	 
			//	String fullScreenshotPath = fullScreenshot(driver, result.getName());
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
						        
			
//	        DatabaseQueryTestEnv db = new DatabaseQueryTestEnv();
//	        db.triglavTestsData();
			
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
		TestRunnerLocal.testList = testList;
	}

	public static int getPassedSteps() {
		return passedSteps;





	}


	public void setPassedSteps(int passedSteps) {
		TestRunnerLocal.passedSteps = passedSteps;
	}


	public static int getFailedSteps() {
		return failedSteps;
	}


	public void setFailedSteps(int failedSteps) {
		TestRunnerLocal.failedSteps = failedSteps;
	}


	public static int getSkippedSteps() {
		return skippedSteps;
	}


	public void setSkippedSteps(int skippedSteps) {
		TestRunnerLocal.skippedSteps = skippedSteps;
	}
	
	public static String getTestName() {
		return testName;
	}


	public void  setTestName(String testName) {
		TestRunnerLocal.testName = testName;
	}


	public static Date getTestStartTime() {
		return testStartTime;
	}


	public void setTestStartTime(Date testStartTime) {
		TestRunnerLocal.testStartTime = testStartTime;
	}


	public static Date getTestEndTime() {
		return testEndTime;
	}


	public void setTestEndTime(Date testEndTime) {
		TestRunnerLocal.testEndTime = testEndTime;
	}


	public static Status getTestStatus() {
		return testStatus;
	}

	public void setTestStatus(Status testStatus) {
		TestRunnerLocal.testStatus = testStatus;
	}

	public static int getPassedTests() {
		return passedTests;
	}

	public static void setPassedTests(int passedTests) {
		TestRunnerLocal.passedTests = passedTests;
	}

	public static int getFailedTests() {
		return failedTests;
	}

	public static void setFailedTests(int failedTests) {
		TestRunnerLocal.failedTests = failedTests;
	}

	public static int getSkippedTests() {
		return skippedTests;
	}

	public static void setSkippedTests(int skippedTests) {
		TestRunnerLocal.skippedTests = skippedTests;
	}

	public static int getAllTests() {
		return allTests;
	}

	public static void setAllTests(int allTests) {
		TestRunnerLocal.allTests = allTests;
	}


	public static List<String> getNeuspesni() {
		return neuspesni;
	}


	public static void setNeuspesni(List<String> neuspesni) {
		TestRunnerLocal.neuspesni = neuspesni;
	}
	
	

}


