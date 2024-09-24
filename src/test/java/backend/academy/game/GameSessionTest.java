package backend.academy.game;

import backend.academy.entity.Word;
import backend.academy.types.Category;
import backend.academy.types.Complexity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameSessionTest {

    private GameSession gameSession;

    @BeforeEach
    void setUp() {
        Word word = new Word(Complexity.MEDIUM, Category.FOOD, "example");
        gameSession = new GameSession(word);
    }

    @Test
    void testSessionAliveAfterOneMistake() {
        gameSession.guess('q');
        assertTrue(gameSession.isAlive());
    }

    @Test
    void testSessionDeadAfterMistakes() {
        int maxMistakes = gameSession.maxMistakes();
        for (int i = 0; i < maxMistakes; i++) {
            gameSession.guess('q');
        }
        assertFalse(gameSession.isAlive());
    }

    @Test
    void testMistakeCountIfCorrect() {
        gameSession.guess('e');
        assertEquals(0, gameSession.mistakeCount());
    }

    @Test
    void testMistakeCountIfNotCorrect() {
        gameSession.guess('q');
        assertEquals(1, gameSession.mistakeCount());
    }

}
