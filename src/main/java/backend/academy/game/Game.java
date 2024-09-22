package backend.academy.game;

import backend.academy.entity.Word;
import backend.academy.exception.WordNotFoundException;
import backend.academy.service.GuessingService;
import backend.academy.service.WordService;
import backend.academy.service.render.ConsoleRender;
import backend.academy.storage.word.InCodeWordStorage;
import backend.academy.types.Category;
import backend.academy.types.Complexity;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Game {

    private GameSession gameSession;
    private final PrintStream printStream;
    private final Scanner scanner;
    private GuessingService guessingService;
    private final ConsoleRender consoleRender;

    public Game(PrintStream printStream, InputStream inputStream) {
        this.printStream = printStream;
        this.scanner = new Scanner(inputStream, StandardCharsets.UTF_8);
        this.consoleRender = new ConsoleRender();
    }

    public void start() {
        while (!initWord()) {
            printStream.println("Try again");
        }
        printStream.println(gameSession.word().getInfo());
        gameProcess();
        if (gameSession.isWordGuessed()) {
            printStream.println("You guessed the word! :D");
        } else {
            printStream.println("You didn't guess the word :(");
        }
    }

    private boolean initWord() {
        printStream.println("Choose category");
        for (Category category : Category.values()) {
            printStream.println(category.toString());
        }
        Category category = Category.getCategory(scanner.nextLine().toUpperCase());
        if (category == null) {
            printStream.println("You input invalid category. Random was chosen");
        }

        printStream.println("Choose complexity");
        for (Complexity complexity : Complexity.values()) {
            printStream.println(complexity.toString());
        }
        Complexity complexity = Complexity.getComplexity(scanner.nextLine().toUpperCase());
        if (complexity == null) {
            printStream.println("You input invalid complexity. Random was chosen");
        }
        WordService wordService = new WordService(new InCodeWordStorage());
        try {
            Word word = wordService.getWord(category, complexity);
            this.gameSession = new GameSession(word);
            this.guessingService = new GuessingService(gameSession);
            return true;
        } catch (WordNotFoundException e) {
            printStream.println(e.getMessage());
            return false;
        }
    }

    private void gameProcess() {
        while (gameSession.isAlive()) {
            printStream.println(consoleRender.render(gameSession.mistakeService().mistakeCount(),
                gameSession.mistakeService().maxMistakes()));
            printStream.println(new String(gameSession.mask()));
            String input;
            do {
                printStream.println("Input only one letter");
                input = scanner.nextLine().toLowerCase();
            } while (input.length() != 1 || !Character.isLetter(input.charAt(0)));
            boolean guessResult = guessingService.guessLetter(input.charAt(0), gameSession);
            if (guessResult) {
                printStream.println("You guessed the letter");
            } else {
                gameSession.mistakeService().incrementMistakeCount();
                printStream.println("Incorrect letter");
            }
            printStream.println("______________________________________");
        }
        printStream.println(consoleRender.render(gameSession.mistakeService().mistakeCount(),
            gameSession.mistakeService().maxMistakes()));
        printStream.println("Word was: " + gameSession.word().word());
    }

}
