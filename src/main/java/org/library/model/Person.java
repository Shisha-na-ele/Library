package org.library.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
@Entity
@Table (name = "person")
public class Person {
    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
    private List<Book> bookList;
    @Id
    @Column(name = "id")
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "date")
    private Date date;
    public Person (){

    }
    public Person(String name, Date date) {
        this.name = name;
        this.date = date;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }
}
