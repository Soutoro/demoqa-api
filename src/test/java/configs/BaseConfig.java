package configs;

import org.aeonbits.owner.Config;

public interface BaseConfig extends Config {

    @Key("baseUrl")
    @DefaultValue("https://demoqa.com")
    String getBaseUrl();

    @Key("login")
    @DefaultValue("Ttest")
    String getLogin();

    @Key("password")
    @DefaultValue("!Aa12345678")
    String getPassword();

}
