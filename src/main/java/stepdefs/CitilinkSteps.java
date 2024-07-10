package stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CitilinkCountTest;
import pages.CitilinkSearchInCatalog;
import pages.CitilinkSetParameters;
import stash.Context;

public class CitilinkSteps extends BaseSteps {


    @Given("пользователь открывает каталог и выполняет поиск раздела с смартфонами")
    public void пользовательОткрываетКаталогИВыполняетПоискРазделаССмартфонами() {

        CitilinkSearchInCatalog citilinkSearchInCatalog = (CitilinkSearchInCatalog) testContext.get(Context.CURRENT_PAGE.name());
        citilinkSearchInCatalog.searchChapterInCatalog("Каталог", "Смартфоны и планшеты");
        CitilinkSetParameters citilinkSetParameters = new CitilinkSetParameters(chromeDriver);
        testContext.put(Context.CURRENT_PAGE.name(), citilinkSetParameters);
    }


    @When("^пользователь выполняет поиск товара по фильтру бренда \"(.*)\"$")
    public void пользовательВыполняетПоискТовараПоФильтруБренда(String value) {

        CitilinkSetParameters citilinkSetParameters = (CitilinkSetParameters) testContext.get(Context.CURRENT_PAGE.name());
        citilinkSetParameters.setParameters(value);
        CitilinkCountTest citilinkCountTest = new CitilinkCountTest(chromeDriver);
        testContext.put(Context.CURRENT_PAGE.name(), citilinkCountTest);
    }


    @Then("^все названия товаров содержат \"(.*)\"$")
    public void всеНазванияТоваровСодержат(String text) {

        CitilinkCountTest citilinkCountTest = (CitilinkCountTest) testContext.get(Context.CURRENT_PAGE.name());
        citilinkCountTest.checkConditionsOnAllPages(text);



    }

}
