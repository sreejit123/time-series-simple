package org.sreejit.timeseriessimple.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.sreejit.timeseriessimple.db.TransactionDb;
import org.sreejit.timeseriessimple.models.db.TransactionChain;
import org.sreejit.timeseriessimple.models.events.TransactionAddedEvent;
import org.sreejit.timeseriessimple.models.events.TransactionReadEvent;
import org.sreejit.timeseriessimple.models.transactions.Transaction;

import java.util.LinkedList;

@Service
public class TransactionDbManagerService {

    @Autowired
    private TransactionDb transactionDb;

    @Autowired
    private ApplicationEventMulticaster applicationEventMulticaster;


    @EventListener(TransactionReadEvent.class)
    public void addToDb(final TransactionReadEvent transactionReadEvent) {
        final var transaction = transactionReadEvent.getTransaction();
        final var accountNumberToTransactionMap = transactionDb.accountNumberToTransactionMap();
        final TransactionChain retrievedTransactionChain =
                accountNumberToTransactionMap.getOrDefault(transaction.accountNumber(),
                        new TransactionChain(new LinkedList<>()));
        final var transactions = retrievedTransactionChain.transactions();
        transactions.add(transaction);

        accountNumberToTransactionMap.put(transaction.accountNumber(), new TransactionChain(transactions));

        applicationEventMulticaster.multicastEvent(new TransactionAddedEvent(this, transaction));
    }

}
