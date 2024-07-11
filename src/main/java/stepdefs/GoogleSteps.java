package stepdefs;

import config.Properties;
import io.cucumber.java.en.Given;

import pages.CitilinkSearchInCatalog;
import pages.GoogleAfterSearch;
import pages.GoogleBeforeSearch;
import stash.Context;

/**
 * Класс {@code GoogleSteps} расширяет BaseSteps и содержит шаги для взаимодействия с поисковиком Google.
 */
public class GoogleSteps extends BaseSteps {

    /**
     * Шаг: Пользователь открывает поисковик Google.
     * Открывается URL Google из свойств теста и сохраняется текущая страница в контексте теста.
     */
    @Given("пользователь открывает поисковик google")
    public void пользовательОткрываетПоисковикGoogle() {
        chromeDriver.get(Properties.testProperties.googleUrl());
        GoogleBeforeSearch googleBeforeSearch = new GoogleBeforeSearch(chromeDriver);
        testContext.put(Context.CURRENT_PAGE.name(), googleBeforeSearch);
    }

    /**
     * Шаг: Пользователь ищет заданный текст и переходит на веб-страницу по заголовку.
     * Выполняется поиск по заданному тексту и обновляется текущая страница в контексте теста.
     *
     * @param text текст для поиска
     * @param link заголовок ссылки для перехода
     */
    @Given("^пользователь ищет '(.*)' и по заголовку '(.*)' переходит на веб страницу$")
    public void пользовательИщетИПереходитНаВебСтраницу(String text, String link) {
        GoogleBeforeSearch googleBeforeSearch = (GoogleBeforeSearch) testContext.get(Context.CURRENT_PAGE.name());
        googleBeforeSearch.find(text);
        GoogleAfterSearch googleAfterSearchPO = new GoogleAfterSearch(chromeDriver);
        testContext.put(Context.CURRENT_PAGE.name(), googleAfterSearchPO);
    }

    /**
     * Шаг: Пользователь выбирает ссылку на определенный ресурс.
     * Проверяется, что заголовок содержит текст ссылки, и выполняется переход по ссылке.
     * Обновляется текущая страница в контексте теста.
     *
     * @param link название ссылки для выбора
     */
    @Given("пользователь выбирает '(.*)'$")
    public void пользовательВыбирает(String link) {
        GoogleAfterSearch googleAfterSearch = (GoogleAfterSearch) testContext.get(Context.CURRENT_PAGE.name());
        googleAfterSearch.verifyTitleContainsText(link);
        googleAfterSearch.goToPageByLinkName(link);
        CitilinkSearchInCatalog citilinkSearchInCatalog = new CitilinkSearchInCatalog(chromeDriver);
        testContext.put(Context.CURRENT_PAGE.name(), citilinkSearchInCatalog);
    }
}
