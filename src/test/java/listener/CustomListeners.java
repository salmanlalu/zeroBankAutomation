package listener;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.relevantcodes.extentreports.LogStatus;

import base.testBase;
import utilities.testUtil;

public class CustomListeners extends testBase implements ITestListener {

	@Override
	public void onFinish(ITestContext arg0) {

	}

	@Override
	public void onStart(ITestContext arg0) {

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {

	}

	@Override
	public void onTestFailure(ITestResult arg0) {
		
		//capture screenshots
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		try {
			testUtil.captureScreenshot();
		} catch (IOException e) {

			e.printStackTrace();
		}

		test.log(LogStatus.FAIL, arg0.getName().toUpperCase() + " Failed with exception: " +arg0.getThrowable());
		test.log(LogStatus.FAIL, test.addScreenCapture(testUtil.screenShotName));
		
		//for reportng reporting
		Reporter.log("<a target=\"_blank\" href=" + testUtil.screenShotName + ">Screenshot</a>");
		Reporter.log("<br>");
		Reporter.log("<br>");
		Reporter.log("<a target=\"_blank\" href=" + testUtil.screenShotName + "><img src=" + testUtil.screenShotName
				+ " height=200 width=200></img></a>");

		//Must do things for extent report
		report.endTest(test);
		report.flush();

	}

	@Override
	public void onTestSkipped(ITestResult arg0) {

		test.log(LogStatus.SKIP, arg0.getName().toUpperCase()+" Skipped the test as the Run mode is NO");
		
		report.endTest(test);
		report.flush();

	}

	@Override
	public void onTestStart(ITestResult arg0) {

		test = report.startTest(arg0.getName().toUpperCase());

	}

	@Override
	public void onTestSuccess(ITestResult arg0) {

		test.log(LogStatus.PASS, arg0.getName().toUpperCase() + "PASS");
		report.endTest(test);
		report.flush();

	}

}
