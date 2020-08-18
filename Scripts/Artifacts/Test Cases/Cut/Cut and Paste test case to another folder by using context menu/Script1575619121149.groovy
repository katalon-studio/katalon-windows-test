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
 * 4. Cut the sample test case by using context menu (Right click -> Cut)
 * 5. Paste the cut test case to the sample folder by using context menu (Right click -> Paste)
 * 6. Verify the sample test case is disappeared from the original folder
 * 7. Verify the sample test case is appeared under the sample folder
 * 8. Delete the sample folder
 * 9. Close Katalon Studio
 */

String sampleTestCaseName = NamingKeyword.generateTestCaseName()
String sampleFolderName = NamingKeyword.generateTestCaseFolderName()

Windows.comment('2. Create a sample test case')
TestCasesKeyword.createTestCase(sampleTestCaseName)

Windows.comment('3. Create a sample folder')
TestCasesKeyword.createFolder(sampleFolderName)

Windows.comment('4. Cut the sample test case by using context menu (Right click -> Cut)')
TestsExplorerKeyword.openContextMenuAtTreeItem(sampleTestCaseName)
WindowsEnhancedKeyword.sendKeys('ccc')
WindowsEnhancedKeyword.sendKeys(Keys.ENTER)

Windows.comment('5. Paste the cut test case to the sample folder by using context menu (Right click -> Paste)')
TestsExplorerKeyword.openContextMenuAtTreeItem(sampleFolderName)
WindowsEnhancedKeyword.sendKeys('p')
WindowsEnhancedKeyword.sendKeys(Keys.ENTER)

Windows.comment('6. Verify the sample test case is disappeared from the original folder')
Windows.sleep(1000L)
WebElement originalTestCase = TestsExplorerKeyword.findTreeItem(sampleTestCaseName, TestsExplorerKeyword.TESTS_EXPLORER_NAME)
WindowsEnhancedKeyword.verifyElementNotPresent(originalTestCase, FailureHandling.STOP_ON_FAILURE)

Windows.comment('7. Verify the sample test case is appeared under the sample folder')
WebElement movedTestCase = TestsExplorerKeyword.findTreeItem(sampleTestCaseName, sampleFolderName)
WindowsEnhancedKeyword.verifyElementPresent(movedTestCase, FailureHandling.STOP_ON_FAILURE)

Windows.comment('8. Delete the sample folder')
TestsExplorerKeyword.deleteTreeItem(sampleFolderName)

if (!GlobalVariable.G_runTestCasesContinuously) {
	Windows.comment('9. Close Katalon Studio')
	MainWindowKeyword.close()
}



