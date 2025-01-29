package candycoder.transactionapi.services.impl;

import candycoder.transactionapi.controllers.dtos.StatisticsResponseDto;
import candycoder.transactionapi.controllers.dtos.TransactionRequestDto;
import candycoder.transactionapi.services.StatisticsService;
import candycoder.transactionapi.services.TransactionService;
import io.micrometer.core.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
public class StatisticsServiceImpl implements StatisticsService {
    private final Logger log = LoggerFactory.getLogger(StatisticsServiceImpl.class);
    private final TransactionService transactionService;

    public StatisticsServiceImpl(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Override
    @Timed(value = "statistics.time.execution")
    public StatisticsResponseDto calculateTransactionStatistics(Integer searchInterval) {
        log.info("Starting to calculate transaction statistics for the last {} seconds.", searchInterval);
        List<TransactionRequestDto> transactions = transactionService.getAllTransactions(searchInterval);

        if (transactions.isEmpty()) {
            log.info("No transactions found for the last {} seconds.", searchInterval);
            return new StatisticsResponseDto(0L, 0.0, 0.0, 0.0, 0.0);
        }

        log.info("Converting transactions into statistical data (count, sum, average, min, max).");
        DoubleSummaryStatistics transactionsStatistics = transactions.stream()
                .mapToDouble(TransactionRequestDto::value).summaryStatistics();

        log.info("Transaction statistics calculated: count = {}, sum = {}, average = {}, min = {}, max = {}.",
                transactionsStatistics.getCount(),
                transactionsStatistics.getSum(),
                transactionsStatistics.getAverage(),
                transactionsStatistics.getMin(),
                transactionsStatistics.getMax());


        log.info("Returning transaction statistics.");
        return new StatisticsResponseDto(transactionsStatistics.getCount(),
                transactionsStatistics.getSum(),
                transactionsStatistics.getAverage(),
                transactionsStatistics.getMin(),
                transactionsStatistics.getMax());
    }
}
