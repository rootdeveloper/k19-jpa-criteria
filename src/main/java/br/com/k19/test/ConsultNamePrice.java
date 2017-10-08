package br.com.k19.test;

import br.com.k19.model.Book;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class ConsultNamePrice {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("k21-criteria-pu");
        EntityManager manager = factory.createEntityManager();

        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);
        Root<Book> root = cq.from(Book.class);
        cq.multiselect(root.<String>get("name").alias("book.name"), root.<Double>get("price").alias("book.price"));

        TypedQuery<Tuple> query = manager.createQuery(cq);
        List<Tuple> list = query.getResultList();

        for (Tuple tuple : list) {
            System.out.println(tuple.get("book.name"));
            System.out.println(tuple.get("book.price"));
        }

        manager.close();
        factory.close();
    }
}
