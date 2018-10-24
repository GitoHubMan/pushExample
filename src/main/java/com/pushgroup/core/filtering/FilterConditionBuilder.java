package com.pushgroup.core.filtering;

import com.pushgroup.core.domain.Browser;
import com.pushgroup.core.domain.Category;
import com.pushgroup.core.mapper.SubscriptionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class FilterConditionBuilder {

    @Autowired
    private SubscriptionMapper subscribeMapper;

    public List<Condition> buildInitConditionList() {
        List<Condition> res = new ArrayList<>();

        Condition idCondition = new Condition("sub.id", "Id", null,
                Operator.EQUAL.getSign(), null, null, ViewElement.INPUT.name());
        Condition categoryCondition = new Condition("cat.name", "Category",
                subscribeMapper.getAllCategories().stream().map(Category::getName).collect(Collectors.toList()), Operator.EQUAL.getSign(),
                null, null, ViewElement.SELECT.name());
        Condition landingLangCondition = new Condition("sub.landing_language", "Landing Language",
                subscribeMapper.getAllLandingLanguages(), Operator.EQUAL.getSign(), null, null, ViewElement.SELECT.name());
        
        List<String> browserNames = subscribeMapper.getAllBrowsersNames();
        List<Condition> browserVersions = new ArrayList<>();
        for(String browserName : browserNames) {
            browserVersions.add(new Condition("sub.brw_major_version", browserName + " versions",
                    subscribeMapper.getAllMajorVersionsByBrowserName(browserName), Operator.EQUAL.getSign(), null, browserName, ViewElement.SELECT.name()));
        }
        Condition browserCondition = new Condition("sub.brw_name", "Browser", browserNames,
                Operator.EQUAL.getSign(), browserVersions, null, ViewElement.SELECT.name());

        Condition browserLangCondition = new Condition("sub.brw_language", "Browser Language",
                subscribeMapper.getAllBrowserLanguages(), Operator.EQUAL.getSign(), null, null, ViewElement.SELECT.name());

        Condition countryNameCondition = new Condition("sub.country_name", "Country Name", subscribeMapper.getAllCountryNames(),
                Operator.EQUAL.getSign(), null, null, ViewElement.SELECT.name());
        Condition cityNameCondition = new Condition("sub.city_name", "City Name", subscribeMapper.getAllCityNames(),
                Operator.EQUAL.getSign(), null, null, ViewElement.SELECT.name());

        List<String> osNames = subscribeMapper.getAllOsNames();
        List<Condition> osVersions = new ArrayList<>();
        for(String osName : osNames) {
            osVersions.add(new Condition("sub.os_version", osName + " versions",
                    subscribeMapper.getAllVersionsByOsName(osName), Operator.EQUAL.getSign(), null, osName, ViewElement.SELECT.name()));
        }
        Condition osCondition = new Condition("sub.os_name", "Os", osNames,
                Operator.EQUAL.getSign(), osVersions, null, ViewElement.SELECT.name());

        res.add(idCondition);
        res.add(osCondition);
        res.add(countryNameCondition);
        res.add(cityNameCondition);
        res.add(browserCondition);
        res.add(browserLangCondition);
        res.add(categoryCondition);
        res.add(landingLangCondition);
        return res;
    }
}
