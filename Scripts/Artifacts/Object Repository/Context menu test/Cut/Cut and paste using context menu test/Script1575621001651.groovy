import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.katalon.windows_test.keywords.WindowsEnhancedKeyword as WindowsEnhancedKeyword
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

'Collapse all items on Tests Explorer'
Windows.click(findWindowsObject('Object Repository/Tests Explorer/Button_Collapse_All'))

'Double click on the Object Repository tree item'
Windows.doubleClick(findWindowsObject('Object Repository/Tests Explorer/TreeItem_Root_Object_Repository'))

'Verify the target object present'
WindowsEnhancedKeyword.verifyElementPresent(findWindowsObject('Object Repository/Tests Explorer/TreeItem_New_Test_Object'), 
    FailureHandling.STOP_ON_FAILURE)

'Right click to show context menu'
Windows.rightClick(findWindowsObject('Object Repository/Tests Explorer/TreeItem_New_Test_Object'))

'Switch to desktop to locate Cut menu item'
Windows.switchToDesktop()

'Click on Cut menu item'
Windows.click(findWindowsObject('Object Repository/Tests Explorer/Menu/MenuItem_Cut'))

'Switch back to application'
Windows.switchToApplication()

'Right click on the new parent folder'
Windows.rightClick(findWindowsObject('Object Repository/Tests Explorer/Artifacts/Object Repository/TreeItem_New_Folder'))

'Switch to desktop to locate Paste menu item'
Windows.switchToDesktop()

'Click on Paste menu item on a target folder'
Windows.click(findWindowsObject('Object Repository/Tests Explorer/Menu/MenuItem_Paste'))

'Switch back to application'
Windows.switchToApplication()

'Wait for KS to complete the paste action'
Windows.delay(2)

'Verify the target object present moved from root folder to the new parent folder on Tests Explorer'
WindowsEnhancedKeyword.verifyElementPresent(findWindowsObject('Object Repository/Tests Explorer/Artifacts/Object Repository/TreeItem_Test_Object_Cut'), 
    FailureHandling.STOP_ON_FAILURE)

WindowsEnhancedKeyword.verifyElementNotPresent(findWindowsObject('Object Repository/Tests Explorer/TreeItem_New_Test_Object'),
	FailureHandling.STOP_ON_FAILURE)
