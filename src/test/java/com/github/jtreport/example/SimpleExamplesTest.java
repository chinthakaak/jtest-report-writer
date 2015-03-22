package com.github.jtreport.example;

import com.github.jtreport.annotations.TestCase;
import com.github.jtreport.annotations.TestScenario;
import com.github.jtreport.core.JtreportRunner;
import com.github.jtreport.core.ReportSummary;
import com.github.jtreport.core.ReportTestRunner;
import com.github.jtreport.core.ReportTypePrinterEnum;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

@RunWith(ReportTestRunner.class)
@TestScenario("Put here the description for this test Class.")
public class SimpleExamplesTest extends JtreportRunner {

	@Override
	public ReportSummary initalizeReport() {
		return new ReportSummary("/tmp/jtreportDir",
				new ArrayList<ReportTypePrinterEnum>() {

					private static final long serialVersionUID = 1L;

					{
						this.add(ReportTypePrinterEnum.PDF);
						this.add(ReportTypePrinterEnum.ODT);
						this.add(ReportTypePrinterEnum.ODS);
						this.add(ReportTypePrinterEnum.XML);
						this.add(ReportTypePrinterEnum.PNG);
						this.add(ReportTypePrinterEnum.XHTML);

					}
				});
	}

	@Ignore
	@Test
	@TestCase(keyCustomReport = "Primary key for report")
	public void simpleTest1() {
	}

	@Ignore("Put here why this test is skipped")
	@Test
	@TestCase
	public void simpleTest2() {
	}

	@Ignore("This comment was override in the report")
	@Test
	@TestCase(ignored = "Put here why this test is skipped. This comment go in the test report")
	public void simpleTest3() {
	}

	@Ignore
	@Test
	@TestCase()
	public void simpleTest4() {
		Assert.assertEquals("Are the same", 0, 1);
	}

	@Ignore
	@Test
	@TestCase( failed = "This ovverride the failed massage.")
	public void simpleTest5() {
		Assert.assertEquals("Are the same", 0, 1);
	}

	@Ignore
	@Test
	@TestCase
	public void simpleTest6() {
		final Integer x = null;
		x.intValue();
	}

	@Ignore
	@Test
	@TestCase(error = "This ovverride the error massage.")
	public void simpleTest7() {
		final Integer x = null;
		x.intValue();
	}
}
