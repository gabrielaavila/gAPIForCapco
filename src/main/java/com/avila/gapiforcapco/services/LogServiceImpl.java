package com.avila.gapiforcapco.services;

import com.avila.gapiforcapco.dtos.LogResponse;
import com.avila.gapiforcapco.entities.Log;
import com.avila.gapiforcapco.repositories.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LogServiceImpl implements LogService {

    private final LogRepository repository;

    @Autowired
    public LogServiceImpl(LogRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<LogResponse> fetchAllLogsUntilNow() {
        List<Log> logs =  repository.findAll();
        return logs.stream().map(LogResponse::transformIntoLogResponse).collect(Collectors.toList());
    }

    @Override
    public void saveLog(String endpointPath, String controllerName) {
        repository.save(new Log(endpointPath, controllerName, LocalDateTime.now()));
    }
}
