package com.katalon.windows_test.keywords

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import org.openqa.selenium.Keys
import org.openqa.selenium.NotFoundException
import org.openqa.selenium.NoSuchElementException

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

public class AuthKeyword {

	static boolean login(String username, String password) {
		try {
			Windows.setText(findWindowsObject("Object Repository/Dialogs/Activation/Edit_Username"), username);
			Windows.setText(findWindowsObject("Object Repository/Dialogs/Activation/Edit_Password"), password);
			WindowsEnhancedKeyword.sendKeys(Keys.ENTER);
			return true;
		} catch (Exception error) {
			return false;
		}
	}

	static void deactive() {
		Windows.click(findWindowsObject("Object Repository/Toolbar/Account/SplitButton_Account"));
		WindowsEnhancedKeyword.sendKeys('d');
	}
}
