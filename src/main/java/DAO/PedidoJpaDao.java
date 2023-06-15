package DAO;

import Model.Cardapio;
import Model.Cliente;
import Model.Pedido;
import Model.StatusPedido;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class PedidoJpaDao {

    private static PedidoJpaDao instance;
    private EntityManagerFactory entityManagerFactory;
    protected EntityManager entityManager;

    public static PedidoJpaDao getInstance() {
        if (instance == null) {
            instance = new PedidoJpaDao();
        }
        return instance;
    }

    private PedidoJpaDao() {
        entityManagerFactory = Persistence.createEntityManagerFactory("sabor_caseiro");
        entityManager = entityManagerFactory.createEntityManager();
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

    public List<Cliente> getAllClientes() {
        try {
            TypedQuery<Cliente> query = entityManager.createQuery("SELECT c FROM Cliente c", Cliente.class);
            return query.getResultList();
        } finally {
            entityManager.close();
        }
    }

    public List<StatusPedido> getAllStatusPedidos() {
        try {
            TypedQuery<StatusPedido> query = entityManager.createQuery("SELECT s FROM StatusPedido s", StatusPedido.class);
            return query.getResultList();
        } finally {
            entityManager.close();
        }
    }

    public List<Cardapio> getAllCardapios() {
        try {
            TypedQuery<Cardapio> query = entityManager.createQuery("SELECT c FROM Cardapio c", Cardapio.class);
            return query.getResultList();
        } finally {
            entityManager.close();
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
