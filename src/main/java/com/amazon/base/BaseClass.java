package com.amazon.base;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import com.amazon.utilities.ExcelReader;
import com.amazon.utilities.ExtentManager;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;

/**
 * @author Pradeep Kumar C
 *
 */
public class BaseClass {

	public static AndroidDriver<AndroidElement> driver;
	public static AppiumDriverLocalService service;
	public static Logger log = Logger.getLogger("devpinoyLogger");
	public static ExcelReader excel = new ExcelReader(
			System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\Testdata.xlsx");
	public static WebDriverWait wait;
	public ExtentReports report = ExtentManager.getInstance();;
	public static ExtentTest test;

	/**
	 * This method is to initialize the capabilities
	 * @return Driver object after initializing it with desired capabilities
	 */
	public static AndroidDriver<AndroidElement> initializeCapabilities() {

		/**
		 * Path where the apk file is located
		 */
		File applicationPath = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\application\\");
		File appName = new File(applicationPath, Constants.applicationName);
		log.debug("Application found at location" + applicationPath);

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, Constants.deviceName);
		log.debug("The device name is " + Constants.deviceName);

		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, Constants.automationName);
		capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, Constants.newCommandTimeout);
		capabilities.setCapability(MobileCapabilityType.APP, appName.getAbsolutePath());
		capabilities.setCapability(MobileCapabilityType.ORIENTATION, Constants.orientation);
		log.debug("The orientation is : " + Constants.orientation);

		capabilities.setCapability(AndroidMobileCapabilityType.APP_WAIT_ACTIVITY, Constants.appWaitActivity);

		try {
			driver = new AndroidDriver<AndroidElement>(new URL(Constants.appiumUrl), capabilities);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(Constants.implicitWait, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, Constants.explicitWait);
		return driver;

	}

	/**
	 * Method to start the appium server programmatically
	 * It will check if the server is already running on the default port and starts it if it is not already running.
	 * @return - returns the AppiumDriverLocalService object.
	 */
	public static AppiumDriverLocalService startAppiumServer() {

		boolean flag = checkIfAppiumServerIsRunning(4723);

		if (!flag) {
			service = AppiumDriverLocalService.buildDefaultService();
			service.start();
		}
		return service;
	}

	/**
	 * Method to stop the appium server
	 */
	public static void stopAppiumServer() {

		service.stop();
	}

	/**
	 * Method to check if the appium server is running on the default port.
	 * @param port - default port on which the appium server is running 
	 * @return true if the server is running on the default port otherwise return false.
	 */
	public static boolean checkIfAppiumServerIsRunning(int port) {
		boolean isAppiumServerRunning = false;
		ServerSocket serverSocket;

		try {
			serverSocket = new ServerSocket(port);
			serverSocket.close();

		} catch (IOException e) {
			// If control comes here, then it means the port is already in use
			isAppiumServerRunning = true;
		} finally {
			serverSocket = null;
		}
		return isAppiumServerRunning;
	}

	/**
	 * Method that will initialize the test execution
	 * It will start the appium server and initialize it with the desired capabilities. 
	 */
	@BeforeSuite
	public void initialize() {
		service = startAppiumServer();
		driver = initializeCapabilities();

	}

	/**
	 * Method that will stop the appium server after all the scripts have been executed.
	 */
	@AfterSuite
	public void stopTheServer() {

		stopAppiumServer();

	}

}