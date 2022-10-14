package org.library.model;


import java.sql.Date;
import java.util.List;

public class Person {

    private int id;
    private String name;
    private Date date;

    private List<Book> listBook;

    public Person (){

    }

    public Person(int id,String name, Date date, List<Book> listBook) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.listBook = listBook;
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
}
