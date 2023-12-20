package com.dwq.mapper;
import com.dwq.entity.dto.Address;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AddressMapper {
    @Insert("INSERT INTO dwq_address (user_id, consignee, address) VALUES (#{userId}, #{consignee}, #{address})")
    @Options(useGeneratedKeys = true, keyProperty = "addressId")
    void insertAddress(Address address);

    @Delete("DELETE FROM dwq_address WHERE address_id = #{addressId}")
    void deleteAddress(Integer addressId);

    @Update("UPDATE dwq_address SET user_id = #{userId}, consignee = #{consignee}, address = #{address} WHERE address_id = #{addressId}")
    void updateAddress(Address address);

    @Select("SELECT * FROM dwq_address WHERE address_id = #{addressId}")
    Address selectAddressById(Integer addressId);

    @Select("SELECT * FROM dwq_address")
    List<Address> selectAllAddresses();
}
