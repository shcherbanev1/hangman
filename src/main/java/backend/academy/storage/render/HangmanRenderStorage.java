package backend.academy.storage.render;

import lombok.experimental.UtilityClass;

@UtilityClass
public class HangmanRenderStorage {

    private static final String[] HANGMAN_STATES = {
        """

           |
           |
           |
           |
           |
           |
           |
        _______
        """,
        """
           ________
           |
           |
           |
           |
           |
           |
           |
        _______
        """,
        """
           ________
           |      |
           |
           |
           |
           |
           |
           |
        _______
        """,
        """
           ________
           |      |
           |      O
           |
           |
           |
           |
           |
        _______
        """,
        """
           ________
           |      |
           |      O
           |      |
           |
           |
           |
           |
        _______
        """,
        """
           ________
           |      |
           |      O
           |     /|
           |
           |
           |
           |
        _______
        """,
        """
           ________
           |      |
           |      O
           |     /|\\
           |
           |
           |
           |
        _______
        """,
        """
           ________
           |      |
           |      O
           |     /|\\
           |      |
           |
           |
           |
        _______
        """,
        """
           ________
           |      |
           |      O
           |     /|\\
           |      |
           |     /
           |
           |
        _______
        """,
        """
           ________
           |      |
           |      O
           |     /|\\
           |      |
           |     / \\
           |
           |
        _______
        """
    };

    public static String getState(int index) {
        return HANGMAN_STATES[index];
    }

    public static int size() {
        return HANGMAN_STATES.length;
    }

}
