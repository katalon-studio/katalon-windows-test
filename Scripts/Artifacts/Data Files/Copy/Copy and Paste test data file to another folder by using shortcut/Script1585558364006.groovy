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
 * 3. Create new folder
 * 3. Copy the new test data by using shortcut key [Control + C]
 * 4. Paste the copied test data to another folder by using shortcut key [Control + V]
 * 5. Verify the cloned test data is appeared under the new folder
 * 6. Delete both cloned test data and new folder
 * 7. Close Katalon Studio
 */

String newTestDataName = NamingKeyword.generateTestDataName()
String newFolderName = NamingKeyword.generateTestDataFolderName()

if(!GlobalVariable.G_runTestCasesContinuously){
	Windows.comment('1. Open Katalon Studio and wait for project to load')
	Windows.startApplicationWithTitle(GlobalVariable.G_appPath, 'Katalon Studio')
	ProjectsKeyword.waitForProjectLoad()
}

Windows.comment('2. Create new test data')
TestDataKeyword.createDataFile(newTestDataName)

Windows.comment('3. Create new folder')
Windows.sleep(1000L)
TestDataKeyword.createFolder(newFolderName)

Windows.comment('4. Copy the new test data by using shortcut key [Control + C]')
WebElement newTestDataTreeItem = TestsExplorerKeyword.findTreeItem(newTestDataName)
newTestDataTreeItem.click()
newTestDataTreeItem.sendKeys(Keys.chord(Keys.CONTROL, 'c'))

Windows.comment('5. Paste the copied test data to another foler by using shortcut key [Control + V]')
WebElement newFolderTreeItem = TestsExplorerKeyword.findTreeItem(newFolderName)
newFolderTreeItem.click()
newFolderTreeItem.sendKeys(Keys.chord(Keys.CONTROL, 'v'))

Windows.comment('6. Verify the cloned test data is appeared under the new folder')
Windows.sleep(1000L)
WebElement clonedTestDataTreeItem = TestsExplorerKeyword.findTreeItem(newTestDataName, newFolderName)
WindowsEnhancedKeyword.verifyElementPresent(clonedTestDataTreeItem, FailureHandling.STOP_ON_FAILURE)

Windows.comment('7. Delete both cloned test data and new folder')
TestsExplorerKeyword.deleteTreeItem(newTestDataName)
TestsExplorerKeyword.deleteTreeItem(newFolderName)

if(!GlobalVariable.G_runTestCasesContinuously){
	Windows.comment('7. Close Katalon Studio')
	MainWindowKeyword.close()
}