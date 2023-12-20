package com.dwq.controller;
import com.dwq.entity.dto.Address;
import com.dwq.service.impl.AddressService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth/addresses")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping
    public void addAddress(@ModelAttribute Address address) {
        addressService.addAddress(address);
    }

    @DeleteMapping("/{id}")
    public void deleteAddress(@PathVariable("id") Integer addressId) {
        addressService.deleteAddress(addressId);
    }

    @PutMapping
    public void updateAddress(@ModelAttribute Address address) {
        addressService.updateAddress(address);
    }

    @GetMapping("/{id}")
    public Address getAddress(@PathVariable("id") Integer addressId) {
        return addressService.getAddress(addressId);
    }

    @GetMapping
    public List<Address> getAllAddresses() {
        return addressService.getAllAddresses();
    }
}
