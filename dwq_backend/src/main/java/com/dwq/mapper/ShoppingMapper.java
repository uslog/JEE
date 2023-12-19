package com.dwq.mapper;

import com.dwq.entity.dto.Shopping;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ShoppingMapper {
    @Insert("INSERT INTO dwq_shopping (user_id, pet_id, total_price) VALUES (#{userId}, #{petId}, #{totalPrice})")
    @Options(useGeneratedKeys = true, keyProperty = "shoppingId")
    void insertShopping(Shopping shopping);

    @Delete("DELETE FROM dwq_shopping WHERE shopping_id = #{shoppingId}")
    void deleteShopping(Integer shoppingId);

    @Update("UPDATE dwq_shopping SET user_id = #{userId}, pet_id = #{petId}, total_price = #{totalPrice} WHERE shopping_id = #{shoppingId}")
    void updateShopping(Shopping shopping);

    @Select("SELECT * FROM dwq_shopping WHERE shopping_id = #{shoppingId}")
    Shopping selectShoppingById(Integer shoppingId);

    @Select("SELECT * FROM dwq_shopping")
    List<Shopping> selectAllShoppings();
}
