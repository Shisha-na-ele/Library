package org.library.controller;

import org.library.model.Person;
import org.library.service.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("AllPerson", personService.findAll());
        return "Person/index";
    }

    @GetMapping("/new")
    public String addUser (Model model){
        model.addAttribute("person", new Person());
        return "Person/new";
    }

    @PostMapping()
    public String saveAddUser(@ModelAttribute("person") Person person){
        personService.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}")
    public String show (Model model, @PathVariable("id") int id){
        model.addAttribute("person", personService.findOne(id));
        return "Person/show";
    }

    @GetMapping("/{id}/edit")
    public String update(Model model, @PathVariable("id") int id){
        model.addAttribute("person", personService.findOne(id));
        return "Person/edit";
    }

    @PostMapping("{id}")
    public String updateUser (@ModelAttribute("person")Person person){
        personService.update(person);
        return "redirect:/people/{id}";
    }

    @GetMapping("/{id}/delete")
    public String delete (@ModelAttribute("person") Person person){
        personService.delete(person.getId());
        return "redirect:/people";
    }

}
