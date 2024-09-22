package backend.academy.service;

import backend.academy.types.Category;
import backend.academy.types.Complexity;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import lombok.Getter;

public class InteractionService {

    @Getter private static final String MESSAGE_FOR_INPUT = "Input only one letter";

    private final PrintStream printStream;
    private final Scanner scanner;

    public InteractionService(PrintStream printStream, InputStream inputStream) {
        this.printStream = printStream;
        scanner = new Scanner(inputStream, StandardCharsets.UTF_8);
    }

    public Category inputCategory() {
        printStream.println("Choose category");
        for (Category category : Category.values()) {
            printStream.println(category.toString());
        }
        Category category = Category.getCategory(scanner.nextLine().toUpperCase());
        if (category == null) {
            printStream.println("You input invalid category. Random was chosen");
        }
        return category;
    }

    public Complexity inputComplexity() {
        printStream.println("Choose complexity");
        for (Complexity complexity : Complexity.values()) {
            printStream.println(complexity.toString());
        }
        Complexity complexity = Complexity.getComplexity(scanner.nextLine().toUpperCase());
        if (complexity == null) {
            printStream.println("You input invalid complexity. Random was chosen");
        }
        return complexity;
    }

    public void println(String message) {
        if ("Empty word".equals(message)) {
            printStream.println("try other category and complexity");
        }
        printStream.println(message);
    }

    public char letterInput() {
        String input;
        do {
            printStream.println(MESSAGE_FOR_INPUT);
            input = scanner.nextLine().toLowerCase();
        } while (input.length() != 1 || !Character.isLetter(input.charAt(0)));
        return input.charAt(0);
    }

}
