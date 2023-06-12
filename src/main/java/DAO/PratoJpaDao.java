package DAO;

import Model.PratosCardapio;

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

    /**
     *
     * @param id |
     * @return |
     */

    public PratosCardapio getById(final int id) {
        return entityManager.find(PratosCardapio.class, id);
    }

    /**
     *
     * @param id |
     * @return |
     */

    public void removeById(final int id) {
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
     * @return |
     */

    //@SuppressWarnings("unchecked")
    //public Cliente listarCardapios(String cpf) {
      //  EntityManager entityManager1 = getEntityManager();
       // Query query = entityManager.createQuery("SELECT c FROM Cliente c where c.cpf = :cpf");
        //query.setParameter("cpf", cpf);
        //List<Cliente> clientes = query.getResultList();

        //if (!clientes.isEmpty()) {
          //  return clientes.get(0);
        //} else {
          //  return null;
        //}
    //}

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
