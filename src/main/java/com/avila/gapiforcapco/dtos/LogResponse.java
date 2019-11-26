package com.avila.gapiforcapco.dtos;

import com.avila.gapiforcapco.entities.Log;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogResponse {
    private final String endpointPath;
    private final String controllerName;
    private final String date;

    private LogResponse(String endpointPath, String controllerName, Date date) {
        this.endpointPath = endpointPath;
        this.controllerName = controllerName;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yy hh:mm:ss");
        this.date = dateFormat.format(date);
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
