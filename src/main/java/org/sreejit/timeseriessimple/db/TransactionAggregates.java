package org.sreejit.timeseriessimple.db;

import org.springframework.stereotype.Component;
import org.sreejit.timeseriessimple.models.db.BucketData;
import org.sreejit.timeseriessimple.models.db.BucketKey;

import java.util.Map;

@Component
public record TransactionAggregates(
        Map<BucketKey, BucketData> aggregates
) {}
