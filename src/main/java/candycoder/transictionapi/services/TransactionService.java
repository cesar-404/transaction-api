package candycoder.transictionapi.services;

import candycoder.transictionapi.controllers.dtos.TransactionRequestDto;
import java.util.List;

public interface TransactionService {

    void addTransaction(TransactionRequestDto transactionRequestDto);

    void clearTransactions();

    List<TransactionRequestDto> getAllTransactions(Integer timeInterval);

    List<TransactionRequestDto> getTransactionDtosList();

}
