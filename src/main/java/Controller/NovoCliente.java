package Controller;

import Model.Cliente;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class NovoCliente {

    public static void main(String[] args) {

        //Faz a inserção de um novo cliente

            try {
                EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("sabor_caseiro");
                EntityManager entityManager = entityManagerFactory.createEntityManager();

                Cliente novoCliente = new Cliente("João", "01040688098", "54999957486", "Rua tal",
                "9966000", "Campinas do Sul", "RS", "28/07/2001", "Masculino");

                entityManager.getTransaction().begin();
                entityManager.persist(novoCliente);
                entityManager.getTransaction().commit();

                entityManager.close();
                entityManagerFactory.close();

                System.out.println("Cliente inserido com sucesso!");

            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}
