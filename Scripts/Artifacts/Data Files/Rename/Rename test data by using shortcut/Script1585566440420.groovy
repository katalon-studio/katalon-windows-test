import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import org.openqa.selenium.Keys
import org.openqa.selenium.WebElement

import com.katalon.windows_test.artifacts.ProjectsKeyword
import com.katalon.windows_test.artifacts.TestCasesKeyword
import com.katalon.windows_test.artifacts.TestDataKeyword
import com.katalon.windows_test.artifacts.TestSuitesKeyword
import com.katalon.windows_test.components.MainContentKeyword
import com.katalon.windows_test.components.MainWindowKeyword
import com.katalon.windows_test.components.TestsExplorerKeyword
import com.katalon.windows_test.keywords.WindowsEnhancedKeyword
import com.katalon.windows_test.util.NamingKeyword
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


/**
 * [Script]
 *
 * 1. Open `Katalon Studio` and wait for project to load
 * 2. Create a new test data
 * 3. Open rename dialog by using shortcut key [F2]
 * 4. Input new test data name
 * 5. Verify the new test data tree item name is changed
 * 6. Verify the new tes data  tab item name is changed
 * 7. Delete the new test data
 * 8. Close Katalon Studio
 */

String newTestDataName = NamingKeyword.generateTestDataName()
String verifyTestDataName = NamingKeyword.generateTestDataName()

if (!GlobalVariable.G_runTestCasesContinuously) {
	Windows.comment('1. Open `Katalon Studio` and wait for project to load')
	Windows.startApplication(GlobalVariable.G_appPath)
	ProjectsKeyword.waitForProjectLoad()
}

Windows.comment('2. Create a new test data')
TestDataKeyword.createDataFile(newTestDataName)

Windows.comment('3. Open rename dialog by using shortcut key [F2]')
Windows.sleep(1000L)
WebElement newTestSuiteTreeItem = TestsExplorerKeyword.findTreeItem(newTestDataName)
newTestSuiteTreeItem.click()
newTestSuiteTreeItem.sendKeys(Keys.F2)

Windows.comment('4. Input new test data name')
Windows.sleep(1000L)
Windows.clearText(findWindowsObject('Object Repository/Dialogs/Rename Test Data/Edit_Name'))
Windows.setText(findWindowsObject('Object Repository/Dialogs/Rename Test Data/Edit_Name'), verifyTestDataName)
Windows.click(findWindowsObject('Object Repository/Dialogs/Rename Test Data/Button_OK'))

Windows.comment('5. Verify the new test data tree item name is changed')
Windows.sleep(1000L)
WebElement verifyTestDataTreeItem = MainContentKeyword.findTabItem(verifyTestDataName)
WindowsEnhancedKeyword.verifyElementPresent(verifyTestDataTreeItem, FailureHandling.STOP_ON_FAILURE)

//Windows.comment('6. Verify the new test data tab item name is changed')
//WebElement verifyTabItem = MainContentKeyword.findTabItem(newTestDataName)
//WindowsEnhancedKeyword.verifyElementPresent(verifyTabItem, FailureHandling.STOP_ON_FAILURE)

Windows.comment('7. Delete the new test data')
TestsExplorerKeyword.deleteTreeItem(verifyTestDataName)

if (!GlobalVariable.G_runTestCasesContinuously) {
	Windows.comment('8. Close Katalon Studio')
	MainWindowKeyword.close()
}





