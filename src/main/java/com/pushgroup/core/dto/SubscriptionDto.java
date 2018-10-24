package com.pushgroup.core.dto;



import com.pushgroup.core.domain.Subscription;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

public class SubscriptionDto {
    private Long id;
    private List<String> categoryNames;
    private String sourceUrl;
    private String endpoint;
    private Timestamp expirationTime;
    private Timestamp createdTs;
    private KeyDto keys;
    private BrowserInfoDto browserInfo;
    private String landingLanguage;
    private GeoInfoDto geoInfo;
    private OsInfoDto osInfo;

    public SubscriptionDto() {
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

    public KeyDto getKeys() {
        return keys;
    }

    public void setKeys(KeyDto keys) {
        this.keys = keys;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<String> getCategoryNames() {
        return categoryNames;
    }

    public void setCategoryNames(List<String> categoryNames) {
        this.categoryNames = categoryNames;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public BrowserInfoDto getBrowserInfo() {
        return browserInfo;
    }

    public void setBrowserInfo(BrowserInfoDto browserInfo) {
        this.browserInfo = browserInfo;
    }

    public String getLandingLanguage() {
        return landingLanguage;
    }

    public void setLandingLanguage(String landingLanguage) {
        this.landingLanguage = landingLanguage;
    }

    public Timestamp getCreatedTs() {
        return createdTs;
    }

    public void setCreatedTs(Timestamp createdTs) {
        this.createdTs = createdTs;
    }

    public GeoInfoDto getGeoInfo() {
        return geoInfo;
    }

    public void setGeoInfo(GeoInfoDto geoInfo) {
        this.geoInfo = geoInfo;
    }

    public OsInfoDto getOsInfo() {
        return osInfo;
    }

    public void setOsInfo(OsInfoDto osInfo) {
        this.osInfo = osInfo;
    }

    public Subscription toDomain() {
        Subscription res = new Subscription();
        res.setId(this.id);
        res.setEndpoint(this.endpoint);
        res.setExpirationTime(this.expirationTime);
        res.setP256dh(this.keys != null? this.keys.p256dh : null);
        res.setAuth(this.keys != null? this.keys.auth : null);
        res.setSourceUrl(this.sourceUrl);
        res.setBrowserName(this.browserInfo != null? this.browserInfo.browserName : null);
        res.setBrowserMajorVersion(this.browserInfo != null? this.browserInfo.majorVersion : null);
        res.setBrowserFullVersion(this.browserInfo != null? this.browserInfo.fullVersion : null);
        res.setBrowserLanguage(this.browserInfo != null? this.browserInfo.language : null);
        res.setLandingLanguage(this.landingLanguage);
        res.setCategoryNames(this.categoryNames);
        res.setIp(this.geoInfo != null? this.geoInfo.ip : null);
        res.setCountryCode(this.geoInfo != null? this.geoInfo.countryCode : null);
        res.setCountryName(this.geoInfo != null? this.geoInfo.countryName : null);
        res.setCityName(this.geoInfo != null? this.geoInfo.cityName : null);
        res.setRegionName(this.geoInfo != null? this.geoInfo.regionName : null);
        res.setOsName(this.osInfo != null? this.osInfo.name : null);
        res.setOsVersion(this.osInfo != null? this.osInfo.version : null);
        return res;
    }

    public List<Subscription> fromDtoList(List<? extends SubscriptionDto> subscriptions) {
        if(isNull(subscriptions)) {
            return Collections.emptyList();
        }
        return subscriptions.stream().map(SubscriptionDto::toDomain).collect(Collectors.toList());
    }

    public static SubscriptionDto fromDomain(Subscription sub) {
        SubscriptionDto dto = new SubscriptionDto();
        dto.setId(sub.getId());
        dto.setEndpoint(sub.getEndpoint());
        dto.setSourceUrl(sub.getSourceUrl());
        dto.setLandingLanguage(sub.getLandingLanguage());
        dto.setCreatedTs(sub.getCreatedTs());
        dto.setKeys(new KeyDto(sub.getP256dh(), sub.getAuth()));
        dto.setBrowserInfo(new BrowserInfoDto(sub.getBrowserName(), null, sub.getBrowserMajorVersion(), null, null, sub.getBrowserLanguage()));
        dto.setGeoInfo(new GeoInfoDto(sub.getIp(), sub.getCountryCode(), sub.getCountryName(), sub.getCityName(), sub.getRegionName()));
        dto.setOsInfo(new OsInfoDto(sub.getOsName(), sub.getOsVersion()));
        return dto;
    }

    public static List<SubscriptionDto> fromDomainList(List<? extends Subscription> subscriptions) {
        if(isNull(subscriptions)){
            return Collections.emptyList();
        }
        return subscriptions.stream().map(SubscriptionDto::fromDomain).collect(Collectors.toList());
    }


    private static class KeyDto {
        private String p256dh;
        private String auth;

        public KeyDto() {
        }

        public KeyDto(String p256dh, String auth) {
            this.p256dh = p256dh;
            this.auth = auth;
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
    }

    private static class BrowserInfoDto {
        private String browserName;
        private String fullVersion;
        private String majorVersion;
        private String navAppName;
        private String navUserAgent;
        private String language;

        public BrowserInfoDto() {
        }

        public BrowserInfoDto(String browserName, String fullVersion, String majorVersion, String navAppName, String navUserAgent, String language) {
            this.browserName = browserName;
            this.fullVersion = fullVersion;
            this.majorVersion = majorVersion;
            this.navAppName = navAppName;
            this.navUserAgent = navUserAgent;
            this.language = language;
        }

        public String getBrowserName() {
            return browserName;
        }

        public void setBrowserName(String browserName) {
            this.browserName = browserName;
        }

        public String getFullVersion() {
            return fullVersion;
        }

        public void setFullVersion(String fullVersion) {
            this.fullVersion = fullVersion;
        }

        public String getMajorVersion() {
            return majorVersion;
        }

        public void setMajorVersion(String majorVersion) {
            this.majorVersion = majorVersion;
        }

        public String getNavAppName() {
            return navAppName;
        }

        public void setNavAppName(String navAppName) {
            this.navAppName = navAppName;
        }

        public String getNavUserAgent() {
            return navUserAgent;
        }

        public void setNavUserAgent(String navUserAgent) {
            this.navUserAgent = navUserAgent;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }
    }

    public static class GeoInfoDto {
        private String ip;
        private String countryCode;
        private String countryName;
        private String cityName;
        private String regionName;

        public GeoInfoDto() {
        }

        public GeoInfoDto(String ip) {
            this.ip = ip;
        }

        public GeoInfoDto(String ip, String countryCode, String countryName, String cityName, String regionName) {
            this.ip = ip;
            this.countryCode = countryCode;
            this.countryName = countryName;
            this.cityName = cityName;
            this.regionName = regionName;
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
    }

    public static class OsInfoDto {
        private String name;
        private String version;

        public OsInfoDto() {
        }

        public OsInfoDto(String name, String version) {
            this.name = name;
            this.version = version;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }
    }
}
