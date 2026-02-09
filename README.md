# Halo-Homework - Test Automation Framework

Test automation framework for UI and API testing, built with Java, TestNG, Selenium, and REST Assured.

## Tech Stack

| Technology         | Version | Purpose                          |
|--------------------|---------|----------------------------------|
| Java               | 11      | Programming language             |
| Selenium WebDriver | 4.27.0  | Browser automation               |
| TestNG             | 7.10.2  | Test runner and assertions        |
| WebDriverManager   | 5.9.2   | Automatic ChromeDriver setup     |
| REST Assured       | 5.4.0   | API testing                      |
| Maven              | -       | Build and dependency management  |

## Project Structure

```
src/
├── main/java/
│   └── TecnoGSM/
│       └── pages/
│           ├── HomePage.java              # TecnoGSM homepage page object
│           └── SearchResultsPage.java     # TecnoGSM search results page object
│
└── test/java/
    └── TecnoGSM/
        ├── base/
        │   └── BaseTest.java              # WebDriver setup/teardown
        ├── tests/
        │   ├── HomePageTest.java          # Homepage smoke tests (5 tests)
        │   └── SearchTest.java            # Search functionality tests (2 tests)
        └── testng.xml                     # TestNG suite configuration
    └── FakeStoreAPI/
        ├── base/
        │   └── ApiBaseTest.java             # REST Assured base URI config
        ├── tests/
        │   ├── ProductsApiTest.java         # Products endpoint tests (3 tests)
        │   ├── CartApiTest.java             # Cart endpoint tests (2 tests)
        └── testng.xml                       # FakeStoreAPI suite configuration
    └── resources/
        └── schemas/
            └── products-schema.json           # JSON schema for /products endpoint
```

## Framework Layers

### 1. Page Objects (`src/main/java/TecnoGSM/pages`)

### 2. Base Test (`src/test/java/TecnoGSM/base`)
- `@BeforeMethod` — launches a new Chrome browser, sets implicit wait (10s), maximizes window
- `@AfterMethod` — quits the browser

### 3. Test Classes (`src/test/java/TecnoGSM/tests`)
Each test class extends `BaseTest`.

## Test Scenarios

### HomePageTest (TecnoGSM)
| Test                          | Description                                      |
|-------------------------------|--------------------------------------------------|
| `verifyPageTitle`             | Page title contains "tecno"                      |
| `verifyLogoDisplayed`         | Store logo is visible                            |
| `verifySearchBarPresent`      | Search input field is visible                    |
| `verifyNavigationLinksPresent`| At least one nav link exists                     |
| `verifyCartWidgetDisplayed`   | Shopping cart widget is visible                  |

### SearchTest (TecnoGSM)
| Test                                    | Description                                    |
|-----------------------------------------|------------------------------------------------|
| `searchForProduct_returnsResults`       | Search "modulo" returns at least one result     |
| `searchForInvalidProduct_showsNoResults`| Search gibberish returns zero results           |

### Why these scenarios matter

These tests act as a **smoke test suite** — they validate that the most critical elements and flows of the e-commerce site are functional after every deployment or change.

- **Page title and logo** confirm the correct page is loading and branding is intact. A missing logo or wrong title often signals a broken deployment or misconfigured environment.
- **Search bar and navigation links** are the primary ways users discover products. If either is missing or broken, customers cannot browse the catalog, directly impacting sales.
- **Cart widget** is the entry point to the purchase flow. If it disappears, no user can complete a checkout.
- **Search with valid input** verifies the search engine and product database are connected and returning results. This is the happy path that most users follow.
- **Search with invalid input** verifies the application handles edge cases gracefully instead of crashing or showing misleading content.

Together, these 7 tests provide fast, high-confidence feedback on whether the site's core functionality is operational, without the overhead of full end-to-end transaction tests.

### ProductsApiTest (FakeStoreAPI)
| Test                     | Description                                          |
|--------------------------|------------------------------------------------------|
| `getAllProducts`         | GET /products returns 200 and non-empty list         |
| `getProductById`         | GET /products/1 returns 200 with correct id          |
| `getProductsByCategory`  | GET /products/category/electronics returns 200       |

### CartApiTest (FakeStoreAPI)
| Test                     | Description                                          |
|--------------------------|------------------------------------------------------|
| `getAllCarts`            | GET /carts returns 200 and non-empty list            |
| `getCartById`            | GET /carts/1 returns 200 with correct id             |

## How to Run

### Prerequisites
- Java 11+ installed
- Maven installed
- Chrome browser installed (ChromeDriver is managed automatically)

### Run all tests (using the testng.xml suite)
```bash
mvn clean test
```

### Run only TecnoGSM UI tests
```bash
mvn clean test -DsuiteXmlFile=src/test/java/TecnoGSM/testng.xml
```

### Run only FakeStoreAPI tests
```bash
mvn clean test -DsuiteXmlFile=src/test/java/FakeStoreAPI/testng.xml
```

### Run a single test class
```bash
mvn clean test -Dtest=TecnoGSM.tests.HomePageTest
mvn clean test -Dtest=FakeStoreAPI.tests.ProductsApiTest
```

