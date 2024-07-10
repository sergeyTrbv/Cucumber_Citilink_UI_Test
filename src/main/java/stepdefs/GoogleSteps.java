package stepdefs;

import config.Properties;
import io.cucumber.java.en.Given;

import pages.CitilinkSearchInCatalog;
import pages.GoogleAfterSearch;
import pages.GoogleBeforeSearch;
import stash.Context;


public class GoogleSteps extends BaseSteps {

    @Given("пользователь открывает поисковик google")
    public void пользовательОткрываетПоисковикGoogle() {
        chromeDriver.get(Properties.testProperties.googleUrl());
        GoogleBeforeSearch googleBeforeSearch = new GoogleBeforeSearch(chromeDriver);
        testContext.put(Context.CURRENT_PAGE.name(), googleBeforeSearch);
    }


    @Given("^пользователь ищет '(.*)' и по заголовку '(.*)' переходит на веб страницу$")
    public void пользовательИщетИПереходитНаВебСтраницу(String text, String link) {
        GoogleBeforeSearch googleBeforeSearch = (GoogleBeforeSearch) testContext.get(Context.CURRENT_PAGE.name());
        googleBeforeSearch.find(text);
        GoogleAfterSearch googleAfterSearchPO = new GoogleAfterSearch(chromeDriver);
        testContext.put(Context.CURRENT_PAGE.name(), googleAfterSearchPO);
    }


    @Given("пользователь тыкает '(.*)'$")
    public void пользовательТыкает(String link) {
        GoogleAfterSearch googleAfterSearch = (GoogleAfterSearch) testContext.get(Context.CURRENT_PAGE.name());
        googleAfterSearch.verifyTitleContainsText(link);
        googleAfterSearch.goToPageByLinkName(link);

        CitilinkSearchInCatalog citilinkSearchInCatalog = new CitilinkSearchInCatalog(chromeDriver);
        testContext.put(Context.CURRENT_PAGE.name(), citilinkSearchInCatalog);
    }
}
