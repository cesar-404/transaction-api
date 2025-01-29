package candycoder.transictionapi.services.impl;

import candycoder.transictionapi.controllers.dtos.TransactionRequestDto;
import candycoder.transictionapi.infra.exceptions.UnprocessableEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TransactionServiceImplTest {

    private TransactionServiceImpl transactionServiceImpl;

    @BeforeEach
    void setUp() {
        transactionServiceImpl = new TransactionServiceImpl();
    }

    @Test
    void testAddTransactionSuccess() {
        TransactionRequestDto validTransaction = new TransactionRequestDto(
                100.0,
                OffsetDateTime.now().minusSeconds(200));

        transactionServiceImpl.addTransaction(validTransaction);

        assertEquals(1, transactionServiceImpl.getTransactionDtosList().size());
        assertEquals(validTransaction, transactionServiceImpl.getTransactionDtosList().getFirst());
    }

    @Test
    void testAddTransactionFailureTimestamp() {
        TransactionRequestDto futureTransaction = new TransactionRequestDto(100.0,
                OffsetDateTime.now().plusDays(1));

        UnprocessableEntity unprocessableEntity = assertThrows(UnprocessableEntity.class, () ->
                transactionServiceImpl.addTransaction(futureTransaction));

        assertEquals("Timestamp cannot be after now", unprocessableEntity.getMessage());
    }

    @Test
    void testAddTransactionFailureNegativeValue() {
        TransactionRequestDto negativeTransaction = new TransactionRequestDto(-100.0,
                OffsetDateTime.now().minusSeconds(100));

        UnprocessableEntity unprocessableEntity = assertThrows(UnprocessableEntity.class, () ->
                transactionServiceImpl.addTransaction(negativeTransaction));

        assertEquals("Value cannot be negative", unprocessableEntity.getMessage());
    }

    @Test
    void testClearTransactionSuccess() {
        TransactionRequestDto validTransaction = new TransactionRequestDto(
                100.0,
                OffsetDateTime.now().minusSeconds(200));

        transactionServiceImpl.addTransaction(validTransaction);
        transactionServiceImpl.clearTransactions();

        assertEquals(0, transactionServiceImpl.getTransactionDtosList().size());
    }

    @Test
    void testGetAllTransactionsSuccess() {
        TransactionRequestDto validTransaction = new TransactionRequestDto(
                100.0,
                OffsetDateTime.now().minusSeconds(20));
        TransactionRequestDto validTransaction2 = new TransactionRequestDto(
                100.0,
                OffsetDateTime.now().minusSeconds(20));

        transactionServiceImpl.addTransaction(validTransaction);
        transactionServiceImpl.addTransaction(validTransaction2);
        List<TransactionRequestDto> list = transactionServiceImpl.getAllTransactions(60);

        assertEquals(2, list.size());
        assertEquals(validTransaction, list.getFirst());
        assertEquals(validTransaction2, list.getLast());
    }

    @Test
    void testGetAllTransactionSuccessWithTimestamp() {
        TransactionRequestDto validTransaction = new TransactionRequestDto(
                100.0,
                OffsetDateTime.now().minusSeconds(20));
        TransactionRequestDto validTransaction2 = new TransactionRequestDto(
                100.0,
                OffsetDateTime.now().minusSeconds(300));

        transactionServiceImpl.addTransaction(validTransaction);
        transactionServiceImpl.addTransaction(validTransaction2);
        List<TransactionRequestDto> list = transactionServiceImpl.getAllTransactions(60);

        assertEquals(1, list.size());
        assertEquals(validTransaction, list.getFirst());
    }

    @Test
    void testGetTransactionDtoListSuccess() {
        assertEquals(0, transactionServiceImpl.getTransactionDtosList().size());
    }
}