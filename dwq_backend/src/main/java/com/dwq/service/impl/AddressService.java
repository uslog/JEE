package com.dwq.service.impl;

import com.dwq.entity.dto.Address;
import com.dwq.mapper.AddressMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    private final AddressMapper addressMapper;

    public AddressService(AddressMapper addressMapper) {
        this.addressMapper = addressMapper;
    }

    public void addAddress(Address address) {
        addressMapper.insertAddress(address);
    }

    public void deleteAddress(Integer addressId) {
        addressMapper.deleteAddress(addressId);
    }

    public void updateAddress(Address address) {
        addressMapper.updateAddress(address);
    }

    public Address getAddress(Integer addressId) {
        return addressMapper.selectAddressById(addressId);
    }

    public List<Address> getAllAddresses() {
        return addressMapper.selectAllAddresses();
    }
}
