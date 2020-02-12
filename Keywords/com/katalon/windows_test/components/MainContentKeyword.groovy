package com.katalon.windows_test.components

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
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable
import org.openqa.selenium.WebElement
import org.openqa.selenium.Keys
import org.openqa.selenium.NoSuchElementException

public class MainContentKeyword {

	static WebElement findTabItem(String tabItemName) {
		String tabItemXPath = String.format('//Tab[@Name="%s"]/TabItem[@Name="%s"]',
				tabItemName, tabItemName);
		return WindowsEnhancedKeyword.findElementByXPath(tabItemXPath);
	}

	static void closeTabItem(String tabItemName) {
		closeTabItem(tabItemName, FailureHandling.STOP_ON_FAILURE)
	}

	static void closeTabItem(String tabItemName, FailureHandling flowControl) {
		WebElement tabItem = findTabItem(tabItemName)
		WindowsEnhancedKeyword.verifyElementPresent(tabItem, flowControl)
		WindowsEnhancedKeyword.sendKeys(Keys.chord(Keys.CONTROL, 'w'))
	}

	static WebElement findViewTab(String viewTabName) {
		try {
			String viewTabXPath = String.format('//Pane/Tab/Pane/Tab/TabItem[@Name="%s"]', viewTabName);
			return Windows.getDriver().findElementByXPath(viewTabXPath);
		} catch (NoSuchElementException error) {
			return null;
		}
	}

	static void switchViewTab(String viewTabName) {
		WebElement viewTab = findViewTab(viewTabName);
		viewTab.click();
	}
}
