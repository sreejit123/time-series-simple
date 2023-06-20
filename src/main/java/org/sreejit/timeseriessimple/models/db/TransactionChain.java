package org.sreejit.timeseriessimple.models.db;

import org.sreejit.timeseriessimple.models.transactions.Transaction;

import java.util.LinkedList;

public record TransactionChain(
        LinkedList<Transaction> transactions
) {}
