package org.skypro.skyshop.model.search;

import java.util.UUID;

public class SearchResult {
    private final String id;
    private final String name;
    private final String contentType;

    private SearchResult(String id, String name, String contentType) {
        this.id = id;
        this.name = name;
        this.contentType = contentType;
    }

    public static SearchResult search(Searchable searchable) {
        String id = searchable.getID().toString();
        String name = searchable.getName();
        String content = searchable.getContentType();

        return new SearchResult(id, name, content);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContentType() {
        return contentType;
    }
}
