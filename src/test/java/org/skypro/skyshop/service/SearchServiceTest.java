package org.skypro.skyshop.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.Searchable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SearchServiceTest {

    @Mock
    private StorageService storageService;
    @InjectMocks
    private SearchService searchService;


    @Test
    public void search_whenStorageServiceIsEmpty_thenMapIsEmpty() {

        Mockito.when(storageService.getSumProductAndArticleMap()).thenReturn(new HashMap<UUID, Searchable>());

        Assertions.assertEquals(new HashMap<UUID, SearchResult>(), searchService.search("test"));
    }

    @Test
    public void search_whenStorageServiceIsNotEmpty_thenResultMapIsEmpty() {
        Map<UUID, Searchable> test = new HashMap<>();
        UUID testUUID = UUID.randomUUID();
        test.put(testUUID,
                new Article("Ночник", "Ночник для новорожденных с генератором белого шума", testUUID));

        Mockito.when(storageService.getSumProductAndArticleMap()).thenReturn(test);

        Assertions.assertEquals(new HashMap<UUID, SearchResult>(), searchService.search("test"));
    }

    @Test
    public void search_whenStorageServiceIsNotEmpty_thenReturnMapWithResult() {
        Map<UUID, Searchable> test = new HashMap<>();
        Map<UUID, SearchResult> result = new HashMap<>();
        UUID testUUID1 = UUID.randomUUID();
        UUID testUUID2 = UUID.randomUUID();
        test.put(testUUID1,
                new Article("Ночник", "Ночник для новорожденных с генератором белого шума", testUUID1));
        test.put(testUUID2,
                new Article("Видеоняня",
                        "Видеоняня с монитором, беспроводная 1280 * 720 HD", testUUID2));
        result.put(testUUID1, SearchResult.search(test.get(testUUID1)));

        Mockito.when(storageService.getSumProductAndArticleMap()).thenReturn(test);

        Assertions.assertEquals(result, searchService.search("Ночник"));
    }

}