package pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CitilinkCountTest {

    private static final String PRODUCT_CARD = "//div[contains(@class, 'e1l56t9a0')]";

    //Объект String с шаблоном XPath для кнопки "Следующая" для перехода на следующую страницу каталога.
    private static final String NEXT_BUTTON = "//div[text()='Следующая']";

    //Объект String с шаблоном XPath для элементов с описанием товара в каталоге.
    private static final String TITLE_ELEMENTS_IN_CATALOG = ".//a[contains(@class, 'app-catalog-9gnskf') and not(ancestor::div[@data-meta-name='ProductsCompilation__slide'])]";
    private WebDriver chromeDriver;
    private WebDriverWait wait;
    private int currentPage = 1;

    /**
     * Конструктор класса, инициализирующий WebDriver и WebDriverWait.
     */
    public CitilinkCountTest(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
        this.wait = new WebDriverWait(chromeDriver, Duration.ofSeconds(5));
    }

    /**
     * Метод {@code checkConditionsOnAllPages} проверяет условия на всех страницах каталога.
     * Переходит на следующую страницу, пока она доступна, и проверяет условия на каждой странице.
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
     * Метод {@code checkPageConditions} проверяет условия на текущей странице каталога.
     * Включает проверку названий брендов и цен.
     */
//        private void checkPageConditions (String text) {
//            waitForPageLoad();
//            checkBrandNames(text);
//        }

    /**
     * Метод {@code checkBrandNames} проверяет название бренда на текущей странице.
     * Убеждается, что название содержат {text}.
     */
    private void checkBrandNames(String text) {
        List<WebElement> productElementsTitle = chromeDriver.findElements(By.xpath(TITLE_ELEMENTS_IN_CATALOG));
        for (WebElement productElement : productElementsTitle) {
            String title = productElement.getAttribute("title");

            Assert.assertTrue("На странице " + currentPage +
                    " название бренда техники не соответствует '" + text + "'", title.contains(text));
        }
    }


    /**
     * Метод {@code navigateToNextPage} переходит на следующую страницу каталога, если она доступна.
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
     * Метод {@code scrollIntoViewAndClick} прокручивает элемент в поле зрения и кликает по нему.
     */
    private void scrollIntoViewAndClick(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) chromeDriver;
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        js.executeScript("arguments[0].click();", element);
        wait.until(ExpectedConditions.stalenessOf(element));
    }

//        private void waitForPageLoad () {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }


}
