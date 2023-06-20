package org.sreejit.timeseriessimple.models.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import org.sreejit.timeseriessimple.models.db.BucketData;
import org.sreejit.timeseriessimple.models.db.BucketKey;
import org.sreejit.timeseriessimple.models.transactions.Transaction;

@Getter
public class TransactionReadEvent extends ApplicationEvent {

    private final Transaction transaction;

    public TransactionReadEvent(final Object source, final Transaction transaction) {
        super(source);
        this.transaction = transaction;
    }
}
