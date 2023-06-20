package org.sreejit.timeseriessimple.models.db;

public record BucketData(
        int slidingIndexOnBlock,
        Object aggregateData
) {}
