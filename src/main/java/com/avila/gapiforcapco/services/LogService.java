package com.avila.gapiforcapco.services;

import com.avila.gapiforcapco.dtos.LogResponse;

import java.util.List;

public interface LogService {

    List<LogResponse> fetchAllLogsUntilNow();

    void saveLog(String endpointPath, String controllerName);

}
