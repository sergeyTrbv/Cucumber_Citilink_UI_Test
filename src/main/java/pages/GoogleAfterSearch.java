package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.time.Instant;
import java.util.Set;


/**
 * Класс {@code GoogleAfterSearch} предоставляет функциональность для работы с результатами поиска в Google.
 *
 * @author SergeyTrbv
 */
public class GoogleAfterSearch {

    /**
     * Константа для формирования начальной части XPath заголовков результатов поиска.
     */
    private static final String SEARCH_RESULTS_HEAD = "//div[@id='rso']//a[@href]//h3[contains(.,'";

    /**
     * Константа для формирования конечной части XPath заголовков результатов поиска.
     */
    private static final String SEARCH_RESULT_TAIL = "')]";

    /**
     * Веб-драйвер для взаимодействия с браузером.
     */
    protected WebDriver chromeDriver;

    /**
     * Объект типа {@code WebDriverWait} использующийся для ожидания элементов на странице.
     */
    private WebDriverWait wait;

    /**
     * Конструктор класса, инициализирующий веб-драйвер и ожидание.
     *
     * @param chromeDriver веб-драйвер для взаимодействия с браузером
     */
    public GoogleAfterSearch(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
        this.wait = new WebDriverWait(chromeDriver, Duration.ofSeconds(10));
    }

    /**
     * Метод проверяет наличие заданного текста в заголовках результатов поиска.
     *
     * @param link текст для проверки в заголовках результатов поиска
     */
    public void verifyTitleContainsText(String link) {
        String xpath = SEARCH_RESULTS_HEAD + link + SEARCH_RESULT_TAIL;
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        Assert.assertFalse("Не найдено тайтла с текстом: '" + link, chromeDriver.
                findElements(By.xpath(xpath)).isEmpty());
    }

    /**
     * Метод переходит на страницу по ссылке с заданным текстом в заголовке.
     *
     * @param link текст в заголовке ссылки для перехода
     */
    public void goToPageByLinkName(String link) {
        String xpath = SEARCH_RESULTS_HEAD + link + SEARCH_RESULT_TAIL;
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        WebElement linkElement = chromeDriver.findElement(By.xpath(xpath));
        String originalWindow = chromeDriver.getWindowHandle();
        linkElement.click();
        Set<String> allWindows = chromeDriver.getWindowHandles();
        for (String window : allWindows) {
            if (!originalWindow.equals(window)) {
                chromeDriver.switchTo().window(window);
                break;
            }
        }
    }

}