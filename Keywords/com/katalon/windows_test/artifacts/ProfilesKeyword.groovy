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
import org.apache.commons.lang3.StringUtils

import internal.GlobalVariable

public class ProfilesKeyword {
	static final String ROOT_PROFILE_FOLDER_NAME = "Profiles";

	static final String DEFAULT_PROFILE_NAME = "default";

	static void createProfile(String name) {
		if (StringUtils.isBlank(name)) {
			return;
		}
		TestsExplorerKeyword.openContextMenuAtTreeItem(ROOT_PROFILE_FOLDER_NAME)
		WindowsEnhancedKeyword.sendKeys('n')
		WindowsEnhancedKeyword.sendKeys('e')
		Windows.clearText(findWindowsObject('Object Repository/Dialogs/New Exectution Profiles/Edit_Name'))
		Windows.setText(findWindowsObject('Object Repository/Dialogs/New Exectution Profiles/Edit_Name'), name);
		Windows.click(findWindowsObject('Object Repository/Dialogs/New Exectution Profiles/Button_OK'));
	}
}
