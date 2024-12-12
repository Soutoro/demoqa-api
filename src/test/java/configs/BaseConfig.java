package configs;

import org.aeonbits.owner.Config;

public interface BaseConfig extends Config {

    @Key("baseUrl")
    @DefaultValue("https://demoqa.com")
    String getBaseUrl();

}
