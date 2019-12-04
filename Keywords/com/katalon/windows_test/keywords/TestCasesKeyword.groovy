package com.katalon.windows_test.keywords
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
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable

import org.openqa.selenium.Keys as Keys
import org.apache.commons.lang3.StringUtils
import org.openqa.selenium.WebElement

public class TestCasesKeyword {
	static final String ROOT_TEST_CASES_FOLDER_NAME = "Test Cases";

	static WebElement createTestCase() {
		return createTestCase(NamingKeyword.generateTestCaseName());
	}

	static void createTestCase(String testCaseName) {
		MenubarKeyword.openNewMenu();
		Windows.click(findWindowsObject('Object Repository/MenuBar/File_New_TestCase'));
		Windows.setText(findWindowsObject('Object Repository/Dialogs/New Test Case/Name'), testCaseName);
		Windows.click(findWindowsObject('Object Repository/Dialogs/New Test Case/OK'));
	}

	static void createFolder(String folderName, String parentFolderName) {
		if (!StringUtils.isBlank(parentFolderName)) {
			TestsExplorerKeyword.focusToTreeItem(parentFolderName);
		} else {
			TestsExplorerKeyword.focusToTreeItem(ROOT_TEST_CASES_FOLDER_NAME);
		}
	}

	static moveTestCase(String testCaseName, String targetFolderName, boolean createFolderIfNotExist) {
		WebElement targetFolder = null;
		if (!StringUtils.isBlank(targetFolderName)) {
			String folderXPath = String.format('//Pane[@Name="Tests Explorer"]//TreeItem[@Name="%s"]', targetFolderName);
			targetFolder = Windows.getDriver().findElementByXPath(folderXPath);

			if (targetFolder == null && createFolderIfNotExist) {
			}
		}

		if (targetFolder != null) {
			targetFolder.click();
			Windows.sendKeys(targetFolder, Keys.chord(Keys.CONTROL, 'v'))
		}
	}
}
