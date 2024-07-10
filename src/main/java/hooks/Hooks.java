package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.chrome.ChromeDriver;
import stash.Context;
import stash.TestContext;

import java.time.Duration;

/**
 * Класс задает методы, которые могут выполняться в различных точках цикла выполнения Cucumber.
 * Обычно они используются для настройки и демонтажа среды до и после каждого сценария
 */
public class Hooks {

    /**
     * Инициализация и настройка драйвера
     * value указывает, для каких тегов выполнить метод
     */
    @Before(value = "@ui")
    public void initializeWebDriver() {
        System.out.println("Инициализация веб-драйвера");
        System.setProperty("webdriver.chrome.driver", System.getenv("CHROME_DRIVER"));
        ChromeDriver chromeDriver = new ChromeDriver();
        TestContext testContext = TestContext.getInstance();
        chromeDriver.manage().window().maximize();
        chromeDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        testContext.put(String.valueOf(Context.CHROMEDRIVER), chromeDriver);
    }

    /**
     * Завершение работы драйвера
     */
    @After(value = "@ui")
    public void quitWebDriver() {
        System.out.println("Завершение работы веб-драйвера");
        ChromeDriver chromeDriver = (ChromeDriver) TestContext.getInstance().get(String.valueOf(Context.CHROMEDRIVER));
        chromeDriver.quit();
    }


    /**
     * Получение методанных сценария по завершении теста
     * @param scenario - объект метаданных сценария
     * отсутствие тега после @Before говорит о том, что метод применяется ко всем тестам
     */
    @Before
    public void getScenarioInfo(Scenario scenario) {
        System.out.println("____________________________");
        System.out.println(scenario.getId());
        System.out.println(scenario.getName());
        System.out.println(scenario.getStatus());
        System.out.println(scenario.isFailed());
        System.out.println(scenario.getSourceTagNames());
        System.out.println(scenario.getUri());
        System.out.println("____________________________");
    }


//    /**
//     * order задает очередность выполнения методов по принципу FIFO
//     */
//    @Before(order = 1)
//    public void start_01(){
//        System.out.println("start_01");
//    }
//
//    @Before (order = 2)
//    public void start_02(){
//        System.out.println("start_02");
//    }
//
//    @After(order = 1)
//    public void end_01(){
//        System.out.println("end_01");
//    }
//
//    @After(order = 2)
//    public void end_02(){
//        System.out.println("end_02");
//    }
//
//    @Before(value = "@correct", order = 3)
//    public void customBefore() {
//        System.out.println("Before только для фичи @correct");
//    }
//
//    @After(value = "@correct", order = 3)
//    public void customAfter() {
//        System.out.println("After только для фичи @correct");
//    }
//
//    @Before(value = "not @fail", order = 3)
//    public void withoutBefore() {
//        System.out.println("start_03 для фич кроме @fail");
//    }
//
//    @After(value = "not @fail", order = 3)
//    public void withoutAfter() {
//        System.out.println("end_03 для фич кроме @fail");
//    }
}
