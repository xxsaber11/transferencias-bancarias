package com.migui.transferenciasbancarias.controladores;

import com.mercadopago.exceptions.MPException;
import com.mercadopago.net.MPSearchRequest;
import com.mercadopago.resources.Payment;
import com.mercadopago.sdk.models.MPSearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pagos")
@CrossOrigin(origins = "*")//TODO: especificar el origen
public class PagosControladores {

    private final PagosServicios pagosServicios;

    @Autowired
    public PagosControladores(PagosServicios pagosServicios) {
        this.pagosServicios = pagosServicios;
    }

    @GetMapping("/buscar")
    public MPSearchRequest<Payment> buscarPagosConFiltros() {
        try {
            return pagosServicios.buscarPagosConFiltros();
        } catch (MPException e) {
            // Manejar la excepción en caso de error al buscar los pagos
            e.printStackTrace();
            return null; // O devuelve un mensaje de error apropiado
        }
    }
}
