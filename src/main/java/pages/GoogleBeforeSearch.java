package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Класс {@code GoogleBeforeSearch} представляет собой объект страницы перед выполнением поиска в Google.
 * Он содержит элементы страницы и методы для взаимодействия с ними.
 *
 *  * @author SergeyTrbv
 */
public class GoogleBeforeSearch {

    /**
     * Объект String с шаблоном XPath для поля ввода поискового запроса.
     */
    private static final String SEARCH_FIELD = "//textarea[@title='Поиск']";

    /**
     * Объект String с шаблоном XPath для кнопки "Поиск в Google".
     */
    private static final String SEARCH_BUTTON = "//div[not (@jsname)]/center/*[@value='Поиск в Google']";

    /**
     * Элемент поля ввода поискового запроса.
     */
    private WebElement inputSearch;

    /**
     * Элемент кнопки "Поиск в Google".
     */
    private WebElement buttonSearch;

    /**
     * Веб-драйвер для взаимодействия с браузером.
     */
    protected WebDriver chromeDriver;

    /**
     * Конструктор класса, инициализирующий веб-драйвер и находящий элементы поиска.
     *
     * @param chromeDriver веб-драйвер для взаимодействия с браузером
     */
    public GoogleBeforeSearch(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
        this.inputSearch = chromeDriver.findElement(By.xpath(SEARCH_FIELD));
        this.buttonSearch = chromeDriver.findElement(By.xpath(SEARCH_BUTTON));
    }


    /**
     * Метод для выполнения поиска в Google по заданному тексту.
     * Вводит текст в поле поиска и нажимает кнопку "Поиск в Google".
     *
     * @param text текст для поиска
     */
    public void find(String text) {
        inputSearch.sendKeys(text);
        buttonSearch.click();
    }
}
