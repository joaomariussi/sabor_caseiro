package DAO;

import Model.Cardapio;
import Model.PratosCardapio;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

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

    // lista os cardápios
    public List<Cardapio> getAllCardapios() {
        try {
            entityManager.getTransaction().begin();
            List<Cardapio> cardapios = entityManager.createQuery("SELECT c FROM Cardapio c", Cardapio.class).getResultList();
            entityManager.getTransaction().commit();
            return cardapios;
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
            return null;
        }
    }

    /**
     *
     * @param id |
     * @return |
     */

    public PratosCardapio getById(final int id) {
        try {
            entityManager.getTransaction().begin();
            PratosCardapio pratosCardapio = entityManager.find(PratosCardapio.class, id);
            entityManager.getTransaction().commit();
            return pratosCardapio;
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

    public void removerPrato(final int id) {
        try {
            PratosCardapio pratosCardapio = getById(id);
            entityManager.getTransaction().begin();
            entityManager.remove(pratosCardapio);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    /**
     * @param pratosCardapio |
     */
    public PratosCardapio persist(PratosCardapio pratosCardapio) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(pratosCardapio);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
        return pratosCardapio;
    }

}
