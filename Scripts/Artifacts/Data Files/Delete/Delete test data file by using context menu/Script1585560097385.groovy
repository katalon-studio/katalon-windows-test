import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import org.openqa.selenium.Keys
import org.openqa.selenium.WebElement

import com.katalon.windows_test.artifacts.ProjectsKeyword
import com.katalon.windows_test.artifacts.TestDataKeyword
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
 * Script
 * 
 * 1. Open 'Katalon Studio' and wait for project to load
 * 2. Create new test data 
 * 3. Delete the new test data by using context menu [Right click -> Delete]
 * 4. Verify the new test data is deleted
 * 5. Verify the new test data tab is closed
 * 6. Close Katalon Studio
 */

String newTestDataName = NamingKeyword.generateTestDataName()

if(!GlobalVariable.G_runTestCasesContinuously){
	Windows.comment('1. Open Katalon Studio and wait for project to load')
	Windows.startApplicationWithTitle(GlobalVariable.G_appPath, 'Katalon Studio')
	ProjectsKeyword.waitForProjectLoad()
}

Windows.comment('2. Create new test data')
Windows.sleep(1000L)
TestDataKeyword.createDataFile(newTestDataName)

Windows.comment('3. Delete the new test data by using context menu [Right click ->Delete]')
TestsExplorerKeyword.openContextMenuAtTreeItem(newTestDataName)
WindowsEnhancedKeyword.sendKeys('d')
WindowsEnhancedKeyword.sendKeys(Keys.ENTER)

Windows.comment('4. Verify the new test data is delete')
Windows.sleep(1000L)
WebElement newTestDataTreeItem = TestsExplorerKeyword.findTreeItem(newTestDataName)
WindowsEnhancedKeyword.verifyElementNotPresent(newTestDataTreeItem, FailureHandling.STOP_ON_FAILURE)

//Windows.comment('5. Verify the new test data tab is closed')
//WebElement newTestDataTab = MainContentKeyword.findTabItem(newTestDataName)
//WindowsEnhancedKeyword.verifyElementNotPresent(newTestDataTab, FailureHandling.STOP_ON_FAILURE)

if(!GlobalVariable.G_runTestCasesContinuously){
	Windows.comment('6. Close Katalon Studio')
	MainWindowKeyword.close()
}