package com.epam.lab.service.impl;

import com.epam.lab.dto.AuthorDto;
import com.epam.lab.exception.EntityAlreadyExistsException;
import com.epam.lab.exception.NoSuchEntityException;
import com.epam.lab.model.Author;
import com.epam.lab.repository.AuthorDao;
import com.epam.lab.service.AuthorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorDao authorDao;
    private ModelMapper modelMapper;

    @Autowired
    public AuthorServiceImpl(AuthorDao authorDao, ModelMapper modelMapper) {
        this.authorDao = authorDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean create(AuthorDto authorDto) {
        Author author = convertToEntity(authorDto);
        if (authorDao.isNotExist(author)) {
            return authorDao.create(convertToEntity(authorDto));
        } else {
            throw new EntityAlreadyExistsException("Can't create " + author + ". This author already exists");
        }
    }

    @Override
    public AuthorDto findById(Long id) {
        if (authorDao.isExist(id)) {
            return convertToDto(authorDao.findById(id));
        } else {
            throw new NoSuchEntityException("No author find with id = " + id + ".");
        }
    }

    @Override
    public Collection<AuthorDto> readAll() {
        Collection<Author> authors = authorDao.readAll();
        Set<AuthorDto> authorDtos = new HashSet<>();
        authors.forEach(author -> {
            AuthorDto authorDto = convertToDto(author);
            authorDtos.add(authorDto);
        });
        return authorDtos;
    }

    @Override
    public AuthorDto update(AuthorDto authorDto) {
        Long id = authorDto.getId();
        Author author = convertToEntity(authorDto);
        if (authorDao.isExist(id)) { // if author with given id exists
            if (authorDao.isNotExist(author)) // if author with given name and surname doesn't already exist
            {
                return convertToDto(authorDao.update(author));
            } else {
                throw new EntityAlreadyExistsException("Can't update author with id = "
                        + id + " because author with name = "
                        + author.getName() + " and surname = "
                        + author.getSurname() + " already exists" );
            }
        } else {
            throw new NoSuchEntityException("No author find with id = " + authorDto.getId() + " to update.");
        }
    }

    @Override
    public boolean delete(AuthorDto authorDto) {
        Long id = authorDto.getId();
        if (authorDao.isExist(authorDto.getId())) {
            return authorDao.delete(convertToEntity(authorDto));
        } else {
            throw new NoSuchEntityException("No author find with id = " + id + " to delete.");
        }
    }

    @Override
    public boolean delete(Long id) {
        if (authorDao.isExist(id)) {
            return authorDao.delete(id);
        } else {
            throw new NoSuchEntityException("No author find with id = " + id + " to delete.");
        }
    }

    @Override
    public AuthorDto convertToDto(Author author) {
        return modelMapper.map(author, AuthorDto.class);
    }

    @Override
    public Author convertToEntity(AuthorDto authorDto) {
        return modelMapper.map(authorDto, Author.class);
    }
}
