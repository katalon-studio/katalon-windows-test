import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.katalon.windows_test.components.MainContentKeyword
import com.katalon.windows_test.components.MainWindowKeyword
import com.katalon.windows_test.components.MenubarKeyword
import com.katalon.windows_test.artifacts.ProfilesKeyword
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
 * 2. Open `New` execution profile at `Profiles` folder
 * 3. Input profile info & Press `OK`
 * 5. Verify the created execution profile is existed under Profiles folder
 * 6. Verify the created execution profile is opened
 * 7. Set the created execution profile as default execution profile
 * 8. Verify the created execution profile is default execution profile
 * 9. Set default execution profile as deafult execution profile 
 * 10. Delete the created execution profile
 * 11. Close Katalon Studio
 */

String profileName = ProfilesKeyword.ROOT_PROFILE_FOLDER_NAME
String newProfileName = NamingKeyword.generateProfileName()

if (!GlobalVariable.G_runTestCasesContinuously) {
	Windows.comment('1. Open `Katalon Studio` and wait for project to load')
	Windows.startApplicationWithTitle(GlobalVariable.G_appPath, "Katalon Studio")
	ProjectsKeyword.waitForProjectLoad()
}

Windows.comment('2. Open `New` execution profile at `Profiles` folder')
TestsExplorerKeyword.openContextMenuAtTreeItem(profileName)
WindowsEnhancedKeyword.sendKeys('n')
WindowsEnhancedKeyword.sendKeys('e')

Windows.comment('3. Input execution profile name and press OK')
Windows.clearText(findWindowsObject('Object Repository/Dialogs/New Exectution Profiles/Edit_Name'))
Windows.setText(findWindowsObject('Object Repository/Dialogs/New Exectution Profiles/Edit_Name'), newProfileName)
Windows.click(findWindowsObject('Object Repository/Dialogs/New Exectution Profiles/Button_OK'))

Windows.comment('5. Verify the created execution profile is existed under Profiles folder')
Windows.sleep(1000L)
WebElement createdProfileTreeItem = TestsExplorerKeyword.findTreeItem(newProfileName)
WindowsEnhancedKeyword.verifyElementPresent(createdProfileTreeItem, FailureHandling.STOP_ON_FAILURE)

Windows.comment('6. Verify the created execution profile is opened')
WebElement createdProfileTab = MainContentKeyword.findTabItem(newProfileName)
WindowsEnhancedKeyword.verifyElementPresent(createdProfileTab, FailureHandling.STOP_ON_FAILURE)

Windows.comment('7. Set the created execution profile as default execution profile')
TestsExplorerKeyword.openContextMenuAtTreeItem(newProfileName)
WindowsEnhancedKeyword.sendKeys('s')

Windows.comment('8. Verify the created execution profile is default execution profile')
String defaultEecutionProfileName=TestsExplorerKeyword.getFirstChildItem(profileName).getText()
Windows.comment(defaultEecutionProfileName)
Windows.verifyEqual(defaultEecutionProfileName, newProfileName, FailureHandling.STOP_ON_FAILURE)

Windows.comment('9. Set the created execution profile as default execution profile')
TestsExplorerKeyword.openContextMenuAtTreeItem('default')
WindowsEnhancedKeyword.sendKeys('s')

Windows.comment('10. Delete the created execution profile')
TestsExplorerKeyword.deleteTreeItem(newProfileName)

if (!GlobalVariable.G_runTestCasesContinuously) {
	Windows.comment('11. Close Katalon Studio')
	MainWindowKeyword.close()
}
