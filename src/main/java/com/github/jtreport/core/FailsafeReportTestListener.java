package com.github.jtreport.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by ka40215 on 8/12/14.
 */
public class FailsafeReportTestListener extends AbstractReportTestListner {

    private static final Logger L = LoggerFactory
            .getLogger(DefaultReportTestListner.class);
    @Override
    ReportSummary getReportSummary(Class<?> testClass) {
        final String jtreportConfig = System.getProperty("jtreportConfig");
        L.debug("jtreportConfig [" + jtreportConfig + "]");
        return new ReportSummary(jtreportConfig);
    }

    @Override
    String setReportName(String currentName) {
        return currentName;
    }
}
