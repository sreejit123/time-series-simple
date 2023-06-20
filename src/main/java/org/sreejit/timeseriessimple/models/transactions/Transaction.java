package org.sreejit.timeseriessimple.models.transactions;

import java.util.UUID;

public record Transaction(

        UUID id,

        TransactionDirection direction,

        Long timestamp,

        String accountNumber,

        Double amount
) {}


