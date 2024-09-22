package backend.academy.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class InteractionServiceTest {

    private ByteArrayOutputStream outputStream;
    private InteractionService interactionService;

    @BeforeEach
    public void setUp() {
        outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        interactionService = new InteractionService(printStream, new ByteArrayInputStream("abc\naa\na".getBytes()));
    }

    @Test
    void testOnlyOneLetterInInput() {
        char letter = interactionService.letterInput();
        assertEquals('a', letter);
    }

    @Test
    void testAskWhileNotOnlyOneLetterInInput() {
        interactionService.letterInput();
        String output = outputStream.toString();
        assertEquals(3, countOccurrences(output, InteractionService.MESSAGE_FOR_INPUT()));
    }

    private static int countOccurrences(String text, String substring) {
        Pattern pattern = Pattern.compile(Pattern.quote(substring));
        Matcher matcher = pattern.matcher(text);
        int count = 0;
        while (matcher.find()) {
            count++;
        }
        return count;
    }

}
