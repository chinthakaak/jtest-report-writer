package com.github.jtreport.core;

import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;
import org.junit.runner.Description;

/**
 * Class that represents the result of the single test.
 * 
 * @author Fabio Rubino.
 * 
 */
public class TestMethodResult {

	private final Description descriptionResult;
	private final String testDescription;
	private final DateTime executionDate;
	private StateTestEnum testState;
	private String resultDescription;
	private final String testClassName;
	private final String testMethodName;
	private String testKey;
	private final String runningTime;

    private  String testId=null;
    private  String testName=null;
    private  String testDescription2=null;
    private  String testData=null;
    private  String testExpectedResult=null;


    private String testSteps=null;
    private String automated=null;



    /**
	 * Default Constructor
	 * 
	 * @param descriptionResult
	 *            {@link Description} of this test.
	 * @param testClassName
	 *            test class name.
	 * @param testMethodName
	 *            test method name.
	 * @param testDescription
	 *            description of the test.
	 * @param executionDate
	 *            test execution time Date.
	 * @param testState
	 *            steate of the test.
	 * @param resultDescription
	 *            description result of this test.
	 * @param runningTime
	 *            test execution time.
	 * @param testKey
	 *            use test with key for velocity type of report.
	 */
	public TestMethodResult(final Description descriptionResult,
			final String testClassName, final String testMethodName,
			final DateTime executionDate, final StateTestEnum testState,
			final String resultDescription, final String runningTime,
			final String testKey,final String testId,
            final String testName,final String testDescription,
            final String testData,final String testSteps,
            final String testExpectedResult, String automated) {

        this.descriptionResult = descriptionResult;
        this.testClassName = testClassName;
        this.testMethodName = testMethodName;
        this.executionDate = executionDate;
        this.testState = testState;
        this.resultDescription = resultDescription;
        this.runningTime = runningTime;
		this.testKey = testKey;
        this.testId = testId;
        this.testName = testName;
        this.testDescription = testDescription;
        this.testData= testData;
        this.testSteps=testSteps;
        this.testExpectedResult = testExpectedResult;
        this.automated=automated;

	}


	public Description getDescriptionResult() {
		return this.descriptionResult;
	}

	public DateTime getExecutionDate() {
		return this.executionDate;
	}

	public String getFormattdDate() {
		return this.executionDate.toString((ISODateTimeFormat
				.basicDateTimeNoMillis()));
	}

	public String getResultDescription() {
		return this.resultDescription;
	}

	public String getRunningTime() {
		return this.runningTime;
	}

	public String getTestClassName() {
		return this.testClassName;
	}

	public String getTestKey() {
		return this.testKey;
	}

	public String getTestMethodName() {
		return this.testMethodName;
	}

	public StateTestEnum getTestState() {
		return this.testState;
	}

	public void setResultDescription(final String resultDescription) {
		this.resultDescription = resultDescription;
	}

	public void setTestState(final StateTestEnum testState) {
		this.testState = testState;
	}

    public String getTestId() {
        return testId;
    }

    public String getTestName() {
        return testName;
    }

    public String getTestDescription() {
        return testDescription;
    }

    public String getTestData() {
        return testData;
    }

    public String getTestExpectedResult() {
        return testExpectedResult;
    }

    public String getTestSteps() {
        return testSteps;
    }

    public String isAutomated() {
        return automated;
    }

}
