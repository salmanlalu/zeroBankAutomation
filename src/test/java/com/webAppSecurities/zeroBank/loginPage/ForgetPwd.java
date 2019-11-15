package com.webAppSecurities.zeroBank.loginPage;

import java.util.Hashtable;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.testBase;
import utilities.DataProviders;
import utilities.DataUtil;

public class ForgetPwd extends testBase {

	@Test(dataProvider = "logInPageDP", dataProviderClass = DataProviders.class)
	public static void forgetpwd(Hashtable<String, String> data) throws AssertionError {

		// DataUtil.isTestRunnableSuite("ForgetPwd", "LoginPageSuite");
		DataUtil.checkExecutionSuite("LoginPageSuite", "ForgetPwd", data.get("RunMode"));

		try {

			click("signInBtn_ID");
			click("forgetPwdBtn_XPATH");
			type("emailInputTextBox_ID", data.get("Email"));
			click("sendPwdBtn_NAME");

			assertThat(getTextFromMessage("successMessageLoginPage_XPATH"), containsString(data.get("Message")));

		} catch (AssertionError e) {

			log.info(e);
			Assert.fail(String.valueOf(e), e);
		}
	}
}
