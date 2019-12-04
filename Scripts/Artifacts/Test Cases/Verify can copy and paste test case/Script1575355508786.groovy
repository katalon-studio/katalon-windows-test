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
 * 1. Create 1 test case in the root folder (Test Cases)
 * 2. Verify if the test case are created successfully
 * 3. Copy the test case from folder 1 to folder 2
 * 4. Verify if 
 */

String firstFolderName = UUID.randomUUID().toString();
String secondFolderName = UUID.randomUUID().toString();

Windows.startApplication(GlobalVariable.G_appPath)

Thread.sleep(GlobalVariable.G_longTimeout)

Windows.click(findWindowsObject('Object Repository/Dialogs/Plugins/Close'))

WindowsTestObject firstTestCase = findWindowsObject('Object Repository/Tests Explorer/FirstTestCase')

Windows.click(firstTestCase)

Windows.sendKeys(firstTestCase, Keys.chord(Keys.CONTROL, 'c'))

Windows.sendKeys(firstTestCase, Keys.chord(Keys.CONTROL, 'v'))

//String clonedTestCaseName = firstTestCase.getAttribute('Name') + ' - Copy'
//clonedTestCase = Windows.findElement(clonedTestCaseName)
//assert clonedTestCase != null

Windows.closeApplication()



