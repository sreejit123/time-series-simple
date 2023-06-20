package org.sreejit.timeseriessimple.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.sreejit.timeseriessimple.models.transactions.Transaction;
import org.sreejit.timeseriessimple.models.transactions.TransactionInput;

@Mapper
public interface TransactionInputMapper {

    TransactionInputMapper INSTANCE = Mappers.getMapper(TransactionInputMapper.class);

    Transaction mapInputToTransaction(TransactionInput transactionInput);

    default Long mapToLong(String string) {
        return string != null && !string.isEmpty() ? Long.parseLong(string) : null;
    }
}
