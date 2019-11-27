import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import org.openqa.selenium.Keys

import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable as GlobalVariable

Windows.startApplication(GlobalVariable.G_appPath)

Windows.delay(GlobalVariable.G_longTimeout)

Windows.sendKeys(findWindowsObject("Object Repository/MenuBar/MenuItem_File"), Keys.chord(Keys.CONTROL + "O"))

Windows.clearText(findWindowsObject("Object Repository/Windows/Browser For Folder/Edit_Folder_Path"))

Windows.setText(findWindowsObject("Object Repository/Windows/Browser For Folder/Edit_Folder_Path"), GlobalVariable.G_projectLocation)

Windows.click(findWindowsObject("Object Repository/Windows/Browser For Folder/Button_OK"))

Windows.delay(GlobalVariable.G_longTimeout)

String text = Windows.getText(findWindowsObject("Object Repository/Windows/Window_Application"))

assert text.contains(GlobalVariable.G_projectLocation)