package br.com.k19.test;

import br.com.k19.model.Book;
import br.com.k19.model.Book_;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class ConsultBookMostExpensive {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("k21-criteria-pu");
        EntityManager manager = factory.createEntityManager();

        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Double> cq = cb.createQuery(Double.class);
        Root<Book> root = cq.from(Book.class);
        cq.select(cb.max(root.get(Book_.price)));

        TypedQuery<Double> query = manager.createQuery(cq);
        Double largerPrice = query.getSingleResult();

        System.out.println(largerPrice);

        manager.close();
        factory.close();
    }
}
