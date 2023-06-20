package org.sreejit.timeseriessimple.models.rules;

import org.sreejit.timeseriessimple.models.BucketType;
import org.sreejit.timeseriessimple.models.transactions.TransactionDirection;

public record Rule (
        int ruleNumber,
        BucketType bucketType,
        TransactionDirection transactionDirection,
        double amountThreshold,
        RuleOperation operator
) {}
