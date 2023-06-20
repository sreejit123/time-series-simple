package org.sreejit.timeseriessimple.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sreejit.timeseriessimple.db.Subscription;

@Slf4j
@Service
public class UserRegistrationService {

    @Autowired
    private Subscription subscription;

    public void registerUserToRule(final String user, final int rule) {
        subscription.subscribeUserToRule(user, rule);
    }
}
