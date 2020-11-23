package com.katalon.windows_test.artifacts

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import org.openqa.selenium.Keys

import com.katalon.windows_test.components.MenubarKeyword
import com.katalon.windows_test.components.TestsExplorerKeyword
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
import org.apache.commons.lang3.StringUtils

public class TestSuitesKeyword {

	static final String ROOT_TEST_SUITES_FOLDER_NAME = 'Test Suites';

	static void createTestSuite(String name){
		createTestSuite(name , '');
	}

	static void createTestSuite(String name, String description){
		TestsExplorerKeyword.focusToTreeItem(ROOT_TEST_SUITES_FOLDER_NAME);
		MenubarKeyword.openNewMenu();
		WindowsEnhancedKeyword.sendKeys('t');
		WindowsEnhancedKeyword.sendKeys('t');
		WindowsEnhancedKeyword.sendKeys(Keys.ENTER);
		inputTestSuiteInfo(name, description);
	}

	static void inputTestSuiteInfo(String name, String description){
		if(!StringUtils.isBlank(name)){
			Windows.setText(findWindowsObject('Object Repository/Dialogs/New Test Suite/Edit_Name'), name)
		}

		if(!StringUtils.isBlank(description)){
			Windows.setText(findWindowsObject('Object Repository/Dialogs/New Test Suite/Edit_Description'), description)
		}

		Windows.click(findWindowsObject('Object Repository/Dialogs/New Test Suite/Button_OK'));
	}

	static void openContextMenu() {
		TestsExplorerKeyword.openContextMenuAtTreeItem(ROOT_TEST_SUITES_FOLDER_NAME);
	}

	static void openNewContextMenu() {
		TestsExplorerKeyword.openNewContextMenuAtTreeItem(ROOT_TEST_SUITES_FOLDER_NAME);
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
			parentFolderName = ROOT_TEST_SUITES_FOLDER_NAME;
		}
		TestsExplorerKeyword.createFolderUsingAcceleratorKeys(name, parentFolderName);
	}
}
