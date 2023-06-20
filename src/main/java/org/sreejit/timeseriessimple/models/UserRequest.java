package org.sreejit.timeseriessimple.models.messaging;

public record UserRequest (
        String user,
        Integer rule
) {}
