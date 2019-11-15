package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import utilities.ExtentManager;

public class testBase {

	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static FileInputStream fis1;
	public static WebDriverWait wait;
	public ExtentReports report = ExtentManager.getInstance();
	public static ExtentTest test;
	public static String browser;
	public static Logger log = Logger.getLogger(testBase.class.getName());

	@BeforeTest
	public void setUp() throws IOException {

		// for log4j configuration
		BasicConfigurator.configure();

		// open the config.propoerties file
		try {
			fis = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\config.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// loading config.propoerties file
		try {
			config.load(fis);
			log.info("config file loaded");

		} catch (IOException e) {
			e.printStackTrace();
			log.debug("config file unable to load");
		}

		// open Object repository properties file
		try {
			fis1 = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\OR.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// load the OR.properties file
		try {
			OR.load(fis1);
			log.info("Object repository file loaded");
		} catch (IOException e) {
			e.printStackTrace();
			log.debug("unable to load OR file");
		}

		// for jenkins configuration (setting the parameter for browser)
		if (System.getenv("browser") != null && !System.getenv("browser").isEmpty()) {

			browser = System.getenv("browser");
		} else {
			browser = config.getProperty("browser");
		}

		config.setProperty("browser", browser);

		// setting the webdrivers according to the config properties file
		if (config.getProperty("browser").equals("chrome")) {

			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\chromedriver.exe");

			// setting up the headless options according to the config properties file
			ChromeOptions option = new ChromeOptions();
			Boolean headless = Boolean.valueOf((config.getProperty("headless_option")));
			option.setHeadless(headless);

			driver = new ChromeDriver(option);

		}

		else if (config.getProperty("browser").equals("firefox")) {
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\geckodriver.exe");

			FirefoxOptions option = new FirefoxOptions();
			Boolean headless = Boolean.valueOf((config.getProperty("headless_option")));
			option.setHeadless(headless);

			driver = new FirefoxDriver(option);

		}

		driver.get(config.getProperty("testsiteurl"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit_wait")),
				TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 10);

	}

	// Custom method for determining a webelement presence
	public boolean isPresentElement(By by) {

		try {

			driver.findElement(by);
			return true;

		} catch (NoSuchElementException e) {

			return false;
		}

	}

	// Custom method for clicking on an web element
	public static void click(String locator) {

		if (locator.endsWith("_XPATH")) {

			driver.findElement(By.xpath(OR.getProperty(locator))).click();

		} else if (locator.endsWith("_CSS")) {

			driver.findElement(By.cssSelector(OR.getProperty(locator))).click();

		} else if (locator.endsWith("_ID")) {

			driver.findElement(By.id(OR.getProperty(locator))).click();

		} else if (locator.endsWith("_NAME")) {

			driver.findElement(By.name(OR.getProperty(locator))).click();
		}

		test.log(LogStatus.INFO, "clicking on the button: " + locator);
		log.info("clicking on the button: " + locator);
	}

	// Custom method for inputting string into the text box
	public static void type(String locator, String value) {

		if (locator.endsWith("_XPATH")) {

			driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_CSS")) {

			driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_ID")) {

			driver.findElement(By.id(OR.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_NAME")) {

			driver.findElement(By.name(OR.getProperty(locator))).sendKeys(value);
		}

		test.log(LogStatus.INFO, "Input into the TextBox: " + locator + " and entering the value of: " + value);
		log.info("Input into the TextBox: " + locator + " and entering the value of: " + value);
	}

	static WebElement dropdown;

	// Custom method for selecting options from the drop down menu
	public static void select(String locator, String value) {

		if (locator.endsWith("_XPATH")) {
			dropdown = driver.findElement(By.xpath(OR.getProperty(locator)));
		} else if (locator.endsWith("_CSS")) {
			dropdown = driver.findElement(By.cssSelector(OR.getProperty(locator)));
		} else if (locator.endsWith("_ID")) {
			dropdown = driver.findElement(By.id(OR.getProperty(locator)));
		} else if (locator.endsWith("_NAME")) {
			dropdown = driver.findElement(By.name(OR.getProperty(locator)));
		}

		Select select = new Select(dropdown);
		select.selectByVisibleText(value);

		test.log(LogStatus.INFO, "Selecting the option of: " + value + " from the dropdown element: " + locator);
		log.info("Selecting the option of: " + value + " from the dropdown element: " + locator);
	}

	// Custom method for selecting options from the drop down menu with value
	public static void select_radio(String locator) {

		if (locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).click();
		} else if (locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
		} else if (locator.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(locator))).click();
		} else if (locator.endsWith("_NAME")) {
			driver.findElement(By.name(OR.getProperty(locator))).click();
		}

		test.log(LogStatus.INFO, "Selecting the radio button : " + locator);
		log.info("Selecting the radio button : " + locator);
	}

	public static String getTitle() {

		String s = driver.getTitle();
		return s;
	}

	public static String getTextFromMessage(String locator) {

		String s = null;
		if (locator.endsWith("_XPATH")) {

			s = driver.findElement(By.xpath(OR.getProperty(locator))).getText();
		} else if (locator.endsWith("_CSS")) {

			s = driver.findElement(By.cssSelector(OR.getProperty(locator))).getText();
		} else if (locator.endsWith("_ID")) {

			s = driver.findElement(By.id(OR.getProperty(locator))).getText();
		} else if (locator.endsWith("_NAME")) {

			s = driver.findElement(By.name(OR.getProperty(locator))).getText();
		}

		return s;

	}

	@AfterTest
	public void tearDown() {

		if (driver != null) {

			driver.quit();

		}

	}
}