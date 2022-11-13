package org.library.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.library.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Component
public class PersonDao {
    private SessionFactory sessionFactory;
    @Autowired
    public void setSessionFactory (SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Transactional()
    public List<Person> index(){
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Person ").getResultList();
    }
    @Transactional
    public void addUser(Person person){
        Session session = sessionFactory.getCurrentSession();
        session.save(person);
    }

    @Transactional()
    public Person show(int id){
        Session session = sessionFactory.getCurrentSession();
        return session.get(Person.class,id);
    }
    @Transactional
    public void updateUser(Person person){
        Session session = sessionFactory.getCurrentSession();
        session.update(person);
    }
    @Transactional
    public void delete(Person person){
        Session session =sessionFactory.getCurrentSession();
        session.delete(person);
    }

}
