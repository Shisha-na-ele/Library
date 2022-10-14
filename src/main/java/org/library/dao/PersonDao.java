package org.library.dao;

import org.library.model.Book;
import org.library.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index(){
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }

    public void addUser(Person person){
        jdbcTemplate.update("INSERT INTO Person VALUE (id,?,?)", person.getName(), person.getDate());
    }

    public Person show(int id){
       return jdbcTemplate.queryForObject("SELECT * FROM Person WHERE id=?",
               new BeanPropertyRowMapper<>(Person.class), id);
    }

    public void updateUser(Person person, int id){
        jdbcTemplate.update("UPDATE Person SET name=?, date=? where id=?", person.getName(), person.getDate(), id);
    }

    public void delete(int id){
        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
    }

    public List<Book> addListBook(int id){
        List<Book> bookList =
        jdbcTemplate.query("SELECT person.id, person.name, book.name, book.author, " +
                "book.year FROM person JOIN book  on person.id = book.idUser WHERE person.id = ?",
                new BeanPropertyRowMapper<>(Book.class), id);
        if (bookList.size()>0){
            return bookList;
        } else {
            return null;
        }

    }


}
