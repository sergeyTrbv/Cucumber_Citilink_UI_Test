package helpers;

import org.junit.Assert;

/**
 * Класс {@code Assertions} предоставляет методы для выполнения проверок в тестах.
 * Эти методы обертывают стандартные утверждения из JUnit, добавляя дополнительные возможности,
 * такие как логирование шагов теста.
 *
 * @author sergeyTrbv
 */
public class Assertions {
    /**
     * Проверяет переданное условие и выводит сообщение при ошибке.
     *
     * @param condition условие для проверки
     * @param message   сообщение при ошибке
     */
    public static void assertTrue(boolean condition, String message) {
        Assert.assertTrue(message, condition);
    }

    /**
     * Проверяет переданное условие (должно быть ложным) и выводит сообщение при ошибке.
     *
     * @param condition условие для проверки
     * @param message   сообщение при ошибке
     */
    public static void assertFalse(boolean condition, String message) {
        Assert.assertFalse(message, condition);
    }

    /**
     * Проверяет, что текущий URL соответствует ожидаемому и выводит сообщение при ошибке.
     *
     * @param expectedUrl ожидаемый URL
     * @param currentUrl  текущий URL
     * @param message     сообщение при ошибке
     */
    public static void assertEquals(String expectedUrl, String currentUrl, String message) {
        Assert.assertEquals(message, expectedUrl, currentUrl);
    }
}
