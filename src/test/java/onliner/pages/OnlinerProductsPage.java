package onliner.pages;

import framework.elements.Button;
import framework.elements.Checkbox;
import framework.elements.InputField;
import framework.elements.Label;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class OnlinerProductsPage extends OnlinerBasePage {
    private static String pageLocator =  "//h1[@class='schema-header__title'][contains(text(),'%s')]";

    private final String filterButtonLocator =
            "//div[@class='schema-filter-button__inner-container']";
    private final String checkboxFilterLocator =
            "//div[@class='schema-filter__label'][contains(.,'%s')]/following-sibling::div//span[contains(text(),'%s')]";
    private final String inputFilterLocator =
            "//div[@class='schema-filter__label']/following-sibling::div//input[@placeholder='%s']";
    private final String productPriceLocator =
            "//div[@class='schema-product__price']/a/span";
    private final String productTitleLocator =
            "//div[@class='schema-product__title']";
    private final String productDescriptionLocator =
            "//div[@class='schema-product__description']";

    private Button btnFilter = new Button(By.xpath(filterButtonLocator));

    private Checkbox cbxFilter;
    private InputField fldFilter;

    private Label lblProductTitles = new Label(By.xpath(productTitleLocator));
    private Label lblProductPrices = new Label(By.xpath(productPriceLocator));
    private Label lblProductDescriptions = new Label(By.xpath(productDescriptionLocator));

    public OnlinerProductsPage(String pageLocatorValue) {
        super(By.xpath(String.format(pageLocator, pageLocatorValue)));
    }

    /**
     * Set checkbox filter
     * @param filterType
     * @param filterValue
     * @return OnlinerProductsPage
     */
    public OnlinerProductsPage setCheckboxFilter(String filterType, String filterValue) {
        cbxFilter = new Checkbox(By.xpath(String.format(checkboxFilterLocator, filterType, filterValue)));
        cbxFilter.scrollIntoView();
        cbxFilter.click();
        btnFilter.waitForElementIsPresent();
        return this;
    }

    /**
     * Set input filter
     * @param filterType
     * @param filterInputValue
     * @return OnlinerProductsPage
     */
    public OnlinerProductsPage setInputFilter (String filterType, String filterInputValue) {
        fldFilter = new InputField(By.xpath(String.format(inputFilterLocator, filterType)));
        fldFilter.scrollIntoView();
        fldFilter.sendKeys(filterInputValue);
        btnFilter.waitForElementIsPresent();
        return this;
    }

    /**
     * Apply filter set
     * @param filterType
     * @param filterValue
     * @return OnlinerProductsPage
     */
    public OnlinerProductsPage applyFilter(Filters filterType, String filterValue) {
        switch(filterType) {
            case PRODUCER:
            case RESOLUTION:
            case MINDIAGONAL:
            case MAXDIAGONAL:
                setCheckboxFilter(filterType.toString(),filterValue);
                break;
            case MAXPRICE:
                setInputFilter(filterType.toString(),filterValue);
                break;
            default:
                break;
        }
        return this;
    }

    /**
     * Check if product title matches filter
     * @param filterValue
     * @return Boolean
     */
    public boolean isEachProductTitleContainsFilterValue(String filterValue) {
        List<WebElement> productsTitles = lblProductTitles.getElements();
        for (WebElement element : productsTitles) {
            if(!element.getText().contains(filterValue)){
                return false;
            };
        }
        return true;
    }

    /**
     * Check if product price matches filter
     * @param filterValue
     * @return Boolean
     */
    public boolean isEachProductPriceMatchesFilterValue(String filterValue) {
        List<WebElement> productsPrices = lblProductPrices.getElements();
        for (WebElement element : productsPrices) {
            Double price = Double.parseDouble(element.getText().replaceAll(" р.","").replace(',','.'));
            if(!(price <= Double.parseDouble(filterValue))){
                return false;
            };
        }
        return true;
    }

    /**
     * Check if product description matches filter
     * @param filterValue
     * @return Boolean
     */
    public boolean isEachProductDescriptionContainsFilterValue(String filterValue){
        List<WebElement> productsDescriptions = lblProductDescriptions.getElements();
        for (WebElement element : productsDescriptions) {
            if(!element.getText().contains(filterValue)){
                return false;
            };
        }
        return true;
    }

    /**
     * Check if product description contains filter value in range
     * @param filterValueStartRange
     * @param filterValueEndRange
     * @return Boolean
     */
    public boolean isEachProductDescriptionContainsFilterValueInRange(String filterValueStartRange, String filterValueEndRange){
        List<WebElement> productsDescriptions = lblProductDescriptions.getElements();
        for (WebElement element : productsDescriptions) {
            Double diagonal = Double.parseDouble(element.getText().substring(0,2));
            if(!(diagonal >= Double.parseDouble(filterValueStartRange) && diagonal <= Double.parseDouble(filterValueEndRange))){
                return false;
            };
        }
        return true;
    }
}

