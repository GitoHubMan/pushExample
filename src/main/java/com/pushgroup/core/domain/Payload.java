package com.pushgroup.core.domain;

import java.sql.Timestamp;
import java.util.List;

public class Payload {
    private Long id;
    private String title;
    private String body;
    private String data;
    private String iconUrl;
    private String imageUrl;
    private String badgeUrl;
    private String soundUrl;
    private String vibrateAsJson;
    private String dir;
    private String tag;
    private Boolean requireInteraction;
    private Boolean renotify;
    private Boolean silent;
    private List<Action> actions;
    private String actionsAsJson;
    private Timestamp timestamp;
    private String offerUrl;
    private String offerName;
    private String accountName;
    private Long subTotal;
    private String createdBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getBadgeUrl() {
        return badgeUrl;
    }

    public void setBadgeUrl(String badgeUrl) {
        this.badgeUrl = badgeUrl;
    }

    public String getSoundUrl() {
        return soundUrl;
    }

    public void setSoundUrl(String soundUrl) {
        this.soundUrl = soundUrl;
    }

    public String getVibrateAsJson() {
        return vibrateAsJson;
    }

    public void setVibrateAsJson(String vibrateAsJson) {
        this.vibrateAsJson = vibrateAsJson;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Boolean getRequireInteraction() {
        return requireInteraction;
    }

    public void setRequireInteraction(Boolean requireInteraction) {
        this.requireInteraction = requireInteraction;
    }

    public Boolean getRenotify() {
        return renotify;
    }

    public void setRenotify(Boolean renotify) {
        this.renotify = renotify;
    }

    public Boolean getSilent() {
        return silent;
    }

    public void setSilent(Boolean silent) {
        this.silent = silent;
    }

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getOfferUrl() {
        return offerUrl;
    }

    public void setOfferUrl(String offerUrl) {
        this.offerUrl = offerUrl;
    }

    public String getOfferName() {
        return offerName;
    }

    public void setOfferName(String offerName) {
        this.offerName = offerName;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Long getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Long subTotal) {
        this.subTotal = subTotal;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getActionsAsJson() {
        return actionsAsJson;
    }

    public void setActionsAsJson(String actionsAsJson) {
        this.actionsAsJson = actionsAsJson;
    }

    @Override
    public String toString() {
        return "Payload{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", data='" + data + '\'' +
                ", iconUrl='" + iconUrl + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", badgeUrl='" + badgeUrl + '\'' +
                ", soundUrl='" + soundUrl + '\'' +
                ", vibrateAsJson='" + vibrateAsJson + '\'' +
                ", dir='" + dir + '\'' +
                ", tag='" + tag + '\'' +
                ", requireInteraction=" + requireInteraction +
                ", renotify=" + renotify +
                ", silent=" + silent +
                ", actions=" + actions +
                ", actionsAsJson='" + actionsAsJson + '\'' +
                ", timestamp=" + timestamp +
                ", offerUrl='" + offerUrl + '\'' +
                ", offerName='" + offerName + '\'' +
                ", accountName='" + accountName + '\'' +
                ", subTotal=" + subTotal +
                ", createdBy='" + createdBy + '\'' +
                '}';
    }

    public static class Action {
        private String action;
        private String title;
        private String iconUrl;
        private String url;

        public Action() {
        }

        public Action(String action, String title, String iconUrl, String url) {
            this.action = action;
            this.title = title;
            this.iconUrl = iconUrl;
            this.url = url;
        }

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getIconUrl() {
            return iconUrl;
        }

        public void setIconUrl(String iconUrl) {
            this.iconUrl = iconUrl;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
