package org.delroo.tas.reporter;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

import java.io.File;
import java.util.List;

public class GenerateCucumberReports {

    private static final String CUCUMBER_REPORTS_FOLDER = "target/cucumber-parallel";

    public static void main(String[] args) {
        ReportingArtifacts artifacts = new ReportingArtifacts(CUCUMBER_REPORTS_FOLDER);
        artifacts.reduceDuplicatedFeature();
        artifacts.moveScenariosIntoParentFeature();
        generateSampleReport(artifacts.getArtifacts());
    }

    private static void generateSampleReport(final List<String> jsonFiles) {
        File reportOutputDirectory = new File("target");

        Configuration configuration = new Configuration(reportOutputDirectory, "DelRoo TAS");
        configuration.setParallelTesting(false);
        configuration.setRunWithJenkins(false);
        configuration.setBuildNumber("1");
        configuration.addClassifications("Platform", "Windows");
        configuration.addClassifications("Browser", "Firefox");
        configuration.addClassifications("Branch", "release/1.0");

        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
        reportBuilder.generateReports();
    }
}
