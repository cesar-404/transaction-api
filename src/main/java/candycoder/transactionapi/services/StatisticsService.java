package candycoder.transactionapi.services;

import candycoder.transactionapi.controllers.dtos.StatisticsResponseDto;

public interface StatisticsService {

    StatisticsResponseDto calculateTransactionStatistics(Integer searchInterval);

}
