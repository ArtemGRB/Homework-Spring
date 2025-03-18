package org.skypro.skyshop.model.article;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public final class Article implements Searchable {
    private String title;
    private String article;
    private final UUID id;

    @Override
    public UUID getID() {
        return id;
    }

    public Article(String title, String article, UUID id) {
        this.title = title;
        this.article = article;
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(title, article.title);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(title);
    }

    public String toString() {
        return this.title + "'" + this.article;
    }

    public String getName() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArticle() {
        return this.article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    @JsonIgnore
    @Override
    public String getSearchTerm() {
        return title + " " + article;
    }

    @JsonIgnore
    @Override
    public String getContentType() {
        return "ARTICLE";
    }
}