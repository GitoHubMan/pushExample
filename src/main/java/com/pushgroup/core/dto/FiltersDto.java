package com.pushgroup.core.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FiltersDto {
    private List<String> categories = new ArrayList<>();
    private List<String> landingLanguages = new ArrayList<>();
    private List<BrowserFilter> browserFilters = new ArrayList<>();
    private int limit = 20;
    private int offset = 0;

    public FiltersDto() {
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public List<String> getLandingLanguages() {
        return landingLanguages;
    }

    public void setLandingLanguages(List<String> landingLanguages) {
        this.landingLanguages = landingLanguages;
    }

    public List<BrowserFilter> getBrowserFilters() {
        return browserFilters;
    }

    public void setBrowserFilters(List<BrowserFilter> browserFilters) {
        this.browserFilters = browserFilters;
    }

    public void setBrowserFilters(HashMap<String, List<String>> map) {
        this.browserFilters = new ArrayList<>();
        for(Map.Entry<String, List<String>> entry : map.entrySet()) {
            BrowserFilter brw = new BrowserFilter();
            brw.setName(entry.getKey());
            brw.setVersions(entry.getValue());
            this.browserFilters.add(brw);
        }
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public class BrowserFilter {
        private String name;
        private List<String> versions;

        public BrowserFilter() {
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<String> getVersions() {
            return versions;
        }

        public void setVersions(List<String> versions) {
            this.versions = versions;
        }
    }
}
