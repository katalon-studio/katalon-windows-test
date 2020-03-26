import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.katalon.windows_test.artifacts.ProfilesKeyword
import com.katalon.windows_test.artifacts.ProjectsKeyword
import com.katalon.windows_test.components.MainWindowKeyword
import com.katalon.windows_test.components.TestsExplorerKeyword
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

import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions

/* Script
 * 1. Open `Katalon Studio` and wait for project to load
 * 2. Open context menu at treeitem name default
 * 3. Get menu item rename
 * 4. Verify menu item rename is disable
 * 5. Close Katalon Studio
*/

String defaultProfileName = ProfilesKeyword.DEFAULT_PROFILE_NAME

if (!GlobalVariable.G_runTestCasesContinuously) {
	Windows.comment('1. Open `Katalon Studio` and wait for project to load')
	Windows.startApplication(GlobalVariable.G_appPath)
	ProjectsKeyword.waitForProjectLoad()
}

Windows.comment('2. Open context menu at treeitem name default')
//TestsExplorerKeyword.openTreeItem(ProfilesKeyword.ROOT_PROFILE_FOLDER_NAME)
TestsExplorerKeyword.openContextMenuAtTreeItem(defaultProfileName)

Windows.comment('3. Get menu item rename')
WebElement renameElement = TestsExplorerKeyword.findContextMenuItem('Rename')

Windows.comment('4. Verify menu item rename is disable')
Windows.verifyEqual(false, renameElement.isEnabled())

if(!GlobalVariable.G_runTestCasesContinuously){
	Windows.comment('5. Close Katalon Studio')
	MainWindowKeyword.close()
}