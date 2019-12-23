package com.katalon.windows_test.artifacts

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

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
}
