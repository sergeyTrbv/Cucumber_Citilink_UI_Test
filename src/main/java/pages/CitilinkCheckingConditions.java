package pages;

import helpers.Assertions;
import helpers.CookieManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
/**
 * Класс {@code CitilinkCheckingConditions} предназначен для проверки условий на страницах каталога товаров на сайте Citilink.
 * Он использует Selenium WebDriver для взаимодействия с веб-элементами и проверки соответствия названий брендов заданному тексту.
 *
 * @author sergeyTrbv
 */
public class CitilinkCheckingConditions {

    /**
     * Объект String с шаблоном XPath для карточек продуктов в каталоге.
     */
    private static final String PRODUCT_CARD = "//div[contains(@class, 'e1l56t9a0')]";

    /**
     * Константа, содержащая XPath для кнопки "Следующая" страницы.
     */
    private static final String NEXT_BUTTON = "//div[text()='Следующая']";

    /**
     * Константа, содержащая XPath для элементов с описанием товара в каталоге.
     */
    private static final String TITLE_ELEMENTS_IN_CATALOG = ".//a[contains(@class, 'app-catalog-9gnskf') and " +
            "not(ancestor::div[@data-meta-name='ProductsCompilation__slide'])]";

    /**
     * Веб-драйвер для взаимодействия с браузером.
     */
    protected WebDriver chromeDriver;

    /**
     * Объект типа {@code WebDriverWait} использующийся для ожидания элементов на странице.
     */
    private WebDriverWait wait;

    /**
     * Переменная для отслеживания текущей страницы.
     */
    private int currentPage = 1;

    /**
     * Конструктор класса, инициализирующий WebDriver и WebDriverWait.
     *
     * @param chromeDriver экземпляр WebDriver для взаимодействия с браузером.
     */
    public CitilinkCheckingConditions(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
        this.wait = new WebDriverWait(chromeDriver, Duration.ofSeconds(5));
    }

    /**
     * Метод проверяет условия на всех страницах каталога.
     * Переходит на следующую страницу, пока она доступна, и проверяет условия на каждой странице.
     *
     * @param text текст, который должен содержаться в названии бренда товара.
     */
    public void checkConditionsOnAllPages(String text) {
        boolean hasNextPage = true;
        while (hasNextPage) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            checkBrandNames(text);
            hasNextPage = navigateToNextPage();
        }
        if (!hasNextPage) {
            System.out.println("Тест на наличие " + text + " завершен");
        }
    }

    /**
     * Метод проверяет название бренда на текущей странице.
     * Убеждается, что название содержит заданный текст.
     *
     * @param text текст, который должен содержаться в названиях брендов.
     */
    private void checkBrandNames(String text) {
        List<WebElement> productElementsTitle = chromeDriver.findElements(By.xpath(TITLE_ELEMENTS_IN_CATALOG));
        for (WebElement productElement : productElementsTitle) {
            String title = productElement.getAttribute("title");
            Assertions.assertTrue(title.contains(text), "На странице " + currentPage +
                    " название бренда техники не соответствует '" + text + "'");
        }
    }

    /**
     * Метод переходит на следующую страницу каталога, если она доступна.
     *
     * @return true, если переход на следующую страницу был успешным, иначе false.
     */
    private boolean navigateToNextPage() {
        List<WebElement> nextPageButtons = chromeDriver.findElements(By.xpath(NEXT_BUTTON));
        if (!nextPageButtons.isEmpty() && nextPageButtons.get(0).isEnabled()) {
            WebElement nextButton = nextPageButtons.get(0);
            scrollIntoViewAndClick(nextButton);
            currentPage++;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Метод прокручивает элемент в поле зрения и кликает по нему.
     *
     * @param element элемент, который нужно прокрутить и кликнуть.
     */
    private void scrollIntoViewAndClick(WebElement element) {
        CookieManager.getInstance(chromeDriver).clickCookieIfNeeded();
        JavascriptExecutor js = (JavascriptExecutor) chromeDriver;
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        js.executeScript("arguments[0].click();", element);
        wait.until(ExpectedConditions.stalenessOf(element));
    }
}
