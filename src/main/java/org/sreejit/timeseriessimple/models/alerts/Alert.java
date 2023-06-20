package org.sreejit.timeseriessimple.models.alerts;

import org.sreejit.timeseriessimple.models.rules.Rule;
import org.sreejit.timeseriessimple.models.transactions.Transaction;

public record Alert (
    Rule rule,
    Transaction transaction
) {}
