package com.github.jtreport.ext;

import com.github.jtreport.annotations.TestCase;
import com.github.jtreport.annotations.Automated;
import com.github.jtreport.annotations.Manual;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by ka40215 on 8/12/14.
 */

//@TestTitle("Auth / Confirm / Charge Tests")
//@TestSummary("This test contains auth /confirm / charge flows with REST api")
//@TestClassReport(description = "Test default type of printer (PDF, XML, ODT, ODS ...).")
public class ExtTest{

//    @TestId("AUTH_P_001")
//    @TestName("Authorization success flow")
//    @TestDescription("This method will execute the auth REST request with all mandatory parameters")
//    @TestData("user = user1, password = password1, amount=10")
//    @TestExpectedResult("response = 200")
    @Automated
    @Manual
    @Test
    public void testAuthRequest() throws Exception {

//        cu = JavaParser.parse(new FileInputStream("/data/opensource/jt-report/src/test/java/com/github/jtreport/ext/ExtTest.java"));

//        System.out.println(cu.getComments());

        int a =3;
        Assert.assertTrue(true);

        //todo
        Assert.assertTrue(true);
//te
        //ter

        Assert.assertTrue(true);

        Assert.assertTrue(true);

//        MethodChanger m=new MethodChanger();
//        m.test();
    }

    @TestCase("first test decription")
    public void testAnnotations(){
        //rr
        Assert.assertTrue(true);
    }
}


