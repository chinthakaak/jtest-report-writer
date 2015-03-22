package com.github.jtreport.datasource;

import com.github.jtreport.core.TestMethodResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

/**
 * Utils to create a datasource,
 * 
 * @author Fabio Rubino.
 * 
 */
public class DataSourceUtils {

	private static final Logger L = LoggerFactory
			.getLogger(DataSourceUtils.class);

	public static DataSource createDataSource(
			final Collection<TestMethodResult> singleTestResultList) {
		final DataSource dataSource = new DataSource("state","testMethodName",
				 "resultDescription", "formattedDate", "runningTime",
                "testId","testName","testDescription","testData","testSteps","testExpectedResult","automated");
		L.debug("Number of test adding in the datasource ["
				+ singleTestResultList.size() + "]");
		for (final TestMethodResult testMethodResult : singleTestResultList) {
			final String testState = testMethodResult.getTestState().name();
			final String testMethodName = testMethodResult.getTestMethodName();
			final String testDescription = testMethodResult
					.getTestDescription();
			final String resultDescription = testMethodResult
					.getResultDescription();
			final String formattdDate = testMethodResult.getFormattdDate();
			final String runningTime = testMethodResult.getRunningTime();
			// Description descriptionResult =
			// testMethodResult.getDescriptionResult();

            // New columns
            final String testId=testMethodResult.getTestId();
            final String testName =testMethodResult.getTestName();
            final String testData =testMethodResult.getTestData();
            final String testSteps=testMethodResult.getTestSteps();
            final String testExpectedResult=testMethodResult.getTestExpectedResult();
            final String automated=testMethodResult.isAutomated();

			dataSource.add(testState, testMethodName,
					resultDescription,formattdDate, runningTime,testId,testName,testDescription,testData,testSteps,testExpectedResult,automated);
		}
		return dataSource;
	}
}
