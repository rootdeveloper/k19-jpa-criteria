package br.com.k19.test;

import br.com.k19.model.Author;
import br.com.k19.model.Book;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PopulateDatabase {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("k21-criteria-pu");
        EntityManager manager = factory.createEntityManager();

        manager.getTransaction().begin();

        Book book1 = new Book();
        book1.setName("The Battle for Your Mind");
        book1.setPrice(20.6);
        manager.persist(book1);

        Book book2 = new Book();
        book2.setName("Differentiate or Die");
        book2.setPrice(15.8);
        manager.persist(book2);

        Book book3 = new Book();
        book3.setName("How to Transform Your Ideas");
        book3.setPrice(32.7);
        manager.persist(book3);

        Book book4 = new Book();
        book4.setName("Digital Fortress");
        book4.setPrice(12.9);
        manager.persist(book4);

        Author author1 = new Author();
        author1.setName("Patrick Cullen");
        author1.getBooks().add(book2);
        author1.getBooks().add(book4);
        manager.persist(author1);

        Author author2 = new Author();
        author2.setName("Fraser Seitel");
        author2.getBooks().add(book3);
        manager.persist(author2);

        Author author3 = new Author();
        author3.setName("Al Ries");
        author3.getBooks().add(book1);
        manager.persist(author3);

        Author author4 = new Author();
        author4.setName("Al Ries");
        author4.getBooks().add(book1);
        author4.getBooks().add(book2);
        author4.getBooks().add(book4);
        manager.persist(author4);

        manager.getTransaction().commit();

        manager.close();
        factory.close();
    }
}
