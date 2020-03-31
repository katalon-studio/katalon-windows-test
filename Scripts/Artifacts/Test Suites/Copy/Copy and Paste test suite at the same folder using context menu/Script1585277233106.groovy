import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
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

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.katalon.windows_test.artifacts.ProjectsKeyword as ProjectsKeyword
import com.katalon.windows_test.artifacts.TestCasesKeyword as TestCasesKeyword
import com.katalon.windows_test.artifacts.TestSuitesKeyword
import com.katalon.windows_test.components.MainContentKeyword as MainContentKeyword
import com.katalon.windows_test.components.MainWindowKeyword as MainWindowKeyword
import com.katalon.windows_test.components.MenubarKeyword as MenubarKeyword
import com.katalon.windows_test.components.TestsExplorerKeyword as TestsExplorerKeyword
import com.katalon.windows_test.keywords.WindowsEnhancedKeyword as WindowsEnhancedKeyword
import com.katalon.windows_test.util.NamingKeyword as NamingKeyword
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
import org.openqa.selenium.WebElement as WebElement

/**
 * [Script]
 *
 * 1. Open `Katalon Studio` and wait for project to load
 * 2. Create a new test suite
 * 3. Copy the new test suite by using context menu (Right click -> Copy)
 * 4. Paste the copied test suite at the same folder by using context menu (Right click -> Paste)
 * 5. Verify the cloned test suite is appeared (Name: "<sample test case name> - Copy")
 * 6. Delete both new & cloned test suite
 * 7. Close Katalon Studio
 */

String newTestSuiteName = NamingKeyword.generateTestSuiteName()
String clonedTestSuiteName = newTestSuiteName + " - Copy"

if(!GlobalVariable.G_runTestCasesContinuously){
	Windows.comment('1. Open Katalon Studio and wait for project to load')
	Windows.startApplicationWithTitle(GlobalVariable.G_appPath, 'Katalon Studio')
	ProjectsKeyword.waitForProjectLoad()
}

Windows.comment('2. Create a new test suite')
TestSuitesKeyword.createTestSuite(newTestSuiteName)

Windows.comment('3. Copy the new test suite by using context menu (Right click -> Copy)')
TestsExplorerKeyword.openContextMenuAtTreeItem(newTestSuiteName)
WindowsEnhancedKeyword.sendKeys('c')
WindowsEnhancedKeyword.sendKeys(Keys.ENTER)

Windows.comment('4. Paste the copied test suite by using context menu (Right click -> Paste)')
TestsExplorerKeyword.openContextMenuAtTreeItem(newTestSuiteName)
WindowsEnhancedKeyword.sendKeys('p')
WindowsEnhancedKeyword.sendKeys(Keys.ENTER)

Windows.comment('5. Verify the cloned test suite is appeared (Name: "<sample test suite name> - Copy")')
Windows.sleep(1000L)
WebElement clonedTestSuiteTreeItem = TestsExplorerKeyword.findTreeItem(clonedTestSuiteName)
WindowsEnhancedKeyword.verifyElementPresent(clonedTestSuiteTreeItem, FailureHandling.STOP_ON_FAILURE)

Windows.comment('6. Delete both new and cloned test suite')
TestsExplorerKeyword.deleteTreeItem(newTestSuiteName)
TestsExplorerKeyword.deleteTreeItem(clonedTestSuiteName)

if(!GlobalVariable.G_runTestCasesContinuously){
	Windows.comment('7. Close Katalon Studio')
	MainWindowKeyword.close()
}
