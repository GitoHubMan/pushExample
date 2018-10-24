package com.pushgroup.core.domain;

public class Browser {
    private Long id;
    private String name;
    private String majorVersion;
    private String fullVersion;
    private String language;

    public Browser() {
    }

    public Browser(String name, String majorVersion, String fullVersion, String language) {
        this.name = name;
        this.majorVersion = majorVersion;
        this.fullVersion = fullVersion;
        this.language = language;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMajorVersion() {
        return majorVersion;
    }

    public void setMajorVersion(String majorVersion) {
        this.majorVersion = majorVersion;
    }

    public String getFullVersion() {
        return fullVersion;
    }

    public void setFullVersion(String fullVersion) {
        this.fullVersion = fullVersion;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
