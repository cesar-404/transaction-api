package candycoder.transictionapi.services.impl;

import candycoder.transictionapi.controllers.dtos.TransactionRequestDto;
import candycoder.transictionapi.infra.exceptions.UnprocessableEntity;
import candycoder.transictionapi.services.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final Logger log = LoggerFactory.getLogger(TransactionServiceImpl.class);
    private final List<TransactionRequestDto> transactionDtosList = new ArrayList<>();

    public void addTransaction(TransactionRequestDto transactionRequestDto) {

        log.info("Validating if the transaction timestamp ({}) is after the current time ({}).",
                transactionRequestDto.timestamp(), OffsetDateTime.now());
        if (transactionRequestDto.timestamp().isAfter(OffsetDateTime.now())) {
            log.error("Transaction timestamp ({}) is after the current time ({}). Throwing UnprocessableEntity.",
                    transactionRequestDto.timestamp(), OffsetDateTime.now());
            throw new UnprocessableEntity("Timestamp cannot be after now");
        }
        log.info("Validating if the transaction ({}) is greater than 0",
                transactionRequestDto.value());
        if (transactionRequestDto.value() < 0) {
            log.error("Transaction value ({}) is negative. Throwing UnprocessableEntity.",
                    transactionRequestDto.value());
            throw new UnprocessableEntity("Value cannot be negative");
        }

        log.info("Transaction ({}) successfully validated. Adding to the transaction list.",
                transactionRequestDto.value());
        transactionDtosList.add(transactionRequestDto);
    }

    public void clearTransactions() {
        log.info("Clearing all transactions from the list.");
        transactionDtosList.clear();
    }

    public List<TransactionRequestDto> getAllTransactions(Integer timeInterval) {
        log.info("Getting transactions from the last {} seconds.",
                timeInterval);
        OffsetDateTime time = OffsetDateTime.now().minusSeconds(timeInterval);

        log.info("Filtering transactions based on timestamp. Returning filtered transaction list.");
        return transactionDtosList.stream()
                .filter(transaction -> transaction.timestamp()
                        .isAfter(time))
                .toList();
    }
}
