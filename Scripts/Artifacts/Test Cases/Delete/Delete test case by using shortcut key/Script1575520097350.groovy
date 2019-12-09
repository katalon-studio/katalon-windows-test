import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.katalon.windows_test.artifacts.ProjectsKeyword
import com.katalon.windows_test.artifacts.TestCasesKeyword
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
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.WebElement

/**
 * [Script]
 *
 * 1. Open `Katalon Studio` and wait for project to load
 * 2. Create a sample test case
 * 3. Delete the created test case by shortcut key [Delete] -> [Enter]
 * 4. Verify the test case is removed from test cases tree
 * 5. Verify the test case is closed
 * 6. Close Katalon Studio
 */

String newTestCaseName = NamingKeyword.generateTestCaseName()

Windows.comment('1. Open `Katalon Studio` and wait for project to load')
Windows.startApplication(GlobalVariable.G_appPath)
ProjectsKeyword.waitForProjectLoad()

Windows.comment('2. Create a sample test case')
TestCasesKeyword.createTestCaseUsingFileMenu(newTestCaseName)

Windows.comment('3. Delete the created test case by shortcut key [Delete] -> [Enter]')
WebElement createdTestCaseTreeItem = TestsExplorerKeyword.findTreeItem(newTestCaseName)
createdTestCaseTreeItem.click()
WindowsEnhancedKeyword.pressKey(Keys.DELETE)
WindowsEnhancedKeyword.pressKey(Keys.ENTER)

Windows.comment('4. Verify the created test case is deleted')
WebElement deletedTestCaseTreeItem = TestsExplorerKeyword.findTreeItem(newTestCaseName)
WindowsEnhancedKeyword.verifyElementNotPresent(deletedTestCaseTreeItem, FailureHandling.STOP_ON_FAILURE)

Windows.comment('5. Verify the created test case is closed')
WebElement deletedTestCaseTab = MainContentKeyword.findTabItem(newTestCaseName)
WindowsEnhancedKeyword.verifyElementNotPresent(deletedTestCaseTab, FailureHandling.STOP_ON_FAILURE)

Windows.comment('6. Close Katalon Studio')
MainWindowKeyword.close()

