package com.pushgroup.core.filtering;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Operator {
    EQUAL("eq", "="),
    NOT_EQUAL("not eq", "!="),
    LESS_THAN("lt", "<"),
    LESS_THAN_OR_EQUAL("lt", "<="),
    GREATER_THAN("gt", ">"),
    GREATER_THAN_OR_EQUAL("gt or eq", ">="),
    MATCHES_ANY("in", "in"),
    NOT_MATCHES_ANY("not in", "not in"),
    LIKE("like", "like"),
    CONTAINS("contains", "~"),
    NOT_CONTAINS("not contains", "!~"),
    IS("is", "is"),
    IS_NOT("is not", "is not");

    private String shortName;
    private String sign;

    Operator(String shortName) {
        this.shortName = shortName;
    }

    Operator(String shortName, String sign) {
        this.shortName = shortName;
        this.sign = sign;
    }

    public String getShortName() {
        return shortName;
    }

    public String getSign() {
        return sign;
    }

    public static Operator getByShortName(String shortName) {
        for (Operator Operator : Operator.values()) {
            if (Operator.getShortName().equals(shortName)) {
                return Operator;
            }
        }
        return null;
    }

    public static List<Operator> getOperatorsExcept(Operator... excludeOperators) {
        List<Operator> res = new ArrayList<>();
        for(Operator Operator : Operator.values()) {
            if(!Arrays.stream(excludeOperators).anyMatch(op -> op.name().equalsIgnoreCase(Operator.name())))
                res.add(Operator);
        }
        return res;
    }

    public static List<Operator> getOperatorsExceptContains() {
        return getOperatorsExcept(Operator.CONTAINS, Operator.NOT_CONTAINS);
    }

    public static Operator getBySign(String sign) {
        for (Operator Operator : Operator.values()) {
            if (Operator.getSign().equalsIgnoreCase(sign)) {
                return Operator;
            }
        }
        return null;
    }
}
