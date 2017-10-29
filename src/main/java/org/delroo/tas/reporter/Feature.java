package org.delroo.tas.reporter;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

public final class Feature {

    public static final String ELEMENTS_XPATH = "[0].elements";

    private String id;
    private String uri;
    private List elements;

    public Feature(final String featurePath) {
        this(new File(featurePath));
    }

    public Feature(final File featureFile) {
        try {
            DocumentContext content = JsonPath.parse(featureFile);
            this.id = content.read("$[0].id").toString();
            this.uri = featureFile.getAbsolutePath();
            this.elements = content.read("$[0].elements", List.class);
        } catch (IOException e) {
            throw new RuntimeException("Could not parse feature: " + e.getMessage());
        }
    }

    public String getId() {
        return id;
    }

    public String getUri() {
        return uri;
    }

    public List getElements() {
        return elements;
    }

    public void setElements(List elements) {
        this.elements = elements;
    }

    public void persist() {
        try {
            File currentFile = new File(uri);
            DocumentContext newDoc = JsonPath.parse(currentFile).set(ELEMENTS_XPATH, elements);
            FileUtils.writeStringToFile(currentFile, newDoc.jsonString(), Charset.defaultCharset());
        } catch (IOException e) {
            throw new RuntimeException("Could not aggregate reporting files: feature " + id);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Feature feature = (Feature) o;

        return id.equals(feature.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
