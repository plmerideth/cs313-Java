/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myjpa;

import author.Author;
import book.Book;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Paul Merideth
 */
 
public class MyJPA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        // TODO code application logic here
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myJPAPU");
        EntityManager em = emf.createEntityManager();
        
        /* Code used to test and debug
        Author author = em.find(Author.class, 2);        
        String myTest = author.getName();        
        System.out.println("Author: " + author.getName());        
        Query query = em.createQuery("SELECT n FROM Author n");
        List<Author> authors = query.getResultList();
        
        for (Author showAuthor : authors)
        {
            System.out.println("Author: " + showAuthor.getName());
        }
        */

        Query bookQuery = em.createQuery("SELECT b FROM Book b");
        List<Book> books = bookQuery.getResultList();
        
        for (Book showBook : books )
        {
            System.out.println("Book: " + showBook.getTitle() + ", Author: " + showBook.getAuthor().getName());
        }
        
        System.out.println("\n\nAdding new book and author ...");
        
        em.getTransaction().begin();
        
        Author newAuthor = new Author();
        newAuthor.setName("Paul Merideth");
        em.persist(newAuthor);
        em.getTransaction().commit();
        
        em.getTransaction().begin();
        Book newBook = new Book();
        newBook.setTitle("Welcome to my World");
        newBook.setAuthor(newAuthor);        
        em.persist(newBook);
        em.getTransaction().commit();
        
        bookQuery = em.createQuery("SELECT b FROM Book b");
        books = bookQuery.getResultList();
        
        for (Book showBook : books )
        {
            System.out.println("Book: " + showBook.getTitle() + ", Author: " + showBook.getAuthor().getName());
        }
                
        em.close();
    }    
}
