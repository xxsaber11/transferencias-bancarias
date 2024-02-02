package com.migui.transferenciasbancarias.conf;

import org.springframework.context.annotation.Configuration;

import com.google.api.client.util.Value;
import com.mercadopago.MercadoPagoConfig;

@Configuration
public class MpConf {

    @Value("${mercadopago.property.authorization}")
    private static String token;
    static {
        MercadoPagoConfig.setAccessToken(token);
    }

}