package TecnoGSM.tests;

import TecnoGSM.base.BaseTest;
import TecnoGSM.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {

    private HomePage homePage;

    @BeforeMethod
    public void navigateToHome() {
        homePage = new HomePage(driver);
        homePage.open();
    }

    @Test(description = "Verify the page title contains Tecno GSM")
    public void verifyPageTitle() {
        String title = homePage.getPageTitle();
        Assert.assertTrue(title.toLowerCase().contains("tecno"),
                "Page title should contain 'tecno'. Actual: " + title);
    }

    @Test(description = "Verify the store logo is displayed")
    public void verifyLogoDisplayed() {
        Assert.assertTrue(homePage.isLogoDisplayed(), "Store logo should be visible");
    }

    @Test(description = "Verify the search bar is present on the homepage")
    public void verifySearchBarPresent() {
        Assert.assertTrue(homePage.isSearchBarDisplayed(), "Search bar should be visible");
    }

    @Test(description = "Verify navigation links are present")
    public void verifyNavigationLinksPresent() {
        Assert.assertFalse(homePage.getNavigationLinks().isEmpty(),
                "Navigation should have at least one link");
    }

    @Test(description = "Verify the cart widget is displayed")
    public void verifyCartWidgetDisplayed() {
        Assert.assertTrue(homePage.isCartWidgetDisplayed(), "Cart widget should be visible");
    }
}