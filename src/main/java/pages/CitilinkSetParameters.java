package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Класс {@code CitilinkSetParameters} предназначен для установки параметров фильтрации на странице каталога Citilink.
 * Он использует Selenium WebDriver для взаимодействия с веб-элементами.
 *
 * @author SergeyTrbv
 */
public class CitilinkSetParameters {

    /**
     * Константа с шаблоном XPath для элемента "Бренд" в фильтре.
     */
    private static final String BRAND_TABLE = "//div[@data-meta-name='FilterDropdown' and @data-meta-value='Бренд']";

    /**
     * Константа, содержащая начало XPath для чекбокса значения фильтра.
     */
    private static final String CHECKBOX_VALUE_HEAD = "//input[@id='";

    /**
     * Константа, содержащая конец XPath для чекбокса значения фильтра.
     */
    private static final String CHECKBOX_VALUE_TAIL = "']";

    /**
     * Веб-драйвер для взаимодействия с браузером.
     */
    protected WebDriver chromeDriver;

    /**
     * Объект типа {@code WebDriverWait} использующийся для ожидания элементов на странице.
     */
    private WebDriverWait wait;


    /**
     * Конструктор класса, инициализирующий WebDriver и WebDriverWait.
     *
     * @param chromeDriver экземпляр WebDriver для взаимодействия с браузером.
     */
    public CitilinkSetParameters(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
        this.wait = new WebDriverWait(chromeDriver, Duration.ofSeconds(10));
    }

    /**
     * Метод устанавливает параметры фильтрации на странице каталога.
     *
     * @param value значение параметра для установки.
     */
    public void setParameters(String value) {
        JavascriptExecutor js = (JavascriptExecutor) chromeDriver;
        WebElement brandTable = chromeDriver.findElement(By.xpath(BRAND_TABLE));
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", brandTable);

        String xpath = CHECKBOX_VALUE_HEAD + value.toLowerCase() + CHECKBOX_VALUE_TAIL;
        WebElement checkBoxApple = chromeDriver.findElement(By.xpath(xpath));
        checkBoxApple.click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
