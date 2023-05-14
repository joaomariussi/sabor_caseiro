package Controller;

import Model.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class NovoUsuario {

    public static void main(String[] args) {

        //Faz a inserção de um novo usuário

            try {
                EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("sabor_caseiro");
                EntityManager entityManager = entityManagerFactory.createEntityManager();

                Usuario novoUsuario = new Usuario("Guilherme","gui@gmail.com","11223344");

                entityManager.getTransaction().begin();
                entityManager.persist(novoUsuario);
                entityManager.getTransaction().commit();

                entityManager.close();
                entityManagerFactory.close();

                System.out.println("Usuário inserido com sucesso!");

            } catch (Exception e) {
                System.out.println("Erro ao inserir usuário, tente novamente!");
            }
    }
}
