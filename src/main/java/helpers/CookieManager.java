package helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CookieManager {

    private static final String COOCKIE = "//button[ @data-meta-disabled=\"false\"]/span[text()='Я согласен']";
    private static CookieManager instance;
    private boolean cookieClicked = false;

    private WebDriver chromeDriver;

    private CookieManager() {
    }
    private CookieManager(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
    }

    /**
     * Метод {@code getInstance} возвращает единственный экземпляр класса CookieManager.
     * Если экземпляр еще не создан, он будет создан в этом методе.
     */
    public static synchronized CookieManager getInstance(WebDriver chromeDriver) {
        if (instance == null) {
            instance = new CookieManager(chromeDriver);
        }
        return instance;
    }

    /**
     * Метод {@code clickCookieIfNeeded} нажимает кнопку согласия на использование cookies, если она еще не была нажата.
     */
    public void clickCookieIfNeeded() {
        if (!cookieClicked) {

            WebDriverWait wait = new WebDriverWait(chromeDriver, Duration.ofSeconds(10));
            WebElement cookieButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(COOCKIE)));
            cookieButton.click();
            cookieClicked = true;
        }
    }
}
