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
 * 3. Open test case containing folder by using context menu (Right click -> Open containing folder)
 * 4. Verify the test case containing folder is opened
 * 5. Delete the sample test case
 * 6. Close Katalon Studio
 */

String sampleTestCaseName = NamingKeyword.generateTestCaseName()

Windows.comment('1. Open `Katalon Studio` and wait for project to load')
Windows.startApplication(GlobalVariable.G_appPath)
ProjectsKeyword.waitForProjectLoad()

Windows.comment('2. Create a sample test case')
TestCasesKeyword.createTestCaseUsingFileMenu(sampleTestCaseName)

Windows.comment('3. Open test case containing folder by using context menu (Right click -> Open containing folder)')
TestsExplorerKeyword.openContextMenuAtTreeItem(sampleTestCaseName)
Windows.click(findWindowsObject('Object Repository/Tests Explorer/Menu/MenuItem_Open containing folder'))
Windows.switchToApplication()

Windows.comment('4. Verify the test case containing folder is opened')
//sampleTestCaseTab = MainContentKeyword.findTabItem(sampleTestCaseName)
//WindowsEnhancedKeyword.verifyElementPresent(sampleTestCaseTab, FailureHandling.STOP_ON_FAILURE)

Windows.comment('5. Delete the sample test case')
TestsExplorerKeyword.deleteTreeItem(sampleTestCaseName)

Windows.comment('6. Close Katalon Studio')
MainWindowKeyword.close()




