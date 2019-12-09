package com.katalon.windows_test.artifacts
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.katalon.windows_test.components.MenubarKeyword
import com.katalon.windows_test.components.TestsExplorerKeyword
import com.katalon.windows_test.util.NamingKeyword
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

	static void createTestCaseUsingContextMenu(String testCaseName) {
		TestsExplorerKeyword.openNewContextMenu(ROOT_TEST_CASES_FOLDER_NAME);
		Windows.click(findWindowsObject('Object Repository/Tests Explorer/Menu/MenuItem_Test_Case'));
		inputTestCaseInfo(testCaseName);
	}

	static void createTestCaseUsingFileMenu(String testCaseName) {
		createTestCaseUsingFileMenu(testCaseName, '');
	}

	static void createTestCaseUsingFileMenu(String testCaseName, String testCaseDescription) {
		createTestCaseUsingFileMenu(testCaseName, testCaseDescription, '');
	}

	static void createTestCaseUsingFileMenu(String testCaseName, String testCaseDescription, String testCaseTag) {
		MenubarKeyword.openNewMenu();
		Windows.click(findWindowsObject('Object Repository/MenuBar/File/MenuItem_File_New_TestCase'));
		inputTestCaseInfo(testCaseName, testCaseDescription, testCaseTag);
	}

	static void inputTestCaseInfo(String testCaseName) {
		inputTestCaseInfo(testCaseName, '');
	}

	static void inputTestCaseInfo(String testCaseName, String description) {
		inputTestCaseInfo(testCaseName, description, '');
	}

	static void inputTestCaseInfo(String testCaseName, String description, String tag) {
		if (!StringUtils.isBlank(testCaseName)) {
			Windows.setText(findWindowsObject('Object Repository/Dialogs/New Test Case/Edit_Name'), testCaseName);
		}
		if (!StringUtils.isBlank(description)) {
			Windows.setText(findWindowsObject('Object Repository/Dialogs/New Test Case/Edit_Description'), description);
		}
		if (!StringUtils.isBlank(tag)) {
			Windows.setText(findWindowsObject('Object Repository/Dialogs/New Test Case/Edit_Tag'), tag);
		}
		Windows.click(findWindowsObject('Object Repository/Dialogs/New Test Case/Button_OK'));
	}

	static void createFolderUsingFileMenu(String folderName) {
		createFolderUsingFileMenu(folderName, '');
	}

	static void createFolderUsingFileMenu(String folderName, String parentFolderName) {
		if (!StringUtils.isBlank(parentFolderName)) {
			TestsExplorerKeyword.focusToTreeItem(parentFolderName);
		} else {
			TestsExplorerKeyword.focusToTreeItem(ROOT_TEST_CASES_FOLDER_NAME);
		}
		TestsExplorerKeyword.createFolderAtFocusedFolder(folderName);
	}

	static void createFolderUsingContextMenu(String folderName) {
		createFolderUsingContextMenu(folderName, '');
	}

	static void createFolderUsingContextMenu(String folderName, String parentFolderName) {
		if (StringUtils.isBlank(parentFolderName)) {
			parentFolderName = ROOT_TEST_CASES_FOLDER_NAME;
		}
		TestsExplorerKeyword.createFolderUsingContextMenu(folderName, parentFolderName);
	}

	static void openNewContextMenu() {
		TestsExplorerKeyword.openNewContextMenu(ROOT_TEST_CASES_FOLDER_NAME);
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
