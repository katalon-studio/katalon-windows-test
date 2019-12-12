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

'Click on Copy ID menu item'
Windows.click(findWindowsObject('Object Repository/Tests Explorer/Menu/MenuItem_Copy ID'))

'Paste the object ID in the Tests Explorer search field'
Windows.clearText(findWindowsObject("Object Repository/Tests Explorer/Edit_Search"))

Windows.sendKeys(findWindowsObject("Object Repository/Tests Explorer/Edit_Search"), Keys.chord(Keys.CONTROL + "V"))

'Wait for the copied text pasted'
Windows.delay(2)

'Verify the pasted text is the same with the target object ID'
assert Windows.getText(findWindowsObject("Object Repository/Tests Explorer/Edit_Search")) == 'Object Repository/New Test Object'

Windows.clearText(findWindowsObject("Object Repository/Tests Explorer/Edit_Search"))

