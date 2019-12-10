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
import org.apache.commons.lang3.StringUtils
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.NoSuchElementException

public class TestsExplorerKeyword {
	public static final String TESTS_EXPLORER_NAME = "Tests Explorer";
	
	static WebElement findTreeItem(String treeItemName) {
		return findTreeItem(treeItemName, '');
	}

	static WebElement findTreeItem(String treeItemName, String parentFolder) {
		try {
			String treeItemXPath = String.format('//Pane[@Name="%s"]//TreeItem[@Name="%s"]',
					TESTS_EXPLORER_NAME, treeItemName);
			if (!StringUtils.isEmpty(parentFolder)) {
				treeItemXPath = String.format('//Pane[@Name="%s"]//TreeItem[@Name="%s"]/TreeItem[@Name="%s"]',
					TESTS_EXPLORER_NAME, parentFolder, treeItemName);
			}
			return Windows.getDriver().findElementByXPath(treeItemXPath);
		} catch (NoSuchElementException error) {
			return null;
		}
	}

	static WebElement focusToTreeItem(String treeItemName) {
		WebElement treeItem = findTreeItem(treeItemName);
		if (treeItem != null) {
			treeItem.click();
		}
		return treeItem;
	}
	
	static WebElement openTreeItem(String treeItemName) {
		WebElement treeItem = findTreeItem(treeItemName);
		treeItem.sendKeys(Keys.ENTER);
		return treeItem;
	}

	static void deleteTreeItem(String treeItemName) {
		WebElement treeItem = findTreeItem(treeItemName);
		if (treeItem != null) {
			deleteTreeItem(treeItem)
		}
	}

	static void deleteTreeItem(WebElement treeItem) {
		if (treeItem == null) {
			return;
		}
		treeItem.click();
		WindowsEnhancedKeyword.pressKey(Keys.DELETE);
		WindowsEnhancedKeyword.pressKey(Keys.ENTER);
	}

	static void createFolderAtFocusedFolder(String folderName) {
		MenubarKeyword.openNewMenu();
		WindowsEnhancedKeyword.safeClick(findWindowsObject('Object Repository/MenuBar/File/MenuItem_File_New_Folder'));
		Windows.setText(findWindowsObject('Object Repository/Dialogs/New Folder/Edit_Name'), folderName);
		Windows.click(findWindowsObject('Object Repository/Dialogs/New Folder/Button_OK'));
	}

	static void createFolderUsingContextMenu(String folderName, String parentFolderName) {
		openNewContextMenu(parentFolderName);
		WindowsEnhancedKeyword.safeClick(findWindowsObject('Object Repository/Tests Explorer/Menu/MenuItem_Folder'));
		Windows.setText(findWindowsObject('Object Repository/Dialogs/New Folder/Edit_Name'), folderName);
		Windows.click(findWindowsObject('Object Repository/Dialogs/New Folder/Button_OK'));
	}

	/**
	 * * This method will switch to Desktop context. Use `Windows.switchToApplication()` to switch back.
	 * @param treeItemName
	 */
	static void openContextMenuAtTreeItem(String treeItemName) {
		if (StringUtils.isBlank(treeItemName)) {
			return;
		}
		WebElement treeItem = findTreeItem(treeItemName);
		Actions action = new Actions(Windows.getDriver());
		action.contextClick(treeItem).build().perform();
		Windows.switchToDesktop();
	}

	static void openNewContextMenu(String treeItemName) {
		if (StringUtils.isBlank(treeItemName)) {
			return;
		}
		openContextMenuAtTreeItem(treeItemName);
		Windows.click(findWindowsObject("Object Repository/Tests Explorer/Menu/MenuItem_New"));
		Windows.switchToApplication();
	}
}
