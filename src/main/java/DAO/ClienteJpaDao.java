package DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ClienteJpaDao {

    private static ClienteJpaDao instance;

    protected EntityManager entityManager;

    public static ClienteJpaDao getInstance() {
        if (instance == null) {
            instance = new ClienteJpaDao();
        }
        return instance;
    }

    private ClienteJpaDao() {
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
