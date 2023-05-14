package Controller;

import Model.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class RemoverUsuario {

    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("sabor_caseiro");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Usuario usuario = entityManager.find(Usuario.class, 2);

        if (usuario != null) {
            entityManager.getTransaction().begin();
            entityManager.remove(usuario);
            entityManager.getTransaction().commit();
            System.out.println("Usuário deletado com Sucesso!");
        } else {
            System.out.println("Usuário não encontrado");
        }

        entityManager.close();
        entityManagerFactory.close();
    }
}