package com.amazon.utilities;

import static java.time.Duration.ofSeconds;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import com.amazon.base.BaseClass;
import com.relevantcodes.extentreports.LogStatus;
import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.TouchAction;

public class Utilities extends BaseClass {

	public static String screenshotPath;
	public static String screenshotName;

	/**
	 * Method to take a screenshot
	 * @throws IOException - if the file is not found
	 */
	public static void captureScreenshot() throws IOException {

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		Date d = new Date();
		screenshotName = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";

		FileUtils.copyFile(scrFile,
				new File(System.getProperty("user.dir") + "\\target\\surefire-reports\\html\\" + screenshotName));

	}

	/**
	 * Method to read data from excel.
	 * @param m - It takes the method name as paramater.
	 * @return - It retruns an array of Objects. 
	 */
	@DataProvider(name = "dataprovider")
	public Object[][] getData(Method m) {

		String sheetName = m.getName();
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);

		Object[][] data = new Object[rows - 1][1];

		Hashtable<String, String> table = null;

		for (int rowNum = 2; rowNum <= rows; rowNum++) {

			table = new Hashtable<String, String>();

			for (int colNum = 0; colNum < cols; colNum++) {

				table.put(excel.getCellData(sheetName, colNum, 1), excel.getCellData(sheetName, colNum, rowNum));
				data[rowNum - 2][0] = table;
			}

		}

		return data;

	}

	/**
	 * Method to tap on element.
	 * @param elementToTap - It takes the element to tap as a parameter.
	 */
	public static void tapOnElement(WebElement elementToTap) {
		TouchAction touch = new TouchAction(driver);
		touch.tap(tapOptions().withElement(element(elementToTap))).perform();
		test.log(LogStatus.INFO, "Tapped on element : " + elementToTap);
	}

	/**
	 * Method to select a random element from a list of elements
	 * @param elements - It takes a list of elements as parameter.
	 * @return - returns a randomly selected element.
	 */
	public static WebElement getRandomElement(List<WebElement> elements) {
		Random random = new Random();
		return elements.get(random.nextInt(elements.size()));

	}

	/**
	 * Method to generate a random number in the specified range.
	 * @param range - It takes the range as paramater.
	 * @return - returns a random number in the specified range.
	 */
	public static int generateRandomNumber(int range) {
		int randomNumber = (int) (Math.random() * range);
		return randomNumber;
	}

	/**
	 * Method to long press on an element.
	 * @param fromElement - first element
	 * @param toElement - second element.
	 */
	public static void longPressOnElement(WebElement fromElement, WebElement toElement) {
		TouchAction touch = new TouchAction(driver);
		touch.longPress(longPressOptions().withElement(element(fromElement)).withDuration(ofSeconds(2)))
				.moveTo(element(toElement)).release().perform();
	}

	/**
	 * Method to scroll to view the specified element using UiScrollable.
	 * @param uiSelector - It takes the uiSelector as a paramater.
	 * @return - It returns a String identified by the uiSelector.
	 */
	public static String scrollInToViewElement(String uiSelector) {
		return "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(" + uiSelector
				+ ".instance(0));";
	}

	/**
	 * Method to scroll.
	 * It takes the resourceId and textToSearch as parameters.
	 * @param resourceId - resourceId parameter.
	 * @param textToSearch - textToSearch parameter.
	 * @return - It returns the String identified by the resource and the text searched with.
	 */
	public static String scroll(String resourceId, String textToSearch) {

		return "new UiScrollable(new UiSelector().resourceId(\"" + resourceId + "\")).scrollIntoView("
				+ "new UiSelector().textContains(\"" + textToSearch + "\").instance(0))";

	}

}