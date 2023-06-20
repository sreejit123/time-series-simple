package org.sreejit.timeseriessimple.db;

import org.springframework.stereotype.Component;
import org.sreejit.timeseriessimple.models.db.TransactionChain;

import java.util.Map;

@Component
public record TransactionDb(
        Map<String, TransactionChain> accountNumberToTransactionMap
) {}
