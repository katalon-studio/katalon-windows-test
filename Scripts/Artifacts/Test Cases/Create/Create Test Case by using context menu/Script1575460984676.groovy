import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.katalon.windows_test.components.MainContentKeyword
import com.katalon.windows_test.components.MainWindowKeyword
import com.katalon.windows_test.components.MenubarKeyword
import com.katalon.windows_test.artifacts.ProjectsKeyword
import com.katalon.windows_test.artifacts.TestCasesKeyword
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
import org.testng.TestException
import org.openqa.selenium.WebElement


/**
 * [Script]
 *
 * 1. Open `Katalon Studio` and wait for project to load
 * 2. Open `New` context menu on `Test Cases` folder
 * 3. Select `Test Case`
 * 4. Input test case info & Press `OK`
 * 5. Verify the created test case is existed under Test Cases folder
 * 6. Verify the created test case is opened
 * 7. Delete the created test case
 * 8. Close Katalon Studio
 */

String newTestCaseName = NamingKeyword.generateTestCaseName()

Windows.comment('1. Open `Katalon Studio` and wait for project to load')
Windows.startApplication(GlobalVariable.G_appPath)
ProjectsKeyword.waitForProjectLoad();

Windows.comment('2. Open `New` context menu on `Test Cases` folder')
TestCasesKeyword.openNewContextMenu()

Windows.comment('3. Select `Test Case`')
Windows.click(findWindowsObject('Object Repository/Tests Explorer/Menu/MenuItem_Test_Case'))

Windows.comment('4. Input test case info')
Windows.setText(findWindowsObject('Object Repository/Dialogs/New Test Case/Edit_Name'), newTestCaseName)
Windows.click(findWindowsObject('Object Repository/Dialogs/New Test Case/Button_OK'))

Windows.comment('5. Verify the created test case is existed under Test Cases folder')
WebElement createdTestCaseTreeItem = TestsExplorerKeyword.findTreeItem(newTestCaseName)
WindowsEnhancedKeyword.verifyElementPresent(createdTestCaseTreeItem, FailureHandling.STOP_ON_FAILURE)

Windows.comment('6. Verify the created test case is opened')
WebElement createdTestCaseTab = MainContentKeyword.findTabItem(newTestCaseName)
WindowsEnhancedKeyword.verifyElementPresent(createdTestCaseTab, FailureHandling.STOP_ON_FAILURE)

Windows.comment('7. Delete the created test case')
TestsExplorerKeyword.deleteTreeItem(createdTestCaseTreeItem)

Windows.comment('8. Close Katalon Studio')
MainWindowKeyword.close()




