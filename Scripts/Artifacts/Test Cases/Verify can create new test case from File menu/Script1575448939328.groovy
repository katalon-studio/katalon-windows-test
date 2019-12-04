import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.katalon.windows_test.keywords.KatalonWindowKeyword
import com.katalon.windows_test.keywords.MenubarKeyword
import com.katalon.windows_test.keywords.ProjectsKeyword
import com.katalon.windows_test.keywords.TestsExplorerKeyword
import com.katalon.windows_test.keywords.WindowsEnhancedKeyword
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
 * 1. Open `New File` menu
 * 2. Select `Test Case`
 * 3. Input test case info & Press `OK`
 * 4. Verify the created test case is existed under Test Cases folder
 * 5. Verify the created test case is opened
 * 6. Delete the created test case
 */

String newTestCaseName = UUID.randomUUID().toString()

Windows.startApplication(GlobalVariable.G_appPath)

ProjectsKeyword.waitForProjectLoad();

Windows.comment('1. Open `New File` menu')
MenubarKeyword.openNewMenu();

Windows.comment('2. Select `Test Case`')
Windows.click(findWindowsObject('Object Repository/MenuBar/File_New_TestCase'))

Windows.comment('3. Input test case info')
Windows.setText(findWindowsObject('Object Repository/Dialogs/New Test Case/Name'), newTestCaseName)
Windows.click(findWindowsObject('Object Repository/Dialogs/New Test Case/OK'))

Windows.comment('4. Verify the created test case is existed under Test Cases folder')
WebElement createdTestCaseTreeItem = TestsExplorerKeyword.findTreeItem(newTestCaseName)
WindowsEnhancedKeyword.verifyElementPresent(createdTestCaseTreeItem, FailureHandling.STOP_ON_FAILURE)

Windows.comment('5. Verify the created test case is opened')
def createdTestCaseTab = TestsExplorerKeyword.findTreeItem(newTestCaseName);
WindowsEnhancedKeyword.verifyElementPresent(createdTestCaseTab, FailureHandling.STOP_ON_FAILURE);

Windows.comment('6. Delete the created test case')
createdTestCaseTreeItem.click()
createdTestCaseTreeItem.sendKeys(Keys.DELETE)

KatalonWindowKeyword.closeKatalon()






