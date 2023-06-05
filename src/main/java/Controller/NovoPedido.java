package Controller;

import Model.Pedido;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class NovoPedido {

    public static void main(String[] args) {

        //Faz a inserção de um novo pedido

        try {
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("sabor_caseiro");
            EntityManager entityManager = entityManagerFactory.createEntityManager();

            Pedido novoPedido = new Pedido("03/06/2023", 25.0, "3", "teste");

            entityManager.getTransaction().begin();
            entityManager.persist(novoPedido);
            entityManager.getTransaction().commit();

            entityManager.close();
            entityManagerFactory.close();

            System.out.println("Pedido inserido com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
