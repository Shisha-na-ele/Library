package org.library.controller;


import org.library.model.Book;
import org.library.model.Person;
import org.library.service.BookService;
import org.library.service.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;



@Controller
@RequestMapping("/books")
public class BookController {
        private final BookService bookService;
        private final PersonService personService;

    public BookController(BookService bookService, PersonService personService) {
        this.bookService = bookService;
        this.personService = personService;
    }


    @GetMapping()
    public String index(Model model){
        model.addAttribute("AllBooks", bookService.findAll());
        return "Book/index";
    }

    @GetMapping("/new")
    public String add (Model model){
        model.addAttribute("book", new Book());
        return "Book/new";
    }

    @PostMapping()
    public String save (@ModelAttribute("book") Book book){
        bookService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}")
    public String show (Model model, @PathVariable("id") int id){
        model.addAttribute("book", bookService.findOne(id));
        model.addAttribute("AllReader", personService.findAll());
        model.addAttribute("person", new Person());
        return "/Book/show";
    }

    @GetMapping("/{id}/edit")
    public String update (Model model, @PathVariable("id") int id){
        model.addAttribute("book", bookService.findOne(id));
        return "/Book/edit";
    }

    @PostMapping("/{id}/edit")
    public String saveBook(@ModelAttribute("book") Book book) {
        bookService.update(book);
        return "redirect:/books/{id}";
    }

    @GetMapping("/{id}/delete")
    public String delete(@ModelAttribute("book") Book book){
        bookService.delete(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/deleteReader")
    public String deleteReader(@PathVariable("id") int id){
        bookService.deleteOwner(id);
        return "redirect:/books/{id}";
    }

    @PostMapping("/{id}/addReader")
    public String addReader(@PathVariable("id") int id, @ModelAttribute Person person){
        bookService.addOwner(id, person);
        return "redirect:/books/{id}";
    }
}
