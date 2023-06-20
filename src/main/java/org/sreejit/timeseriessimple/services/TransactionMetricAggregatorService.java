package org.sreejit.timeseriessimple.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.sreejit.timeseriessimple.db.TransactionAggregates;
import org.sreejit.timeseriessimple.db.TransactionDb;
import org.sreejit.timeseriessimple.models.BucketType;
import org.sreejit.timeseriessimple.models.db.BucketData;
import org.sreejit.timeseriessimple.models.db.BucketKey;
import org.sreejit.timeseriessimple.models.events.AggregateUpdatedEvent;
import org.sreejit.timeseriessimple.models.events.TransactionAddedEvent;
import org.sreejit.timeseriessimple.models.transactions.TransactionDirection;

@Service
public class TransactionMetricAggregatorService {

    @Autowired
    private TransactionDb transactionDb;

    @Autowired
    private TransactionAggregates aggregatesStore;

    @Autowired
    private ApplicationEventMulticaster applicationEventMulticaster;

    @EventListener(TransactionAddedEvent.class)
    public void aggregate(final TransactionAddedEvent transactionAddedEvent) {
        final var aggregates = aggregatesStore.aggregates();
        var transaction = transactionAddedEvent.getTransaction();
        final var transactions =
                transactionDb.accountNumberToTransactionMap().get(transaction.accountNumber()).transactions();

        for (final BucketType type : BucketType.values()) {
            final BucketKey bucketKey = new BucketKey(transaction.accountNumber(),
                    transaction.direction(), type);
            final BucketKey bucketKeyAll = new BucketKey(transaction.accountNumber(),
                    TransactionDirection.ALL, type);
            final var bucketData = aggregates.getOrDefault(bucketKey, new BucketData(0, 0.0));
            final var bucketDataAll = aggregates.getOrDefault(bucketKeyAll, new BucketData(0, 0.0));
            final int index = bucketData.slidingIndexOnBlock();
            int newIndex = index;
            Double newAggregate = (Double) bucketData.aggregateData();
            Double newAggregateAll = (Double) bucketDataAll.aggregateData();
            for (int i = index; i < transactions.size(); i++) {
                final var block = transactions.get(i);
                if (block.timestamp() - transaction.timestamp() > type.getMilliseconds()) {
                    newIndex = i;
                    newAggregate -= block.amount();
                    newAggregateAll -= block.amount();
                }
            }
            newAggregate += transaction.amount();
            newAggregateAll += transaction.amount();

            final var newBucketData = new BucketData(newIndex, newAggregate);
            aggregates.put(bucketKey, newBucketData);
            applicationEventMulticaster.multicastEvent(new AggregateUpdatedEvent(this, bucketKey, newBucketData));

            final var newBucketDataAll = new BucketData(newIndex, newAggregateAll);
            aggregates.put(bucketKeyAll, newBucketDataAll);
            applicationEventMulticaster.multicastEvent(new AggregateUpdatedEvent(this, bucketKeyAll, newBucketDataAll));
        }


    }
}
