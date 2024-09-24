package backend.academy.game;

import backend.academy.entity.Word;
import backend.academy.exception.WordNotFoundException;
import backend.academy.service.InteractionService;
import backend.academy.service.WordService;
import backend.academy.service.render.ConsoleRender;
import backend.academy.types.Category;
import backend.academy.types.Complexity;
import java.io.InputStream;
import java.io.PrintStream;

public class Game {

    private GameSession gameSession;
    private final InteractionService interactionService;
    private final ConsoleRender consoleRender;
    private final WordService wordService;

    public Game(PrintStream printStream, InputStream inputStream, WordService wordService) {
        this.interactionService = new InteractionService(printStream, inputStream);
        this.consoleRender = new ConsoleRender();
        this.wordService = wordService;
    }

    public void start() {
        while (!initWord()) {
            interactionService.println("Try again");
        }
        interactionService.println(gameSession.word().getInfo());

        gameProcess();

        if (gameSession.isWordGuessed()) {
            interactionService.println("You guessed the word! :D");
        } else {
            interactionService.println("You didn't guess the word :(");
        }
    }

    private boolean initWord() {
        Category category = interactionService.inputCategory();
        Complexity complexity = interactionService.inputComplexity();
        Word word = generateWord(category, complexity);
        return word != null;
    }

    private void gameProcess() {
        while (gameSession.isAlive()) {
            interactionService.println(consoleRender.render(gameSession.mistakeCount(),
                gameSession.maxMistakes()));
            interactionService.println(new String(gameSession.mask()));
            interactionService.println(showTriedChars());

            char letter = interactionService.letterInput();
            boolean guessResult = gameSession.guess(letter);
            if (guessResult) {
                interactionService.println("You guessed the letter");
            } else {
                interactionService.println("Incorrect letter");
            }
            interactionService.println("______________________________________");
        }

        interactionService.println(consoleRender.render(gameSession.mistakeCount(),
            gameSession.maxMistakes()));
        interactionService.println("Word was: " + gameSession.word().word());
    }

    private Word generateWord(Category category, Complexity complexity) {
        try {
            Word word = wordService.getWord(category, complexity);
            this.gameSession = new GameSession(word);
            return word;
        } catch (WordNotFoundException e) {
            interactionService.println(e.getMessage());
            return null;
        }
    }

    private String showTriedChars() {
        if (gameSession.triedChars().isEmpty()) {
            return "no tried chars";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (char ch : gameSession.triedChars()) {
            stringBuilder.append(ch).append(' ');
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return "Tried chars: " + stringBuilder;
    }

}
