import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import org.openqa.selenium.Keys

import com.katalon.windows_test.keywords.WindowsEnhancedKeyword as WindowsEnhancedKeyword
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

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

'Switch to desktop to locate Copy menu item'
Windows.switchToDesktop()

'Click on Properties menu item'
Windows.click(findWindowsObject('Object Repository/Tests Explorer/Menu/MenuItem_Properties'))

'Switch back to application'
Windows.switchToApplication()

'Verify object\'s ID of the ID text field on Properties dialog'
assert Windows.getText(findWindowsObject("Object Repository/Windows/Artifacts/Object Repository/Edit_Properties_ID")) == 'Object Repository/New Test Object'

'Verify object\' name of the Name text field on Properties dialog'
assert Windows.getText(findWindowsObject("Object Repository/Windows/Artifacts/Object Repository/Edit_Properties_Name")) == 'New Test Object'

'Verify object\' description of the Description text area on Properties dialog'
assert Windows.getText(findWindowsObject("Object Repository/Windows/Artifacts/Object Repository/Document_Properties_Description")) == 'This is a description'

'Close the Properties dialog'
Windows.click(findWindowsObject("Object Repository/Windows/Button_OK"))


