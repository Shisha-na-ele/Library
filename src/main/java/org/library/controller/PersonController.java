package org.library.controller;

import org.library.dao.PersonDao;
import org.library.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PersonController {
    private final PersonDao personDao;

    public PersonController(PersonDao personDao) {
        this.personDao = personDao;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("AllPerson", personDao.index());
        return "Person/index";
    }

    @GetMapping("/new")
    public String addUser (Model model){
        model.addAttribute("person", new Person());
        return "Person/new";
    }

    @PostMapping()
    public String saveAddUser(@ModelAttribute Person person){
        personDao.addUser(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}")
    public String show (Model model, @PathVariable("id") int id){
        model.addAttribute("person", personDao.show(id));
        model.addAttribute("AllBook", personDao.addListBook(id));
        return "Person/show";
    }

    @GetMapping("/{id}/edit")
    public String update(Model model, @PathVariable("id") int id){
        model.addAttribute("person", personDao.show(id));
        return "Person/edit";
    }

    @PostMapping("{id}")
    public String updateUser (@PathVariable("id") int id, @ModelAttribute Person person){
        personDao.updateUser(person, id);
        return "redirect:/people/{id}";
    }

    @GetMapping("/{id}/delete")
    public String delete (@PathVariable("id") int id){
        personDao.delete(id);
        return "redirect:/people";
    }

}
