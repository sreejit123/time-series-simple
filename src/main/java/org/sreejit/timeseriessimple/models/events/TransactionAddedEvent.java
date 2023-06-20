package org.sreejit.timeseriessimple.models.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import org.sreejit.timeseriessimple.models.transactions.Transaction;

@Getter
public class TransactionAddedEvent extends ApplicationEvent {

    private final Transaction transaction;

    public TransactionAddedEvent(final Object source, final Transaction transaction) {
        super(source);
        this.transaction = transaction;
    }
}
