package com.epam.lab.controller;

import com.epam.lab.dto.AuthorDto;
import com.epam.lab.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(value = "/authors")
public class AuthorController {
    private AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public Collection<AuthorDto> readAll() {
        return authorService.readAll();
    }

    @GetMapping(value = "/{id}")
    public AuthorDto findOne(@PathVariable Long id) {
        return authorService.findById(id);
    }

    @PostMapping
    public boolean create(@RequestBody AuthorDto authorDto) {
        return authorService.create(authorDto);
    }

    @PutMapping(value = "/{id}")
    public AuthorDto update(@PathVariable Long id, @RequestBody AuthorDto authorDto) {
        authorDto.setId(id);
        return authorService.update(authorDto);
    }

    @DeleteMapping(value = "/{id}")
    public boolean delete(@PathVariable Long id) {
        AuthorDto authorDto = new AuthorDto();
        authorDto.setId(id);
        return authorService.delete(authorDto);
    }
}
