package com.javPOL.magazineJava.dao;

import com.javPOL.magazineJava.model.Supplier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SupplierDaoImpl implements SupplierDao {

    private final JdbcTemplate jdbcTemplate;

    public SupplierDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Supplier findById(Integer id) {
        String sql = "SELECT * FROM Supplier WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new SupplierRowMapper());
    }

    @Override
    public List<Supplier> findAll() {
        String sql = "SELECT * FROM Supplier";
        return jdbcTemplate.query(sql, new SupplierRowMapper());
    }

    @Override
    public void save(Supplier supplier) {
        String sql = "INSERT INTO Supplier (name, county, backAccNum, phone, dialigNum, taxIdentificationNum) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                supplier.getName(),
                supplier.getCountry(),
                supplier.getBankAccountNumber(),
                supplier.getPhoneNumber(),
                supplier.getInternationalDialingNumber(),
                supplier.getTaxIdentificationNumber());
    }

    @Override
    public void update(Supplier supplier) {
        String sql = "UPDATE Supplier SET name = ?, country = ?, phone = ? WHERE id = ?";
        jdbcTemplate.update(sql,
                supplier.getName(),
                supplier.getCountry(),
                supplier.getPhoneNumber(),
                supplier.getId());
    }

    @Override
    public void deleteById(Integer id) {
        String sql = "DELETE FROM Supplier WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}

