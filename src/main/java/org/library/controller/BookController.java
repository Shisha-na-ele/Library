package org.library.controller;

import org.library.dao.BookDao;
import org.library.dao.PersonDao;
import org.library.model.Book;
import org.library.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BookController {
        private final BookDao bookDao;
        private final PersonDao personDao;

    @Autowired
    public BookController(BookDao bookDao, PersonDao personDao) {
        this.bookDao = bookDao;
        this.personDao = personDao;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("AllBooks", bookDao.index());
        return "Book/index";
    }

    @GetMapping("/new")
    public String add (Model model){
        model.addAttribute("book", new Book());
        return "Book/new";
    }

    @PostMapping()
    public String save (@ModelAttribute Book book){
        bookDao.addBook(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}")
    public String show (Model model, @PathVariable("id") int id){
        model.addAttribute("book", bookDao.show(id));
        model.addAttribute("reader", bookDao.getReader(id));
        model.addAttribute("AllReader", personDao.index());
        model.addAttribute("person", new Person());
        return "/Book/show";
    }

    @GetMapping("/{id}/edit")
    public String update (Model model, @PathVariable("id") int id){
        model.addAttribute("book", bookDao.show(id));
        return "/Book/edit";
    }

    @PostMapping("/{id}/edit")
    public String saveBook(@ModelAttribute Book book, @PathVariable("id") int id){
        bookDao.edit(id, book);
        return "redirect:/books/{id}";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") int id){
        bookDao.delete(id);
        return "redirect:/books";
    }

    @GetMapping("/{id}/deleteReader")
    public String deleteReader(@PathVariable("id") int id){
        bookDao.deleteReader(id);
        return "redirect:/books/{id}";
    }

    @PostMapping("/{id}/addReader")
    public String addReader(@PathVariable("id") int id, @ModelAttribute Person person){
        bookDao.addReader(id, person.getId());
        return "redirect:/books/{id}";
    }
}
