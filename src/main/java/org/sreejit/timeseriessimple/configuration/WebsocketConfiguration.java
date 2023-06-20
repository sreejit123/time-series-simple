package org.sreejit.timeseriessimple.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.sreejit.timeseriessimple.handlers.EventHandler;

@Configuration
@EnableWebSocket
public class WebsocketConfiguration implements WebSocketConfigurer {

    @Autowired
    private EventHandler eventHandler;

    @Override
    public void registerWebSocketHandlers(final WebSocketHandlerRegistry registry) {
        registry.addHandler(eventHandler, "/ws/register");
    }
}
