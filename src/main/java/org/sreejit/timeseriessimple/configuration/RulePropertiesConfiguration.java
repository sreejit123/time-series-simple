package org.sreejit.timeseriessimple.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.sreejit.timeseriessimple.models.BucketType;
import org.sreejit.timeseriessimple.models.rules.Rule;
import org.sreejit.timeseriessimple.models.rules.RuleOperation;
import org.sreejit.timeseriessimple.models.rules.RuleProperties;
import org.sreejit.timeseriessimple.models.transactions.TransactionDirection;

import java.util.List;

@Configuration
public class RulePropertiesConfiguration {

    @Bean
    public RuleProperties getRuleProperties() {
        final Rule firstRule =
                new Rule(1, BucketType.DAY, TransactionDirection.ALL, 10000, RuleOperation.GT);

        final Rule secondRule =
                new Rule(1, BucketType.MONTH, TransactionDirection.IN, 50000, RuleOperation.GT);

        return new RuleProperties(List.of(firstRule, secondRule));

    }
}
