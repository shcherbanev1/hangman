package backend.academy.service;

import backend.academy.entity.Word;
import backend.academy.exception.WordNotFoundException;
import backend.academy.storage.word.WordStorage;
import backend.academy.types.Category;
import backend.academy.types.Complexity;
import java.security.SecureRandom;
import java.util.List;
import java.util.Map;


public class WordService {

    private static final String NO_COMPLEXITIES_FOUND = "No complexities found for category ";
    private static final String NO_CATEGORIES_FOUND = "No categories found in word storage";

    private final WordStorage wordStorage;
    private final SecureRandom random;

    public WordService(WordStorage wordStorage) {
        this.wordStorage = wordStorage;
        this.random = new SecureRandom();
    }

    public Word getWord(Category category, Complexity complexity) throws WordNotFoundException {
        if (category != null && complexity != null) {
            return getRandomWord(category, complexity);
        } else if (category != null) {
            return getRandomWord(category);
        } else if (complexity != null) {
            return getRandomWord(complexity);
        }
        return getRandomWord();
    }

    private Word getRandomWord(Category category, Complexity complexity) throws WordNotFoundException {
        Map<Complexity, List<Word>> wordsByComplexity = getWordsByCategory(category);

        List<Word> words = wordsByComplexity.get(complexity);
        return getRandomElement(words, "No words found for category " + category + " and complexity " + complexity);
    }

    private Word getRandomWord(Category category) throws WordNotFoundException {
        Map<Complexity, List<Word>> wordsByComplexity = getWordsByCategory(category);

        Complexity randomComplexity = getRandomElement(wordsByComplexity.keySet().stream().toList(),
            NO_COMPLEXITIES_FOUND + category);
        return getRandomWord(category, randomComplexity);
    }

    private Word getRandomWord(Complexity complexity) throws WordNotFoundException {
        Category randomCategory =
            getRandomElement(wordStorage.wordMap().keySet().stream().toList(), NO_CATEGORIES_FOUND);
        return getRandomWord(randomCategory, complexity);
    }

    private Word getRandomWord() throws WordNotFoundException {
        Category randomCategory =
            getRandomElement(wordStorage.wordMap().keySet().stream().toList(), NO_CATEGORIES_FOUND);
        Complexity randomComplexity =
            getRandomElement(wordStorage.wordMap().get(randomCategory).keySet().stream().toList(),
                NO_COMPLEXITIES_FOUND + randomCategory);
        return getRandomWord(randomCategory, randomComplexity);
    }

    private Map<Complexity, List<Word>> getWordsByCategory(Category category) throws WordNotFoundException {
        Map<Complexity, List<Word>> wordsByComplexity = wordStorage.wordMap().get(category);
        if (wordsByComplexity == null) {
            throw new WordNotFoundException("Category " + category + " not found in word storage");
        }
        return wordsByComplexity;
    }

    private <T> T getRandomElement(List<T> list, String errorMessage) throws WordNotFoundException {
        if (list == null || list.isEmpty()) {
            throw new WordNotFoundException(errorMessage);
        }
        return list.get(random.nextInt(list.size()));
    }

}
