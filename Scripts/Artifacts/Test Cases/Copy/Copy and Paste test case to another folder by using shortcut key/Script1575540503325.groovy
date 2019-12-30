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
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.WebElement


/**
 * [Script]
 *
 * 1. Open `Katalon Studio` and wait for project to load
 * 2. Create a sample test case
 * 3. Create a sample folder
 * 4. Copy the created test case by using shortcut key [Ctrl + C]
 * 5. Paste the copied test case to the sample folder by using shortcut key [Ctrl + V]
 * 6. Verify the cloned test case is appeared under the sample folder
 * 7. Delete both sample test case and sample folder
 * 8. Close Katalon Studio
 */

String sampleTestCaseName = NamingKeyword.generateTestCaseName()
String sampleFolderName = NamingKeyword.generateTestCaseFolderName()

if (!GlobalVariable.G_runTestCasesContinuously) {
	Windows.comment('1. Open `Katalon Studio` and wait for project to load')
	Windows.startApplication(GlobalVariable.G_appPath)
	ProjectsKeyword.waitForProjectLoad()
}

Windows.comment('2. Create a sample test case')
TestCasesKeyword.createTestCase(sampleTestCaseName)

Windows.comment('3. Create a sample folder')
TestCasesKeyword.createFolder(sampleFolderName)

Windows.comment('4. Copy the created test case by using shortcut key [Ctrl + C]')
WebElement sampleTestCase = TestsExplorerKeyword.findTreeItem(sampleTestCaseName)
sampleTestCase.sendKeys(Keys.chord(Keys.CONTROL, 'c'))

Windows.comment('5. Paste the copied test case to the sample folder by using shortcut key [Ctrl + V]')
WebElement sampleFolder = TestsExplorerKeyword.findTreeItem(sampleFolderName)
sampleFolder.sendKeys(Keys.chord(Keys.CONTROL, 'v'))

Windows.comment('6. Verify the cloned test case is appeared under the sample folder')
Windows.sleep(1000L)
WebElement clonedTestCase = TestsExplorerKeyword.findTreeItem(sampleTestCaseName, sampleFolderName)
WindowsEnhancedKeyword.verifyElementPresent(clonedTestCase, FailureHandling.STOP_ON_FAILURE)

Windows.comment('7. Delete both sample test case and sample folder')
TestsExplorerKeyword.deleteTreeItem(sampleFolderName)
Windows.sleep(1000L)
TestsExplorerKeyword.deleteTreeItem(sampleTestCaseName)

if (!GlobalVariable.G_runTestCasesContinuously) {
	Windows.comment('8. Close Katalon Studio')
	MainWindowKeyword.close()
}


