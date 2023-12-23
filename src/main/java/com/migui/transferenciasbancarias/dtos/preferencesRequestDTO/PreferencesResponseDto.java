package com.migui.transferenciasbancarias.dtos.preferencesRequestDTO;

import com.migui.transferenciasbancarias.model.preferences.Item;
import com.migui.transferenciasbancarias.model.preferences.Payer;
import lombok.*;

import java.util.List;


@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class PreferencesResponseDto {

    private Payer payer;

    private List<Item> items;

    private String urlInit;

}
