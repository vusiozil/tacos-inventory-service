package com.tacos.inventory.repository;

import com.tacos.inventory.model.IngredientStock;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;


@ApplicationScoped
public class IngredientStockRepository {

    @Inject
    private SessionFactory sessionFactory;


    public void saveOrUpdate(IngredientStock ingredientStock) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.saveOrUpdate(ingredientStock);

        transaction.commit();
        session.close();

    }

    public Optional<IngredientStock> getById(String ingredientId) {

        Session session = sessionFactory.openSession();

        return Optional.ofNullable((IngredientStock) session.get(IngredientStock.class, ingredientId));
    }


    public void delete(String ingredientId) {
        Session session = sessionFactory.openSession();
        IngredientStock stock = (IngredientStock) session.get(IngredientStock.class, ingredientId);

        if (stock == null) {
            throw new RuntimeException("IngredientStock not found for ID: " + ingredientId);
        }

        Transaction transaction = session.beginTransaction();
        session.delete(stock);
        transaction.commit();

    }

    public List<IngredientStock> findAll() {

        Session session = sessionFactory.openSession();
        return session
                .createQuery("FROM IngredientStock")
                .list();
    }
}
