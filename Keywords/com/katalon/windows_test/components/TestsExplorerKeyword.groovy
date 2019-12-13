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

	static WebElement findTreeItem(String treeItemName, String parentFolderName) {
		try {
			String treeItemXPath = String.format('//Pane[@Name="%s"]//TreeItem[@Name="%s"]',
					TESTS_EXPLORER_NAME, treeItemName);
			if (!StringUtils.isEmpty(parentFolderName)) {
				treeItemXPath = String.format('//Pane[@Name="%s"]//TreeItem[@Name="%s"]/TreeItem[@Name="%s"]',
						TESTS_EXPLORER_NAME, parentFolderName, treeItemName);
			}
			return Windows.getDriver().findElementByXPath(treeItemXPath);
		} catch (NoSuchElementException error) {
			return null;
		}
	}

	static WebElement focusToTreeItem(String treeItemName) {
		return focusToTreeItem(treeItemName, '');
	}

	static WebElement focusToTreeItem(String treeItemName, String parentFolderName) {
		WebElement treeItem = findTreeItem(treeItemName, parentFolderName);
		if (treeItem != null) {
			treeItem.click();
		}
		return treeItem;
	}

	static WebElement openTreeItem(String treeItemName) {
		return openTreeItem(treeItemName, '');
	}

	static WebElement openTreeItem(String treeItemName, String parentFolderName) {
		WebElement treeItem = findTreeItem(treeItemName, parentFolderName);
		treeItem.sendKeys(Keys.ENTER);
		return treeItem;
	}

	static void deleteTreeItem(String treeItemName) {
		deleteTreeItem(treeItemName, '');
	}

	static void deleteTreeItem(String treeItemName, String parentFolderName) {
		WebElement treeItem = findTreeItem(treeItemName, parentFolderName);
		if (treeItem != null) {
			deleteTreeItem(treeItem)
		}
	}

	static void deleteTreeItem(WebElement treeItem) {
		if (treeItem == null) {
			return;
		}
		treeItem.click();
		WindowsEnhancedKeyword.sendKeys(Keys.DELETE);
		WindowsEnhancedKeyword.sendKeys(Keys.ENTER);
	}

	static void createFolderUsingAcceleratorKeys(String folderName) {
		createFolderUsingAcceleratorKeys(folderName, '');
	}

	static void createFolderUsingAcceleratorKeys(String folderName, String parentFolderName) {
		if (!StringUtils.isBlank(parentFolderName)) {
			focusToTreeItem(parentFolderName);
		}
		WindowsEnhancedKeyword.sendKeys(Keys.ALT);
		WindowsEnhancedKeyword.releaseKey(Keys.ALT);
		WindowsEnhancedKeyword.sendKeys('f');
		WindowsEnhancedKeyword.sendKeys(Keys.ENTER);
		WindowsEnhancedKeyword.sendKeys('f');
		inputFolderInfo(folderName);
	}

	static void inputFolderInfo(String folderName) {
		Windows.setText(findWindowsObject('Object Repository/Dialogs/New Folder/Edit_Name'), folderName);
		Windows.click(findWindowsObject('Object Repository/Dialogs/New Folder/Button_OK'));
	}

	static void openContextMenuAtTreeItem(String treeItemName) {
		if (StringUtils.isBlank(treeItemName)) {
			return;
		}
		WebElement treeItem = findTreeItem(treeItemName);
		Actions action = new Actions(Windows.getDriver());
		action.contextClick(treeItem).build().perform();
	}

	static void openNewContextMenu(String treeItemName) {
		if (StringUtils.isBlank(treeItemName)) {
			return;
		}
		openContextMenuAtTreeItem(treeItemName);
		WindowsEnhancedKeyword.sendKeys('n');
	}
}
