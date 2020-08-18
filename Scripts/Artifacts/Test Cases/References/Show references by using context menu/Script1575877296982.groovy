import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.katalon.windows_test.artifacts.ProjectsKeyword
import com.katalon.windows_test.artifacts.TestCasesKeyword
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

import org.openqa.selenium.Keys
import org.openqa.selenium.WebElement


/**
 * [Script]
 *
 * 1. Open `Katalon Studio` and wait for project to load
 * 2. Create a sample test case
 * 3. Show References by using context menu (Right click -> Show References)
 * 4. Verify test case references are listed under Search tab
 * 5. Delete the sample test case
 * 6. Close Katalon Studio
 */

String sampleTestCaseName = NamingKeyword.generateTestCaseName()

Windows.comment('2. Create a sample test case')
TestCasesKeyword.createTestCase(sampleTestCaseName)

Windows.comment('3. Show References by using context menu (Right click -> Show References)')
TestsExplorerKeyword.openContextMenuAtTreeItem(sampleTestCaseName)
WindowsEnhancedKeyword.sendKeys('s')

Windows.comment('4. Verify test case references are listed under Search tab')
String searchTabXPath = String.format('//Tab[@Name="Search"]/TabItem[@Name="Search"]')
WebElement searchTab = Windows.getDriver().findElementByXPath(searchTabXPath)
WindowsEnhancedKeyword.verifyElementPresent(searchTab, FailureHandling.STOP_ON_FAILURE)

Windows.comment('5. Delete the sample test case')
TestsExplorerKeyword.deleteTreeItem(sampleTestCaseName)

if (!GlobalVariable.G_runTestCasesContinuously) {
	Windows.comment('6. Close Katalon Studio')
	MainWindowKeyword.close()
}

