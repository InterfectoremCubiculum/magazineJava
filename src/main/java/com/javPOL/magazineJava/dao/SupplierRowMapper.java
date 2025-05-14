package com.javPOL.magazineJava.dao;

import com.javPOL.magazineJava.model.Supplier;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SupplierRowMapper implements RowMapper<Supplier> {
    @Override
    public Supplier mapRow(ResultSet rs, int rowNum) throws SQLException {
        Supplier supplier = new Supplier();
        supplier.setId(rs.getInt("id"));
        supplier.setName(rs.getString("name"));
        return supplier;
    }
}