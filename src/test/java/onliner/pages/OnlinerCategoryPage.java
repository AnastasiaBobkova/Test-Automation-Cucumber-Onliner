package onliner.pages;

import framework.elements.Button;
import org.openqa.selenium.By;

public class OnlinerCategoryPage extends OnlinerBasePage {
    private static String pageLocator = "//h1[@class='catalog-navigation__title'][contains(text(),'%s')]";

    private final String catalogNavigationItemLocator =
            "//span[@class='catalog-navigation-classifier__item-title'][contains(.,'%s')]";
    private final String catalogAsideListItemLocator =
            "//div[@class='catalog-navigation-list__aside-item'][contains(.,'%s')]";
    private final String catalogAsideListDropdownItemLocator =
            "//a[@class='catalog-navigation-list__dropdown-item'][contains(.,'%s')]";

    private Button btnCatalogNavigationItem;
    private Button btnCatalogAsideListItem;
    private Button btnCatalogAsideListDropdownItem;

    public OnlinerCategoryPage(String pageLocatorValue) {
        super(By.xpath(String.format(pageLocator, pageLocatorValue)));
    }

    /**
     * Navigate catalog menu
     * @param selectedCatalogNavigationItem
     * @return
     */
    public OnlinerCategoryPage selectCatalogNavigationItem(String selectedCatalogNavigationItem) {
        btnCatalogNavigationItem = new Button(By.xpath(String.format(catalogNavigationItemLocator,selectedCatalogNavigationItem)));
        btnCatalogNavigationItem.click();
        return this;
    }

    /**
     * Navigate catalog submenu
     * @param selectedCatalogAsideListItem
     * @param selectedCatalogAsideListDropdownItem
     * @return OnlinerProductsPage
     */
    public OnlinerProductsPage moveToAndSelectCatalogAsideListDropdownItem(String selectedCatalogAsideListItem, String selectedCatalogAsideListDropdownItem) {
        btnCatalogAsideListItem = new Button(By.xpath(String.format(catalogAsideListItemLocator,selectedCatalogAsideListItem)));
        btnCatalogAsideListDropdownItem = new Button(By.xpath(String.format(catalogAsideListDropdownItemLocator,selectedCatalogAsideListDropdownItem)));
        btnCatalogAsideListItem.moveToElementAndClick();
        btnCatalogAsideListDropdownItem.moveToElementAndClick();
        return new OnlinerProductsPage(selectedCatalogAsideListDropdownItem);
    }
}
