package DAO;

import Model.Cliente;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

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

    /**
     *
     * @param id |
     * @return |
     */

    public Cliente getById(final int id) {
        return entityManager.find(Cliente.class, id);
    }

    /**
     *
     * @param id |
     * @return |
     */

    public void removeById(final int id) {
        try {
            Cliente cliente = getById(id);
            entityManager.getTransaction().begin();
            entityManager.remove(cliente);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    /**
     * @param cpf |
     * @return |
     */
    public Cliente getByCpf(final String cpf) {
        Query query = entityManager.createQuery("SELECT c FROM Cliente c WHERE c.cpf = :cpf");
        query.setParameter("cpf", cpf);
        return (Cliente) query.getSingleResult();
    }

    /**
     * @return |
     */

    @SuppressWarnings("unchecked")
    public List<Cliente> listarTodos() {
        return entityManager.createQuery("FROM " + Cliente.class.getName()).getResultList();
    }

    /**
     * @param cliente |
     */
    public Cliente persist(Cliente cliente) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(cliente);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
        return cliente;
    }






















}
