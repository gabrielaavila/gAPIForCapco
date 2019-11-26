package com.avila.gapiforcapco.entities;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    @Temporal(TemporalType.DATE)
    private Date requisitionDate;

    public Log() {
    }

    public Log(String endpointPath, String controllerName, Date requisitionDate) {
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

    public Date getRequisitionDate() {
        return requisitionDate;
    }
}
