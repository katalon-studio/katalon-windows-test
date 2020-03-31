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

import com.katalon.windows_test.components.MainContentKeyword
import com.katalon.windows_test.components.MainWindowKeyword
import com.katalon.windows_test.components.MenubarKeyword
import com.katalon.windows_test.artifacts.ProjectsKeyword
import com.katalon.windows_test.artifacts.TestDataKeyword
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
 * 2. Open `New Test Data` dialog by using File menu
 * 3. Input test data info & Press `OK`
 * 4. Verify the created test data is existed under Data Files folder
 * 5. Verify the created test data is opened
 * 6. Delete the created test data
 * 7. Close Katalon Studio
 */

String newTestDataName = NamingKeyword.generateTestDataName()
String newTestDataDescription = NamingKeyword.generateTestDataDescription()


if (!GlobalVariable.G_runTestCasesContinuously) {
	Windows.comment('1. Open `Katalon Studio` and wait for project to load')
	Windows.startApplicationWithTitle(GlobalVariable.G_appPath, "Katalon Studio")
	ProjectsKeyword.waitForProjectLoad()
}

Windows.comment('2. Open New Test Data dialog by using File menu')
Windows.sleep(1000L)
TestsExplorerKeyword.openContextMenuAtTreeItem(TestDataKeyword.ROOT_DATA_FILE_FOLDER_NAME)
MenubarKeyword.openNewMenu()
WindowsEnhancedKeyword.sendKeys('t')
WindowsEnhancedKeyword.sendKeys('t')
WindowsEnhancedKeyword.sendKeys('t')
WindowsEnhancedKeyword.sendKeys(Keys.ENTER)

Windows.comment('3. Input test Data inof and press OK')
Windows.sleep(1000L)
Windows.clearText(findWindowsObject('Object Repository/Dialogs/New Test Data/Edit_Name'))
Windows.setText(findWindowsObject('Object Repository/Dialogs/New Test Data/Edit_Name'), newTestDataName)
Windows.clearText(findWindowsObject('Object Repository/Dialogs/New Test Data/Edit_Description'))
Windows.setText(findWindowsObject('Object Repository/Dialogs/New Test Data/Edit_Description'), newTestDataDescription)
Windows.click(findWindowsObject('Object Repository/Dialogs/New Test Data/Button_OK'));

Windows.comment('4. Verify the created test Data is existed under Test Data folder')
Windows.sleep(1000L)
WebElement createdTestDataTreeItem = TestsExplorerKeyword.findTreeItem(newTestDataName)
WindowsEnhancedKeyword.verifyElementPresent(createdTestDataTreeItem, FailureHandling.STOP_ON_FAILURE)

Windows.comment('5. Verify the created test data is opened')
Windows.sleep(1000L)
WebElement createdTestDataTab = MainContentKeyword.findTabItem(newTestDataName)
WindowsEnhancedKeyword.verifyElementPresent(createdTestDataTab, FailureHandling.STOP_ON_FAILURE)

Windows.comment('6. Delete created test Data')
TestsExplorerKeyword.deleteTreeItem(newTestDataName)

if(!GlobalVariable.G_runTestCasesContinuously){
	Windows.comment('7. Close Katalon Studio')
	MainWindowKeyword.close()
}
