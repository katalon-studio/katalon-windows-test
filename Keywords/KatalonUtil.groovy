import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable
import org.openqa.selenium.WebElement

public class KatalonUtil {
	private static final String TESTS_EXPLORER_NAME = "Tests Explorer";

	public static WebElement focusToTestsExplorerTreeItem(String treeItemName) {
		String treeItemXPath = String.format('//Pane[@Name="%s"]//TreeItem[@Name="%s"]',
				TESTS_EXPLORER_NAME, treeItemName);
		return Windows.getDriver().findElementByXPath(treeItemXPath);
	}
	
	public static void openNewFileMenu() {
		Windows.click(findWindowsObject('Object Repository/MenuBar/File'));
		Windows.click(findWindowsObject('Object Repository/MenuBar/File_New'));
	}
	
	public static void createFolderAtFocusedFolder(String testCaseName) {
		openNewFileMenu();
		Windows.click(findWindowsObject('Object Repository/MenuBar/File_New_Folder'));
		Windows.setText(findWindowsObject('Object Repository/Dialogs/New Test Case/Name'), testCaseName);
		Windows.click(findWindowsObject('Object Repository/Dialogs/New Test Case/OK'))
	}
}
