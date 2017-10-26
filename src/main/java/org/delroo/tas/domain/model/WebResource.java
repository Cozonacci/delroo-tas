package org.delroo.tas.domain.model;

import java.time.LocalDateTime;
import java.util.stream.Stream;

public class WebResource {

    private String id;
    private String type;
    private String sectionId;
    private String sectionName;
    private LocalDateTime webPublicationDate;
    private String webTitle;
    private String webUrl;
    private String apiUrl;
    private boolean isHosted;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSectionId() {
        return sectionId;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public LocalDateTime getWebPublicationDate() {
        return webPublicationDate;
    }

    public void setWebPublicationDate(LocalDateTime webPublicationDate) {
        this.webPublicationDate = webPublicationDate;
    }

    public String getWebTitle() {
        return webTitle;
    }

    public void setWebTitle(String webTitle) {
        this.webTitle = webTitle;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public boolean isHosted() {
        return isHosted;
    }

    public void setIsHosted(boolean hosted) {
        isHosted = hosted;
    }

    public boolean relatesTo(String content) {
        // Assumption: id, sectionId, sectionName, webTitle, webUrl or apiUrl should have some content specific info
        return Stream.of(this.id, this.sectionId, this.sectionName, this.webTitle, this.webUrl, this.apiUrl)
                .anyMatch(item -> item.contains(content));
    }

}