package Controller;

import Model.Cliente;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class RemoverCliente {

    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("sabor_caseiro");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Cliente cliente = entityManager.find(Cliente.class, 2);

        if (cliente != null) {
            entityManager.getTransaction().begin();
            entityManager.remove(cliente);
            entityManager.getTransaction().commit();
            System.out.println("Cliente deletado com Sucesso!");
        } else {
            System.out.println("Cliente não encontrado");
        }

        entityManager.close();
        entityManagerFactory.close();
    }
}