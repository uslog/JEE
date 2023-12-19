package com.dwq.mapper;

import com.dwq.entity.dto.OrderDetail;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderDetailMapper {
    @Insert("INSERT INTO dwq_order_detail (order_id, pet_id, quantity, price) VALUES (#{orderId}, #{petId}, #{quantity}, #{price})")
    @Options(useGeneratedKeys = true, keyProperty = "detailId")
    void insertOrderDetail(OrderDetail orderDetail);

    @Delete("DELETE FROM dwq_order_detail WHERE detail_id = #{detailId}")
    void deleteOrderDetail(Integer detailId);

    @Update("UPDATE dwq_order_detail SET order_id = #{orderId}, pet_id = #{petId}, quantity = #{quantity}, price = #{price} WHERE detail_id = #{detailId}")
    void updateOrderDetail(OrderDetail orderDetail);

    @Select("SELECT * FROM dwq_order_detail WHERE detail_id = #{detailId}")
    OrderDetail selectOrderDetailById(Integer detailId);

    @Select("SELECT * FROM dwq_order_detail")
    List<OrderDetail> selectAllOrderDetails();
}
