package com.application.repository;

import com.application.entity.Cart;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CartMyBatisRepository {

    @Results({
            @Result(property = "cartId", column = "cart_id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "products", column = "product", typeHandler =com.application.repository.JsonTypeHandler.class),
    })
    @Select("SELECT ct.cart_id, ct.user_id, ct.product FROM cart as ct")
    public List< Cart > findAll();

    @Results({
            @Result(property = "cartId", column = "cart_id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "products", column = "product", typeHandler =com.application.repository.JsonTypeHandler.class),
    })
    @Select("SELECT ct.cart_id, ct.user_id, ct.product FROM cart as ct WHERE ct.cart_id = #{cartId}")
    public Cart findById(String cartId);

    @Delete("DELETE FROM cart WHERE cart_id = #{cartId}")
    public int deleteById(String cartId);

    @Insert("INSERT INTO cart(cart_id, user_id, product)  VALUES (#{cartId}, #{userId}, #{products, typeHandler=com.application.repository.JsonTypeHandler})")
    public int insert(Cart cart);

    @Results({
            @Result(property = "cartId", column = "cart_id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "products", column = "product", typeHandler =com.application.repository.JsonTypeHandler.class),
    })
    @Update("UPDATE cart SET user_id=#{userId}, product=#{products, typeHandler=com.application.repository.JsonTypeHandler} where cart_id=#{cartId}")
    public int update(Cart cart);
}