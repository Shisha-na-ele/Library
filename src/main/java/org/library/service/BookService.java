package org.library.service;

import org.library.model.Book;
import org.library.model.Person;
import org.library.repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BookService {

    private final BookRepo bookRepo;

    @Autowired
    public BookService(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    public List<Book> findAll(){
        return bookRepo.findAll(Sort.by("name"));
    }

    public Book findOne(int id){
        Optional<Book> findBook = bookRepo.findById(id);
        return findBook.orElse(null);
    }

    @Transactional
    public void save(Book book){
        bookRepo.save(book);
    }

    @Transactional
    public void update(Book bookUpdate){
        bookRepo.save(bookUpdate);
    }

    @Transactional
    public void delete(Book book){
        bookRepo.delete(book);
    }

    @Transactional
    public void deleteOwner(int id){
        findOne(id).setOwner(null);
    }

    @Transactional
    public void addOwner(int id, Person newOwner){
        findOne(id).setOwner(newOwner);
    }


}
