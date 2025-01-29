package candycoder.transactionapi.controllers.dtos;

import java.time.OffsetDateTime;

public record TransactionRequestDto(Double value,
                                    OffsetDateTime timestamp) {
}
