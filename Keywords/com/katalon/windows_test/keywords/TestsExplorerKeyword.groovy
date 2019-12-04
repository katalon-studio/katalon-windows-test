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
import org.openqa.selenium.WebElement

public class TestsExplorerKeyword {
	public static final String TESTS_EXPLORER_NAME = "Tests Explorer";

	static WebElement findTreeItem(String treeItemName) {
		String treeItemXPath = String.format('//Pane[@Name="%s"]//TreeItem[@Name="%s"]',
				TESTS_EXPLORER_NAME, treeItemName);
		return Windows.getDriver().findElementByXPath(treeItemXPath);
	}

	static WebElement focusToTreeItem(String treeItemName) {
		WebElement treeItem = findTreeItem(treeItemName);
		treeItem.click();
		return treeItem;
	}

	static void createFolderAtFocusedFolder(String folderName) {
		MenubarKeyword.openNewMenu();
		Windows.click(findWindowsObject('Object Repository/MenuBar/File_New_Folder'));
		Windows.setText(findWindowsObject('Object Repository/Dialogs/New Test Case/Name'), folderName);
		Windows.click(findWindowsObject('Object Repository/Dialogs/New Test Case/OK'))
	}
}
