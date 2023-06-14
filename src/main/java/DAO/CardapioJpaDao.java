package DAO;

import Model.Cardapio;

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

    /**
     *
     * @param id |
     * @return |
     */

    public Cardapio getById(final int id) {
        try {
            entityManager.getTransaction().begin();
            Cardapio cardapio = entityManager.find(Cardapio.class, id);
            entityManager.getTransaction().commit();
            return cardapio;
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
            return null;
        }
    }

    /**
     *
     * @param id |
     */

    public void removerCardapio(final int id) {
        try {
            Cardapio cardapio = getById(id);
            entityManager.getTransaction().begin();
            entityManager.remove(cardapio);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    /**
     * @param cardapio |
     */
    public Cardapio persist(Cardapio cardapio) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(cardapio);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
        return cardapio;
    }
}
