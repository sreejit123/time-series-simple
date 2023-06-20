package org.sreejit.timeseriessimple.db;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class Subscription {

    private Map<Integer, List<String>> ruleToSubscribedUsers = new ConcurrentHashMap<>();

    public void subscribeUserToRule(final String user, final Integer rule) {
        final var usersForRule = ruleToSubscribedUsers.getOrDefault(rule, new ArrayList<>());
        usersForRule.add(user);
        ruleToSubscribedUsers.put(rule, usersForRule);
    }

    public List<String> fetchAllUsersForRule(final Integer rule) {
        return ruleToSubscribedUsers.getOrDefault(rule, new ArrayList<>());
    }
}
