package com.javPOL.magazineJava.dao.CustomerDAO;

import com.javPOL.magazineJava.dao.DaoImpl;
import com.javPOL.magazineJava.model.Customer;
import com.javPOL.magazineJava.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
public class CustomerDaoImpl extends DaoImpl<Customer, Long> implements CustomerDao {

    public CustomerDaoImpl() {
        super(Customer.class);
    }

    @Override
    public List<Customer> findByUser(User user) {
        TypedQuery<Customer> query = entityManager.createQuery(
                "SELECT c FROM Customer c WHERE c.user = :user ORDER BY c.isDefault DESC, c.name ASC",
                Customer.class);
        query.setParameter("user", user);
        return query.getResultList();
    }

    @Override
    public Optional<Customer> findByIdAndUser(Long id, User user) {
        TypedQuery<Customer> query = entityManager.createQuery(
                "SELECT c FROM Customer c WHERE c.id = :id AND c.user = :user",
                Customer.class);
        query.setParameter("id", id);
        query.setParameter("user", user);

        List<Customer> results = query.getResultList();
        if (results.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(results.get(0));
    }

    @Override
    public List<Customer> findByUserAndIsDefault(User user, Boolean isDefault) {
        TypedQuery<Customer> query = entityManager.createQuery(
                "SELECT c FROM Customer c WHERE c.user = :user AND c.isDefault = :isDefault",
                Customer.class);
        query.setParameter("user", user);
        query.setParameter("isDefault", isDefault);
        return query.getResultList();
    }

    @Override
    public void clearDefaultForUser(User user) {
        int updatedCount = entityManager.createQuery(
                "UPDATE Customer c SET c.isDefault = false WHERE c.user = :user")
                .setParameter("user", user)
                .executeUpdate();
    }
}
