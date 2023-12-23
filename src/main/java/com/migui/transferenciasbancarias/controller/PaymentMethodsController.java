package com.migui.transferenciasbancarias.controller;

import com.migui.transferenciasbancarias.dtos.PaymentMethodsDTO;
import com.migui.transferenciasbancarias.model.paymentMethods.PaymentMethods;
import com.migui.transferenciasbancarias.services.PaymentMethodsServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/paymentMethods")
@CrossOrigin(origins = "*")
public class PaymentMethodsController {


    /**
     * Obtiene los metodos de pagos desde mercado pago
     */
    @Autowired
    PaymentMethodsServices paymentMethodsServices;

    @GetMapping()
    public List<PaymentMethods> get() {
        return paymentMethodsServices.getPaymentMethodsFromApi();
    }
}
