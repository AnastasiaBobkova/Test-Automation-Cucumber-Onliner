package onliner.stepdefinitions;

import framework.Browser;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import onliner.pages.Filters;
import onliner.pages.OnlinerCategoryPage;
import onliner.pages.OnlinerHomePage;
import onliner.pages.OnlinerProductsPage;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;
import java.util.Map;

public class OnlinerCatalogFiltersSteps {

    private static Browser browser = Browser.getInstance();

    private OnlinerHomePage homePage;
    private OnlinerCategoryPage categoryPage;
    private OnlinerProductsPage productsPage;

    private static String ERROR_MSG_TITLE_NOT_MATCH = "Not each product title contains selected producer: %s";
    private static String ERROR_MSG_PRICE_NOT_MATCH_RANGE = "Not each product price is within selected range: до %s";
    private static String ERROR_MSG_DESCRIPTION_NOT_MATCH = "Not each product description contains filtered value: %s";
    private static String ERROR_MSG_DESCRIPTION_NOT_MATCH_RANGE = "Not each product description contains value within selected range: %s %s";

    private static HashMap<String,String> appliedFilters = new HashMap<String, String>();

    @Given("Onliner Home Page is opened")
    public void onlinerHomePageIsOpened() {
        browser.navigate(browser.props.getProperty("URL"));
        homePage = new OnlinerHomePage();
        homePage.assertIsOpen();
    }

    @And("User clicks {string} on Onliner Home Page")
    public void selectNavigationItem(String selectedNavigationItem) {
        categoryPage = homePage.selectNavigationItem(selectedNavigationItem);
    }

    @And("selects {string} on Onliner Category Page")
    public void selectCatalogNavigationItem(String selectedCatalogNavigationItem) {
        categoryPage.selectCatalogNavigationItem(selectedCatalogNavigationItem);
    }

    @And("moves to {string} and {string} on Onliner Category Page")
    public void moveToCatalogAsideListDropdownItem(String selectedCatalogAsideListItem,
                                                   String selectedCatalogAsideListDropdownItem ) {
        productsPage = categoryPage.moveToAndSelectCatalogAsideListDropdownItem(selectedCatalogAsideListItem,
                selectedCatalogAsideListDropdownItem);
    }

    @Given("User is on Onliner Products Page")
    public void onlinerProductsPageIsOpened() {
        productsPage.assertIsOpen();
    }

    @When("User checks the following filters")
    public void checkFilters(Map<String,String> filtersToApply) {
        filtersToApply.forEach((field,value) -> {
            productsPage.applyFilter(Filters.valueOf(field),value);
            appliedFilters.put(field,value);
        });
    }

    @Then("correct search results are displayed")
    public void correctSearchResultsAreDisplayed() {

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue(productsPage.isEachProductTitleContainsFilterValue(appliedFilters.get("PRODUCER")),
                String.format(ERROR_MSG_TITLE_NOT_MATCH,appliedFilters.get("PRODUCER")));
        softAssert.assertTrue(productsPage.isEachProductPriceMatchesFilterValue(appliedFilters.get("MAXPRICE")),
                String.format(ERROR_MSG_PRICE_NOT_MATCH_RANGE,appliedFilters.get("MAXPRICE")));
        softAssert.assertTrue(productsPage.isEachProductDescriptionContainsFilterValue(appliedFilters.get("RESOLUTION")),
                String.format(ERROR_MSG_DESCRIPTION_NOT_MATCH,appliedFilters.get("RESOLUTION")));
        softAssert.assertTrue(productsPage.isEachProductDescriptionContainsFilterValueInRange(appliedFilters.get("MINDIAGONAL"),appliedFilters.get("MAXDIAGONAL")),
                String.format(ERROR_MSG_DESCRIPTION_NOT_MATCH_RANGE,appliedFilters.get("MINDIAGONAL"),appliedFilters.get("MAXDIAGONAL")));
        softAssert.assertAll();
    }
}
