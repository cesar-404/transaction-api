package candycoder.transictionapi.controllers;

import candycoder.transictionapi.controllers.dtos.StatisticsResponseDto;
import candycoder.transictionapi.services.StatisticsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    private final StatisticsService statisticsService;

    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @Operation(description = "Get the transaction statistics within the indicated interval (default 60 seconds).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Statistics returned successfully."),
    })
    @GetMapping
    public ResponseEntity<StatisticsResponseDto> getStatistics(@RequestParam(value = "searchInterval",
            required = false,
            defaultValue = "60") Integer searchInterval) {
        return ResponseEntity.ok(statisticsService.calculateTransactionStatistics(searchInterval));
    }
}
