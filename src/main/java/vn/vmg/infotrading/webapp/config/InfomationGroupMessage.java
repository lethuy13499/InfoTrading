package vn.vmg.infotrading.webapp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = "classpath:information-group.yml", factory = YamlPropertySourceFactory.class)
@ConfigurationProperties(prefix = "information-group")
public class InfomationGroupMessage {
    private String searchCode;
    private String searchDesciption;
    private String searchStatus;
    private String onActive;
    private String infomationExist;

    public String getSearchCode() {
        return searchCode;
    }

    public void setSearchCode(String searchCode) {
        this.searchCode = searchCode;
    }

    public String getSearchDesciption() {
        return searchDesciption;
    }

    public void setSearchDesciption(String searchDesciption) {
        this.searchDesciption = searchDesciption;
    }

    public String getSearchStatus() {
        return searchStatus;
    }

    public void setSearchStatus(String searchStatus) {
        this.searchStatus = searchStatus;
    }

    public String getOnActive() {
        return onActive;
    }

    public void setOnActive(String onActive) {
        this.onActive = onActive;
    }

    public String getInfomationExist() {
        return infomationExist;
    }

    public void setInfomationExist(String infomationExist) {
        this.infomationExist = infomationExist;
    }
}
