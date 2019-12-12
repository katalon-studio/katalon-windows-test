import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import org.openqa.selenium.Keys as Keys
import com.katalon.windows_test.keywords.WindowsEnhancedKeyword as WindowsEnhancedKeyword
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

'Open Test Object'
Windows.callTestCase(findTestCase('Test Cases/Artifacts/Object Repository/Context menu test/Open/Open by double clicking test'), 
    [:])

'Click on XPath selection method'
Windows.click(findWindowsObject('Object Repository/Tests Explorer/Artifacts/Object Repository/General test object/RadioButton_Selector_XPATH'))

'Set text for Selector Locator'
Windows.sendKeys(findWindowsObject('Object Repository/Tests Explorer/Artifacts/Object Repository/General test object/Edit_Selector_Locator'), Keys.chord(Keys.CONTROL + 'A'))

Windows.setText(findWindowsObject('Object Repository/Tests Explorer/Artifacts/Object Repository/General test object/Edit_Selector_Locator'), '//*[@id="login"]')

'Verify text is set'
assert Windows.getText(findWindowsObject('Object Repository/Tests Explorer/Artifacts/Object Repository/General test object/Edit_Selector_Locator')) == '//*[@id="login"]'

'Verify the New Test Object part is able to save'
WindowsEnhancedKeyword.verifyElementPresent(findWindowsObject('Object Repository/MainPartStack/TabItem_Editing_New_Test_Object'), 
    FailureHandling.STOP_ON_FAILURE)

'Click on Attributes selection method'
Windows.click(findWindowsObject('Object Repository/Tests Explorer/Artifacts/Object Repository/General test object/RadioButton_Selector_Attributes'))

'Set text for Selector Locator'
Windows.sendKeys(findWindowsObject('Object Repository/Tests Explorer/Artifacts/Object Repository/General test object/Edit_Selector_Locator'), Keys.chord(Keys.CONTROL + 'A'))

Windows.setText(findWindowsObject('Object Repository/Tests Explorer/Artifacts/Object Repository/General test object/Edit_Selector_Locator'), '//*[@id="login"]')

'Verify text is not set'
assert Windows.getText(findWindowsObject('Object Repository/Tests Explorer/Artifacts/Object Repository/General test object/Edit_Selector_Locator')) == ''

'Verify the New Test Object part is able to save'
WindowsEnhancedKeyword.verifyElementPresent(findWindowsObject('Object Repository/MainPartStack/TabItem_Editing_New_Test_Object'), 
    FailureHandling.STOP_ON_FAILURE)

'Click on CSS selection method'
Windows.click(findWindowsObject('Object Repository/Tests Explorer/Artifacts/Object Repository/General test object/RadioButton_Selector_CSS'))

'Set text for Selector Locator'
Windows.sendKeys(findWindowsObject('Object Repository/Tests Explorer/Artifacts/Object Repository/General test object/Edit_Selector_Locator'), Keys.chord(Keys.CONTROL + 'A'))

Windows.setText(findWindowsObject('Object Repository/Tests Explorer/Artifacts/Object Repository/General test object/Edit_Selector_Locator'), '#id')

'Verify text is NOT set'
assert Windows.getText(findWindowsObject('Object Repository/Tests Explorer/Artifacts/Object Repository/General test object/RadioButton_Selector_CSS')) == '#id'

'Verify the New Test Object part is able to save'
WindowsEnhancedKeyword.verifyElementPresent(findWindowsObject('Object Repository/MainPartStack/TabItem_Editing_New_Test_Object'), 
    FailureHandling.STOP_ON_FAILURE)