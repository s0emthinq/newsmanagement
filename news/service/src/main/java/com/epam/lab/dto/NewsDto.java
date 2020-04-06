package com.epam.lab.dto;

import com.epam.lab.model.Tag;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class NewsDto extends BaseDto {
    private Long id;
    private String title;
    private String shortText;
    private String fullText;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate creationDate;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate modificationDate;

    private AuthorDto authorDto;
    private List<Tag> tags;

    public NewsDto() {
    }

    public NewsDto(Long id, String title, String shortText, String fullText, LocalDate creationDate,
                   LocalDate modificationDate, AuthorDto authorDto, List<Tag> tags) {
        this.id = id;
        this.title = title;
        this.shortText = shortText;
        this.fullText = fullText;
        this.creationDate = creationDate;
        this.modificationDate = modificationDate;
        this.authorDto = authorDto;
        this.tags = tags;
    }

    public NewsDto(Long id, String title, String shortText, String fullText, LocalDate creationDate,
                   LocalDate modificationDate) {
        this.id = id;
        this.title = title;
        this.shortText = shortText;
        this.fullText = fullText;
        this.creationDate = creationDate;
        this.modificationDate = modificationDate;
    }

    public NewsDto(String title, String shortText, String fullText, LocalDate creationDate,
                   LocalDate modificationDate, AuthorDto authorDto, List<Tag> tags) {
        this.title = title;
        this.shortText = shortText;
        this.fullText = fullText;
        this.creationDate = creationDate;
        this.modificationDate = modificationDate;
        this.authorDto = authorDto;
        this.tags = tags;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortText() {
        return shortText;
    }

    public void setShortText(String shortText) {
        this.shortText = shortText;
    }

    public String getFullText() {
        return fullText;
    }

    public void setFullText(String fullText) {
        this.fullText = fullText;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(LocalDate modificationDate) {
        this.modificationDate = modificationDate;
    }

    public AuthorDto getAuthorDto() {
        return authorDto;
    }

    public void setAuthorDto(AuthorDto authorDto) {
        this.authorDto = authorDto;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewsDto newsDto = (NewsDto) o;
        return title.equals(newsDto.title) &&
                shortText.equals(newsDto.shortText) &&
                fullText.equals(newsDto.fullText) &&
                creationDate.equals(newsDto.creationDate) &&
                modificationDate.equals(newsDto.modificationDate) &&
                Objects.equals(authorDto, newsDto.authorDto) &&
                Objects.equals(tags, newsDto.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, shortText, fullText, creationDate, modificationDate, authorDto, tags);
    }

    @Override
    public String toString() {
        return "NewsDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", shortText='" + shortText + '\'' +
                ", fullText='" + fullText + '\'' +
                ", creationDate=" + creationDate +
                ", modificationDate=" + modificationDate +
                ", authorDto=" + authorDto +
                ", tags=" + tags +
                '}';
    }
}
