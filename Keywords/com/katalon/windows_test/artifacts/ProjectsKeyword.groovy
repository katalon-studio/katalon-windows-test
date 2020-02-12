package com.katalon.windows_test.artifacts

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.katalon.windows_test.keywords.WindowsEnhancedKeyword
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.kms.katalon.core.configuration.RunConfiguration
import java.util.concurrent.TimeUnit

import org.openqa.selenium.Keys
import org.openqa.selenium.NotFoundException
import org.openqa.selenium.WebElement
import io.appium.java_client.AppiumDriver

import internal.GlobalVariable

public class ProjectsKeyword {

	private static final int FIND_ELEMENT_TIMEOUT_IN_MILLIS = 50;

	def static void waitForProjectLoad() {
		waitForProjectLoad(FailureHandling.STOP_ON_FAILURE);
	}

	def static void waitForProjectLoad(FailureHandling flowControl) {
		long timeout = GlobalVariable.G_longTimeout * 1000;
		Date startTime = new Date();
		long elapsed = 0;
		AppiumDriver<?> driver = Windows.getDriver();
		driver.manage().timeouts().implicitlyWait(FIND_ELEMENT_TIMEOUT_IN_MILLIS, TimeUnit.MILLISECONDS);

		boolean isPassed = false;
		while (elapsed < timeout) {
			try {
				try {
					WebElement loadingDialog = Windows.getDriver().findElementByXPath('/Window//Window');
					if (loadingDialog == null) {
						isPassed = true;
						break;
					}
				} catch (NotFoundException error) {
					isPassed = true;
					break;
				}
				WebElement closeButton = Windows.findElement(findWindowsObject('Object Repository/Dialogs/Plugins/Button_Close'), FailureHandling.OPTIONAL);
				if (closeButton != null) {
					closeButton.click();
					isPassed = true;
					break;
				}
			} catch (Exception error) {
				//
			}
			println ''
			println 'waitForProjectLoad...'
			println ''
			elapsed = new Date().getTime() - startTime.getTime();
		}

		int defaultTimeout = RunConfiguration.getTimeOut();
		driver.manage().timeouts().implicitlyWait(defaultTimeout, TimeUnit.SECONDS);

		if (isPassed) {
			KeywordUtil.markPassed("Katalon Project is fully loaded");
		} else {
			KeywordUtil.markFailedAndStop("Failed to wait for project to load");
		}
	}

	def static boolean hasOpenedAnyProjects() {
		try {
			WebElement lblStart = Windows.findElement(findWindowsObject("Object Repository/Tests Explorer/Text_START"));
			return lblStart == null;
		} catch (Exception error) {
			return false;
		}
	}

	def static boolean openProjectOrDefault(String projectPath) {
		if (this.hasOpenedAnyProjects()) {
			return false;
		}
		this.openProject(projectPath);
		return true;
	}

	def static void openProject(String projectPath) {
		try {
			Windows.sendKeys(findWindowsObject("Object Repository/MenuBar/File/MenuItem_File"), Keys.chord(Keys.CONTROL + "O"));
			Windows.clearText(findWindowsObject("Object Repository/Windows/Browser For Folder/Edit_Folder_Path"));
			Windows.setText(findWindowsObject("Object Repository/Windows/Browser For Folder/Edit_Folder_Path"), projectPath);
			WindowsEnhancedKeyword.sendKeys(Keys.ENTER);
		} catch (NotFoundException error) {
			// Just skip
		}
	}

	def static void closeAndCleanProject() {
		Windows.click(findWindowsObject("Object Repository/MenuBar/Project/MenuItem_Project"));
		WindowsEnhancedKeyword.sendKeys('cc');
		WindowsEnhancedKeyword.sendKeys(Keys.ENTER);
	}
}
