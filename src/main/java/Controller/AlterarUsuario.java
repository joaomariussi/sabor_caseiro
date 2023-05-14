package Controller;

import Model.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AlterarUsuario {

    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("sabor_caseiro");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Usuario usuario = entityManager.find(Usuario.class, 2);
        entityManager.detach(usuario);

        usuario.setNome("João Pedro Mariussi");
        entityManager.merge(usuario);

        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }
}