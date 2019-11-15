package com.webAppSecurities.zeroBank.loginPage;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Hashtable;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.testBase;
import utilities.DataProviders;
import utilities.DataUtil;

public class LoginWithValidPwd extends testBase {

	@Test(dataProvider = "logInPageDP", dataProviderClass = DataProviders.class)
	public static void loginwithvalidpwd(Hashtable<String, String> data) throws AssertionError {

		DataUtil.checkExecutionSuite("LoginPageSuite", "LoginWithValidPwd", data.get("RunMode"));

		try {

			click("signInBtn_ID");
			type("logInNametextbox_ID", data.get("Username"));
			type("logInPasswordTextBox_ID", data.get("Password"));
			click("submitBtn_NAME");

			wait.until(ExpectedConditions
					.presenceOfElementLocated(By.xpath(OR.getProperty("errormessageloginpage_XPATH"))));

			assertThat(getTextFromMessage("errormessageloginpage_XPATH"), containsString(data.get("Error")));

		} catch (AssertionError e) {

			log.info(e);
			Assert.fail(String.valueOf(e), e);
		}
	}

}