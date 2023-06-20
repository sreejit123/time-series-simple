package org.sreejit.timeseriessimple.models.transactions;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record TransactionInput(
        @JsonProperty("tran_id")
        UUID id,

        @JsonProperty("tran_direction")
        TransactionDirection direction,

        @JsonProperty("tran_time")
        String timestamp,

        @JsonProperty("acc_nmbr")
        String accountNumber,

        @JsonProperty("tran_amnt")
        Double amount
){}
