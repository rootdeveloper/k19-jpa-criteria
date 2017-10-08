package br.com.k19.test;

import br.com.k19.model.Book;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class TestCriteria {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("k21-criteria-pu");
        EntityManager manager = factory.createEntityManager();

        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
        CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);
        Root<Book> root1 = criteriaQuery.from(Book.class);
        criteriaQuery.select(root1);

        TypedQuery<Book> query = manager.createQuery(criteriaQuery);
        List<Book> books = query.getResultList();

        for (Book book : books) {
            System.out.println(book.getName());
        }
    }
}
