package com.epam.lab.service.impl;

import com.epam.lab.dto.TagDto;
import com.epam.lab.exception.EntityAlreadyExistsException;
import com.epam.lab.exception.NoSuchEntityException;
import com.epam.lab.model.Tag;
import com.epam.lab.repository.TagDao;
import com.epam.lab.service.TagService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class TagServiceImpl implements TagService {

    private TagDao tagDao;
    private ModelMapper modelMapper;

    @Autowired
    public TagServiceImpl(TagDao tagDao, ModelMapper modelMapper) {
        this.tagDao = tagDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean create(TagDto tagDto) {
        Tag tag = convertToEntity(tagDto);
        if (tagDao.isNotExist(tag)) {
            return tagDao.create(tag);
        } else {
            throw new EntityAlreadyExistsException("Can't create "
                    + tag + "because tag with name = "
                    + tag.getName() + " already exists");
        }
    }

    @Override
    public TagDto findById(Long id) {
        if (tagDao.isExist(id)) {
            return convertToDto(tagDao.findById(id));
        } else {
            throw new NoSuchEntityException("Not tag found with id = " + id);
        }
    }

    @Override
    public Collection<TagDto> readAll() {
        Collection<Tag> tags = tagDao.readAll();
        Set<TagDto> tagDtos = new HashSet<>();
        tags.forEach(tag -> {
            tagDtos.add(convertToDto(tag));
        });
        return tagDtos;
    }

    @Override
    public TagDto update(TagDto tagDto) {
        Long id = tagDto.getId();
        Tag tag = convertToEntity(tagDto);
        if (tagDao.isExist(id)) {  // if tag with given id exists
            if (tagDao.isNotExist(tag)) { // if a tag with given name doesn't already exist
                return convertToDto(tagDao.update(tag));
            } else {
                throw new EntityAlreadyExistsException("Can't update tags name with id = "
                        + id + " because tag with name = "
                        + tag.getName() + "already exists" );
            }
        } else {
            throw new NoSuchEntityException("Not tag with id = " + id + " to update.");
        }
    }

    @Override
    public boolean delete(TagDto tagDto) {
        Long id = tagDto.getId();
        if (tagDao.isExist(id)) {
            return tagDao.delete(convertToEntity(tagDto));
        } else {
            throw new NoSuchEntityException("Not tag with id = " + id + " to delete.");
        }
    }

    @Override
    public boolean delete(Long id) {
        if (tagDao.isExist(id)) {
            return tagDao.delete(id);
        } else {
            throw new NoSuchEntityException("Not tag with id = " + id + " to delete.");
        }
    }

    @Override
    public TagDto convertToDto(Tag tag) {
        return modelMapper.map(tag, TagDto.class);
    }

    @Override
    public Tag convertToEntity(TagDto tagDto) {
        return modelMapper.map(tagDto, Tag.class);
    }
}
