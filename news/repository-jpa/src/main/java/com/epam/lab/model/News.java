package com.epam.lab.model;

import javax.persistence.*;
import java.time.LocalDate;

import java.util.Objects;
import java.util.Set;

import static com.epam.lab.repository.impl.NewsDaoImpl.*;

@Entity
@Table(name = TABLE_NAME_NEWS)
public class News extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(name = CLM_NAME_SHORT_TEXT, nullable = false)
    private String shortText;
    @Column(name = CLM_NAME_FULL_TEXT, nullable = false)
    private String fullText;
    @Column(name = CLM_NAME_CREATION_DATE, nullable = false)
    private LocalDate creationDate;
    @Column(name = CLM_NAME_MODIFICATION_DATE, nullable = false)
    private LocalDate modificationDate;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "news_author",
            joinColumns = {@JoinColumn(name = "news_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "author_id", referencedColumnName = "id")})
    private Author author;
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "news_tag",
            joinColumns = {@JoinColumn(name = "news_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id", referencedColumnName = "id")})
    private Set<Tag> tags;

    public News() {
    }

    public News(Long id, String title, String shortText, String fullText, LocalDate creationDate,
                LocalDate modificationDate, Author author, Set<Tag> tags) {
        this.id = id;
        this.title = title;
        this.shortText = shortText;
        this.fullText = fullText;
        this.creationDate = creationDate;
        this.modificationDate = modificationDate;
        this.author = author;
        this.tags = tags;
    }

    public News(Long id, String title, String shortText, String fullText, LocalDate creationDate,
                LocalDate modificationDate) {
        this.id = id;
        this.title = title;
        this.shortText = shortText;
        this.fullText = fullText;
        this.creationDate = creationDate;
        this.modificationDate = modificationDate;
    }

    public News(String title, String shortText, String fullText, LocalDate creationDate,
                LocalDate modificationDate, Author author, Set<Tag> tags) {
        this.title = title;
        this.shortText = shortText;
        this.fullText = fullText;
        this.creationDate = creationDate;
        this.modificationDate = modificationDate;
        this.author = author;
        this.tags = tags;
    }

    public News(String title, String shortText, String fullText, LocalDate creationDate,
                LocalDate modificationDate) {
        this.title = title;
        this.shortText = shortText;
        this.fullText = fullText;
        this.creationDate = creationDate;
        this.modificationDate = modificationDate;
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

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        News news = (News) o;
        return Objects.equals(id, news.id) &&
                Objects.equals(title, news.title) &&
                Objects.equals(shortText, news.shortText) &&
                Objects.equals(fullText, news.fullText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, shortText, fullText);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("News{");
        sb.append("id=").append(id);
        sb.append(", title='").append(title).append('\'');
        sb.append(", shortText='").append(shortText).append('\'');
        sb.append(", fullText='").append(fullText).append('\'');
        sb.append(", creationDate=").append(creationDate);
        sb.append(", modificationDate=").append(modificationDate);
        sb.append(", author=").append(author);
        sb.append(", tags=").append(tags);
        sb.append('}');
        return sb.toString();
    }
}
