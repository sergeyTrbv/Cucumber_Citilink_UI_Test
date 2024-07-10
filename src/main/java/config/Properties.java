package config;

import org.aeonbits.owner.ConfigFactory;

/**
 * Класс управления глобальными настройками (пропертями)
 */
public class Properties {

    public static TestProperties testProperties = ConfigFactory.create(TestProperties.class);


}