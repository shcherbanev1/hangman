package backend.academy.service;

import backend.academy.entity.Word;
import backend.academy.storage.word.InCodeWordStorage;
import backend.academy.types.Category;
import backend.academy.types.Complexity;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WordServiceTest {

    private WordService wordService;

    @BeforeEach
    void setUp() {
        wordService = new WordService(new InCodeWordStorage());
    }

    @SneakyThrows
    @Test
    void testWordGenerationWithCategory() {
        Word word = wordService.getWord(Category.ANIMALS, null);
        assertEquals(Category.ANIMALS, word.category());
    }

    @SneakyThrows
    @Test
    void testWordGenerationWithComplexity() {
        Word word = wordService.getWord(null, Complexity.HARD);
        assertEquals(Complexity.HARD, word.complexity());
    }

}
