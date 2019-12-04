package com.katalon.windows_test.keywords

import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.WindowsTestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.windows.constants.WindowsDriverConstants
import com.kms.katalon.core.windows.driver.WindowsDriverFactory
import com.kms.katalon.core.windows.keyword.helper.WindowsActionHelper
import org.openqa.selenium.WebElement

import io.appium.java_client.windows.WindowsDriver

public class WindowsEnhancedKeyword {
	public static final String DESIRED_CAPABILITIES_PROPERTY = "desiredCapabilities";

	public static final String WIN_APP_DRIVER_PROPERTY = "winAppDriverUrl";

	def static boolean verifyElementPresent(WindowsTestObject windowsObject, FailureHandling flowControl) {
		try {
			assert WindowsActionHelper.create(WindowsDriverFactory.getWindowsSession()).findElements(windowsObject).size() > 0
			KeywordUtil.markPassed('Element presents')
		} catch (Throwable e) {
			if (flowControl == FailureHandling.OPTIONAL) {
				KeywordUtil.markWarning("Element does not present")
			} else if (flowControl == FailureHandling.CONTINUE_ON_FAILURE) {
				KeywordUtil.markFailed("Element does not present")
			} else {
				KeywordUtil.markFailedAndStop("Element does not present")
			}
		}
	}

	def static boolean verifyElementNotPresent(WindowsTestObject windowsObject, FailureHandling flowControl) {
		try {
			assert WindowsActionHelper.create(WindowsDriverFactory.getWindowsSession()).findElements(windowsObject).size() == 0
			KeywordUtil.markPassed('Element does not present')
		} catch (Throwable e) {
			if (flowControl == FailureHandling.OPTIONAL) {
				KeywordUtil.markWarning("Element presents")
			} else if (flowControl == FailureHandling.CONTINUE_ON_FAILURE) {
				KeywordUtil.markFailed("Element presents")
			} else {
				KeywordUtil.markFailedAndStop("Element presents")
			}
		}
	}

	def static boolean verifyElementPresent(WebElement windowsObject, FailureHandling flowControl) {
		def isPassed = windowsObject != null;
		if (flowControl == FailureHandling.STOP_ON_FAILURE) {
			assert isPassed == true;
		}
		if (isPassed) {
			KeywordUtil.markPassed('Element present');
		} else {
			KeywordUtil.markFailed('Element not present');
		}
		return isPassed;
	}

	def static void pressKey(String keys) {
		WindowsDriver driver = WindowsDriverFactory.getWindowsDriver()
		driver.getKeyboard().pressKey(keys)
	}

	def static void cleanAllSessions() {
		Map<String, Object> userConfigProperties = RunConfiguration.getDriverPreferencesProperties("Windows");
		if (userConfigProperties == null) {
			userConfigProperties = new HashMap<String, Object>();
		}

		String remoteAddressURLAsString = (String) userConfigProperties.getOrDefault(WIN_APP_DRIVER_PROPERTY,
				WindowsDriverConstants.DEFAULT_WIN_APP_DRIVER_URL);
	}
}
