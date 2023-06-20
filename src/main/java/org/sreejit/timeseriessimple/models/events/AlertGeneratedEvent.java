package org.sreejit.timeseriessimple.models.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import org.sreejit.timeseriessimple.models.db.BucketData;
import org.sreejit.timeseriessimple.models.db.BucketKey;
import org.sreejit.timeseriessimple.models.rules.Rule;

@Getter
public class AlertGeneratedEvent extends ApplicationEvent {

    private final BucketKey bucketKey;
    private final BucketData bucketData;

    private final Rule rule;

    public AlertGeneratedEvent(final Object source,
                               final BucketKey bucketKey,
                               final BucketData bucketData,
                               final Rule rule) {
        super(source);
        this.bucketKey = bucketKey;
        this.bucketData = bucketData;
        this.rule = rule;
    }
}
