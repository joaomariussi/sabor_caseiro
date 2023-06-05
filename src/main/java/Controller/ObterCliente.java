package Controller;

import Model.Cliente;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ObterCliente {

    public static void main(String[] args) {

        //Faz a busca de um novo cliente

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("sabor_caseiro");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Cliente cliente = entityManager.find(Cliente.class, 1);
        System.out.println("O Cliente encontrado foi: " + cliente.getNome());

        entityManager.close();
        entityManagerFactory.close();
    }
}
