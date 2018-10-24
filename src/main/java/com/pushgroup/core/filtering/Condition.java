package com.pushgroup.core.filtering;

import java.util.List;

public class Condition {
    private String queryFieldName;
    private String displayName;
    private List<String> selectedValues;
    private List<String> allowedValues;
    private String operator;
    private List<Condition> relatedChildrenCondition;
    private String relatedParentValue;
    private String viewElementName;

    public Condition(String queryFieldName, String displayName, List<String> allowedValues,
                     String operator, List<Condition> relatedChildrenCondition, String relatedParentValue, String viewElementName) {
        this.queryFieldName = queryFieldName;
        this.displayName = displayName;
        this.allowedValues = allowedValues;
        this.operator = operator;
        this.relatedChildrenCondition = relatedChildrenCondition;
        this.relatedParentValue = relatedParentValue;
        this.viewElementName = viewElementName;
    }

    public String getQueryFieldName() {
        return queryFieldName;
    }

    public void setQueryFieldName(String queryFieldName) {
        this.queryFieldName = queryFieldName;
    }

    public List<String> getSelectedValues() {
        return selectedValues;
    }

    public void setSelectedValues(List<String> selectedValues) {
        this.selectedValues = selectedValues;
    }

    public List<String> getAllowedValues() {
        return allowedValues;
    }

    public void setAllowedValues(List<String> allowedValues) {
        this.allowedValues = allowedValues;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public List<Condition> getRelatedChildrenCondition() {
        return relatedChildrenCondition;
    }

    public void setRelatedChildrenCondition(List<Condition> relatedChildrenCondition) {
        this.relatedChildrenCondition = relatedChildrenCondition;
    }

    public String getRelatedParentValue() {
        return relatedParentValue;
    }

    public void setRelatedParentValue(String relatedParentValue) {
        this.relatedParentValue = relatedParentValue;
    }

    public String getViewElementName() {
        return viewElementName;
    }

    public void setViewElementName(String viewElementName) {
        this.viewElementName = viewElementName;
    }
}
