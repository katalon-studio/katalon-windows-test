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

'Open Test Object'
Windows.callTestCase(findTestCase("Test Cases/Artifacts/Object Repository/Context menu test/Open/Open by double clicking test"), [:])

'Click on Parent iframe radio button'
Windows.click(findWindowsObject('Object Repository/Tests Explorer/Artifacts/Object Repository/General test object/RadioButton_Parent_iframe'))

'Click on Browse... button'
Windows.click(findWindowsObject('Object Repository/Tests Explorer/Artifacts/Object Repository/General test object/Button_Browse'))

'Select Parent Test Object on Object Repository Browser dialog'
Windows.click(findWindowsObject('Object Repository/Windows/Object Repository Browser/TreeItem_Parent_Test_Object'))

'Click on OK button'
Windows.click(findWindowsObject('Object Repository/Windows/Button_OK'))

'Verify text of parent iframe text field is the same as ID of the parent object'
assert Windows.getText(findWindowsObject('Object Repository/Tests Explorer/Artifacts/Object Repository/General test object/Text_Parent_iframe')) == 'Object Repository/Parent Test Object'

'Verify the New Test Object part is able to save'
WindowsEnhancedKeyword.verifyElementPresent(findWindowsObject("Object Repository/MainPartStack/TabItem_Editing_New_Test_Object"), FailureHandling.STOP_ON_FAILURE)

'Click on Shadow Root Parent radio button'
Windows.click(findWindowsObject("Object Repository/Tests Explorer/Artifacts/Object Repository/General test object/RadioButton_Shadow_Root_Parent"))

'Click on Browse... button'
Windows.click(findWindowsObject('Object Repository/Tests Explorer/Artifacts/Object Repository/General test object/Button_Browse'))

'Select Parent Test Object on Object Repository Browser dialog'
Windows.click(findWindowsObject('Object Repository/Windows/Object Repository Browser/TreeItem_Parent_Test_Object'))

'Click on OK button'
Windows.click(findWindowsObject('Object Repository/Windows/Button_OK'))

'Verify text of Shadow Root Parent text field is the same as ID of the parent object'
assert Windows.getText(findWindowsObject('Object Repository/Tests Explorer/Artifacts/Object Repository/General test object/Text_Parent_iframe')) == 'Object Repository/Parent Test Object'

'Verify the New Test Object part is able to save'
WindowsEnhancedKeyword.verifyElementPresent(findWindowsObject("Object Repository/MainPartStack/TabItem_Editing_New_Test_Object"), FailureHandling.STOP_ON_FAILURE)

