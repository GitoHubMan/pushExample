package com.pushgroup.core.domain;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

public class Subscription {
    private Long id;
    private String endpoint;
    private Timestamp expirationTime;
    private String p256dh;
    private String auth;
    private String sourceUrl;
    private List<Category> categories;
    private List<String> categoryNames;
    private String landingLanguage;
    private String browserName;
    private String browserMajorVersion;
    private String browserFullVersion;
    private String browserLanguage;
    private Long browserId;
    private Timestamp createdTs;
    private String ip;
    private String countryCode;
    private String countryName;
    private String cityName;
    private String regionName;
    private Long ip2locationId;
    private String osName;
    private String osVersion;

    public Subscription() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public Timestamp getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(Timestamp expirationTime) {
        this.expirationTime = expirationTime;
    }

    public String getP256dh() {
        return p256dh;
    }

    public void setP256dh(String p256dh) {
        this.p256dh = p256dh;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public String getBrowserName() {
        return browserName;
    }

    public void setBrowserName(String browserName) {
        this.browserName = browserName;
    }

    public String getLandingLanguage() {
        return landingLanguage;
    }

    public String getBrowserMajorVersion() {
        return browserMajorVersion;
    }

    public void setBrowserMajorVersion(String browserMajorVersion) {
        this.browserMajorVersion = browserMajorVersion;
    }

    public void setLandingLanguage(String landingLanguage) {
        this.landingLanguage = landingLanguage;
    }

    public String getBrowserLanguage() {
        return browserLanguage;
    }

    public void setBrowserLanguage(String browserLanguage) {
        this.browserLanguage = browserLanguage;
    }

    public Long getBrowserId() {
        return browserId;
    }

    public void setBrowserId(Long browserId) {
        this.browserId = browserId;
    }

    public Timestamp getCreatedTs() {
        return createdTs;
    }

    public void setCreatedTs(Timestamp createdTs) {
        this.createdTs = createdTs;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public String getBrowserFullVersion() {
        return browserFullVersion;
    }

    public void setBrowserFullVersion(String browserFullVersion) {
        this.browserFullVersion = browserFullVersion;
    }

    public List<String> getCategoryNames() {
        return categoryNames;
    }

    public void setCategoryNames(List<String> categoryNames) {
        this.categoryNames = categoryNames;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public Long getIp2locationId() {
        return ip2locationId;
    }

    public void setIp2locationId(Long ip2locationId) {
        this.ip2locationId = ip2locationId;
    }

    public String getOsName() {
        return osName;
    }

    public void setOsName(String osName) {
        this.osName = osName;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "id=" + id +
                ", endpoint='" + endpoint + '\'' +
                ", expirationTime=" + expirationTime +
                ", p256dh='" + p256dh + '\'' +
                ", auth='" + auth + '\'' +
                ", sourceUrl='" + sourceUrl + '\'' +
                ", categories=" + categories +
                ", categoryNames=" + categoryNames +
                ", landingLanguage='" + landingLanguage + '\'' +
                ", browserName='" + browserName + '\'' +
                ", browserMajorVersion='" + browserMajorVersion + '\'' +
                ", browserFullVersion='" + browserFullVersion + '\'' +
                ", browserLanguage='" + browserLanguage + '\'' +
                ", browserId=" + browserId +
                ", createdTs=" + createdTs +
                ", ip='" + ip + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", countryName='" + countryName + '\'' +
                ", cityName='" + cityName + '\'' +
                ", regionName='" + regionName + '\'' +
                ", ip2locationId=" + ip2locationId +
                ", osName=" + osName +
                ", osVersion=" + osVersion +
                '}';
    }
}
