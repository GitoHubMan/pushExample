package com.pushgroup.core.dto;

import java.util.List;

public class SubscriptionsInfoDto {
    private List<SubscriptionDto> subscriptions;
    private int limit;
    private int offset;
    private Long total;

    public List<SubscriptionDto> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(List<SubscriptionDto> subscriptions) {
        this.subscriptions = subscriptions;
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

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
