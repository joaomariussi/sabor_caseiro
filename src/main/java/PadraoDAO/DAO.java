package PadraoDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class DAO<E> {

    private static EntityManagerFactory entityManagerFactory;
    private Class<E> classe;
    private EntityManager entityManager;

    static {
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("sabor_caseiro");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DAO() {
        this(null);
    }

    public DAO(Class<E> classe) {
        this.classe = classe;
        entityManager = entityManagerFactory.createEntityManager();
    }

    public DAO<E> abrirTransacao() {
        entityManager.getTransaction().begin();
        return this;
    }

    public void fecharTrasacao() {
        entityManager.getTransaction().commit();
    }

    public DAO<E> incluir(E entidade) {
        entityManager.persist(entidade);
        return this;
    }

    public DAO<E> incluirAtomico(E entidade) {
        entityManager.persist(entidade);
        return this.abrirTransacao().incluir(entidade);
    }

    public List<E> obterTodos() {
        return this.obterTodos(10, 0);
    }

    public List<E> obterTodos(int quantidade, int deslocamento) throws UnsupportedOperationException {
        if (classe == null) {
            throw new UnsupportedOperationException("Classe Nula!!");
        }

        String jpql = "Select e from " + classe.getName() + " e";
        TypedQuery<E> query = entityManager.createQuery(jpql, classe);
        query.setMaxResults(quantidade);
        query.setFirstResult(deslocamento);
        return query.getResultList();
    }

    public void fechar() {
        entityManager.close();
    }










}
