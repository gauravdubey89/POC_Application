package com.application.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import com.application.entity.Product;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@MappedTypes({Product.class})
public class JsonTypeHandler extends BaseTypeHandler<List<Product>> {

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, List<Product> product, JdbcType jdbcType) throws SQLException {
        try {
            preparedStatement.setObject(i, objectMapper.writeValueAsString(product));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> getNullableResult(ResultSet resultSet, String s) throws SQLException {
        if(resultSet.getString(s) != null){
            try {
                CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, Product.class);
                return objectMapper.readValue(resultSet.getString(s), listType);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public List<Product> getNullableResult(ResultSet resultSet, int i) throws SQLException {
        if(resultSet.getString(i) != null){
            try {
                CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, Product.class);
                return objectMapper.readValue(resultSet.getString(i), listType);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public List<Product> getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        if(callableStatement.getString(i) != null){
            try {
                CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, Product.class);
                return objectMapper.readValue(callableStatement.getString(i), listType);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}