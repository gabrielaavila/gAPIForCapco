package com.avila.gapiforcapco.services;

import com.avila.gapiforcapco.dtos.LogResponse;
import com.avila.gapiforcapco.entities.Log;
import com.avila.gapiforcapco.repositories.LogRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class LogServiceImplTest {
    private LogRepository repository;
    private LogServiceImpl logService;

    @BeforeEach
    void setUp() {
        this.repository = mock(LogRepository.class);
        logService = new LogServiceImpl(repository);
    }

    @Test
    public void given_listOfLogs_when_fetchAllLogsUntilNow_then_returnListOfLogResponses() {
        Log log = mock(Log.class);
        List<Log> logs = Arrays.asList(log, log, log);
        String endpointhPath = "someEndpoint";
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String dateformatted = date.format(dateFormat);

        when(repository.findAll()).thenReturn(logs);
        when(log.getEndpointPath())
                .thenReturn(endpointhPath);
        when(log.getControllerName())
                .thenReturn("controller1")
                .thenReturn("controller2")
                .thenReturn("controller3");

        when(log.getRequisitionDate())
                .thenReturn(date);

        List<LogResponse> result = logService.fetchAllLogsUntilNow();

        Assertions.assertEquals(3, result.size());
        Assertions.assertEquals("controller1", result.get(0).getControllerName());
        Assertions.assertEquals(dateformatted, result.get(0).getDate());
        Assertions.assertEquals(endpointhPath, result.get(0).getEndpointPath());
        Assertions.assertEquals("controller2", result.get(1).getControllerName());
        Assertions.assertEquals("controller3", result.get(2).getControllerName());
    }

    @Test
    public void given_logInformation_when_saveLog_then_MustSave() {
        when(repository.save(any(Log.class))).thenReturn(any());

        logService.saveLog("someEndpoint", "someController");

        verify(repository).save(any(Log.class));
    }

}