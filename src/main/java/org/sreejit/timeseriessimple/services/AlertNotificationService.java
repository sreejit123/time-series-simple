package org.sreejit.timeseriessimple.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.sreejit.timeseriessimple.db.Subscription;
import org.sreejit.timeseriessimple.handlers.EventHandler;
import org.sreejit.timeseriessimple.models.events.AlertGeneratedEvent;

import java.util.List;

@Slf4j
@Service
public class AlertNotificationService {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private Subscription subscription;

    @Autowired
    private EventHandler eventHandler;

    @EventListener(AlertGeneratedEvent.class)
    public void processAggregateData(final AlertGeneratedEvent alertGeneratedEvent) {
        final int rule = alertGeneratedEvent.getRule().ruleNumber();

        final List<String> usersForRule = subscription.fetchAllUsersForRule(rule);

        log.info("Alert for rule {} triggered ", rule);

        for (final String user : usersForRule) {
            try {
                eventHandler.sendToUser(user, objectMapper.writeValueAsString(alertGeneratedEvent));
            } catch (final JsonProcessingException e) {
                log.error("Error while converting alert into notification:: {}", e);
            }
        }
    }
}
