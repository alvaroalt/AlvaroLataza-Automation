package TecnoGSM.tests;

import TecnoGSM.base.BaseTest;
import TecnoGSM.pages.HomePage;
import TecnoGSM.pages.SearchResultsPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchTest extends BaseTest {

    private HomePage homePage;

    @BeforeMethod
    public void navigateToHome() {
        homePage = new HomePage(driver);
        homePage.open();
    }

    @Test(description = "Search for a product and verify results are returned")
    public void searchForProduct_returnsResults() {
        SearchResultsPage resultsPage = homePage.searchFor("modulo");
        Assert.assertTrue(resultsPage.hasResults(),
                "Search for 'cable' should return at least one result");
    }

    @Test(description = "Search for invalid text and verify no results")
    public void searchForInvalidProduct_showsNoResults() {
        SearchResultsPage resultsPage = homePage.searchFor("xyznonexistentproduct12345");
        Assert.assertFalse(resultsPage.hasResults(),
                "Search for gibberish should return no results");
    }
}