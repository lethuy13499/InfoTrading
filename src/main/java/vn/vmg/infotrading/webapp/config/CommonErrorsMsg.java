package vn.vmg.infotrading.webapp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = "classpath:errors.yml", factory = YamlPropertySourceFactory.class)
@ConfigurationProperties(prefix = "common")
public class CommonErrorsMsg {

    private String empty;

    public String getEmpty() {
        return empty;
    }

    public void setEmpty(String empty) {
        this.empty = empty;
    }
}
