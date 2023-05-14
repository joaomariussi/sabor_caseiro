package Controller;

import Model.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class ObterUsuarios {

    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("sabor_caseiro");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        String jpql = "Select u from Usuario u";
        TypedQuery<Usuario> query = entityManager.createQuery(jpql, Usuario.class);
        query.setMaxResults(3);

        List<Usuario> usuarios = query.getResultList();

        for (Usuario usuario: usuarios) {
            System.out.println("ID: " + usuario.getId() + " E-mail: " + usuario.getEmail());
        }

        entityManager.close();
        entityManagerFactory.close();
    }
}
