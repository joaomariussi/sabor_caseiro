package DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CardapioJpaDao {

    private static CardapioJpaDao instance;

    protected EntityManager entityManager;

    public static CardapioJpaDao getInstance() {
        if (instance == null) {
            instance = new CardapioJpaDao();
        }
        return instance;
    }

    private CardapioJpaDao() {
        entityManager = getEntityManager();
    }

    private EntityManager getEntityManager() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("sabor_caseiro");
        if (entityManager == null) {
            entityManager = factory.createEntityManager();
        }
        return entityManager;
    }


}
