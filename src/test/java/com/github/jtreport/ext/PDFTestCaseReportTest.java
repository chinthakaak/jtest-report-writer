package com.github.jtreport.ext;

import com.github.jtreport.annotations.*;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by ka40215 on 8/14/14.
 */
@TestPlan("http://confluence.com")
@TestDesign("http://confluence.com")
@TestScenario("Payment REST API Scenario")
@TestSummary("Testing Payment APIs")
public class PDFTestCaseReportTest {

    @Defect("bug0001")
    @TestId("AUTH_P_001")
    @TestCase("Authorization Rest Test")
    @TestDescription("This will give the auth rest call result")
    @TestData("requestId=1000")
    @TestExpectedResult("12$ should be charged")
    @Automated
    @Test
    public void test1(){
        // Create partner
        Assert.assertTrue(true);

        // Create service provider
        Assert.assertTrue(true);

        // Verify charge amount
        Assert.assertTrue(true);
    }
    @TestId("AUTH_P_002")
    @TestCase("Authorization Rest Test2")
    @TestDescription("This will give the auth rest call result2")
    @TestData("requestId=10020")
    @TestExpectedResult("212$ should be charged2")
    @Manual
    @Test
    public void test2(){
        // Login to care application
        ;
        // Create user
        ;
        // Add account
        ;
        //Send a charge call
        ;
        // Verify charge amount
    }
    @TestId("AUTH_P_003")
    @TestCase("Authorization Rest Test3")
    @TestDescription("This will give the auth rest call result3")
    @TestData("requestId=13000")
    @TestExpectedResult("123$ should be charged3")
    @Automated
    @Test
    public void test3(){
        // Set the spendlimit in management console
        Assert.assertTrue(true);
        // Create a charge
        Assert.assertTrue(true);
        // View database table for spent amount
        ;
        // Verify expiry
    }
}
