package org.delroo.tas.reporter;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

import java.io.File;
import java.util.List;

public class GenerateCucumberReports {

    public static void main(String[] args) {
        ReportDetails details = ReportDetails.fromConfig();
        ReportingArtifacts artifacts = new ReportingArtifacts(details.getSourceFolder());
        artifacts.reduceDuplicatedFeature();
        artifacts.moveScenariosIntoParentFeature();
        generateSampleReport(artifacts.getArtifacts(), details);
    }

    private static void generateSampleReport(final List<String> jsonFiles, ReportDetails details) {
        File reportOutputDirectory = new File(details.getOutputFolder());

        Configuration configuration = new Configuration(reportOutputDirectory, "DelRoo TAS");
        configuration.setParallelTesting(details.getBuild().isParallel());
        configuration.setRunWithJenkins(details.getBuild().isJenkins());
        configuration.setBuildNumber(String.valueOf(details.getBuild().getNumber()));
        configuration.addClassifications("Platform", details.getBuild().getPlatform());
        configuration.addClassifications("Browser", details.getBuild().getBrowser());
        configuration.addClassifications("Branch", details.getBuild().getBranch());

        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
        reportBuilder.generateReports();
    }
}
