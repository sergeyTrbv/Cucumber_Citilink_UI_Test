package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class GoogleBeforeSearch {

    private static final String SEARCH_FIELD = "//textarea[@title='Поиск']";
    private static final String SEARCH_BUTTON = "//div[not (@jsname)]/center/*[@value='Поиск в Google']";
    private WebElement inputSearch;
    private WebElement buttonSearch;
    protected WebDriver chromeDriver;


    public GoogleBeforeSearch(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
        this.inputSearch = chromeDriver.findElement(By.xpath(SEARCH_FIELD));
        this.buttonSearch = chromeDriver.findElement(By.xpath(SEARCH_BUTTON));
    }


    /**
     * Метод  для выполнения поиска в Google по заданному тексту.
     */
    public void find(String text) {
        inputSearch.sendKeys(text);
        buttonSearch.click();
    }
}
