package org.sreejit.timeseriessimple.models.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import org.sreejit.timeseriessimple.models.db.BucketData;
import org.sreejit.timeseriessimple.models.db.BucketKey;

@Getter
public class AggregateUpdatedEvent extends ApplicationEvent {

    private final BucketKey bucketKey;
    private final BucketData bucketData;

    public AggregateUpdatedEvent(final Object source, final BucketKey bucketKey, final BucketData bucketData) {
        super(source);
        this.bucketKey = bucketKey;
        this.bucketData = bucketData;
    }
}
