import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import org.openqa.selenium.Keys
import org.openqa.selenium.WebElement

import com.katalon.windows_test.artifacts.ProjectsKeyword
import com.katalon.windows_test.artifacts.TestDataKeyword
import com.katalon.windows_test.artifacts.TestSuitesKeyword
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

/*
 * Scipt
 * 
 * 1. Open 'Katalon Studio' and wait for project to load
 * 2. Create new test data
 * 3. Copy the new test data by using shortcut key [Control + C]
 * 4. Paste the copied test data at the same folder by using shortcut key [Control + V]
 * 5. Verify the cloned test data is appeared [Name: "<new test data name>" + " - Copy"]
 * 6. Delete both cloned and new test data
 * 7. Close Katalon Studio
 */

String newTestDataName = NamingKeyword.generateTestDataName()
String clonedTestDataName = newTestDataName + ' - Copy'

if(!GlobalVariable.G_runTestCasesContinuously){
	Windows.comment('1. Open Katalon Studio and wait for project to load')
	Windows.startApplicationWithTitle(GlobalVariable.G_appPath, 'Katalon Studio')
	ProjectsKeyword.waitForProjectLoad()
}

Windows.comment('2. Create new test data')
TestDataKeyword.createDataFile(newTestDataName)

Windows.comment('3. Copy the new test data by using shortcut key [Control + C]')
WebElement newTestDataTreeItem = TestsExplorerKeyword.findTreeItem(newTestDataName)
newTestDataTreeItem.click()
newTestDataTreeItem.sendKeys(Keys.chord(Keys.CONTROL, 'c'))

Windows.comment('4. Paste the copied test data at the same foler by using shortcut key [Control + V]')
newTestDataTreeItem.click()
newTestDataTreeItem.sendKeys(Keys.chord(Keys.CONTROL, 'v'))

Windows.comment('5. Verify the cloned test data is appeared [Name: "<new test data name>" + " - Copy"]')
Windows.sleep(1000L)
WebElement clonedTestDataTreeItem = TestsExplorerKeyword.findTreeItem(clonedTestDataName)
WindowsEnhancedKeyword.verifyElementPresent(clonedTestDataTreeItem, FailureHandling.STOP_ON_FAILURE)

Windows.comment('6. Delete both cloned and new test data')
TestsExplorerKeyword.deleteTreeItem(newTestDataName)
TestsExplorerKeyword.deleteTreeItem(clonedTestDataName)

if(!GlobalVariable.G_runTestCasesContinuously){
	Windows.comment('7. Close Katalon Studio')
	MainWindowKeyword.close()
}