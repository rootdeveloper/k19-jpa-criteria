package br.com.k19.test;

import br.com.k19.model.Book;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class TestPredicate {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("k21-criteria-pu");
        EntityManager manager = factory.createEntityManager();

        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Book> cq = cb.createQuery(Book.class);
        Root<Book> root = cq.from(Book.class);
        cq.select(root);

        Predicate predicate = cb.equal(root.get("name"), "The Battle for Your Mind");
        cq.where(predicate);

        TypedQuery<Book> query = manager.createQuery(cq);
        List<Book> books = query.getResultList();

        for (Book book : books) {
            System.out.println(book.getId());
            System.out.println(book.getName());
            System.out.println(book.getPrice());
        }

        manager.close();
        factory.close();
    }
}
