package utilities;

import java.io.File;
import java.io.IOException;

import java.util.Date;

import org.apache.commons.io.FileUtils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import base.testBase;

public class testUtil extends testBase {

	public static String screenShotPath;
	public static String screenShotName;

	//method for capturing screenshots and save and renaming
	public static void captureScreenshot() throws IOException {

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		Date d = new Date();
		screenShotName = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";

		FileUtils.copyFile(scrFile,
				new File(System.getProperty("user.dir") + "\\target\\surefire-reports\\html\\" + screenShotName));

	}
	
	public static void logIn(String username, String password) {
		
		click("signInBtn_ID");
		type("logInNametextbox_ID", username);
		type("logInPasswordTextBox_ID", password);
		click("submitBtn_NAME");
		
	}

}