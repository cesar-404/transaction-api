package candycoder.transictionapi.services;

import candycoder.transictionapi.controllers.dtos.StatisticsResponseDto;

public interface StatisticsService {

    StatisticsResponseDto calculateTransactionStatistics(Integer searchInterval);

}
