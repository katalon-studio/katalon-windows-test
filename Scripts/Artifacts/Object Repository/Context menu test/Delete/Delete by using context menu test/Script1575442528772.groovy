import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import org.openqa.selenium.Keys

import com.katalon.windows_test.keywords.WindowsEnhancedKeyword
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.driver.WindowsDriverFactory
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import io.appium.java_client.windows.WindowsDriver

'Open default project'
Windows.callTestCase(findTestCase('Common/Project/Verify can open project'), [:], FailureHandling.STOP_ON_FAILURE)

'Collapse all items on Tests Explorer'
Windows.click(findWindowsObject("Object Repository/Tests Explorer/Button_Collapse_All"))

'Double click on the Object Repository tree item'
Windows.doubleClick(findWindowsObject("Object Repository/Tests Explorer/TreeItem_Root_Object_Repository"))

'Verify the target object present'
WindowsEnhancedKeyword.verifyElementPresent(findWindowsObject("Object Repository/Tests Explorer/TreeItem_New_Test_Object"), FailureHandling.STOP_ON_FAILURE)

'Right click to show context menu'
Windows.rightClick(findWindowsObject("Object Repository/Tests Explorer/TreeItem_New_Test_Object"))

'Switch to desktop to locate Delete menu item'
Windows.switchToDesktop()

'Click on Delete menu item'
Windows.click(findWindowsObject("Object Repository/Tests Explorer/Menu/MenuItem_Delete"))

'Switch back to application'
Windows.switchToApplication()

'Verify the confirmation dialog present'
WindowsEnhancedKeyword.verifyElementPresent(findWindowsObject("Object Repository/Windows/Delete Dialog/Dialog_Delete"), FailureHandling.STOP_ON_FAILURE)

'Click on YES button'
Windows.click(findWindowsObject("Object Repository/Windows/Button_Yes"))

'Wait for progress dialog completes'
Windows.delay(5)

'Verify the target object that\' disapeared on Tests Explorer'
WindowsEnhancedKeyword.verifyElementNotPresent(findWindowsObject("Object Repository/Tests Explorer/TreeItem_New_Test_Object"), FailureHandling.STOP_ON_FAILURE)
