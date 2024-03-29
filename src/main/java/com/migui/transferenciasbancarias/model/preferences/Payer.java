package com.migui.transferenciasbancarias.model.preferences;

import com.migui.transferenciasbancarias.model.IdentificationTypes;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
@Entity
@Getter
@Setter
@Data
@AllArgsConstructor
@Builder(toBuilder = true)
public class Payer{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    public String name;
    public String surname;
    public String email;
    public Date dateCreated;

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "phone")
    public Phone phone;

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "identification")
    public Identification identification;


    public Payer() {

    }
}