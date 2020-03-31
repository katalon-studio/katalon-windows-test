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
 * 1. Open 'Katalon Studio' and wat for project to load
 * 2. Create new test suit
 * 3. Cut the new test suite by using shortcut key [Control +X]
 * 4. Paste the cut test suite at the same folder by using shortcut key [Control +V]
 * 5. Verify the cut test suite is appeared
 * 6. Delete new test suite
 * 7. Close Katalon Studio
 */

String newTestSuiteName = NamingKeyword.generateTestSuiteName()

if(!GlobalVariable.G_runTestCasesContinuously){
	Windows.comment('1. Open Katalon Studio and wait for project to load')
	Windows.startApplicationWithTitle(GlobalVariable.G_appPath, 'Katalon Studio')
	ProjectsKeyword.waitForProjectLoad()
}

Windows.comment('2. Create new tets suite')
TestSuitesKeyword.createTestSuite(newTestSuiteName)

Windows.comment('3. Cut the new tets suite by using shortcut key [Control +X]')
WebElement newTestSuiteTreeItem = TestsExplorerKeyword.findTreeItem(newTestSuiteName)
newTestSuiteTreeItem.click()
newTestSuiteTreeItem.sendKeys(Keys.chord(Keys.CONTROL, 'x'))
Windows.sleep(1000L)

Windows.comment('4. Paste the cut test suite at the same folder by using shortcut key [Control +V]')
newTestSuiteTreeItem.click()
newTestSuiteTreeItem.sendKeys(Keys.chord(Keys.CONTROL, 'v'))

Windows.comment('5. Verify the cut test suite is appeared')
WebElement cutTestSuiteTreeItem = TestsExplorerKeyword.findTreeItem(newTestSuiteName)
WindowsEnhancedKeyword.verifyElementPresent(cutTestSuiteTreeItem, FailureHandling.STOP_ON_FAILURE)

Windows.comment('6. Delete the new test suite')
TestsExplorerKeyword.deleteTreeItem(newTestSuiteName)

if(!GlobalVariable.G_runTestCasesContinuously){
	Windows.comment('7. Close Katalon Studio')
	MainWindowKeyword.close()
}