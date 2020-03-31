import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import org.openqa.selenium.Keys
import org.openqa.selenium.WebElement

import com.katalon.windows_test.artifacts.ProjectsKeyword
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
 * 2. Create a new test data with full properties
 * 3. View new test data properties by using context menu (Right click -> Properties)
 * 4. Verify all new test data properties are presented and correct
 * 5. Delete the new test data
 * 6. Close Katalon Studio
 */

String newTestDataName = NamingKeyword.generateTestDataName()
String newTestDataDescription = NamingKeyword.generateTestDataDescription()

if (!GlobalVariable.G_runTestCasesContinuously) {
	Windows.comment('1. Open `Katalon Studio` and wait for project to load')
	Windows.startApplication(GlobalVariable.G_appPath)
	ProjectsKeyword.waitForProjectLoad()
}

Windows.comment('2. Create a new test data with full properties')
TestDataKeyword.createDataFile(newTestDataName,  newTestDataDescription)

Windows.comment('3. View new test data properties by using context menu (Right click -> Properties)')
TestsExplorerKeyword.openContextMenuAtTreeItem(newTestDataName)
WindowsEnhancedKeyword.sendKeys('p')

Windows.comment('4. Verify all new test data properties are presented and correct')
Windows.sleep(1000L)
//
String name = Windows.getText(findWindowsObject('Object Repository/Dialogs/Test Data Properties/Edit_Name'))
Windows.verifyEqual(name, newTestDataName, FailureHandling.STOP_ON_FAILURE)
//
String description = Windows.getText(findWindowsObject('Object Repository/Dialogs/Test Data Properties/Edit_Description'))
Windows.verifyEqual(description, newTestDataDescription, FailureHandling.STOP_ON_FAILURE)

//
Windows.click(findWindowsObject('Object Repository/Dialogs/Test Data Properties/Button_OK'))


Windows.comment('5. Delete the new test data')
TestsExplorerKeyword.deleteTreeItem(newTestDataName)

if (!GlobalVariable.G_runTestCasesContinuously) {
	Windows.comment('6. Close Katalon Studio')
	MainWindowKeyword.close()
}






