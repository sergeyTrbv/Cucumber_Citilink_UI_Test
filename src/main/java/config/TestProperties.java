package config;

import org.aeonbits.owner.Config;

import static org.aeonbits.owner.Config.*;

@LoadPolicy(LoadType.MERGE)
@Sources({"file:src/test/resources/tests.properties"})
public interface TestProperties extends Config {

    @Key("google.url")
    String googleUrl();

    @Key("citilink.url")
    String citilinkUrl();
}

