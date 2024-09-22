package backend.academy.service;

import backend.academy.service.render.ConsoleRender;
import backend.academy.service.render.HangmanRender;
import backend.academy.storage.render.HangmanRenderStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static backend.academy.storage.render.HangmanRenderStorage.size;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RenderTest {

    private static final int MAX_MISTAKE = 5;
    private HangmanRender hangmanRender;

    @BeforeEach
    void setUp() {
        hangmanRender = new ConsoleRender();
    }

    @Test
    void testRenderForZeroMistakes() {
        String actual = hangmanRender.render(0, MAX_MISTAKE);
        String expected = HangmanRenderStorage.getState(0);
        assertEquals(expected, actual);
    }

    @Test
    void testRenderForMaxMistakes() {
        String actual = hangmanRender.render(MAX_MISTAKE, MAX_MISTAKE);
        String expected = HangmanRenderStorage.getState(HangmanRenderStorage.size() - 1);
        assertEquals(expected, actual);
    }

    @Test
    void testRender() {
        int maxMistake = 3;
        int mistakeCount = 1;
        int stateIndex = (int) Math.floor(((double) mistakeCount / maxMistake) * (size() - 2));
        stateIndex = Math.min(stateIndex + 1, size() - 2);
        String actual = hangmanRender.render(mistakeCount, maxMistake);
        String expected = HangmanRenderStorage.getState(stateIndex);
        assertEquals(expected, actual);
    }

}
