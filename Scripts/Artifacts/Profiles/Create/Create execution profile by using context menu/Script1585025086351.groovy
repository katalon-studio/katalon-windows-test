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
 * 3. Input execution profile info & Press `OK`
 * 4. Verify the created execution profile is existed under Profiles folder
 * 5. Verify the created execution profile is opened
 * 6. Delete the created execution profile
 * 7. Close Katalon Studio
 */

String newProfileName = NamingKeyword.generateProfileName()

if (!GlobalVariable.G_runTestCasesContinuously) {
	Windows.comment('1. Open `Katalon Studio` and wait for project to load')
	Windows.startApplicationWithTitle(GlobalVariable.G_appPath, "Katalon Studio")
	ProjectsKeyword.waitForProjectLoad()
}

Windows.comment('2. Open `New` execution profile at `Profiles` folder')
TestsExplorerKeyword.openNewContextMenuAtTreeItem(ProfilesKeyword.ROOT_PROFILE_FOLDER_NAME)
WindowsEnhancedKeyword.sendKeys('e')

Windows.comment('3. Input execution profile name and press OK')
Windows.clearText(findWindowsObject('Object Repository/Dialogs/New Exectution Profiles/Edit_Name'))
Windows.setText(findWindowsObject('Object Repository/Dialogs/New Exectution Profiles/Edit_Name'), newProfileName)
Windows.click(findWindowsObject('Object Repository/Dialogs/New Exectution Profiles/Button_OK'))

Windows.comment('4. Verify the created execution profile is existed under Profiles folder')
Windows.sleep(1000L)
WebElement createdProfileTreeItem = TestsExplorerKeyword.findTreeItem(newProfileName)
WindowsEnhancedKeyword.verifyElementPresent(createdProfileTreeItem, FailureHandling.STOP_ON_FAILURE)

Windows.comment('5. Verify the created execution profile is opened')
WebElement createdProfileTab = MainContentKeyword.findTabItem(newProfileName)
WindowsEnhancedKeyword.verifyElementPresent(createdProfileTab, FailureHandling.STOP_ON_FAILURE)

Windows.comment('6. Delete the created execution profile')
TestsExplorerKeyword.deleteTreeItem(newProfileName)

if (!GlobalVariable.G_runTestCasesContinuously) {
	Windows.comment('7. Close Katalon Studio')
	MainWindowKeyword.close()
}




