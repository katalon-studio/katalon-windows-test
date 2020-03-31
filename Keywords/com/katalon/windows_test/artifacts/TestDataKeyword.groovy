package com.katalon.windows_test.artifacts

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

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
import com.sun.java.util.jar.pack.Instruction.Switch

import org.apache.commons.lang3.StringUtils
import org.openqa.selenium.Keys

import internal.GlobalVariable

public class TestDataKeyword {
	static final String ROOT_DATA_FILE_FOLDER_NAME = "Data Files";

	static void createDataFile(String name){
		if(StringUtils.isBlank(name)){
			return;
		}
		createDataFile(name, '');
	}

	static void createDataFile(String name, String description){
		createDataFile(name, description, '')
	}

	static void createDataFile(String name, String description, String type){
		TestsExplorerKeyword.openContextMenuAtTreeItem(ROOT_DATA_FILE_FOLDER_NAME)
		WindowsEnhancedKeyword.sendKeys('n');
		WindowsEnhancedKeyword.sendKeys('t');
		inputDataFileInfo(name, description, type);
		Windows.click(findWindowsObject('Object Repository/Dialogs/New Test Data/Button_OK'))
	}

	static void  inputDataFileInfo(String name, String description, String type){
		if(!StringUtils.isBlank(name)){
			Windows.setText(findWindowsObject('Object Repository/Dialogs/New Test Data/Edit_Name'), name);
		}

		if(!StringUtils.isBlank(description)){
			Windows.setText(findWindowsObject('Object Repository/Dialogs/New Test Data/Edit_Description'), description);
		}

		if(!StringUtils.isBlank(type)){
			if(type.equals("Excel File")){
				return;
			}

			if(type.equals("CSV File")){
				Windows.sendKeys(findWindowsObject('Object Repository/Dialogs/New Test Data/ComboBox_DataType'), Keys.ARROW_DOWN);
			}

			if(type.equals("Database File")){
				Windows.sendKeys(findWindowsObject('Object Repository/Dialogs/New Test Data/ComboBox_DataType'), Keys.ARROW_DOWN);
				Windows.sendKeys(findWindowsObject('Object Repository/Dialogs/New Test Data/ComboBox_DataType'), Keys.ARROW_DOWN);
			}

			if(type.equals("Internal File")){
				Windows.sendKeys(findWindowsObject('Object Repository/Dialogs/New Test Data/ComboBox_DataType'), Keys.ARROW_DOWN);
				Windows.sendKeys(findWindowsObject('Object Repository/Dialogs/New Test Data/ComboBox_DataType'), Keys.ARROW_DOWN);
				Windows.sendKeys(findWindowsObject('Object Repository/Dialogs/New Test Data/ComboBox_DataType'), Keys.ARROW_DOWN);
			}
		}

	}

	static void openContextMenu() {
		TestsExplorerKeyword.openContextMenuAtTreeItem(ROOT_DATA_FILE_FOLDER_NAME);
	}

	static void openNewContextMenu() {
		TestsExplorerKeyword.openNewContextMenuAtTreeItem(ROOT_DATA_FILE_FOLDER_NAME);
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
			parentFolderName = ROOT_DATA_FILE_FOLDER_NAME;
		}
		TestsExplorerKeyword.createFolderUsingAcceleratorKeys(name, parentFolderName);
	}
}
