package candycoder.transictionapi.controllers;

import candycoder.transictionapi.controllers.dtos.TransactionRequestDto;
import candycoder.transictionapi.services.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<Void> createTransaction(@RequestBody TransactionRequestDto transactionRequestDto) {
        transactionService.addTransaction(transactionRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> clearTransactions() {
        transactionService.clearTransactions();
        return ResponseEntity.ok().build();
    }
}
