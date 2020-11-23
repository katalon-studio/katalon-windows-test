import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

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
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.WebElement


/**
 * [Script]
 *
 * 1. Open `Katalon Studio` and wait for project to load
 * 2. Create a sample profile
 * 3. Delete the sample execution profile by using shortcut
 * 4. Verify the sample execution profile is removed from Profiles tree
 * 5. Verify the sample execution profile is closed
 * 6. Close Katalon Studio
 */

String newExecutionProfileName = NamingKeyword.generateProfileName()

if (!GlobalVariable.G_runTestCasesContinuously) {
	Windows.comment('1. Open `Katalon Studio` and wait for project to load')
	Windows.startApplication(GlobalVariable.G_appPath)
	ProjectsKeyword.waitForProjectLoad()
}

Windows.comment('2. Create a sample profile')
ProfilesKeyword.createProfile(newExecutionProfileName)

Windows.comment('3. Delete the sample execution profile by using shortcut')
Windows.sleep(1000L)
WebElement createdTestCaseTreeItem = TestsExplorerKeyword.findTreeItem(newExecutionProfileName)
createdTestCaseTreeItem.click()
createdTestCaseTreeItem.sendKeys(Keys.DELETE)
WindowsEnhancedKeyword.sendKeys(Keys.ENTER)

Windows.comment('4. Verify the sample execution profile is removed from Profiles tree')
Windows.sleep(1000L)
WebElement deletedExecutionProfileTreeItem = TestsExplorerKeyword.findTreeItem(newExecutionProfileName)
WindowsEnhancedKeyword.verifyElementNotPresent(deletedExecutionProfileTreeItem, FailureHandling.STOP_ON_FAILURE)

//Windows.comment('5. Verify the sample execution profile is closed')
//Windows.sleep(1000L)
//WebElement deletedExecutionProfileTab = MainContentKeyword.findTabItem(newExecutionProfileName)
//WindowsEnhancedKeyword.verifyElementNotPresent(deletedExecutionProfileTab, FailureHandling.STOP_ON_FAILURE)


if (!GlobalVariable.G_runTestCasesContinuously) {
	Windows.comment('6. Close Katalon Studio')
	MainWindowKeyword.close()
}

