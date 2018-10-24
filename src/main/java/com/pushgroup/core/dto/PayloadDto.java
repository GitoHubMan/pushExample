package com.pushgroup.core.dto;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.pushgroup.core.domain.Payload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class PayloadDto {
    private static final Logger LOGGER = LoggerFactory.getLogger(PayloadDto.class);
    
    private String title;
    private String body;
    private String data;
    private String iconUrl;
    private String imageUrl;
    private String badgeUrl;
    private String soundUrl;
    private List<Integer> vibrate;
    private String dir;
    private String tag;
    private Boolean requireInteraction = true;
    private Boolean renotify = true;
    private Boolean silent;
    private List<ActionDto> actions;
    private Timestamp timestamp;
    private String offerUrl;
    private String offerName;
    private String accountName;

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

    public List<Integer> getVibrate() {
        return vibrate;
    }

    public void setVibrate(List<Integer> vibrate) {
        this.vibrate = vibrate;
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

    public List<ActionDto> getActions() {
        return actions;
    }

    public void setActions(List<ActionDto> actions) {
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

    public Payload toDomain() {
        Payload res = new Payload();
        res.setTitle(this.title);
        res.setBody(this.body);
        res.setData(this.data);
        res.setIconUrl(this.iconUrl);
        res.setImageUrl(this.imageUrl);
        res.setBadgeUrl(this.badgeUrl);
        res.setSoundUrl(this.soundUrl);
        res.setDir(this.dir);
        res.setTag(this.tag);
        res.setRequireInteraction(this.requireInteraction);
        res.setRenotify(this.renotify);
        res.setSilent(this.silent);

//        try {
//            Gson gson = new Gson();
//            String actsAsJson = gson.toJson(this.actions);
//            res.setActionsAsJson(actsAsJson);
//        } catch (Exception e) {}

        if(!CollectionUtils.isEmpty(this.actions)) {
            List<Payload.Action> actions = new ArrayList<>();
            JsonArray jArr = new JsonArray();
            try {
                for(ActionDto dto : this.actions) {
                    JsonObject jObj = new JsonObject();
                    jObj.addProperty("action", dto.getAction());
                    jObj.addProperty("title", dto.getTitle());
                    jObj.addProperty("icon", dto.getIcon());
                    jArr.add(jObj);
                    actions.add(new Payload.Action(dto.getAction(), dto.getTitle(), dto.getIcon(), dto.getUrl()));
                }
                res.setActions(actions);
                res.setActionsAsJson(jArr.toString());
            } catch (Exception e) {
                LOGGER.error("Failed to parse actions. Error: {}", e);
            }
        }

        if(!CollectionUtils.isEmpty(this.vibrate)) {
            try {
                Gson gson = new Gson();
                String vibsAsJson = gson.toJson(this.vibrate);
                res.setVibrateAsJson(vibsAsJson);
            } catch (Exception e) {
                LOGGER.error("Failed to parse vibrate. Error: {}", e);
            }
        }
        res.setSilent(this.silent);
        res.setOfferUrl(this.offerUrl);
        res.setOfferName(this.offerName);
        res.setAccountName(this.accountName);
        return res;
    }




    private class ActionDto {
        private String action;
        private String title;
        private String icon;
        private String url;

        public ActionDto() {
        }

        public ActionDto(String action, String title, String icon, String url) {
            this.action = action;
            this.title = title;
            this.icon = icon;
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

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
