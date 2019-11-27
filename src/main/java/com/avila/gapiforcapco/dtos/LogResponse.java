package com.avila.gapiforcapco.dtos;

import com.avila.gapiforcapco.entities.Log;
import io.swagger.annotations.ApiModel;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ApiModel(description = "Response DTO log information with endpoint path, name of controller called and date of " +
        "the request.")
public class LogResponse {
    private final String endpointPath;
    private final String controllerName;
    private final String date;

    private LogResponse(String endpointPath, String controllerName, LocalDateTime date) {
        this.endpointPath = endpointPath;
        this.controllerName = controllerName;
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        this.date = date.format(dateFormat);
    }

    public String getEndpointPath() {
        return endpointPath;
    }

    public String getControllerName() {
        return controllerName;
    }

    public String getDate() {
        return date;
    }

    public static LogResponse transformIntoLogResponse(Log log) {
        return new LogResponse(log.getEndpointPath(), log.getControllerName(), log.getRequisitionDate());
    }
}
