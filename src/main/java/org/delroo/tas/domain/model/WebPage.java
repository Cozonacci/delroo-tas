package org.delroo.tas.domain.model;

import java.time.LocalDateTime;
import java.util.List;

import static org.delroo.tas.util.ContentValidator.containsIgnoreCases;
import static org.delroo.tas.util.ContentValidator.evaluateExpression;
import static org.delroo.tas.util.StringUtils.getHtmlText;
import static org.delroo.tas.util.StringUtils.getUniqueWords;

public class WebPage extends WebResource {

    private String type;
    private String sectionId;
    private String sectionName;
    private LocalDateTime webPublicationDate;
    private Fields fields;
    private boolean isHosted;
    private List<Edition> editions;

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

    public List<Edition> getEditions() {
        return editions;
    }

    public void setEditions(List<Edition> editions) {
        this.editions = editions;
    }

    public boolean sectionRelatesTo(String query) {
        boolean result = containsIgnoreCases(getId()).test(query);
        System.out.println(String.format("Checking that resource [%s] section matches query [%s]: %s",
                getId(), query, result));
        return result;
    }

    public boolean contentRelatesTo(String query) {
        final String simplifiedContent = getUniqueWords(getHtmlText(fields.getBody())).toString();
        boolean result = evaluateExpression(containsIgnoreCases(simplifiedContent), query);
        System.out.println(String.format("Checking that resource [%s] content matches query [%s]: %s",
                getId(), query, result));
        return result;
    }

}