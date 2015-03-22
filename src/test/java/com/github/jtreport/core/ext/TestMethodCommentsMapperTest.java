package com.github.jtreport.core.ext;

import com.github.jtreport.ext.PDFTestCaseReportTest;
import org.junit.Test;
import org.junit.runner.Description;

public class TestMethodCommentsMapperTest {
    @Test
    public void testGetTestSteps() throws Exception {
        TestMethodCommentsMapper testMethodCommentsMapper = new TestMethodCommentsMapper();
        Description description = Description.createTestDescription(PDFTestCaseReportTest.class, "test1");
        // sample
        System.out.println(testMethodCommentsMapper.getTestSteps(description));

        // System.out.println("test");
        ;
        // comm1
        ;
        // ysysu
        ;
        // comm1
        ;
        // ysysu
    }

    @Test
    public void  testMeth(){

        // dd

        // te

        //int a=1;

        //test1

        //a=1;
        //System.out.println("test1");
    }
}