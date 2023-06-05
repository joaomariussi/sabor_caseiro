package Controller;

import Model.Cliente;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class ObterClientes {

    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("sabor_caseiro");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        String jpql = "Select c from Cliente c";
        TypedQuery<Cliente> query = entityManager.createQuery(jpql, Cliente.class);
        query.setMaxResults(3);

        List<Cliente> clientes = query.getResultList();

        for (Cliente cliente: clientes) {
            System.out.println("ID: " + cliente.getId() + " Nome: " + cliente.getNome());
        }

        entityManager.close();
        entityManagerFactory.close();
    }
}
