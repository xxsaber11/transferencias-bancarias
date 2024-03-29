package com.migui.transferenciasbancarias.services;

import com.migui.transferenciasbancarias.dtos.PaymentMethodsDTO;
import com.migui.transferenciasbancarias.model.paymentMethods.*;
//import com.migui.transferenciasbancarias.repository.paymentmethods.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class PaymentMethodsServices {


    @Value("${my.property.authorization}")
    private   String authorization;

    @Value("${my.property.url}")
    private   String url;

//    @Autowired
//    PaymentMethodsRepository paymentMethodsRepository;


    @Autowired
    RestTemplate restTemplate;


//    @Autowired
//    FinancialInstitutionsRepository financialInstitutionsRepository;
//
//    @Autowired
//    BinRepository binRepository;
//
//    @Autowired
//    CardNumberRepository cardNumberRepository;
//
//
//    @Autowired
//    SecurityCodeRepository securityCodeRepository;
//
//
//    @Autowired
//    ProcessModeRepository processModeRepository;

    public List<PaymentMethods> getPaymentMethodsFromApi() {
        List<PaymentMethods> paymentMethodsListNew = new ArrayList<>();


        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorization);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<PaymentMethodsDTO> entity = new HttpEntity<PaymentMethodsDTO>(headers);
        ResponseEntity<PaymentMethodsDTO[]> paymentMethodsList = restTemplate.exchange(url+"v1/payment_methods", HttpMethod.GET, entity, PaymentMethodsDTO[].class);


        if (paymentMethodsList.getStatusCode().equals(HttpStatus.OK)) {

            System.out.println(paymentMethodsList.getBody());

            /**
             * Recorre el objeto de metodos de pago de mercado pago, y si tiene datos crea el objeto para guardar en la BD
             */
            Arrays.stream(paymentMethodsList.getBody()).forEach(paymentMethods -> {
                PaymentMethods paymentMethodsNew = new PaymentMethods();

                /**
                 * Se crea AdditionalInfoNeeded
                 */

                if (paymentMethods.getAdditional_info_needed().size() > 0) {
                    List<AdditionalInfoNeeded> additionalInfoNeededList = new ArrayList<>();
                    paymentMethods.getAdditional_info_needed().forEach(additionalInfoNeeded -> {

                        AdditionalInfoNeeded additionalInfoNeededNew = new AdditionalInfoNeeded();
                        additionalInfoNeededNew.setInfo(additionalInfoNeeded);
                        additionalInfoNeededList.add(additionalInfoNeededNew);

                    });
                    paymentMethodsNew.setAdditional_info_needed(additionalInfoNeededList);
                }


                /**
                 * Se crea Setting
                 */
                if (paymentMethods.getSettings().size() > 0) {
                    List<Setting> settingList = new ArrayList<>();
                    paymentMethods.getSettings().forEach(setting -> {
                        Setting settingNew = new Setting();
                        /**
                         * Se crea CardNumber
                         */
                        if (setting.getCard_number() != null) {
                            CardNumber cardNumberNew = new CardNumber();
                            cardNumberNew.setValidation(setting.getCard_number().getValidation());
                            cardNumberNew.setLength(setting.getCard_number().getLength());
                            settingNew.setCard_number(cardNumberNew);
                         //   cardNumberRepository.save(cardNumberNew);
                        }

                        /**
                         * Se crea BinEntity
                         */
                        if (setting.getBin() != null) {
                            BinEntity binNew = new BinEntity();
                            binNew.setPattern(setting.getBin().getPattern());
                            binNew.setInstallments_pattern(setting.getBin().getInstallments_pattern());
                            binNew.setExclusion_pattern(setting.getBin().getExclusion_pattern());
                            settingNew.setBin(binNew);
                          //  binRepository.save(binNew);
                        }

                        /**
                         * Se crea SecurityCode
                         */
                        if (setting.getSecurity_code() != null) {
                            SecurityCode securityCodeNew = new SecurityCode();
                            securityCodeNew.setLength(setting.getSecurity_code().getLength());
                            securityCodeNew.setCard_location(setting.getSecurity_code().getCard_location());
                            securityCodeNew.setMode(setting.getSecurity_code().getMode());
                            settingNew.setSecurity_code(securityCodeNew);
                            //  securityCodeRepository.save(securityCodeNew);
                        }
                        settingList.add(settingNew);
                    //    paymentMethodsNew.setSettings(settingList);

                    });

                }


                /**
                 * Se crea processMode
                 */
                if (paymentMethods.getProcessing_modes().size() > 0) {
                    List<ProcessingModes> processingModesList = new ArrayList<>();
                    paymentMethods.getProcessing_modes().forEach(processingModes -> {
                        ProcessingModes processModeNew = new ProcessingModes();
                        processModeNew.setInfo(processingModes);
                        processingModesList.add(processModeNew);
                       // processModeRepository.save(processModeNew);

                    });
                   paymentMethodsNew.setProcessing_modes(processingModesList);
                }


                /**
                 * Se crea la lista de bancos
                 */
                if (paymentMethods.getFinancial_institutions().size() > 0) {

                    List<FinancialInstitution> financialInstitutionList = new ArrayList<FinancialInstitution>();

                    paymentMethods.getFinancial_institutions().forEach(financialInstitution -> {
                        FinancialInstitution financialInstitutionNew = new FinancialInstitution();
                        financialInstitutionNew.setDescription(financialInstitution.getDescription());
                        financialInstitutionNew.setId(financialInstitution.getId());
                     //  financialInstitutionList.add(financialInstitutionNew);
                     //   financialInstitutionsRepository.save(financialInstitutionNew);
                    });

                paymentMethodsNew.setFinancial_institutions(financialInstitutionList);


                } else {
                   paymentMethodsNew.setFinancial_institutions(new ArrayList<>());
                }

                /**
                 * Se arma el objeto completo
                 */

                paymentMethodsNew.setId(paymentMethods.getId());
                paymentMethodsNew.setName(paymentMethods.getName());
                paymentMethodsNew.setPayment_type_id(paymentMethods.getPayment_type_id());
                paymentMethodsNew.setStatus(paymentMethods.getStatus());
                paymentMethodsNew.setSecure_thumbnail(paymentMethods.getSecure_thumbnail());
                paymentMethodsNew.setThumbnail(paymentMethods.getThumbnail());
                paymentMethodsNew.setDeferred_capture(paymentMethods.getDeferred_capture());
                paymentMethodsNew.setMin_allowed_amount(paymentMethods.getMin_allowed_amount());
                paymentMethodsNew.setMax_allowed_amount(paymentMethods.getMax_allowed_amount());
                paymentMethodsNew.setAccreditation_time(paymentMethods.getAccreditation_time());

                paymentMethodsListNew.add(paymentMethodsNew);

              //  paymentMethodsRepository.save(paymentMethodsNew);
              //  savePaymentMethod(paymentMethodsNew);
            });

        }

        return paymentMethodsListNew;
    }


}
