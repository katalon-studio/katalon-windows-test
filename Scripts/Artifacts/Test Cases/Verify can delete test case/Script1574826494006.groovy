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

Windows.startApplication(GlobalVariable.G_appPath)

Thread.sleep(3000)

Windows.click(findWindowsObject('Object Repository/Dialogs/Plugins/Close'))

WindowsTestObject firstFolderHasTestCase = findWindowsObject('Object Repository/Tests Explorer/firstFolderHasTestCase')

WindowsTestObject firstTestCase = findWindowsObject('Object Repository/Tests Explorer/FirstTestCase')

Windows.click(firstTestCase)

Windows.sendKeys(firstTestCase, Keys.chord(Keys.CONTROL, 'c'))

Windows.sendKeys(firstTestCase, Keys.DELETE)

//WindowsTestObject deletedTestCase = Windows.findElement(firstTestCase)
//assert  deletedTestCase == null

Windows.sendKeys(firstTestCase, Keys.chord(Keys.CONTROL, 'v')) // Restore deleted test case

Windows.closeApplication()




