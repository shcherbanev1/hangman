package backend.academy.game;

import backend.academy.exception.WordNotFoundException;
import backend.academy.service.WordService;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GameTest {

    private WordService wordService;
    private Game game;

    @BeforeEach
    void setUp() {
        wordService = mock(WordService.class);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        game = new Game(printStream, new ByteArrayInputStream("\n\n".getBytes()), wordService);
    }

    @SneakyThrows @Test
    void testGameDontWorkWithEmptyWords() {
        when(wordService.getWord(null, null)).thenThrow(new WordNotFoundException("Empty word"));
        Method initWordMethod = game.getClass().getDeclaredMethod("initWord");
        initWordMethod.setAccessible(true);
        assertFalse((Boolean) initWordMethod.invoke(game));

    }

}
