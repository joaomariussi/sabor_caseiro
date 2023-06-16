package DAO;

import Model.Cardapio;
import Model.Cliente;
import Model.Pedido;
import Model.StatusPedido;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class PedidoJpaDao {

    private static PedidoJpaDao instance;

    protected EntityManager entityManager;

    public static PedidoJpaDao getInstance() {
        if (instance == null) {
            instance = new PedidoJpaDao();
        }
        return instance;
    }

    private PedidoJpaDao() {
        entityManager = getEntityManager();
    }

    private EntityManager getEntityManager() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("sabor_caseiro");
        if (entityManager == null) {
            entityManager = factory.createEntityManager();
        }
        return entityManager;
    }

    public List<Cliente> getAllClientes() {
        try {
            entityManager.getTransaction().begin();
            List<Cliente> clientes = entityManager.createQuery("SELECT c FROM Cliente c", Cliente.class).getResultList();
            entityManager.getTransaction().commit();
            return clientes;
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
            return null;
        }
    }

    public List<StatusPedido> getAllStatusPedido() {
        try {
            entityManager.getTransaction().begin();
            List<StatusPedido> statusPedidos = entityManager.createQuery("SELECT c FROM StatusPedido c", StatusPedido.class).getResultList();
            entityManager.getTransaction().commit();
            return statusPedidos;
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
            return null;
        }
    }

    public List<Cardapio> getAllCardapio() {
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



    public void removerPedido(final int id) {
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
}
