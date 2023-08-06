package br.com.med.vol.apimedvol.address;

public record AddressData(String address, String district, String cep,
                          String city, String uf, String number, String complement) {
}
