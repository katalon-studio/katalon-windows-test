import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import org.openqa.selenium.Keys
import org.openqa.selenium.WebElement

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


/**
 * [Script]
 *
 * 1. Open `Katalon Studio` and wait for project to load
 * 2. Create a sample test case
 * 3. Close the sample test case
 * 4. Check for sure that the sample test case is really closed
 * 5. Open the sample test case by using shortcut key [F3]
 * 6. Verify the sample test case is opened
 * 7. Delete the sample test case
 * 8. Close Katalon Studio
 */

String sampleTestCaseName = NamingKeyword.generateTestCaseName()

Windows.comment('2. Create a sample test case')
TestCasesKeyword.createTestCase(sampleTestCaseName)

Windows.comment('3. Close the sample test case')
MainContentKeyword.closeTabItem(sampleTestCaseName)

Windows.comment('4. Check for sure that the sample test case is really closed')
Windows.sleep(1000L)
WebElement sampleTestCaseTab = MainContentKeyword.findTabItem(sampleTestCaseName)
WindowsEnhancedKeyword.verifyElementNotPresent(sampleTestCaseTab, FailureHandling.STOP_ON_FAILURE)

Windows.comment('5. Open the sample test case by using shortcut key [F3]')
WebElement sampleTestCase = TestsExplorerKeyword.findTreeItem(sampleTestCaseName)
sampleTestCase.click()
sampleTestCase.sendKeys(Keys.F3)

Windows.comment('6. Verify the sample test case is opened')
Windows.sleep(1000L)
sampleTestCaseTab = MainContentKeyword.findTabItem(sampleTestCaseName)
WindowsEnhancedKeyword.verifyElementPresent(sampleTestCaseTab, FailureHandling.STOP_ON_FAILURE)

Windows.comment('7. Delete the sample test case')
TestsExplorerKeyword.deleteTreeItem(sampleTestCaseName)

if (!GlobalVariable.G_runTestCasesContinuously) {
	Windows.comment('8. Close Katalon Studio')
	MainWindowKeyword.close()
}



