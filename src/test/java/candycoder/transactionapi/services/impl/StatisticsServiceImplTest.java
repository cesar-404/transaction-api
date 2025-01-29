package candycoder.transactionapi.services.impl;

import candycoder.transactionapi.controllers.dtos.StatisticsResponseDto;
import candycoder.transactionapi.controllers.dtos.TransactionRequestDto;
import candycoder.transactionapi.services.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.OffsetDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class StatisticsServiceImplTest {


    @Mock
    private TransactionService transactionService;

    @InjectMocks
    private StatisticsServiceImpl statisticsServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        TransactionRequestDto validTransaction = new TransactionRequestDto(100.0, OffsetDateTime.now().minusSeconds(60));
        TransactionRequestDto validTransaction2 = new TransactionRequestDto(200.0, OffsetDateTime.now().minusSeconds(60));

        when(transactionService.getAllTransactions(60)).thenReturn(List.of(validTransaction, validTransaction2));
    }

    @Test
    void testCalculateTransactionStatistics() {

        StatisticsResponseDto statisticsResponseDtoTest = new StatisticsResponseDto(
                2L,
                300.0,
                150.0,
                100.0,
                200.0
        );

        StatisticsResponseDto result = statisticsServiceImpl.calculateTransactionStatistics(60);

        assertNotNull(result);
        assertEquals(statisticsResponseDtoTest, result);

    }

    @Test
    void testCalculateTransactionStatisticsFailure() {
        StatisticsResponseDto statisticsResponseDtoTest = new StatisticsResponseDto(
                0L,
                0.0,
                0.0,
                0.0,
                0.0
        );

        StatisticsResponseDto result = statisticsServiceImpl.calculateTransactionStatistics(3000);

        assertNotNull(result);
        assertEquals(statisticsResponseDtoTest, result);
    }
}