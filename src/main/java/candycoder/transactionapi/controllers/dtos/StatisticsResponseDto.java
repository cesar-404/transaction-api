package candycoder.transactionapi.controllers.dtos;

public record StatisticsResponseDto(Long count,
                                    Double sum,
                                    Double avg,
                                    Double min,
                                    Double max) {
}
