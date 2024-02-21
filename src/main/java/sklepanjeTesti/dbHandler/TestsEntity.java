package sklepanjeTesti.dbHandler;

import java.io.Serializable;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;


@Entity
@Table(name = "TESTS_DATA", schema = "SELENIUM")
public class TestsEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TEST_ID")
    private Long id;

    @Column(name = "APPLICATION_NAME")
    private String testName;

    @Column(name = "TEST_DATE")
    private Timestamp testTimestamp;
    
    @Column(name = "ENV_NAME")
    private String envName;

    @Column(name = "TEST_STATUS")
    private String testStatus;

    @Column(name = "PASSED_TESTS")
    private int passedSteps;

    @Column(name = "FAILED_TESTS")
    private int failedSteps;

    @Column(name = "FAILED_TESTS_TEXT")
    private String formattedString;
    
    public TestsEntity() {
	}
	
	public TestsEntity(Long id, String testName, Timestamp testTimestamp, String envName, String testStatus,
			int passedSteps, int failedSteps, String formattedString) {
		this.id = id;
		this.testName = testName;
		this.testTimestamp = testTimestamp;
		this.envName = envName;
		this.testStatus = testStatus;
		this.passedSteps = passedSteps;
		this.failedSteps = failedSteps;
		this.formattedString = formattedString;
	}
	
	

	public String getEnvName() {
		return envName;
	}

	public void setEnvName(String envName) {
		this.envName = envName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public Timestamp getTestTimestamp() {
		return testTimestamp;
	}

	public void setTestTimestamp(Timestamp testTimestamp) {
		this.testTimestamp = testTimestamp;
	}

	public String getTestStatus() {
		return testStatus;
	}

	public void setTestStatus(String testStatus) {
		this.testStatus = testStatus;
	}

	public int getPassedSteps() {
		return passedSteps;
	}

	public void setPassedSteps(int passedSteps) {
		this.passedSteps = passedSteps;
	}

	public int getFailedSteps() {
		return failedSteps;
	}

	public void setFailedSteps(int failedSteps) {
		this.failedSteps = failedSteps;
	}

	public String getFormattedString() {
		return formattedString;
	}

	public void setFormattedString(String formattedString) {
		this.formattedString = formattedString;
	}
    
    
	
}

