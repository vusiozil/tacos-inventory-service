package com.tacos.inventory.repository;

import com.tacos.inventory.model.Ingredient;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Optional;

@ApplicationScoped
public class IngredientRepository {

    @Inject
    private SessionFactory sessionFactory;

    public Ingredient saveOrUpdate(Ingredient item) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(item);
        session.getTransaction().commit();
        session.close();
        return item;
    }

    public void delete(Long id) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Ingredient item = (Ingredient) session.get(Ingredient.class, id);

        if (item != null) {
            session.delete(item);
            session.getTransaction().commit();
        }
        session.close();

    }

    public Optional<Ingredient> getById(String id) {

        Session session = sessionFactory.openSession();

        session.beginTransaction();
        Ingredient ingredient = (Ingredient) session.get(Ingredient.class, id);
        session.close();

        return Optional.of(ingredient);

    }


}
