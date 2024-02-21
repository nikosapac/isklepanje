package sklepanjeTesti.dbHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.aventstack.extentreports.Status;
/*
import sklepanjeTesti.SklepanjeTestCase;
import sklepanjeTesti.SklepanjeTestCase2;

public class DatabaseQuery2 {
		
	String testName = SklepanjeTestCase2.getTestName();
    Date testStartTime = SklepanjeTestCase2.getTestStartTime();
    String testStatus = SklepanjeTestCase2.getTestStatus().toString();
    int passedSteps = SklepanjeTestCase2.getPassedSteps();
 	int failedSteps = SklepanjeTestCase2.getFailedSteps();
 	List<String> opisNeuspesnih = SklepanjeTestCase2.getNeuspesni();
 	String envName = "qa";
 	
	public void triglavTestsData() throws SQLException {
		
		String formattedString = opisNeuspesnih.toString()
	 		    .replace("[", "")  
	 		    .replace("]", "")  
	 		    .trim();           
	 	
		Timestamp testTimestamp = new Timestamp(testStartTime.getTime());
	 	        
	    System.out.println("Ime testa: " + testName);
		System.out.println("Datum testa: " + testTimestamp);
		System.out.println("Status testa: " + testStatus);
		System.out.println("Uspešni testi: " + passedSteps);
		System.out.println("Neuspešni testi: " + failedSteps);
		System.out.println("Opis neuspešnih testov: " + formattedString);
		
        String SQL = "INSERT INTO SELENIUM.TESTS_DATA(APPLICATION_NAME,TEST_DATE,TEST_STATUS,PASSED_TESTS,FAILED_TESTS,FAILED_TESTS_TEXT) "
                + "VALUES(?,?,?,?,?,?)";
        
        TestsEntity testResult = new TestsEntity();
        testResult.setTestName(testName);
        testResult.setTestTimestamp(testTimestamp);
        testResult.setTestStatus(testStatus);
        testResult.setPassedSteps(passedSteps);
        testResult.setFailedSteps(failedSteps);
        testResult.setFormattedString(formattedString);
        testResult.setEnvName(envName);

        try (Session session = createSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(testResult);
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

	
    private SessionFactory createSessionFactory() {
        Configuration configuration = new Configuration().configure();
        return configuration.buildSessionFactory();
    }
} 
   */

