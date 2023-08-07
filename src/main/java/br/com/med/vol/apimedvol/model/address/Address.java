package br.com.med.vol.apimedvol.model.address;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String address;
    private String district;
    private String cep;
    private String city;
    private String uf;
    private String number;
    private String complement;

    public Address(AddressData addressData) {
        this.address = addressData.address();
        this.district = addressData.district();
        this.cep = addressData.cep();
        this.city = addressData.city();
        this.uf = addressData.uf();
        this.number = addressData.number();
        this.complement = addressData.complement();
    }
}
