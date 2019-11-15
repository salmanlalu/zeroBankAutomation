package com.webAppSecurities.zeroBank.loginPage;

import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.testBase;
import utilities.DataProviders;
import utilities.DataUtil;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class LoginWithValidUserPwd extends testBase {

	@Test(dataProvider = "logInPageDP", dataProviderClass = DataProviders.class)
	public static void loginwithvaliduserpwd(Hashtable<String, String> data) throws AssertionError {

		DataUtil.checkExecutionSuite("LoginPageSuite", "LoginWithValidUserPwd", data.get("RunMode"));

		try {

			click("signInBtn_ID");
			type("logInNametextbox_ID", data.get("Username"));
			type("logInPasswordTextBox_ID", data.get("Password"));
			click("submitBtn_NAME");

			assertThat(getTitle(), containsString(data.get("Title")));

		} catch (AssertionError e) {

			log.info(e);
			Assert.fail(String.valueOf(e), e);
		}

	}
}