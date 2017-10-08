package br.com.k19.test;

import br.com.k19.model.Book;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class CalculateAveragePrices {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("k21-criteria-pu");
        EntityManager manager = factory.createEntityManager();

        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Double> cq = cb.createQuery(Double.class);
        Root<Book> root = cq.from(Book.class);
        cq.select(cb.avg(root.<Double>get("price")));

        TypedQuery<Double> query = manager.createQuery(cq);
        Double average = query.getSingleResult();

        System.out.printf("%.2f%n", average);

        manager.close();
        factory.close();
    }
}
