package backend.academy.types;

import lombok.Getter;

@Getter
public enum Complexity {
    EASY(3),
    MEDIUM(5),
    HARD(8);

    private final int maxMistakes;

    Complexity(int maxMistakes) {
        this.maxMistakes = maxMistakes;
    }

    public static Complexity getComplexity(String complexity) {
        try {
            return Complexity.valueOf(complexity);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

}
