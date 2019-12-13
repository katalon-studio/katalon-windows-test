import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

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
 * 2. Create a sample test case with full properties
 * 3. View sample test case properties by using Properties tab
 * 4. Verify all sample test case properties are presented and correct
 * 5. Delete the sample test case
 * 6. Close Katalon Studio
 */

String sampleTestCaseName = NamingKeyword.generateTestCaseName()
String sampleTestCaseDescription = NamingKeyword.generateTestCaseDescription()
String sampleTestCaseTag = NamingKeyword.generateTestCaseTag()
String sampleTestCaseID = TestCasesKeyword.ROOT_TEST_CASES_FOLDER_NAME + '/' + sampleTestCaseName

if (!GlobalVariable.G_runTestCasesContinuously) {
	Windows.comment('1. Open `Katalon Studio` and wait for project to load')
	Windows.startApplication(GlobalVariable.G_appPath)
	ProjectsKeyword.waitForProjectLoad()
}

Windows.comment('2. Create a sample test case with full properties')
TestCasesKeyword.createTestCase(sampleTestCaseName, sampleTestCaseDescription, sampleTestCaseTag)
MainContentKeyword.closeTabItem(sampleTestCaseName)
TestsExplorerKeyword.openTreeItem(sampleTestCaseName)

Windows.comment('3. View sample test case properties by using Properties tab')
MainContentKeyword.switchViewTab("Properties")

Windows.comment('4. Verify all sample test case properties are presented and correct')
String id = Windows.getText(findWindowsObject('Object Repository/Main Content/Properties Tab/Edit_ID'))
Windows.verifyEqual(id, sampleTestCaseID, FailureHandling.STOP_ON_FAILURE)
//
String name = Windows.getText(findWindowsObject('Object Repository/Main Content/Properties Tab/Edit_Name'))
Windows.verifyEqual(name, sampleTestCaseName, FailureHandling.STOP_ON_FAILURE)
//
String description = Windows.getText(findWindowsObject('Object Repository/Main Content/Properties Tab/Edit_Description'))
Windows.verifyEqual(description, sampleTestCaseDescription, FailureHandling.STOP_ON_FAILURE)
//
String tag = Windows.getText(findWindowsObject('Object Repository/Main Content/Properties Tab/Edit_Tag'))
Windows.verifyEqual(tag, sampleTestCaseTag, FailureHandling.STOP_ON_FAILURE)


Windows.comment('5. Delete the sample test case')
TestsExplorerKeyword.deleteTreeItem(sampleTestCaseName)

if (!GlobalVariable.G_runTestCasesContinuously) {
	Windows.comment('6. Close Katalon Studio')
	MainWindowKeyword.close()
}






