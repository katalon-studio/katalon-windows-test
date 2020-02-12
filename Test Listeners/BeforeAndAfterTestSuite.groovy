import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.katalon.windows_test.artifacts.ProjectsKeyword
import com.katalon.windows_test.components.MainWindowKeyword
import com.katalon.windows_test.components.WelcomeKeyword
import com.katalon.windows_test.keywords.AuthKeyword
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject

import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable as GlobalVariable

import com.kms.katalon.core.annotation.BeforeTestCase
import com.kms.katalon.core.annotation.BeforeTestSuite
import com.kms.katalon.core.annotation.AfterTestCase
import com.kms.katalon.core.annotation.AfterTestSuite
import com.kms.katalon.core.context.TestCaseContext
import com.kms.katalon.core.context.TestSuiteContext

class BeforeAndAfterTestSuite {
	/**
	 * Executes before every test suite starts.
	 * @param testSuiteContext: related information of the executed test suite.
	 */
	@BeforeTestSuite
	def BeforeTestSuite(TestSuiteContext testSuiteContext) {
		GlobalVariable.G_runTestCasesContinuously = true
		if (!GlobalVariable.G_runTestSuitesContinuously) {
			Windows.startApplicationWithTitle(GlobalVariable.G_appPath, "Katalon Studio")
			boolean isFirstLogin = AuthKeyword.login(GlobalVariable.G_username, GlobalVariable.G_password)
			if (isFirstLogin) {
				Windows.sleep(10000L)
				WelcomeKeyword.skipWelcomeDialog()
			}
			ProjectsKeyword.openProjectOrDefault(GlobalVariable.G_projectLocation)
			ProjectsKeyword.waitForProjectLoad()
		}
	}

	/**
	 * Executes after every test suite ends.
	 * @param testSuiteContext: related information of the executed test suite.
	 */
	@AfterTestSuite
	def AfterTestSuite(TestSuiteContext testSuiteContext) {
		if (!GlobalVariable.G_runTestSuitesContinuously) {
			ProjectsKeyword.closeAndCleanProject()
			AuthKeyword.deactive()
			Windows.sleep(10000L)
			MainWindowKeyword.closeByClickCloseActivation()
		}
	}
}