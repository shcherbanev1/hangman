package backend.academy.service;

import backend.academy.entity.Word;
import backend.academy.game.GameSession;
import backend.academy.types.Category;
import backend.academy.types.Complexity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MistakeServiceTest {

    MistakeService mistakeService;
    GameSession gameSession;
    GuessingService guessingService;

    @BeforeEach
    void setUp() {
        Word word = new Word(Complexity.MEDIUM, Category.FOOD, "example");
        gameSession = new GameSession(word);
        mistakeService = gameSession.mistakeService();
        guessingService = new GuessingService(word, gameSession.mask());
    }

    @Test
    void testMistakeCountIfCorrect() {
        guessingService.guessLetter('e', gameSession.mask());
        assertEquals(0, mistakeService.mistakeCount());
    }

    @Test
    void testMistakeCountIfNotCorrect() {
        gameSession.guess('q');
        assertEquals(1, mistakeService.mistakeCount());
    }



}
