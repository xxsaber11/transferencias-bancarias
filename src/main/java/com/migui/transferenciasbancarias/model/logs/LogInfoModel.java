package com.migui.transferenciasbancarias.model.logs;

import java.util.UUID;

import org.springframework.http.HttpStatus;

public class LogInfoModel {
    private UUID uuid;
    private Long startTime;
    private int codigoHttp;

    public LogInfoModel(UUID uuid, Long startTime, int codigoHttp, Object objeto) {
        this.uuid = uuid;
        this.startTime = startTime;
        this.codigoHttp = codigoHttp;
    }

    public LogInfoModel(UUID uuid2, int codigoHttp2, HttpStatus httpStatus) {
    }

    public UUID getUuid() {
        return uuid;
    }

    public Long getStartTime() {
        return startTime;
    }

    public Object getcodigoHttp() {
        return codigoHttp;
    }

}
