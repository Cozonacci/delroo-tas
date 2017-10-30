package org.delroo.tas.reporter;

public class Build {
    private int number;
    private boolean parallel;
    private boolean jenkins;
    private String platform;
    private String browser;
    private String branch;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isParallel() {
        return parallel;
    }

    public void setParallel(boolean parallel) {
        this.parallel = parallel;
    }

    public boolean isJenkins() {
        return jenkins;
    }

    public void setJenkins(boolean jenkins) {
        this.jenkins = jenkins;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }
}