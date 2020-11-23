import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import org.openqa.selenium.Keys

import com.katalon.windows_test.artifacts.ProfilesKeyword
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

/*
 * Script
 * 
 * 1. Open `Katalon Studio` and wait for project to load
 * 2. Create new execution profile
 * 3. Open rename dialog by using context menu (Right click -> Rename)
 * 4. Input new execution profile name
 * 5. Verify the sample execution profile tree item name is changed
 * 6. Verify the sample execution profile tab item name is changed
 * 7. Delete the sample execution profile 
 * 8. Close Katalon Studio
 */

String sampleExecutionProfileName = NamingKeyword.generateProfileName()
String newExecutionProfileName = NamingKeyword.generateProfileName()

if(!GlobalVariable.G_runTestCasesContinuously){
	Windows.comment('1. Open `Katalon Studio` and wait for project to load')
	Windows.startApplication(GlobalVariable.G_appPath)
	ProjectsKeyword.waitForProjectLoad()
}

Windows.comment('2. Create new execution profile')
ProfilesKeyword.createProfile(sampleExecutionProfileName)

Windows.comment('3. Open rename dialog by using context menu (Right click -> Rename)')
TestsExplorerKeyword.openContextMenuAtTreeItem(sampleExecutionProfileName)
WindowsEnhancedKeyword.sendKeys('r')
WindowsEnhancedKeyword.sendKeys(Keys.ENTER)

Windows.comment('4. Input new execution profile name')
Windows.clearText(findWindowsObject('Object Repository/Dialogs/Rename Execution Profile/Edit_Name'))
Windows.setText(findWindowsObject('Object Repository/Dialogs/Rename Execution Profile/Edit_Name'), newExecutionProfileName)
Windows.click(findWindowsObject('Object Repository/Dialogs/Rename Execution Profile/Button_OK'))

Windows.comment('5. Verify the new execution tree item name is changed')
Windows.sleep(1000L)
WebElement newExecutionProfileTreeItem = TestsExplorerKeyword.findTreeItem(newExecutionProfileName)
WindowsEnhancedKeyword.verifyElementPresent(newExecutionProfileTreeItem, FailureHandling.STOP_ON_FAILURE)

//Windows.comment('6. Verify the stab item name new execution profile is changed')
//WebElement newTestCaseTabItem = MainContentKeyword.findTabItem(newExecutionProfileName)
//WindowsEnhancedKeyword.verifyElementPresent(newTestCaseTabItem, FailureHandling.STOP_ON_FAILURE)

Windows.comment('7. Delete the new execution profile')
TestsExplorerKeyword.deleteTreeItem(newExecutionProfileName)

if (!GlobalVariable.G_runTestCasesContinuously) {
	Windows.comment('8. Close Katalon Studio')
	MainWindowKeyword.close()
}


