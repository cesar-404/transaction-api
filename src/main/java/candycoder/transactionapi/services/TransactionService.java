package candycoder.transactionapi.services;

import candycoder.transactionapi.controllers.dtos.TransactionRequestDto;
import java.util.List;

public interface TransactionService {

    void addTransaction(TransactionRequestDto transactionRequestDto);

    void clearTransactions();

    List<TransactionRequestDto> getAllTransactions(Integer timeInterval);

    List<TransactionRequestDto> getTransactionDtosList();

}
