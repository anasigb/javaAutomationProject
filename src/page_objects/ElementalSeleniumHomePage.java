/**
 * 
 */
package page_objects;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author anas.igbariah
 *
 */
public class ElementalSeleniumHomePage {
	private ChromeDriver driver;
	private String url = "http://the-internet.herokuapp.com/dynamic_loading/2";
	
	String PageContainer = "div.example";
	String StartButton = "div#start button";
	String greetingElementSelector = "div#finish h4";
	
	public ElementalSeleniumHomePage() {
		System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver.exe");
		driver = new ChromeDriver() ;
	}

	public void load() {
		driver.get(url);
		(new WebDriverWait(driver, 3))
		  .until(ExpectedConditions.presenceOfElementLocated(getSelector(PageContainer)));
	}
	
	public By getSelector(String selector) {
		return By.cssSelector(selector);
	}
	
	public void clickStartButton() {
		driver.findElement(getSelector(StartButton)).click();
	}
	
	public boolean isAjaxCompleted() {
		boolean isAjaxFinished = false;
		isAjaxFinished = (boolean) ((JavascriptExecutor) driver).executeScript("return jQuery.active == 0");
		return isAjaxFinished;
	}
	
	public boolean greetingElementAppeared(int timeout){
		By selector = getSelector(greetingElementSelector);
		try {
			WebElement element = (new WebDriverWait(driver, timeout))
					  .until(ExpectedConditions.presenceOfElementLocated(selector));
			return  element != null;
		}
		catch(Exception e){
			captureScreenshot();
		}
		return false;
	}
		
	public boolean verifyWorldGreetingtype(String sentence) {
		By selector = getSelector(greetingElementSelector);
			WebElement element = driver.findElement(selector);
		if (element.getText().compareTo(sentence) == 0)
				return true;
		else {
			highlightElement(greetingElementSelector);
			captureScreenshot();
			return false;
		}
	}

	public void captureScreenshot() {
		try {
			TakesScreenshot ts = (TakesScreenshot)driver;
			File screenshotFile = ts.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshotFile, new File("./failed_tests_screenshots/failed_test.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public WebElement highlightElement(String selectorName) {
		By selector = getSelector(selectorName);
	    WebElement elem = driver.findElement(selector);
	    if (driver instanceof JavascriptExecutor) {
	        ((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid red'", elem);
	    }
	    return elem;
	}

	public void exit() {
		driver.quit();
	}
}
