package org.library.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.library.model.Book;
import org.library.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class BookDao {
    private SessionFactory sessionFactory;
    @Autowired
    public BookDao (SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }
    @Transactional
    public List<Book> index(){
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Book").getResultList();
    }
    @Transactional
    public void addBook(Book book){
        Session session = sessionFactory.getCurrentSession();
        session.save(book);
    }

    @Transactional
    public Book show(int id){
       Session session = sessionFactory.getCurrentSession();
       return session.get(Book.class, id);
    }

    @Transactional
    public void edit(Book book){
        Session session = sessionFactory.getCurrentSession();
        session.update(book);
    }

    @Transactional
    public void delete(Book book){
        Session session = sessionFactory.getCurrentSession();
        session.delete(book);
    }

    @Transactional
    public void deleteReader(int id){
        Book book = show(id);
        book.setOwner(null);
    }

    @Transactional
    public void addReader(int bookId, Person person){
        Book book = show(bookId);
        book.setOwner(person);
    }

}
