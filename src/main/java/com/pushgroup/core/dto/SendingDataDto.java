package com.pushgroup.core.dto;

import com.pushgroup.core.filtering.Condition;

import java.util.List;

public class SendingDataDto {
    private List<Condition> conditions;
    private PayloadDto payload;

    public SendingDataDto() {
    }

    public List<Condition> getConditions() {
        return conditions;
    }

    public void setConditions(List<Condition> conditions) {
        this.conditions = conditions;
    }

    public PayloadDto getPayload() {
        return payload;
    }

    public void setPayload(PayloadDto payload) {
        this.payload = payload;
    }
}
