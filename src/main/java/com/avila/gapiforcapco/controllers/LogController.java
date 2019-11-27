package com.avila.gapiforcapco.controllers;

import com.avila.gapiforcapco.dtos.LogResponse;
import com.avila.gapiforcapco.services.LogService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LogController {

    private final LogService logService;

    public LogController(LogService logService) {
        this.logService = logService;
    }

    @ApiOperation(value = "Get logs from all requests made to this API until now.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list."),
            @ApiResponse(code = 500, message = "Internal Server Error.")
    })
    @GetMapping("/logs")
    public ResponseEntity<List<LogResponse>> getAllLogs() {
        return new ResponseEntity<>(logService.fetchAllLogsUntilNow(), HttpStatus.OK);
    }
}
