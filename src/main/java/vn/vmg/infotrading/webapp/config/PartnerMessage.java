package vn.vmg.infotrading.webapp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = "classpath:partners.yml", factory = YamlPropertySourceFactory.class)
@ConfigurationProperties(prefix = "partner")
public class PartnerMessage {

    private String searchName;

    private String searchParent;

    private String searchDelegate;

    private String searchNote;

    private String searchStatus;

    private String onActive;

    private String partnerExist;

    private String onParent;
    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public String getSearchParent() {
        return searchParent;
    }

    public void setSearchParent(String searchParent) {
        this.searchParent = searchParent;
    }

    public String getSearchDelegate() {
        return searchDelegate;
    }

    public void setSearchDelegate(String searchDelegate) {
        this.searchDelegate = searchDelegate;
    }

    public String getSearchNote() {
        return searchNote;
    }

    public void setSearchNote(String searchNote) {
        this.searchNote = searchNote;
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

    public String getPartnerExist() {
        return partnerExist;
    }

    public void setPartnerExist(String partnerExist) {
        this.partnerExist = partnerExist;
    }

    public String getOnParent() {
        return onParent;
    }

    public void setOnParent(String onParent) {
        this.onParent = onParent;
    }
}
