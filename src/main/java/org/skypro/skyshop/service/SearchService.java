package org.skypro.skyshop.service;

import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class SearchService {

    private final StorageService storageService;

    public SearchService(StorageService storageService) {
        this.storageService = storageService;
    }

    public Map<UUID, SearchResult> search(String searchString) {

        Map<UUID, Searchable> map = storageService.getSumProductAndArticleMap();
        Map<UUID, SearchResult> result = map.entrySet().stream()
                .filter(m -> m.getValue().getSearchTerm().contains(searchString))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> SearchResult.search(entry.getValue())
                ));
        return result;
    }


}
