import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import org.openqa.selenium.Keys
import org.openqa.selenium.WebElement

import com.katalon.windows_test.artifacts.ProjectsKeyword
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
 * 2. Create a new test suite 
 * 3. View new test suite properties by using context menu (Right click -> Properties)
 * 4. Change the new test suite properties //only change description
 * 5. View new test suite properties by using context menu (Right click -> Properties)
 * 6. Verify all new new test suite properties are presented and correct
 * 7. Delete the new test suite
 * 8. Close Katalon Studio
 */

String newTestSuiteName = NamingKeyword.generateTestSuiteName()
String newTestSuiteDescription = NamingKeyword.generateTestSuiteDescription()
String verifyTestSuiteDescription = NamingKeyword.generateTestSuiteDescription()

if (!GlobalVariable.G_runTestCasesContinuously) {
	Windows.comment('1. Open `Katalon Studio` and wait for project to load')
	Windows.startApplication(GlobalVariable.G_appPath)
	ProjectsKeyword.waitForProjectLoad()
}

Windows.comment('2. Create a new test suite')
TestSuitesKeyword.createTestSuite(newTestSuiteName, newTestSuiteDescription)

Windows.comment('3. View new test suite properties by using context menu (Right click -> Properties)')
TestsExplorerKeyword.openContextMenuAtTreeItem(newTestSuiteName)
WindowsEnhancedKeyword.sendKeys('p')

Windows.comment('4. Change the new test suite properties')
Windows.sleep(1000L)
Windows.click(findWindowsObject('Object Repository/Dialogs/Test Suite Properties/Edit_Description'))
WindowsEnhancedKeyword.sendKeys(Keys.chord(Keys.CONTROL, 'a'))
WindowsEnhancedKeyword.sendKeys(Keys.DELETE)
Windows.setText(findWindowsObject('Object Repository/Dialogs/Test Suite Properties/Edit_Description'), verifyTestSuiteDescription)
//
Windows.click(findWindowsObject('Object Repository/Dialogs/Test Suite Properties/Button_OK'))

Windows.comment('5. View new test suite properties by using context menu (Right click -> Properties)')
TestsExplorerKeyword.openContextMenuAtTreeItem(newTestSuiteName)
WindowsEnhancedKeyword.sendKeys('p')

Windows.comment('6. Verify all new test suite properties are presented and correct')
Windows.sleep(1000L)
String description = Windows.getText(findWindowsObject('Object Repository/Dialogs/Test Suite Properties/Edit_Description'))
Windows.verifyEqual(description, verifyTestSuiteDescription, FailureHandling.STOP_ON_FAILURE)
//
Windows.click(findWindowsObject('Object Repository/Dialogs/Test Suite Properties/Button_OK'))

Windows.comment('7. Delete the new test suite')
TestsExplorerKeyword.deleteTreeItem(newTestSuiteName)

if (!GlobalVariable.G_runTestCasesContinuously) {
	Windows.comment('8. Close Katalon Studio')
	MainWindowKeyword.close()
}






