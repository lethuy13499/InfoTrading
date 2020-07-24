package vn.vmg.infotrading.webapp.internal;

public class HeaderModel {
    private String authorization;
    private String contenttype;
    private String acceptlanguage;
    private Long portalid;
    private Long currentuserid;
    private Long issuperadmin;
    private String apiurl;

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }

    public String getContenttype() {
        return contenttype;
    }

    public void setContenttype(String contenttype) {
        this.contenttype = contenttype;
    }

    public String getAcceptlanguage() {
        return acceptlanguage;
    }

    public void setAcceptlanguage(String acceptlanguage) {
        this.acceptlanguage = acceptlanguage;
    }

    public Long getPortalid() {
        return portalid;
    }

    public void setPortalid(Long portalid) {
        this.portalid = portalid;
    }

    public Long getCurrentuserid() {
        return currentuserid;
    }

    public void setCurrentuserid(Long currentuserid) {
        this.currentuserid = currentuserid;
    }

    public Long getIssuperadmin() {
        return issuperadmin;
    }

    public void setIssuperadmin(Long issuperadmin) {
        this.issuperadmin = issuperadmin;
    }

    public String getApiurl() {
        return apiurl;
    }

    public void setApiurl(String apiurl) {
        this.apiurl = apiurl;
    }
}
