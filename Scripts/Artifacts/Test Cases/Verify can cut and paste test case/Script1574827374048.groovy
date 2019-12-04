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

/* [Script]
 * 1. Prepare 2 folder & 1 test case
 * 2. Cut a test case from folder 1
 * 3. Paste the test case into folder 2
 * 4. Verify:
 *    - The test case is disapeared in folder 1
 *    - The test case is appreared in folder 2
 * 5. Cut the test case in folder 2 and paste back to folder 1 
 * */

Windows.startApplication(GlobalVariable.G_appPath)

Thread.sleep(3000)

Windows.click(findWindowsObject('Object Repository/Dialogs/Plugins/Close'))

WindowsTestObject firstTestCase = findWindowsObject('Object Repository/Tests Explorer/FirstTestCase')

Windows.click(firstTestCase)

Windows.sendKeys(findWindowsObject('Object Repository/Tests Explorer/FirstTestCase'), Keys.chord(Keys.CONTROL, 'x'))

Windows.sendKeys(findWindowsObject('Object Repository/Tests Explorer/FirstTestCase'), Keys.chord(Keys.CONTROL, 'v'))

//String pastedTestCaseName = firstTestCase.getAttribute('Name')
//pastedTestCase = Windows.findElement(pastedTestCaseName)
//assert pastedtestCase != null

Windows.closeApplication()