package TecnoGSM.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class HomePage {

    private final WebDriver driver;

    @FindBy(css = ".js-logo-img, .logo-img")
    private WebElement logo;

    @FindBy(css = "input[name='q']")
    private WebElement searchInput;

    @FindBy(css = ".js-search-btn, .search-input-submit")
    private WebElement searchButton;

    @FindBy(css = ".nav-list-link")
    private List<WebElement> navigationLinks;

    @FindBy(xpath = "//img[contains(@class, 'product-item-image')]")
    private List<WebElement> productCards;

    @FindBy(xpath = "//span[contains(@class,'cart-widget')]")
    private WebElement cartWidget;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public HomePage open() {
        driver.get("https://tecnogsm.mitiendanube.com/");
        return this;
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public boolean isLogoDisplayed() {
        return logo.isDisplayed();
    }

    public boolean isSearchBarDisplayed() {
        return searchInput.isDisplayed();
    }

    public SearchResultsPage searchFor(String query) {
        searchInput.clear();
        searchInput.sendKeys(query);
        searchButton.click();
        return new SearchResultsPage(driver);
    }

    public List<WebElement> getNavigationLinks() {
        return navigationLinks;
    }

    private List<WebElement> findProductCards() {
        By locator = By.xpath("//img[contains(@class, 'product-item-image')]");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));

        List<WebElement> allProducts = driver.findElements(locator);
        return allProducts.stream()
                .filter(WebElement::isDisplayed)
                .collect(Collectors.toList());
    }

    public int getProductCount() {
        return findProductCards().size();
    }

    private void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
    }

    private void waitUntilVisible(WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOf(element));
    }


    public boolean isCartWidgetDisplayed() {
        return cartWidget.isDisplayed();
    }
}