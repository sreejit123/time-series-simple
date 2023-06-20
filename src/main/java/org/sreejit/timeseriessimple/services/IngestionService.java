package org.sreejit.timeseriessimple.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.sreejit.timeseriessimple.TimeSeriesSimpleApplication;
import org.sreejit.timeseriessimple.mappers.TransactionInputMapper;
import org.sreejit.timeseriessimple.models.events.TransactionReadEvent;
import org.sreejit.timeseriessimple.models.transactions.Transaction;
import org.sreejit.timeseriessimple.models.transactions.TransactionInput;

import java.io.*;
import java.nio.charset.StandardCharsets;

@Service
@Slf4j
public class IngestionService {

    private final ObjectMapper mapper = new ObjectMapper();

    private final TransactionInputMapper transactionInputMapper = TransactionInputMapper.INSTANCE;

    @Autowired
    private ApplicationEventMulticaster applicationEventMulticaster;

    @Async
    public void startIngestion() {
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("transactions.json");
        InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        try (final BufferedReader br = new BufferedReader(streamReader)) {
            String line;
            while ((line = br.readLine()) != null) {
                final TransactionInput transactionInput = mapper.readValue(line, TransactionInput.class);

                applicationEventMulticaster.multicastEvent(
                        new TransactionReadEvent(this,
                                transactionInputMapper.mapInputToTransaction(transactionInput)));
            }
        }catch(final IOException e) {
            log.error("Error in reading file {}", e);
        }

    }
}
