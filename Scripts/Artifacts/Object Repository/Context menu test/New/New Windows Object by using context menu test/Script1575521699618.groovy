import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

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
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable

'Open default project'
Windows.callTestCase(findTestCase('Common/Project/Verify can open project'), [:], FailureHandling.STOP_ON_FAILURE)

'Right click on the Object Repository tree item'
Windows.rightClick(findWindowsObject("Object Repository/Tests Explorer/TreeItem_Root_Object_Repository"))

'Switch to desktop to locate the context menu'
Windows.switchToDesktop()

'Click on New menu item'
Windows.click(findWindowsObject("Object Repository/Tests Explorer/Menu/MenuItem_New"))

'Switch back to application to locate the secondary context menu item'
Windows.switchToApplication()

'Click on Windows Object menu item'
Windows.click(findWindowsObject("Object Repository/Tests Explorer/Menu/MenuItem_Windows_Object"))

'Wait for New Windows Object dialog for 2 seconds'
Windows.delay(2)

'Set name for the object'
Windows.setText(findWindowsObject("Object Repository/Windows/Dialog/Edit_Name"), "New Windows Object")

'Set description for the object'
Windows.setText(findWindowsObject("Object Repository/Windows/Dialog/Document_Description"), "This is a description")

'Click on OK button'
Windows.click(findWindowsObject("Object Repository/Windows/Button_OK"))

'Wait for the object created'
Windows.delay(10)

'Verify the object present tree item on Tests Explorer'
WindowsEnhancedKeyword.verifyElementPresent(findWindowsObject("Object Repository/Tests Explorer/TreeItem_New_Windows_Object"), FailureHandling.STOP_ON_FAILURE)

'Verify the object tab present on main editor'
WindowsEnhancedKeyword.verifyElementPresent(findWindowsObject("Object Repository/MainPartStack/TabItem_New_Windows_Object"), FailureHandling.STOP_ON_FAILURE)




