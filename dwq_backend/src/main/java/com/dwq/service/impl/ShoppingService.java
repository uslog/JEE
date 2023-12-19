package com.dwq.service.impl;

import com.dwq.entity.dto.Shopping;
import com.dwq.mapper.ShoppingMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingService {

    private final ShoppingMapper shoppingMapper;

    public ShoppingService(ShoppingMapper shoppingMapper) {
        this.shoppingMapper = shoppingMapper;
    }
    public void addShopping(Shopping shopping) {
        shoppingMapper.insertShopping(shopping);
    }
    public void deleteShopping(Integer shoppingId) {
        shoppingMapper.deleteShopping(shoppingId);
    }
    public void updateShopping(Shopping shopping) {
        shoppingMapper.updateShopping(shopping);
    }

    public Shopping getShopping(Integer shoppingId) {
        return shoppingMapper.selectShoppingById(shoppingId);
    }

    public List<Shopping> getAllShoppings() {
        return shoppingMapper.selectAllShoppings();
    }
}
