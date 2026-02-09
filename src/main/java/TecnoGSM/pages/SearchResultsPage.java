package TecnoGSM.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SearchResultsPage {

    private final WebDriver driver;

    @FindBy(css = ".js-item-product, .item-product")
    private List<WebElement> resultItems;

    @FindBy(css = ".item-name, .js-item-name")
    private List<WebElement> resultTitles;

    @FindBy(css = ".js-search-no-results, .search-no-results")
    private List<WebElement> noResultsMessage;

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public int getResultCount() {
        return resultItems.size();
    }

    public boolean hasResults() {
        return !resultItems.isEmpty();
    }

    public boolean hasNoResultsMessage() {
        return !noResultsMessage.isEmpty();
    }

    public List<WebElement> getResultTitles() {
        return resultTitles;
    }

}