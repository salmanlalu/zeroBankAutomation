package rough;

import java.lang.invoke.MethodHandles;

import org.apache.log4j.chainsaw.Main;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import base.testBase;
import utilities.*;

public class runnabletest extends testBase {

	@Test
	public static void test() {
		
		testUtil.logIn("username", "password");
	}
	
		
	}


