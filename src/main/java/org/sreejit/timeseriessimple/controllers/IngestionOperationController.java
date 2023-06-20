package org.sreejit.timeseriessimple.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.sreejit.timeseriessimple.services.IngestionService;

@RestController()
@RequestMapping("/ingestion")
public class IngestionOperationController {

    @Autowired
    private IngestionService ingestionService;

    @PostMapping("/start")
    public String startIngestion() {
        ingestionService.startIngestion();
        return "OK";
    }

}
