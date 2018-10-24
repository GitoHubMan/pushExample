package com.pushgroup.core.rest;



import com.pushgroup.core.domain.Payload;
import com.pushgroup.core.domain.Subscription;
import com.pushgroup.core.dto.*;
import com.pushgroup.core.filtering.Condition;
import com.pushgroup.core.service.SubscriptionService;
import com.pushgroup.security.user.PushUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController
@RequestMapping("api/subscription")
public class SubscriptionRest {
    private static final Logger LOGGER = LoggerFactory.getLogger(SubscriptionRest.class);

    @Autowired
    private SubscriptionService subscriptionService;



    @RequestMapping(path = "/subscribe", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity subscribe(@RequestBody SubscriptionDto subscriptionDto, HttpServletRequest request) {
        try {
            setClientIp(subscriptionDto, request);
            subscriptionService.subscribe(subscriptionDto.toDomain());
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            LOGGER.error("POST to api/subscription/subscribe failed. Error: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.TEXT_PLAIN).body("");
        }
    }


    @RequestMapping(path = "/send", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity send(@RequestBody SendingDataDto sendingDataDto){
        try {
            if(sendingDataDto.getPayload() == null)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.TEXT_PLAIN).body("Payload can't be null");

            List<Subscription> subscriptions = subscriptionService.getSubscriptions(sendingDataDto.getConditions());
            if(CollectionUtils.isEmpty(subscriptions))
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.TEXT_PLAIN).body("Can't find any subscriptions for given conditions");

            Payload payload = sendingDataDto.getPayload().toDomain();
            payload.setSubTotal((long) subscriptions.size());
            payload.setCreatedBy(((PushUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());

            SendingResultDto res = new SendingResultDto();
            res.setStatuses(subscriptionService.send(subscriptions, payload));

            return ResponseEntity.ok(res);
        } catch (Exception e) {
            LOGGER.error("post to api/subscription/send failed. Error: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.TEXT_PLAIN).body("");
        }
    }

    @RequestMapping(method= RequestMethod.GET, path = "/filters")
    public @ResponseBody ResponseEntity<Object> getAppTechnologies() {
        try {
            LOGGER.info("GET to api/subscription/filters");
            return ResponseEntity.ok(subscriptionService.getFilterConditions());
        } catch (Exception e) {
            LOGGER.error("GET to api/subscription/filters failed. Error: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.TEXT_PLAIN).body("");
        }
    }

    @RequestMapping(method= RequestMethod.POST)
    public @ResponseBody ResponseEntity<Object> getSubscriptions(@RequestBody List<Condition> conditions,
                                                                 @RequestParam(value = "limit", defaultValue = "10")int limit,
                                                                 @RequestParam(value = "offset", defaultValue = "0") int offset) {
        try {
            LOGGER.info("GET to api/subscription");
            SubscriptionsInfoDto infoDto = new SubscriptionsInfoDto();
            infoDto.setLimit(limit);
            infoDto.setOffset(offset);
            infoDto.setTotal(subscriptionService.getCount(conditions));
            infoDto.setSubscriptions(SubscriptionDto.fromDomainList(subscriptionService.getSubscriptions(conditions, limit, offset)));
            return ResponseEntity.ok(infoDto);
        } catch (Exception e) {
            LOGGER.error("GET to api/subscription failed. Error: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.TEXT_PLAIN).body("");
        }
    }

    private void setClientIp(SubscriptionDto subscriptionDto, HttpServletRequest request) {
        if (subscriptionDto.getGeoInfo() == null && request != null) {
            String remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (StringUtils.isEmpty(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
            subscriptionDto.setGeoInfo(new SubscriptionDto.GeoInfoDto(remoteAddr));
        }
    }

}
