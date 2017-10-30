package org.delroo.tas.reporter;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigBeanFactory;
import com.typesafe.config.ConfigFactory;

public class ReportDetails {
    private String name;
    private String sourceFolder;
    private String outputFolder;
    private Build build;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSourceFolder() {
        return sourceFolder;
    }

    public void setSourceFolder(String sourceFolder) {
        this.sourceFolder = sourceFolder;
    }

    public String getOutputFolder() {
        return outputFolder;
    }

    public void setOutputFolder(String outputFolder) {
        this.outputFolder = outputFolder;
    }

    public Build getBuild() {
        return build;
    }

    public void setBuild(Build build) {
        this.build = build;
    }

    public static ReportDetails fromConfig() {
        Config config = ConfigFactory.load();
        return ConfigBeanFactory.create(config.getConfig("app.execution.report"), ReportDetails.class);
    }
}
