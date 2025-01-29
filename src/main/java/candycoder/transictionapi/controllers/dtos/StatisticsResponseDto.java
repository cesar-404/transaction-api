package candycoder.transictionapi.controllers.dtos;

public record StatisticsResponseDto(Long count,
                                    Double sum,
                                    Double avg,
                                    Double min,
                                    Double max) {
}
