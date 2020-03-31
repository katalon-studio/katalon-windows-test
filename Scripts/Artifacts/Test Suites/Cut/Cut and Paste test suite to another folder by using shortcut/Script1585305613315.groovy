import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import org.openqa.selenium.Keys
import org.openqa.selenium.WebElement

import com.katalon.windows_test.artifacts.ProjectsKeyword
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
 * Script
 * 
 * 1. Open 'Katalon Studio' and wait for project to load
 * 2. Create new test suite 
 * 3. Create new folder
 * 4. Cut the new test suite by using shortcut key [Control +X]
 * 5. Paste the cut test suite to the new folder by using shortcut key [Control +V]
 * 6. Verify the cut test case is appeared under the new folder
 * 7. Delete the new folder 
 * 8. Close Katalon Studio
 */

String newTestSuiteName = NamingKeyword.generateTestSuiteName()
String newFolderName = NamingKeyword.generateTestSuiteFolderName()

if(!GlobalVariable.G_runTestCasesContinuously){
	Windows.comment('1. Open Katalon Studio and wait for project to load')
	Windows.startApplicationWithTitle(GlobalVariable.G_appPath, 'Katalon Studio')
	ProjectsKeyword.waitForProjectLoad()
}

Windows.comment('2. Create new test suite')
TestSuitesKeyword.createTestSuite(newTestSuiteName)

Windows.comment('3. Create new folder')
TestSuitesKeyword.createFolder(newFolderName)

Windows.comment('4. Cut the new test suite by using shortcut key [Control +X]')
WebElement newTestSuiteTreeItem = TestsExplorerKeyword.findTreeItem(newTestSuiteName)
newTestSuiteTreeItem.click()
newTestSuiteTreeItem.sendKeys(Keys.chord(Keys.CONTROL, 'x'))

Windows.comment('5. Paste the cut test suite to another folder by using shortcut key [Control +V]')
WebElement newFolderTreeItem = TestsExplorerKeyword.findTreeItem(newFolderName)
newFolderTreeItem.click()
newFolderTreeItem.sendKeys(Keys.chord(Keys.CONTROL, 'v'))

Windows.comment('6. Verify the cut test suite is appeared under the new folder')
WebElement cutTestSuiteTreeItem = TestsExplorerKeyword.findTreeItem(newTestSuiteName, newFolderName)
WindowsEnhancedKeyword.verifyElementPresent(cutTestSuiteTreeItem, FailureHandling.STOP_ON_FAILURE)

Windows.comment('7. Delete new folder')
TestsExplorerKeyword.deleteTreeItem(newFolderName)
WindowsEnhancedKeyword.sendKeys(Keys.ENTER)

if(!GlobalVariable.G_runTestCasesContinuously){
	Windows.comment('8. Close Katalon Studio')
	MainWindowKeyword.close()
}