package com.github.jtreport.printer.pdf;

import com.github.jtreport.core.StateTestEnum;
import com.github.jtreport.core.TestClassResult;
import com.github.jtreport.datasource.DataSourceUtils;
import com.github.jtreport.printer.core.AbstractPrinterStrategyTemaplate;
import com.github.jtreport.printer.core.Templates;
import net.sf.dynamicreports.jasper.builder.JasperConcatenatedReportBuilder;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;
import net.sf.dynamicreports.report.builder.ReportTemplateBuilder;
import net.sf.dynamicreports.report.builder.column.Columns;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.component.ImageBuilder;
import net.sf.dynamicreports.report.builder.datatype.DataTypes;
import net.sf.dynamicreports.report.constant.ImageScale;
import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.dynamicreports.report.exception.DRException;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.TreeMap;

import static net.sf.dynamicreports.report.builder.DynamicReports.field;

/**
 * Created by ka40215 on 8/14/14.
 */
public class PrinterPdfTestCaseStrategy extends AbstractPrinterStrategyTemaplate {
    private class ImageExpression extends AbstractSimpleExpression<InputStream> {

        private static final long serialVersionUID = 1L;

        @Override
        public InputStream evaluate(final ReportParameters reportParameters) {
            final String value = reportParameters.getValue("state");
            final InputStream resourceAsStream = this.getClass().getClassLoader()
                    .getResourceAsStream("img/" + value + ".png");
            return resourceAsStream;
        }
    }

    // private class StateExpression extends AbstractSimpleExpression<String> {
    //
    // private static final long serialVersionUID = 1L;
    //
    // @Override
    // public String evaluate(final ReportParameters reportParameters) {
    // return " " + reportParameters.getValue("state");
    // }
    // }

    @Override
    protected void addColumn(final JasperReportBuilder report) {
        final ImageBuilder image = Components.image(new ImageExpression()).setImageScale(ImageScale.NO_RESIZE);
        report.addColumn(Columns.componentColumn("State", image).setStyle(Templates.columnStyle));
    }

    @Override
    protected void addTitle(final JasperReportBuilder report, final String title) {
        report.addTitle(Templates.createTitleComponent(title, true));
    }

    @Override
    protected void printConcatenateReport(final JasperConcatenatedReportBuilder report, final OutputStream os)
            throws DRException {
        report.toPdf(os);
    }

    @Override
    public void printReport(final JasperReportBuilder report, final OutputStream os) throws DRException {
        report.toPdf(os);
    }

    @Override
    protected void setBackground(final JasperReportBuilder report) {
    }

    @Override
    public String setExtension() {
        return ".pdf";
    }

    @Override
    protected ReportTemplateBuilder setReportTemplate() {
        return Templates.reportTemplate;
    }

    @Override
    public JasperReportBuilder createReport(final TestClassResult testResult,
                                            final JasperReportBuilder report) {
        final Map<StateTestEnum, String> resultTestMap = new TreeMap<StateTestEnum, String>();
        resultTestMap.put(StateTestEnum.PASSED, testResult.getTotalPassed()
                + "");
        resultTestMap.put(StateTestEnum.ERROR, testResult.getTotalError() + "");
        resultTestMap.put(StateTestEnum.FAILED, testResult.getTotalFailed()
                + "");
        resultTestMap.put(StateTestEnum.IGNORED, testResult.getTotalIgnored()
                + "");
        report.addTitle(Templates.createTitleComponent(
                testResult.getTestScenario(), false));
//        report.title(Templates.createSubTitleComponent(testResult
//                .getTestSummary()));
//        report.title(Templates.createSubTitleTestResult(resultTestMap,
//                testResult.getTotalRun(), ""));

        report.title(Templates.createSubTitleTestCaseResult(testResult,testResult.getTotalRun()));
        report.fields(field("state", String.class));
        report.setTemplate(this.setReportTemplate());
        report.addColumn(Columns.column("Test Id", "testId",
                DataTypes.stringType()));
        report.addColumn(Columns.column("Test Name", "testName",
                DataTypes.stringType()));

        report.addColumn(Columns.column("Test Description", "testDescription",
                DataTypes.stringType()));

        report.addColumn(Columns.column("Test Data", "testData",
                DataTypes.stringType()));

        report.addColumn(Columns.column("Test Steps", "testSteps",
                DataTypes.stringType()));
        report.addColumn(Columns.column("Expected Result ", "testExpectedResult",
                DataTypes.stringType()));
        report.addColumn(Columns.column("Test Method Name", "testMethodName",
                DataTypes.stringType()));
        report.addColumn(Columns.column("Automated? ", "automated",
                DataTypes.stringType()));
        report.setDataSource(DataSourceUtils.createDataSource(testResult
                .getTestMethodResultList()));
        report.highlightDetailEvenRows();
        report.addPageFooter(Components.pageNumber());

        return report;
    }
}
