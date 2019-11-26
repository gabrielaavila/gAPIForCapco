package com.avila.gapiforcapco.controllers;

import com.avila.gapiforcapco.dtos.LogResponse;
import com.avila.gapiforcapco.entities.Log;
import com.avila.gapiforcapco.services.LogService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LogController {

    private final LogService logService;

    public LogController(LogService logService) {
        this.logService = logService;
    }

    @GetMapping("/logs")
    public List<LogResponse> getAllLogs() {
        return logService.fetchAllLogsUntilNow();
    }
}
