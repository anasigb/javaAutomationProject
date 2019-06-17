package selenium_tests;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import page_objects.ElementalSeleniumHomePage;

public class ElementalPageTests {
	public ElementalSeleniumHomePage page;
	
	@Before
	public void setUp() throws Exception{
		page = new ElementalSeleniumHomePage();
		page.load();
	}
	
	@Test
	public void helloWorldButtonTest() throws Exception {
		page.clickStartButton();
		page.isAjaxCompleted();
		assertTrue(page.greetingElementAppeared(30));
		assertTrue(page.verifyWorldGreetingtype("Hello World!"));
	}
	
	@Test
	public void heyWorldButtonTest() throws Exception {
		page.clickStartButton();
		assertTrue(page.greetingElementAppeared(30));
		assertTrue(page.verifyWorldGreetingtype("hey World!"));
	}
	
	@After
	public void finish() throws Exception{
		page.exit();
	}
}
