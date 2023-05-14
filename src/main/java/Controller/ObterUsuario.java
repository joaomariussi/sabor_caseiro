package Controller;

import Model.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ObterUsuario {

    public static void main(String[] args) {

        //Faz a busca de um novo usuário

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("sabor_caseiro");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Usuario usuario = entityManager.find(Usuario.class, 1);
        System.out.println("O Usuário encontrado foi: " + usuario.getNome());

        entityManager.close();
        entityManagerFactory.close();
    }
}
