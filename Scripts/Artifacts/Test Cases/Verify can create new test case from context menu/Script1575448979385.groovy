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
import org.openqa.selenium.Keys as Keys

/**
 * [Script]
 * 
 * 1. 
 */


String newTestCaseName = UUID.randomUUID().toString()

Windows.startApplication(GlobalVariable.G_appPath)

Thread.sleep(3000)

Windows.click(findWindowsObject('Object Repository/Dialogs/Plugins/Close'))

Windows.click(findWindowsObject('Object Repository/MenuBar/File'))

Windows.click(findWindowsObject('Object Repository/MenuBar/File_New'))

Windows.click(findWindowsObject('Object Repository/MenuBar/File_New_TestCase'))

Windows.setText(findWindowsObject('Object Repository/Dialogs/New Test Case/Name'), newTestCaseName)

Windows.click(findWindowsObject('Object Repository/Dialogs/New Test Case/OK'))

//newTestCase = Windows.findElement(newTestCaseName)
//assert newTestCase != null

Windows.closeApplication()




