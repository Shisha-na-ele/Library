package org.library.dao;

import org.library.model.Book;
import org.library.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Component
public class BookDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Book> index(){
        return jdbcTemplate.query("SELECT * FROM book", new BeanPropertyRowMapper<>(Book.class));
    }

    public void addBook(Book book){
        jdbcTemplate.update("INSERT INTO Book VALUE (id,?,?,?,idUser)", book.getName(), book.getAuthor(), book.getYear());
    }

    public Book show(int id){
       return jdbcTemplate.queryForObject("SELECT * FROM book WHERE id=?", new BeanPropertyRowMapper<>(Book.class), id);
    }

    public void edit(int id, Book book){
        jdbcTemplate.update("UPDATE book SET name=?, author=?, year=? WHERE id=?", book.getName(),
                book.getAuthor(),book.getYear(), id);
    }

    public void delete(int id){
        jdbcTemplate.update("DELETE FROM book WHERE id=?", id);
    }

    public Person getReader(int id){
        return jdbcTemplate.queryForObject("SELECT book.id, book.name, p.name FROM Book left join person p on p.id = book.idUser" +
                        " WHERE book.id=?", new BeanPropertyRowMapper<>(Person.class), id);

    }

    public void deleteReader(int id){
        jdbcTemplate.update("UPDATE book SET idUser=null WHERE book.id = ? ", id);
    }

    public void addReader(int bookId, int personId){
        jdbcTemplate.update("UPDATE book set idUser = ? where id =?", personId, bookId);
    }



}
