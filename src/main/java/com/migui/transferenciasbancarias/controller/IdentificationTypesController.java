package com.migui.transferenciasbancarias.controller;


import com.migui.transferenciasbancarias.services.IdentificationTypesServices;
import com.migui.transferenciasbancarias.model.IdentificationTypes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/identificationTypes")
@CrossOrigin(origins = "*")


public class IdentificationTypesController {

    /**
     * Obtiene desde mercado pago la lista de tipos de documentos.
     */
    @Autowired
    IdentificationTypesServices identificationTypesServices;

    @GetMapping()
    public List<IdentificationTypes> getIdentificationTypes() {
        return identificationTypesServices.getIdentificationTypes();
    }
}


