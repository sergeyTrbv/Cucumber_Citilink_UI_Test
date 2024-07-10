package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CitilinkSetParameters {

    private static final String BRAND_TABLE = "//div[@data-meta-name='FilterDropdown' and @data-meta-value='Бренд']";
    private static final String CHECKBOX_VALUE_HEAD = "//input[@id='";
    private static final String CHECKBOX_VALUE_TAIL = "']";
    private WebDriver chromeDriver;
    private WebDriverWait wait;

    public CitilinkSetParameters(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
        this.wait = new WebDriverWait(chromeDriver, Duration.ofSeconds(10));
    }

//    private void choosingBrand(String value) {
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

//    public void setParameters(String value) {
//        choosingBrand(value);
//    }

}
