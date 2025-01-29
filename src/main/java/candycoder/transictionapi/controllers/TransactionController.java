package candycoder.transictionapi.controllers;

import candycoder.transictionapi.controllers.dtos.TransactionRequestDto;
import candycoder.transictionapi.services.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(description = "Add a transaction.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Transaction created successfully"),
            @ApiResponse(responseCode = "422", description = "Transaction not accepted."),
            @ApiResponse(responseCode = "400", description = "Invalid request or improperly formatted.")
    })
    @PostMapping
    public ResponseEntity<Void> createTransaction(@RequestBody TransactionRequestDto transactionRequestDto) {
        transactionService.addTransaction(transactionRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(description = "Clear transactions.")
    @DeleteMapping
    public ResponseEntity<Void> clearTransactions() {
        transactionService.clearTransactions();
        return ResponseEntity.ok().build();
    }
}
