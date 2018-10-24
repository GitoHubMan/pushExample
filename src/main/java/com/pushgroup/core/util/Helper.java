package com.pushgroup.core.util;

import com.pushgroup.core.dto.FiltersDto;
import com.pushgroup.core.filtering.Condition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

public final class Helper {
    public final static int BCRYPT_SALT_LENGTH = 11;
    public final static String ANONYMOUS_NAME = "anonymous_name";
    public final static String REQUEST_USERNAME_ATTR_NAME = "username";
    public final static String REQUEST_PASSWORD_ATTR_NAME = "password";
    private static final Logger LOGGER = LoggerFactory.getLogger(Helper.class);


    public static String buildWhereClause(List<Condition> conditions) {
        LOGGER.info("Start to build whereClause");
        StringBuilder sb = new StringBuilder();
        int condIdx = 0;

        for(Condition condition : conditions) {
            if(CollectionUtils.isEmpty(condition.getSelectedValues()) || (condition.getSelectedValues().size() == 1 && StringUtils.isEmpty(condition.getSelectedValues().get(0))))
                continue;
            if(condIdx > 0)
                sb.append(" and ");

            sb.append("(");
            int valIdx = 0;
            for(String value : condition.getSelectedValues()) {
                if(valIdx > 0)
                    sb.append(" or ");

                sb.append("(");
                sb.append(condition.getQueryFieldName() + condition.getOperator() + "'" + value + "'");

                if(condition.getRelatedChildrenCondition() != null) {
                    for(Condition childCondition : condition.getRelatedChildrenCondition()) {
                        if(CollectionUtils.isEmpty(childCondition.getSelectedValues()) ||
                                (childCondition.getSelectedValues().size() == 1 && StringUtils.isEmpty(childCondition.getSelectedValues().get(0))) ||
                                !childCondition.getRelatedParentValue().equals(value))
                            continue;

                        sb.append(" and " + childCondition.getQueryFieldName() + " in(");
                        int childValIdx = 0;
                        for(String childValue : childCondition.getSelectedValues()) {
                            if(childValIdx > 0)
                                sb.append(",");
                            sb.append("'" + childValue + "'");
                            childValIdx++;
                        }
                        sb.append(")");
                    }
                }
                sb.append(")");
                valIdx++;
            }
            sb.append(")");
            condIdx++;
        }

        LOGGER.info("Built whereClause successfully res = {}", sb.toString());
        return sb.toString();
    }
}
