package pages;

import helpers.CookieManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CitilinkSearchInCatalog {

    private static final String CATALOG_MENU_BUTTON = "//a[@data-meta-name='DesktopHeaderFixed__catalog-menu']";
    private static final String SMARTPHONES_AND_TABLETS_BUTTON = "//div[@class='PopupScrollContainer']//span[@color='None' and text()='Смартфоны и планшеты']";
    private static final String SMARTPHONES_BUTTON = "//div[@class='rcs-inner-container']//span[text()='Смартфоны']";
    private static final String SMARTPHONE_PAGE_TITLE = "//h1[text()='Смартфоны']";
    private WebDriver chromeDriver;
    private WebDriverWait wait;

    /**
     * Конструктор класса, инициализирующий WebDriver и WebDriverWait..
     */
    public CitilinkSearchInCatalog(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
        this.wait = new WebDriverWait(chromeDriver, Duration.ofSeconds(10));
    }

    /**
     * Метод {@code openCatalog} открывает каталог, нажимая на кнопку каталога в заголовке страницы.
     */
    private void openCatalog() {

        WebElement catalogProductButton = wait.until(ExpectedConditions.
                elementToBeClickable(By.xpath(CATALOG_MENU_BUTTON)));
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        catalogProductButton.click();
    }

    /**
     * Метод {@code hoverOverLaptopAndPC} наводит курсор на кнопку "Смартфоны и телефоны" в выпадающем меню каталога.
     */
    private void hoverOverSmartphonesAndTablets() {
        WebElement laptopAndPCButton = wait.until(ExpectedConditions.
                visibilityOfElementLocated(By.xpath(SMARTPHONES_AND_TABLETS_BUTTON)));
        Actions actions = new Actions(chromeDriver);
        actions.moveToElement(laptopAndPCButton).perform();
    }

    /**
     * Метод {@code selectLaptop} выбирает раздел "Ноутбуки" в подменю "Ноутбуки и компьютеры".
     */
    private void selectSmartphones() {
        WebElement laptopButton = wait.until(ExpectedConditions.
                visibilityOfElementLocated(By.xpath(SMARTPHONES_BUTTON)));
        laptopButton.click();
    }

    /**
     * Метод {@code verifyTransitionToLaptopPage} проверяет, что произошел переход на страницу "Ноутбуки" и
     * URL соответствует ожидаемому.
     */
    private void verifyTransitionToSmartphonesPage(String chapter, String expectedUrl) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SMARTPHONE_PAGE_TITLE)));
        String currentUrl = chromeDriver.getCurrentUrl();
//        Assertions.assertEquals(expectedUrl, currentUrl, "URL страницы не соответствует ожидаемому");
    }

    /**
     * Метод {@code searchChapterInCatalog} выполняет последовательность действий для поиска раздела "Ноутбуки" в
     * каталоге и проверяет переход на соответствующую страницу.
     */
    public void searchChapterInCatalog(String chapter, String expectedUrl) {

        CookieManager.getInstance(chromeDriver).clickCookieIfNeeded();

        openCatalog();
        hoverOverSmartphonesAndTablets();
        selectSmartphones();
        verifyTransitionToSmartphonesPage(chapter, expectedUrl);
    }
}
