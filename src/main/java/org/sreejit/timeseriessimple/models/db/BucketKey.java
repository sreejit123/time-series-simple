package org.sreejit.timeseriessimple.models.db;

import lombok.EqualsAndHashCode;
import org.sreejit.timeseriessimple.models.BucketType;
import org.sreejit.timeseriessimple.models.transactions.TransactionDirection;

@EqualsAndHashCode
public record BucketKey (
    String accountNumber,
    TransactionDirection transactionDirection,
    BucketType bucketType
) {}
