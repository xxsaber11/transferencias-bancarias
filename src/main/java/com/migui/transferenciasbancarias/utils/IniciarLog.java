package com.migui.transferenciasbancarias.utils;

import java.util.Calendar;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import com.migui.transferenciasbancarias.model.logs.LogInfoModel;


public class IniciarLog {

    private static final Logger log = LoggerFactory.getLogger(IniciarLog.class);

    public static LogInfoModel informacionInicial(int codigoHttp, String nombreMetodo, String tipoMetodo, Object objeto) {
        UUID uuid = UUID.randomUUID();
        Long inicio = Calendar.getInstance().getTimeInMillis();

        log.info("**************************************");
        log.info("**************************************");
        log.info("* SE INICIO UNA SOLICITUD HTTP *");
        log.info("**************************************");
        log.info("**************************************");
        log.info("*");
        log.info("* PARAMETROS RECIBIDOS EN EL METODO {} DENTRO DE {}", nombreMetodo, tipoMetodo);
        log.info("*");
        log.info("**************************************");
        log.info("");
        log.info("UUID: {}", uuid);
        log.info("Inicio de la solicitud: {}", inicio);
        log.info("Codigo de Http: {}",  codigoHttp);
        if(objeto!= null){
            log.info("Datos del Objeto: {}", objeto.toString());
        }else{
            log.info("Datos del Objeto: NULL");
            objeto= "null";
        }

        return new LogInfoModel(uuid, inicio, codigoHttp, objeto);
    }
    public static LogInfoModel actualizarInformacion(int codigoHttp, String cuando, String nombreMetodo, String tipoMetodo, HttpStatus httpStatus, UUID uuid, Object objeto) {
    
        log.info("**************************************");
        log.info("{}", cuando);
        log.info("*");
        log.info("* PARAMETROS RECIBIDOS EN EL METODO {} DENTRO DE {}", nombreMetodo, tipoMetodo);
        log.info("*");
        log.info("**************************************");
        log.info("**************************************");
        log.info("");
        log.info("UUID: {}", uuid);
        log.info("Codigo de Http: {}",  codigoHttp);
        log.info("Datos del estado Http: {}", httpStatus);
        if(objeto!= null){
            log.info("Datos del Objeto: {}", objeto.toString());
        }else{
            log.info("Datos del Objeto: NULL");
        }
        log.info("");
        log.info("");
        log.info("**************************************");

    return new LogInfoModel(uuid, codigoHttp, httpStatus);
    }

    public static LogInfoModel finalizarInformacion(Object e,int codigoHttp, Long inicio, HttpStatus httpStatus, UUID uuid, Object objeto) {
        Long fin = Calendar.getInstance().getTimeInMillis();
        
            log.info("**************************************");
            log.info("");
            log.info("UUID: {}", uuid);
            log.info("Codigo de Http: {}",  codigoHttp);
            log.info("Datos del estado Http: {}", httpStatus);
            log.info("Fin de la solicitud: {}", fin);

        if(objeto!= null){
            log.info("Datos del Objeto: {}", objeto.toString());
        }else{
            log.info("Datos del Objeto: NULL");
        }
        
        log.info("");
        
        if (e != null){
            log.info("");
            log.info("*");
            log.info("*");
            log.info("Cuerpo del error: {}", e);
            log.info("*");
            log.info("*");
            log.info("");
        }
        
            log.info("la solicitud tardo: {} ", (fin-inicio));
            log.info("**************************************");
            log.info("*FINALIZACION DE LA SOLICITUD");
            log.info("**************************************");

    return new LogInfoModel(uuid, codigoHttp, httpStatus);
    }

}

