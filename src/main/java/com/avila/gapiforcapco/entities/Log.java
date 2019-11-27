package com.avila.gapiforcapco.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "Log")
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "endpointPath")
    private String endpointPath;

    @Column(name = "controllerName")
    private String controllerName;

    @Column(name = "requisitionDate")
    private LocalDateTime requisitionDate;

    public Log() {
    }

    public Log(String endpointPath, String controllerName, LocalDateTime requisitionDate) {
        this.endpointPath = endpointPath;
        this.controllerName = controllerName;
        this.requisitionDate = requisitionDate;
    }

    public String getEndpointPath() {
        return endpointPath;
    }

    public String getControllerName() {
        return controllerName;
    }

    public LocalDateTime getRequisitionDate() {
        return requisitionDate;
    }
}
