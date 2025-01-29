package candycoder.transictionapi.controllers;

import candycoder.transictionapi.controllers.dtos.StatisticsResponseDto;
import candycoder.transictionapi.services.StatisticsService;
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

    @GetMapping
    public ResponseEntity<StatisticsResponseDto> getStatistics(@RequestParam(value = "searchInterval",
            required = false,
            defaultValue = "60") Integer searchInterval) {
        return ResponseEntity.ok(statisticsService.calculateTransactionStatistics(searchInterval));
    }
}
