package org.sreejit.timeseriessimple.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.sreejit.timeseriessimple.models.messaging.UserRequest;
import org.sreejit.timeseriessimple.services.UserRegistrationService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class EventHandler extends TextWebSocketHandler {

    private final Map<String, List<WebSocketSession>> userToSessions = new HashMap<>();

    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private UserRegistrationService userRegistrationService;

    @Override
    public void handleTextMessage(final WebSocketSession session, final TextMessage message) throws IOException {
        final UserRequest request = mapper.readValue(message.getPayload(), UserRequest.class);
        userRegistrationService.registerUserToRule(request.user(), request.rule());
        final var sessionsForUser = userToSessions.getOrDefault(request.user(), new ArrayList<>());
        sessionsForUser.add(session);
        userToSessions.put(request.user(), sessionsForUser);
        session.sendMessage(new TextMessage("OK"));

    }

    public void sendToUser(final String user, final String message) {
        if (!userToSessions.containsKey(user)) {
            log.warn("No sessions for user {}", user);
            return;
        }

        final var sessions = userToSessions.get(user);

        for (final var session : sessions) {
            if (!session.isOpen()) {
                log.warn("Session {} is not active for user {}", session.getId(), user);
            }

            try {
                session.sendMessage(new TextMessage(message));
            } catch (IOException e) {
                log.error("Error while sending message to user {} over session {}:: {}", user, session, e);
            }
        }
    }
}
