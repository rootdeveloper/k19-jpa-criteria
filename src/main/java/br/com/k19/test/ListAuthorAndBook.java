package br.com.k19.test;

import br.com.k19.model.Author;
import br.com.k19.model.Book;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.util.Collection;
import java.util.List;

public class ListAuthorAndBook {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("k21-criteria-pu");
        EntityManager manager = factory.createEntityManager();

        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Author> cq = cb.createQuery(Author.class);
        Root<Author> root = cq.from(Author.class);
        root.fetch("books", JoinType.LEFT);
        cq.select(root).distinct(true);

        TypedQuery<Author> query = manager.createQuery(cq);
        List<Author> authors = query.getResultList();

        for (Author author : authors) {
            System.out.println(author.getName());
            Collection<Book> books = author.getBooks();

            for (Book book : books) {
                System.out.println(book.getName());
            }
            System.out.println();
        }

        manager.close();
        factory.close();
    }
}
