package candycoder.transictionapi.controllers.dtos;

import java.time.OffsetDateTime;

public record TransactionRequestDto(Double value,
                                    OffsetDateTime timestamp) {
}
