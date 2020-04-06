package com.epam.lab.controller;

import com.epam.lab.dto.TagDto;
import com.epam.lab.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(value = "/tags")
public class TagController {
    private TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping
    public Collection<TagDto> readAll() {
        return tagService.readAll();
    }

    @GetMapping(value = "/{id}")
    public TagDto findById(@PathVariable Long id) {
        return tagService.findById(id);
    }

    @PostMapping
    public boolean create(@RequestBody TagDto tagDto) {
        return tagService.create(tagDto);
    }

    @PutMapping(value = "/{id}")
    public TagDto update(@PathVariable Long id, @RequestBody TagDto tagDto) {
        tagDto.setId(id);
        return tagService.update(tagDto);
    }

    @DeleteMapping(value = "/{id}")
    public boolean delete(@PathVariable Long id) {
        TagDto tagDto = new TagDto();
        tagDto.setId(id);
        return tagService.delete(tagDto);
    }
}
