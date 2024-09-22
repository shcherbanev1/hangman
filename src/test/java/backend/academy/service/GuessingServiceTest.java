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
        guessingService = new GuessingService(gameSession);
    }

    @Test
    void testMaskInitCorrect() {
        assertEquals("*******", new String(gameSession.mask()));
    }

    @Test
    void testGuessFuncReturnTrueIfCorrect() {
        boolean result = guessingService.guessLetter('e', gameSession);
        assertTrue(result);
    }

    @Test
    void testGuessFuncReturnFalseIfNotCorrect() {
        boolean result = guessingService.guessLetter('q', gameSession);
        assertFalse(result);
    }


    @Test
    void testMaskChangeIfCorrect() {
        guessingService.guessLetter('e', gameSession);
        assertEquals("e*****e", new String(gameSession.mask()));
    }

    @Test
    void testMaskChangeIfNotCorrect() {
        guessingService.guessLetter('q', gameSession);
        assertEquals("*******", new String(gameSession.mask()));
    }

    @Test
    void testGuessFuncReturnTrueIfCorrectCapitalLetter() {
        boolean result = guessingService.guessLetter('E', gameSession);
        assertTrue(result);
    }

    @Test
    void testGuessFuncReturnFalseIfNotCorrectCapitalLetter() {
        boolean result = guessingService.guessLetter('Q', gameSession);
        assertFalse(result);
    }

    @Test
    void testMaskChangeIfCorrectCapitalLetter() {
        guessingService.guessLetter('E', gameSession);
        assertEquals("e*****e", new String(gameSession.mask()));
    }

    @Test
    void testMaskChangeIfNotCorrectCapitalLetter() {
        guessingService.guessLetter('Q', gameSession);
        assertEquals("*******", new String(gameSession.mask()));
    }

}
