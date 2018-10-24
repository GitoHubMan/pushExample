package com.pushgroup.core.dto;

import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SendingResultDto {

    List<SendingStatusInfo> statuses;

    public List<SendingStatusInfo> getStatuses() {
        return statuses;
    }

    public void setStatuses(HashMap<Integer, Integer> domain){
        statuses = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : domain.entrySet()) {
            HttpStatus httpStatus = null;
            try { 
                httpStatus = HttpStatus.valueOf(entry.getKey());
            } catch (Exception e) {}
            statuses.add(new SendingStatusInfo(entry.getKey(), httpStatus != null? httpStatus.getReasonPhrase() : "", entry.getValue()));
        }
    }

    public void setStatuses(List<SendingStatusInfo> statuses) {
        this.statuses = statuses;
    }

    private class SendingStatusInfo {
        private int code;
        private String description;
        private int count;

        public SendingStatusInfo() {
        }

        public SendingStatusInfo(int code, String description, int count) {
            this.code = code;
            this.description = description;
            this.count = count;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }
}
