package org.sreejit.timeseriessimple.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.sreejit.timeseriessimple.models.events.AggregateUpdatedEvent;
import org.sreejit.timeseriessimple.models.events.AlertGeneratedEvent;
import org.sreejit.timeseriessimple.models.rules.Rule;
import org.sreejit.timeseriessimple.models.rules.RuleProperties;
import org.sreejit.timeseriessimple.models.transactions.TransactionDirection;

import java.util.Objects;

@Service
public class AlertManagerService {

    @Autowired
    private RuleProperties ruleProperties;

    @Autowired
    private ApplicationEventMulticaster applicationEventMulticaster;

    @EventListener(AggregateUpdatedEvent.class)
    public void processAggregateData(final AggregateUpdatedEvent aggregateUpdatedEvent) {
        final var bucketKey = aggregateUpdatedEvent.getBucketKey();
        final var bucketData = aggregateUpdatedEvent.getBucketData();

        for (final Rule rule : ruleProperties.rules()) {
            if (!Objects.equals(rule.transactionDirection(), bucketKey.transactionDirection())
            || !Objects.equals(rule.bucketType(), bucketKey.bucketType())) {
                continue;
            }

            var shouldAlertBeTriggered = switch(rule.operator()) {
                case LT -> ((Double) bucketData.aggregateData()) < rule.amountThreshold();
                case LTE -> ((Double) bucketData.aggregateData()) <= rule.amountThreshold();
                case GT -> ((Double) bucketData.aggregateData()) > rule.amountThreshold();
                case GTE -> ((Double) bucketData.aggregateData()) >= rule.amountThreshold();
                case EQ -> ((Double) bucketData.aggregateData()) == rule.amountThreshold();
            };

            if (shouldAlertBeTriggered) {
                applicationEventMulticaster.multicastEvent(new AlertGeneratedEvent(this, bucketKey, bucketData, rule));
            }
        }
    }
}
