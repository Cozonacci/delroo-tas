package org.delroo.tas.domain.model;

import java.time.LocalDateTime;

import static org.delroo.tas.util.ContentValidator.containsIgnoreCases;
import static org.delroo.tas.util.ContentValidator.evaluateExpression;
import static org.delroo.tas.util.StringUtils.getHtmlText;
import static org.delroo.tas.util.StringUtils.getUniqueWords;

public class WebResource {

    private String id;
    private String type;
    private String sectionId;
    private String sectionName;
    private LocalDateTime webPublicationDate;
    private String webTitle;
    private String webUrl;
    private String apiUrl;
    private Fields fields;
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

    public Fields getFields() {
        return fields;
    }

    public void setFields(Fields fields) {
        this.fields = fields;
    }

    public boolean isHosted() {
        return isHosted;
    }

    public void setIsHosted(boolean hosted) {
        isHosted = hosted;
    }

    public boolean relatesTo(String contentQuery) {
        final String simplifiedContent = getUniqueWords(getHtmlText(fields.getBody())).toString();
        boolean result = evaluateExpression(containsIgnoreCases(simplifiedContent), contentQuery);
        System.out.println(String.format("Checking that resource [%s] content matches query [%s]: %s",
                id, contentQuery, result));
        return result;
    }

}