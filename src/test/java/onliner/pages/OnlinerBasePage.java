package onliner.pages;

import framework.BasePage;
import framework.elements.Button;
import org.openqa.selenium.By;

public class OnlinerBasePage extends BasePage {
    private static String pageLocator = "onliner_logo";
    private static String navigationItemLocator = "//ul[@class='b-main-navigation']//span[text() = '%s']";

    private Button btnNavigationItem;

    public OnlinerBasePage() {
        super(By.className(pageLocator));
    }

    public OnlinerBasePage(final By locator) {
        super(locator);
    }

    /**
     * Navigate main menu
     * @param selectedNavigationItem
     * @ OnlinerCategoryPage
     */
    public OnlinerCategoryPage selectNavigationItem(String selectedNavigationItem) {
        btnNavigationItem = new Button(By.xpath(String.format(navigationItemLocator,selectedNavigationItem)));
        btnNavigationItem.click();
        return new OnlinerCategoryPage(selectedNavigationItem);
    }
}
