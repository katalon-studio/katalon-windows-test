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

'Switch to desktop to locate Open menu item'
Windows.switchToDesktop()

'Click on Rename menu item'
Windows.click(findWindowsObject('Object Repository/Tests Explorer/Menu/MenuItem_Rename'))

'Switch back to application'
Windows.switchToApplication()

'Wait for KS to open the rename dialog'
Windows.delay(2)

'Change test object name'
Windows.clearText(findWindowsObject('Object Repository/Windows/Dialog/Rename Dialog/Edit_New_Name'))

Windows.setText(findWindowsObject('Object Repository/Windows/Dialog/Rename Dialog/Edit_New_Name'), 'Renamed Test Object')

'Click on OK button'
Windows.click(findWindowsObject('Object Repository/Windows/Button_OK'))

'Wait KS to rename the target object'
Windows.delay(5)

'Verify the target object present on Tests Explorer with new name'
WindowsEnhancedKeyword.verifyElementPresent(findWindowsObject('Object Repository/Tests Explorer/TreeItem_Renamed_Test_Object'), 
    FailureHandling.STOP_ON_FAILURE)

'Verify the target object not present on Tests Explorer with old name'
WindowsEnhancedKeyword.verifyElementNotPresent(findWindowsObject('Object Repository/Tests Explorer/TreeItem_New_Test_Object'),
	FailureHandling.STOP_ON_FAILURE)