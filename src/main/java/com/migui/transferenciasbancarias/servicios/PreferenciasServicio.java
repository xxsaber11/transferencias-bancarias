package com.migui.transferenciasbancarias.servicios;

import com.migui.transferenciasbancarias.modelos.CompradorModel;
import com.migui.transferenciasbancarias.modelos.ItemModel;

public interface PreferenciasServicio {
    
    Boolean agregarAPreferencias(CompradorModel comprador,
                                ItemModel item);
}