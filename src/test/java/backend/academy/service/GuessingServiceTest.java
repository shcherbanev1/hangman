package backend.academy.service;

import backend.academy.entity.Word;
import backend.academy.game.GameSession;
import backend.academy.types.Category;
import backend.academy.types.Complexity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GuessingServiceTest {

    private GuessingService guessingService;
    private GameSession gameSession;


    @BeforeEach
    void setUp() {
        Word word = new Word(Complexity.HARD, Category.ANIMALS, "example");
        gameSession = new GameSession(word);
        guessingService = new GuessingService(word, gameSession.mask());
    }

    @Test
    void testMaskInitCorrect() {
        assertEquals("*******", new String(gameSession.mask()));
    }

    @Test
    void testGuessFuncReturnTrueIfCorrect() {
        boolean result = guessingService.guessLetter('e', gameSession.mask());
        assertTrue(result);
    }

    @Test
    void testGuessFuncReturnFalseIfNotCorrect() {
        boolean result = guessingService.guessLetter('q', gameSession.mask());
        assertFalse(result);
    }


    @Test
    void testMaskChangeIfCorrect() {
        guessingService.guessLetter('e', gameSession.mask());
        assertEquals("e*****e", new String(gameSession.mask()));
    }

    @Test
    void testMaskChangeIfNotCorrect() {
        guessingService.guessLetter('q', gameSession.mask());
        assertEquals("*******", new String(gameSession.mask()));
    }

    @Test
    void testGuessFuncReturnTrueIfCorrectCapitalLetter() {
        boolean result = guessingService.guessLetter('E', gameSession.mask());
        assertTrue(result);
    }

    @Test
    void testGuessFuncReturnFalseIfNotCorrectCapitalLetter() {
        boolean result = guessingService.guessLetter('Q', gameSession.mask());
        assertFalse(result);
    }

    @Test
    void testMaskChangeIfCorrectCapitalLetter() {
        guessingService.guessLetter('E', gameSession.mask());
        assertEquals("e*****e", new String(gameSession.mask()));
    }

    @Test
    void testMaskChangeIfNotCorrectCapitalLetter() {
        guessingService.guessLetter('Q', gameSession.mask());
        assertEquals("*******", new String(gameSession.mask()));
    }

}
