package DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PratoJpaDao {

    private static PratoJpaDao instance;

    protected EntityManager entityManager;

    public static PratoJpaDao getInstance() {
        if (instance == null) {
            instance = new PratoJpaDao();
        }
        return instance;
    }

    private PratoJpaDao() {
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
