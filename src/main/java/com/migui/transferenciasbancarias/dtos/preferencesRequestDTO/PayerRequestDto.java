package com.migui.transferenciasbancarias.dtos.preferencesRequestDTO;

import com.migui.transferenciasbancarias.model.IdentificationTypes;
import lombok.*;

import java.util.Date;
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class PayerRequestDto {
    public String name;
    public String surname;
    public String email;
    public PhoneRequestDto phone;
    public IdentificationRequestDto identification;
}