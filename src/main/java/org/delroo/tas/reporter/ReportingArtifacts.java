package org.delroo.tas.reporter;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.delroo.tas.util.StringUtils.isNullOrEmpty;

public class ReportingArtifacts {

    private final String location;
    private Map<Feature, List> aggregatedReports = new HashMap<>();

    public ReportingArtifacts(String location) {
        if (isNullOrEmpty(location))
            throw new IllegalArgumentException("Cannot analyse reporting artifacts from null or empty location");
        this.location = location;
    }

    public List<String> getArtifacts() {
        File[] reportingArtifacts = new File(location).listFiles();

        if (reportingArtifacts == null) {
            throw new RuntimeException("Could not load reporting artifacts");
        }

        return Arrays.stream(reportingArtifacts)
                .map(File::getAbsolutePath)
                .filter(file -> file.endsWith(".json"))
                .collect(Collectors.toList());
    }

    @SuppressWarnings("unchecked")
    public void reduceDuplicatedFeature() {
        getArtifacts().forEach(currentFilePath -> {
            File currentFile = new File(currentFilePath);
            Feature currentFeature = new Feature(currentFile);
            if (!aggregatedReports.containsKey(currentFeature)) {
                aggregatedReports.put(currentFeature, currentFeature.getElements());
            } else {
                aggregatedReports.get(currentFeature).addAll(currentFeature.getElements());
                FileUtils.deleteQuietly(currentFile);
            }
        });
    }

    public void moveScenariosIntoParentFeature() {
        getArtifacts().forEach(jsonFilePath -> {
            Feature feature = new Feature(jsonFilePath);
            feature.setElements(aggregatedReports.get(feature));
            feature.persist();
        });
    }
}
