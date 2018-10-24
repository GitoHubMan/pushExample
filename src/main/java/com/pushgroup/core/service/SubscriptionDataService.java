package com.pushgroup.core.service;



import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.pushgroup.core.domain.*;
import com.pushgroup.core.filtering.Condition;
import com.pushgroup.core.filtering.FilterConditionBuilder;
import com.pushgroup.core.mapper.SubscriptionMapper;
import com.pushgroup.core.util.Helper;
import com.pushgroup.security.user.PushUserDetails;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.security.Security;
import java.util.*;


@Service
public class SubscriptionDataService implements SubscriptionService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SubscriptionDataService.class);

    @Autowired
    private SubscriptionMapper subscribeMapper;
    @Autowired
    private FilterConditionBuilder filterConditionBuilder;
    @Autowired
    private Sender sender;


    @Override
    public void subscribe(Subscription subscription) {
        LOGGER.info("Start to subscribe.");
        insertSubscription(subscription);
        insertSubscriptionCategory(subscription);
        LOGGER.info("Subscribed successfully.");
    }

    private void insertSubscription(Subscription subscription) {
        LOGGER.info("Start to insert Subscription[{}]", subscription);
        if(StringUtils.isEmpty(subscription.getCountryCode())) {
            try{
                IpLocation ipLocation = subscribeMapper.getIpLocationByIp(subscription.getIp());
                if(ipLocation != null) {
                    subscription.setCountryCode(ipLocation.getCountryCode());
                    subscription.setCountryName(ipLocation.getCountryName());
                    subscription.setCityName(ipLocation.getCityName());
                    subscription.setRegionName(ipLocation.getRegionName());
                    subscription.setIp2locationId(ipLocation.getId());
                }  else {
                    LOGGER.error("Can't find GEO Info for IP = {}", subscription.getIp());
                }
            } catch (Exception e) {
                LOGGER.error("Can't getIpLocationByIp. Error: " + e);
            }
        }
        subscribeMapper.insertSubscription(subscription);
        LOGGER.info("Inserted Subscription successfully.");
    }

    private void insertSubscriptionCategory(Subscription subscription) {
        LOGGER.info("Start to insert SubscriptionCategory.");
        if(!StringUtils.isEmpty(subscription.getCategoryNames())) {
            for(String catName : subscription.getCategoryNames()) {
                Category category = subscribeMapper.getCategoryByName(catName);
                if(category != null)
                    subscribeMapper.insertSubCatRef(subscription.getId(), category.getId());
                else {
                    category = new Category(catName, null);
                    subscribeMapper.insertCategory(category);
                    subscribeMapper.insertSubCatRef(subscription.getId(), category.getId());
                }
            }
        }
        LOGGER.info("Inserted SubscriptionCategory successfully.");
    }

    @Override
    public HashMap<Integer, Integer> send(List<Subscription> subscriptions, Payload payload) {
        LOGGER.info("Start SubscriptionDataService.send for Payload[{}]", payload);
        HashMap<Integer, Integer> sendingRes = sender.send(subscriptions, payloadToByteArray(payload));

        insertPayload(payload);
        LOGGER.info("Finish SubscriptionDataService.send");
        return sendingRes;
    }

    private void insertPayload(Payload payload) {
        LOGGER.info("Start to insert Payload[{}]", payload);
        try {
            subscribeMapper.insertPayload(payload);
            LOGGER.info("Finish inserting Payload successfully.");
        } catch (Exception e) {
            LOGGER.error("Failed to insert Payload. Error: ", e);
        }
    }

    @Override
    public List<Condition> getFilterConditions() {
        return filterConditionBuilder.buildInitConditionList();
    }

    @Override
    public List<Subscription> getSubscriptions(List<Condition> conditions) {
        LOGGER.info("Start to get getSubscriptions");
        String condition = Helper.buildWhereClause(conditions);
        return subscribeMapper.getSubscriptionsByCondition(condition);
    }

    @Override
    public List<Subscription> getSubscriptions(List<Condition> conditions, int limit, int offset) {
        LOGGER.info("Start to get getSubscriptionsByConditionAndLimitAndOffset");
        String condition = Helper.buildWhereClause(conditions);
        return subscribeMapper.getSubscriptionsByConditionAndLimitAndOffset(condition, limit, offset);
    }

    @Override
    public Long getCount(List<Condition> conditions) {
        LOGGER.info("Start to get subscription");
        String condition = Helper.buildWhereClause(conditions);
        return subscribeMapper.getCountByCondition(condition);
    }

    private byte[] payloadToByteArray(Payload payload) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("title", payload.getTitle());
        jsonObject.addProperty("body", payload.getBody());
        jsonObject.add("data", buildDataJson(payload));
        jsonObject.addProperty("iconUrl", payload.getIconUrl());
        jsonObject.addProperty("imageUrl", payload.getImageUrl());
        jsonObject.addProperty("badgeUrl", payload.getBadgeUrl());
        jsonObject.addProperty("soundUrl", payload.getSoundUrl());
        jsonObject.addProperty("vibrate", payload.getVibrateAsJson());
        
        if(!CollectionUtils.isEmpty(payload.getActions())) {
            JsonArray actArr = new JsonArray();
            for(Payload.Action action : payload.getActions()) {
                JsonObject actJson = new JsonObject();
                actJson.addProperty("action", action.getAction());
                actJson.addProperty("title", action.getTitle());
                actJson.addProperty("icon", action.getIconUrl());
                actArr.add(actJson);
            }
            jsonObject.add("actions", actArr);
        }
        jsonObject.addProperty("dir", payload.getDir());
        jsonObject.addProperty("tag", payload.getTag());
        jsonObject.addProperty("requireInteraction", payload.getRequireInteraction());
        jsonObject.addProperty("renotify", payload.getRenotify());
        jsonObject.addProperty("silent", payload.getSilent());
        jsonObject.addProperty("timestamp", payload.getTimestamp() != null? payload.getTimestamp().toString() : null);

        return jsonObject.toString().getBytes();
    }

    private JsonObject buildDataJson(Payload payload) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("offerUrl", payload.getOfferUrl());
        jsonObject.addProperty("formData", payload.getData());

        if(!CollectionUtils.isEmpty(payload.getActions())) {
            JsonArray actUrls = new JsonArray();
            for(Payload.Action action : payload.getActions()) {
                JsonObject actUrl = new JsonObject();
                actUrl.addProperty("action", action.getAction());
                actUrl.addProperty("url", action.getUrl());
                actUrls.add(actUrl);
            }
            jsonObject.add("actionsUrls", actUrls);
        }
        return jsonObject;
    }

}
