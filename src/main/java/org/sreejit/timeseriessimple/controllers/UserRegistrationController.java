package org.sreejit.timeseriessimple.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.sreejit.timeseriessimple.services.UserRegistrationService;

@Controller
public class UserRegistrationController {

    @Autowired
    UserRegistrationService userRegistrationService;

    @MessageMapping("/ws")
    public String receiveUserRequest(final int rule) throws Exception {
        userRegistrationService.registerUserToRule("abcd", rule);
        return "OK";
    }
}
