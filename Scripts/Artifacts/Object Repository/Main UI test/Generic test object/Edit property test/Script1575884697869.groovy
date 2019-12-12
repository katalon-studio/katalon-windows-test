import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import org.openqa.selenium.Keys
import org.openqa.selenium.WebElement

import com.katalon.windows_test.keywords.WindowsEnhancedKeyword
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

'Call add property test test case'
Windows.callTestCase(findTestCase("Test Cases/Artifacts/Object Repository/Main UI test/Generic test object/Add property test"), [:])


List<WebElement> newPropertyCellItems = Windows.findElements(findWindowsObject("Object Repository/Tests Explorer/Artifacts/Object Repository/General test object/Text_New_Property_Item"))

'Click on property name to edit'
WebElement nameElement = newPropertyCellItems.get(0)

WindowsEnhancedKeyword.clickElementOffset(nameElement, 30, 5)

'Change property name'
Windows.setText(findWindowsObject("Object Repository/Tests Explorer/Artifacts/Object Repository/General test object/Edit_Name_Cell_Value"), 'name')

Windows.sendKeys(findWindowsObject("Object Repository/Tests Explorer/Artifacts/Object Repository/General test object/Edit_Name_Cell_Value"), Keys.chord(Keys.ENTER))

'Verify text of Selector Locator has changed'
assert Windows.getText(findWindowsObject("Object Repository/Tests Explorer/Artifacts/Object Repository/General test object/Edit_Selector_Locator")) == "//*[@name = 'login']"

'Click on property condition to edit'
WebElement conditionElement = newPropertyCellItems.get(1)

WindowsEnhancedKeyword.clickElementOffset(conditionElement, 30, 5)

'Click on dropdown list of property condition'
Windows.click(findWindowsObject("Object Repository/Tests Explorer/Artifacts/Object Repository/General test object/Button_Condition_Dropdown"))

'Select the contains condition'
Windows.click(findWindowsObject("Object Repository/Tests Explorer/Artifacts/Object Repository/General test object/ListItem_Condition_contains"))

'Send ENTER key to condition edit to commit'
Windows.sendKeys(findWindowsObject("Object Repository/Tests Explorer/Artifacts/Object Repository/General test object/Edit_Condition_Cell_Value"), Keys.chord(Keys.ENTER))

'Verify text of Selector Locator has changed'
assert Windows.getText(findWindowsObject("Object Repository/Tests Explorer/Artifacts/Object Repository/General test object/Edit_Selector_Locator")) == "//*[contains(@name, 'login')]"

'Click on property value to edit'
WebElement propertyElement = newPropertyCellItems.get(2)

WindowsEnhancedKeyword.clickElementOffset(propertyElement, 30, 5)

'Change property value'
Windows.setText(findWindowsObject("Object Repository/Tests Explorer/Artifacts/Object Repository/General test object/Document_Value_Cell_Value"), "username")

'Click on Selector Locator to commit'
Windows.click(findWindowsObject("Object Repository/Tests Explorer/Artifacts/Object Repository/General test object/Edit_Selector_Locator"))

'Verify text of Selector Locator has changed'
assert Windows.getText(findWindowsObject("Object Repository/Tests Explorer/Artifacts/Object Repository/General test object/Edit_Selector_Locator")) == "//*[contains(@name, 'username')]"

'Verify the New Test Object part is able to save'
WindowsEnhancedKeyword.verifyElementPresent(findWindowsObject("Object Repository/MainPartStack/TabItem_Editing_New_Test_Object"), FailureHandling.STOP_ON_FAILURE)
