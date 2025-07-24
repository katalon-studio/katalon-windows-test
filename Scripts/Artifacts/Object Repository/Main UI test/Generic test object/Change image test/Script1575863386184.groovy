import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.katalon.windows_test.keywords.WindowsEnhancedKeyword
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable as GlobalVariable

'Open test object'
Windows.callTestCase(findTestCase('Artifacts/Object Repository/Context menu test/Open/Open by double clicking test'), [:], 
    FailureHandling.STOP_ON_FAILURE)

'Click on Browse image button'
Windows.click(findWindowsObject("Object Repository/Tests Explorer/Artifacts/Object Repository/General test object/Button_Browse_Image"))

'Select image'
String imagePath = GlobalVariable.G_projectLocation + "\\Images\\image.png"

'Wait for Open dialog to open'
Windows.delay(2)

'Set image file path for the edit file name'
Windows.setText(findWindowsObject("Object Repository/Tests Explorer/Artifacts/Object Repository/General test object/Edit_File_Name"), imagePath)

'Click on Open button'
Windows.click(findWindowsObject("Object Repository/Windows/Button_Open"))

'Wait for Open dialog to close'
Windows.delay(2)

'Verify text on Edit image field is same as the absolute path of image file'
assert Windows.getText(findWindowsObject("Object Repository/Tests Explorer/Artifacts/Object Repository/General test object/Edit_Image")) == imagePath

'Verify the New Test Object part is able to save'
WindowsEnhancedKeyword.verifyElementPresent(findWindowsObject("Object Repository/MainPartStack/TabItem_Editing_New_Test_Object"), FailureHandling.STOP_ON_FAILURE)

'Click on Use relative path for image'
Windows.click(findWindowsObject("Object Repository/Tests Explorer/Artifacts/Object Repository/General test object/CheckBox_Use_relative_path_for_image"))

'Verify text on Edit image field is same as the relative path of image file'
assert Windows.getText(findWindowsObject("Object Repository/Tests Explorer/Artifacts/Object Repository/General test object/Edit_Image")) == "Images/image.png"

'Verify the New Test Object part is able to save'
WindowsEnhancedKeyword.verifyElementPresent(findWindowsObject("Object Repository/MainPartStack/TabItem_Editing_New_Test_Object"), FailureHandling.STOP_ON_FAILURE)

