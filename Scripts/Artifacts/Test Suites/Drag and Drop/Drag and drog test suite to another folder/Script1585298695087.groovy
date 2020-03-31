import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

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

/**
 * Script
 *
 * 1. Open `Katalon Studio` and wait for project to load
 * 2. Create a new test suite
 * 3. Create a new folder
 * 4. Drag and drop the new test suite to the new folder
 * 5. Verify the new test suite is disappeared from the original folder
 * 6. Verify the new test suite is appeared under the new folder
 * 7. Delete the new folder
 * 8. Close Katalon Studio
 */

String newTestSuiteName = NamingKeyword.generateTestSuiteName()
String newFolderName = NamingKeyword.generateTestSuiteFolderName()

if(!GlobalVariable.G_runTestCasesContinuously){
	Windows.comment('1. Open Katalon Studio and wait for project ot load')
	Windows.startApplicationWithTitle(GlobalVariable.G_appPath, 'Katalon Studio')
	ProjectsKeyword.waitForProjectLoad()
}

Windows.comment('2. Create new test suite')
TestSuitesKeyword.createTestSuite(newTestSuiteName)

Windows.comment('3. Crate new folder')
Windows.sleep(1000L)
TestSuitesKeyword.createFolder(newFolderName)

Windows.comment('4. Drap and drop the new test suite to the new folder')
Windows.sleep(1000L)
WebElement newTestSuiteTreeItem = TestsExplorerKeyword.findTreeItem(newTestSuiteName)
WebElement newFolderTreeItem = TestsExplorerKeyword.findTreeItem(newFolderName)
WindowsEnhancedKeyword.dragAnDrop(newTestSuiteTreeItem, newFolderTreeItem)

Windows.comment('5. Verify the new test suite is disappeared form the original folder')
Windows.sleep(1000L)
WebElement deletedFolderTreeItem = TestsExplorerKeyword.findTreeItem(newTestSuiteName, newFolderName)
WindowsEnhancedKeyword.verifyElementPresent(deletedFolderTreeItem, FailureHandling.STOP_ON_FAILURE)

Windows.comment('7. Delete the new folder')
TestsExplorerKeyword.deleteTreeItem(newFolderName)

if (!GlobalVariable.G_runTestCasesContinuously) {
	Windows.comment('8. Close Katalon Studio')
	MainWindowKeyword.close()
}

