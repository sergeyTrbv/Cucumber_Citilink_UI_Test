package stepdefs;

import config.Properties;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import stash.Context;
import io.cucumber.java.en.Given;

public class GoogleSteps extends BaseSteps {

    @Given("пользователь открывает веб-страницу google")
    public void пользовательОткрываетВебСтраницуGoogle() {

        chromeDriver.get(Properties.testProperties.googleUrl());



//        BellBeforeSearch bellPO = new BellBeforeSearch(chromeDriver);
//        testContext.put(Context.CURRENT_PAGE.name(), bellPO);
    }


    @Given("пользователь ищет и переходит на веб-страницу ситилинк")
    public void пользовательИщетИПереходитНаВебССтраницуСитилинк() {
    }

    @Given("пользователь открывает  каталог и наводит курсор на раздел Смартфоны и телефоны затем кликает на категорию Смартфоны")
    public void пользовательОткрываетКаталогИНаводитКурсорНаРазделСмартфоныИТелефоныЗатемКликаетНаКатегориюСмартфоны() {
    }

    @Given("пользователь в фильтре Производитель устанавливает значение чекбоксы")
    public void пользовательВФильтреПроизводительУстанавливаетЗначениеЯЧекбоксы() {
    }

    @When("пользователь выполняет поиск товара по фильтру Производитель установленному значением чекбоксы")
    public void пользовательВыполняетПоискТовараПоФильтруПроизводительУстановленномуЗначениемЧекбоксы() {
    }

    @Then("все названия товаров содержат одно из ключевых слов слова-проверки")
    public void всеYНазванияТоваровСодержатОдноИзКлючевыхСловСловаПроверки() {
    }
}


//    @Given("пользователь переходит на страницу поиска сайта Bell Integrator")
//    public void пользовательПереходитНаСтраницуПоискаСайтаBellIntegrator() {
//        chromeDriver.get(Properties.testProperties.bellintegrator_search_url());
//        BellBeforeSearch bellPO = new BellBeforeSearch(chromeDriver);
//        testContext.put(Context.CURRENT_PAGE.name(), bellPO);
//    }
//
//    @When("^пользователь выполняет поиск по фразе \"(.*)\"$")
//    public void пользовательВыполняетПоискПоФразе(String searchText) {
//        BellBeforeSearch bellBeforeSearch = (BellBeforeSearch) testContext.get(Context.CURRENT_PAGE.name());
//        bellBeforeSearch.find(searchText);
//        BellBeforeSearch bellPO = new BellAfterSearch(chromeDriver);
//        testContext.put(Context.CURRENT_PAGE.name(), bellPO);
//    }
//
//    @Then("хотя бы в одном результате поиска содержится строка {string}")
//    public void вРезультатахПоискаСодержитсяСтрока(String resultSearchText) {
//        BellAfterSearch bellAfterSearch = (BellAfterSearch) testContext.get(Context.CURRENT_PAGE.name());
//        Assert.assertTrue("Статьи содержащие " + resultSearchText + " не найдены для поискового слова " + resultSearchText,
//                bellAfterSearch.getResults().stream().anyMatch(x -> x.getText().contains(resultSearchText)));
//    }