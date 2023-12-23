package com.migui.transferenciasbancarias.dtos.paymentsResponseDto;


import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class PaymentResponse {


    int transactionAmount;
    String status;
    String descriptionProduct;




}
