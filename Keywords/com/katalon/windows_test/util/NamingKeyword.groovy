package com.katalon.windows_test.util
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

public class NamingKeyword {
	public static String generateRandomUUID() {
		return UUID.randomUUID().toString();
	}

	public static String generateTestCaseFolderName() {
		return generateRandomUUID();
	}

	public static String generateTestCaseName() {
		return generateRandomUUID();
	}
	
	public static String generateTestCaseDescription() {
		return "Hello!" + " (" + generateRandomUUID() + ")";
	}

	public static String generateTestCaseTag() {
		return "Hello Tag!" + " (" + generateRandomUUID() + ")";
	}

	public static String generateProfileName() {
		return generateRandomUUID();
	}

	public static String generateTestSuiteName(){
		return generateRandomUUID();
	}

	public static String generateTestSuiteFolderName() {
		return generateRandomUUID();
	}

	public static String generateTestSuiteDescription(){
		return "Hello!" + " (" + generateRandomUUID() + ")";
	}

	public static String generateTestDataName(){
		return generateRandomUUID();
	}
	
	public static String generateTestDataDescription(){
		return "Hello!" + " (" + generateRandomUUID() + ")";
	}
	
	public static String generateTestDataFolderName(){
		return generateRandomUUID(); 
	}
	
}
