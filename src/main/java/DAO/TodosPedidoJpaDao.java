package DAO;

import Model.Pedido;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class TodosPedidoJpaDao {

    private static TodosPedidoJpaDao instance;

    protected static EntityManager entityManager;

    public static TodosPedidoJpaDao getInstance() {
        if (instance == null) {
            instance = new TodosPedidoJpaDao();
        }
        return instance;
    }

    private TodosPedidoJpaDao() {
        entityManager = getEntityManager();
    }

    private static EntityManager getEntityManager() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("sabor_caseiro");
        if (entityManager == null) {
            entityManager = factory.createEntityManager();
        }
        return entityManager;
    }

    public Pedido persist(Pedido pedido) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(pedido);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
        return pedido;
    }

    public static List<Pedido> getAllPedidos() {
        EntityManager entityManager1 = getEntityManager();
        Query query = entityManager.createQuery("SELECT p FROM Pedido p");
        return query.getResultList();
    }

    /**
     *
     * @param id |
     * @return |
     */

    public Pedido getById(final int id) {
        try {
            entityManager.getTransaction().begin();
            Pedido pedido = entityManager.find(Pedido.class, id);
            entityManager.getTransaction().commit();
            return pedido;
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

    public void removerPedidos(final int id) {
        try {
            Pedido pedido = getById(id);
            entityManager.getTransaction().begin();
            entityManager.remove(pedido);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }
}
