package com.aditya.library;

import jakarta.persistence.*;
import java.util.List;

public class BookDBManager {
    private static final EntityManagerFactory emf = HibernateUtil.getEntityManagerFactory();

    public void addBook(Book book) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(book);
            tx.commit();
            System.out.println("Book added successfully.");
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public List<Book> getAllBooks() {
        EntityManager em = emf.createEntityManager();
        List<Book> books = em.createQuery("FROM Book", Book.class).getResultList();
        em.close();
        return books;
    }

    public Book getBookById(int id) {
        EntityManager em = emf.createEntityManager();
        Book book = em.find(Book.class, id);
        em.close();
        return book;
    }

    public void updateBook(Book book) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.merge(book);
            tx.commit();
            System.out.println("Book updated.");
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void deleteBook(int id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            Book book = em.find(Book.class, id);
            if (book != null) {
                em.remove(book);
                System.out.println("Book deleted.");
            } else {
                System.out.println("Book not found.");
            }
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void close() {
        HibernateUtil.shutdown();
    }
}
