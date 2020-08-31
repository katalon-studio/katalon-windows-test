package com.katalon.windows_test.components

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import org.openqa.selenium.Keys

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

public class MainWindowKeyword {

	static void close() {
		Windows.sleep(1000L);
		WindowsEnhancedKeyword.releaseKey(Keys.ALT);
		WindowsEnhancedKeyword.releaseKey(Keys.F4);
		WindowsEnhancedKeyword.releaseKey(Keys.ENTER);
		WindowsEnhancedKeyword.sendKeys(Keys.chord(Keys.ALT, Keys.F4));
		WindowsEnhancedKeyword.sendKeys(Keys.ENTER);
		clickGreatIWillBeBack();
	}

	static void closeByClickCloseActivation() {
		Windows.click(findWindowsObject("Object Repository/Dialogs/Activation/Button_Close"))
	}

	static void clickGreatIWillBeBack() {
		try {
			Windows.click(findWindowsObject("Object Repository/Dialogs/Close Application/Button_GreatIWillBeBack"));
		}
		catch (Exception e) {
			// Do nothing
		}
	}
}
