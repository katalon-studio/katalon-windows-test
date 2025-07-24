import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import org.openqa.selenium.WebElement

import com.katalon.windows_test.keywords.WindowsEnhancedKeyword
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

'Call add property test test case'
Windows.callTestCase(findTestCase("Test Cases/Artifacts/Object Repository/Main UI test/Generic test object/Add property test"), [:])

'Click on Delete button'
Windows.click(findWindowsObject("Object Repository/Windows/Artifacts/Object Repository/Button_Clear"))

'Verify no property present'
List<WebElement> newPropertyItems = Windows.findElements(findWindowsObject("Object Repository/Tests Explorer/Artifacts/Object Repository/General test object/ListItem_New_Property_Item"))
assert newPropertyItems.size() == 0

'Verify the New Test Object part is able to save'
WindowsEnhancedKeyword.verifyElementPresent(findWindowsObject("Object Repository/MainPartStack/TabItem_Editing_New_Test_Object"), FailureHandling.STOP_ON_FAILURE)