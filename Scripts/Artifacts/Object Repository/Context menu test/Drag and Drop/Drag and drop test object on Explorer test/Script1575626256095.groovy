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

'Drag and drop the object from root folder to new parent folder'
WindowsEnhancedKeyword.dragAnDrop(findWindowsObject('Object Repository/Tests Explorer/TreeItem_New_Test_Object'), findWindowsObject('Object Repository/Tests Explorer/Artifacts/Object Repository/TreeItem_New_Folder'))

'Wait for KS to complete the paste action'
Windows.delay(2)

'Verify the target object present moved from root folder to the new parent folder on Tests Explorer'
WindowsEnhancedKeyword.verifyElementPresent(findWindowsObject('Object Repository/Tests Explorer/Artifacts/Object Repository/TreeItem_Test_Object_Cut'), 
    FailureHandling.STOP_ON_FAILURE)

WindowsEnhancedKeyword.verifyElementNotPresent(findWindowsObject('Object Repository/Tests Explorer/TreeItem_New_Test_Object'),
	FailureHandling.STOP_ON_FAILURE)
