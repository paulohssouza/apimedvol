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

    public void updateData(AddressData addressData) {
        if(addressData.address() != null) this.address = addressData.address();
        if(addressData.district() != null) this.district = addressData.district();
        if(addressData.cep() != null) this.cep = addressData.cep();
        if(addressData.city() != null) this.city = addressData.city();
        if(addressData.uf() != null) this.uf = addressData.uf();
        if(addressData.number() != null) this.number = addressData.number();
        if(addressData.complement() != null) this.complement = addressData.complement();
    }
}
