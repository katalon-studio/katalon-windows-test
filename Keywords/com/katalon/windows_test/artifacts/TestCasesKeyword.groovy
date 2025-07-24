package com.katalon.windows_test.artifacts
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.katalon.windows_test.components.MenubarKeyword
import com.katalon.windows_test.components.TestsExplorerKeyword
import com.katalon.windows_test.keywords.WindowsEnhancedKeyword
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

	static void createTestCase(String name) {
		createTestCase(name, '');
	}

	static void createTestCase(String name, String description) {
		createTestCase(name, description, '');
	}

	static void createTestCase(String name, String description, String tag) {
		createTestCaseUsingAcceleratorKeys(name, description, tag);
	}

	static void createTestCaseUsingAcceleratorKeys(String testCaseName) {
		createTestCaseUsingAcceleratorKeys(testCaseName, '');
	}

	static void createTestCaseUsingAcceleratorKeys(String name, String description) {
		createTestCaseUsingAcceleratorKeys(name, description, '');
	}

	static void createTestCaseUsingAcceleratorKeys(String name, String description, String tag) {
		TestsExplorerKeyword.focusToTreeItem(ROOT_TEST_CASES_FOLDER_NAME);
		MenubarKeyword.openNewMenu();
		WindowsEnhancedKeyword.sendKeys('t');
		WindowsEnhancedKeyword.sendKeys(Keys.ENTER);
		inputTestCaseInfo(name, description, tag);
	}

	static void inputTestCaseInfo(String name) {
		inputTestCaseInfo(name, '');
	}

	static void inputTestCaseInfo(String name, String description) {
		inputTestCaseInfo(name, description, '');
	}

	static void inputTestCaseInfo(String name, String description, String tag) {
		if (!StringUtils.isBlank(name)) {
			Windows.setText(findWindowsObject('Object Repository/Dialogs/New Test Case/Edit_Name'), name);
		}
		if (!StringUtils.isBlank(description)) {
			Windows.setText(findWindowsObject('Object Repository/Dialogs/New Test Case/Edit_Description'), description);
		}
		if (!StringUtils.isBlank(tag)) {
			Windows.setText(findWindowsObject('Object Repository/Dialogs/New Test Case/Edit_Tag'), tag);
		}
		Windows.click(findWindowsObject('Object Repository/Dialogs/New Test Case/Button_OK'));
	}

	static void createFolder(String name) {
		createFolder(name, '');
	}

	static void createFolder(String name, String parentFolderName) {
		createFolderUsingAcceleratorKeys(name, parentFolderName);
	}

	static void createFolderUsingAcceleratorKeys(String name) {
		createFolderUsingAcceleratorKeys(name, '');
	}

	static void createFolderUsingAcceleratorKeys(String name, String parentFolderName) {
		if (StringUtils.isBlank(parentFolderName)) {
			parentFolderName = ROOT_TEST_CASES_FOLDER_NAME;
		}
		TestsExplorerKeyword.createFolderUsingAcceleratorKeys(name, parentFolderName);
	}

	static void openContextMenu() {
		TestsExplorerKeyword.openContextMenuAtTreeItem(ROOT_TEST_CASES_FOLDER_NAME);
	}

	static void openNewContextMenu() {
		TestsExplorerKeyword.openNewContextMenuAtTreeItem(ROOT_TEST_CASES_FOLDER_NAME);
	}
}
