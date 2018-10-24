package com.pushgroup.core.domain;

public class IpLocation {
    private Long id;
    private Long ipFrom;
    private Long ipTo;
    private String countryCode;
    private String countryName;
    private String regionName;
    private String cityName;

    public Long getIpFrom() {
        return ipFrom;
    }

    public void setIpFrom(Long ipFrom) {
        this.ipFrom = ipFrom;
    }

    public Long getIpTo() {
        return ipTo;
    }

    public void setIpTo(Long ipTo) {
        this.ipTo = ipTo;
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

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
