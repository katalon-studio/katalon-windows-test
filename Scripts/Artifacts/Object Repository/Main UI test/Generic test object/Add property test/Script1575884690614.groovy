import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.WebElement

import com.katalon.windows_test.keywords.WindowsEnhancedKeyword as WindowsEnhancedKeyword
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

'Open Test Object'
Windows.callTestCase(findTestCase('Test Cases/Artifacts/Object Repository/Context menu test/Open/Open by double clicking test'), 
    [:])

'Click on Attributes selection method'
Windows.click(findWindowsObject('Object Repository/Tests Explorer/Artifacts/Object Repository/General test object/RadioButton_Selector_Attributes'))

'Click on Add property button'
Windows.click(findWindowsObject("Object Repository/Windows/Artifacts/Object Repository/Button_Add"))

'Set property name'
Windows.setText(findWindowsObject("Object Repository/Windows/Artifacts/Object Repository/Add property dialog/Edit_Property_Name"), "id")

'Set property value'
Windows.setText(findWindowsObject("Object Repository/Windows/Artifacts/Object Repository/Add property dialog/Edit_Property_Value"), "login")

'Click on OK button'
Windows.click(findWindowsObject("Object Repository/Windows/Button_OK"))

'Verify the New Test Object part is able to save'
WindowsEnhancedKeyword.verifyElementPresent(findWindowsObject("Object Repository/MainPartStack/TabItem_Editing_New_Test_Object"), FailureHandling.STOP_ON_FAILURE)

'Scroll to Object\'s Properties table' 
WindowsEnhancedKeyword.scroll(findWindowsObject("Object Repository/Tests Explorer/Artifacts/Object Repository/General test object/ScrollBar"), 50)

'Verify new property is added'
List<WebElement> newPropertyItems = Windows.findElements(findWindowsObject("Object Repository/Tests Explorer/Artifacts/Object Repository/General test object/ListItem_New_Property_Item"))
assert newPropertyItems.size() == 1

'Verify content of the new property in table'
List<WebElement> newPropertyCellItems = Windows.findElements(findWindowsObject("Object Repository/Tests Explorer/Artifacts/Object Repository/General test object/Text_New_Property_Item"))

assert newPropertyCellItems.size() == 4

assert newPropertyCellItems.get(0).getText() == 'id'

assert newPropertyCellItems.get(1).getText() == 'equals'

assert newPropertyCellItems.get(2).getText() == 'login'

'Click on Detect object by? cell'
newPropertyCellItems.get(3).click()

'Verify the Selector Locator text has changed'
assert Windows.getText(findWindowsObject("Object Repository/Tests Explorer/Artifacts/Object Repository/General test object/Edit_Selector_Locator")) == "//*[@id = 'login']"


