package stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CitilinkCheckingConditions;
import pages.CitilinkSearchInCatalog;
import pages.CitilinkSetParameters;
import stash.Context;

/**
 * Класс {@code CitilinkSteps} расширяет BaseSteps и содержит шаги для взаимодействия с каталогом сайта Citilink.
 *
 *  * @author SergeyTrbv
 */
public class CitilinkSteps extends BaseSteps {

    /**
     * Шаг: Пользователь открывает каталог и выполняет поиск раздела с смартфонами.
     * Открывается каталог, выполняется поиск раздела "Смартфоны и планшеты" и обновляется текущая
     * страница в контексте теста.
     */
    @Given("пользователь открывает каталог и выполняет поиск раздела с смартфонами")
    public void пользовательОткрываетКаталогИВыполняетПоискРазделаССмартфонами() {
        CitilinkSearchInCatalog citilinkSearchInCatalog = (CitilinkSearchInCatalog) testContext.
                get(Context.CURRENT_PAGE.name());
        citilinkSearchInCatalog.searchChapterInCatalog("Каталог", "Смартфоны и планшеты");
        CitilinkSetParameters citilinkSetParameters = new CitilinkSetParameters(chromeDriver);
        testContext.put(Context.CURRENT_PAGE.name(), citilinkSetParameters);
    }

    /**
     * Шаг: Пользователь выполняет поиск товара по фильтру бренда.
     * Устанавливаются параметры поиска по бренду и обновляется текущая страница в контексте теста.
     *
     * @param value значение фильтра бренда
     */
    @When("^пользователь выполняет поиск товара по фильтру бренда \"(.*)\"$")
    public void пользовательВыполняетПоискТовараПоФильтруБренда(String value) {
        CitilinkSetParameters citilinkSetParameters = (CitilinkSetParameters) testContext.
                get(Context.CURRENT_PAGE.name());
        citilinkSetParameters.setParameters(value);
        CitilinkCheckingConditions citilinkCheckingConditions = new CitilinkCheckingConditions(chromeDriver);
        testContext.put(Context.CURRENT_PAGE.name(), citilinkCheckingConditions);
    }

    /**
     * Шаг: Все названия товаров содержат текст для проверки результатов поиска.
     * Проверяется, что все названия товаров на всех страницах, предложенных каталогом,
     * содержат указанный текст.
     *
     * @param text текст для проверки в названиях товаров
     */
    @Then("^все названия товаров содержат \"(.*)\"$")
    public void всеНазванияТоваровСодержат(String text) {
        CitilinkCheckingConditions citilinkCheckingConditions = (CitilinkCheckingConditions) testContext.
                get(Context.CURRENT_PAGE.name());
        citilinkCheckingConditions.checkConditionsOnAllPages(text);
    }

}
